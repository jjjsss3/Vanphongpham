package GUI;

import BLL.GiamGiaBLL;
import BLL.SanphamBLL;
import DTO.*;
import GUI.Components.DatePicker;
import GUI.Components.LabelCustom;
import GUI.Components.TextFieldCustom;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QL_TatcaGiamgia extends JPanel {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private ArrayList<ChitietGiamgiaDTO> listCTGG=new ArrayList<>();
    private ArrayList<String> listTenSPCTGG=new ArrayList<>();

    private GiamGiaBLL giamGiaBLL=new GiamGiaBLL();
    private int[] columnspWidth = {
            80, 100, 690, 80, 100, 300, 150
    };
    private DefaultTableModel modelDSGG = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelCTGG = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public QL_TatcaGiamgia() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1520, 500));
        setBackground(new Color(245, 245, 245));
        QL_Giamgia.listGG=new ArrayList<>(); giamGiaBLL.getListGG();
        QL_Giamgia.listAllCTGG =new ArrayList<>(); giamGiaBLL.getListCTGG();

        initComponents();
    }

    public void setRowCTGG(DefaultTableModel model,int phantram,int index){
        model.setValueAt(phantram,index,2);
    }
    public void showTableGG(DefaultTableModel model, ArrayList<GiamGiaDTO> list ) {
        int i = 1;
        for (GiamGiaDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMakm(), s.getTenkm(), formatter.format(s.getNgaybd()),formatter.format(s.getNgaykt())
            });
        }
    }
    public void showTableCTGG(DefaultTableModel model) {
        int i = 0;
        for (ChitietGiamgiaDTO s : listCTGG) {
            model.addRow(new Object[]{
                    i+1, listTenSPCTGG.get(i++), s.getPhantramkm()
            });

        }
    }
    private void getCTGG(String makm){
        listCTGG=new ArrayList<>();
        for (ChitietGiamgiaDTO c:QL_Giamgia.listAllCTGG) {
            if (c.getMakm().equals(makm)) {
                listCTGG.add(c);
                listTenSPCTGG.add(getTensp(c.getMasp()));
            }

        }
    }
    private String getTensp(int masp){
        if(QL_Sanpham.listSP.size()==0) new SanphamBLL().getListSanpham();
        for (SanphamDTO s: QL_Sanpham.listSP) {
            if(s.getMasp()==masp)
                return s.getTensp();
        }
        return "";
    }
    private void initComponents() {
        if(QL_Sanpham.listSP.size()==0) new SanphamBLL().getListSanpham();
        //table sản phẩm
        tblDSGG = new JTable();
        tblDSGG.setRowHeight(40);
        tblDSGG.setModel(modelDSGG);
        modelDSGG.setColumnIdentifiers(new Object[]{
                "STT", "Mã khuyến mãi","Tên chương trình khuyến mãi","Ngày bắt đầu","Ngày kết thúc"
        });
        int i = 0;
        columnspWidth = new int[]{
                40,140, 260, 100,100
        };
        for (int width : columnspWidth) {
            TableColumn column = tblDSGG.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneDSGG = new JScrollPane(tblDSGG);
        showTableGG(modelDSGG, QL_Giamgia.listGG);
        tblDSGG.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                GiamGiaDTO g=QL_Giamgia.listGG.get(tblDSGG.getSelectedRow());
                txtMagg.setText(g.getMakm());
                txtTengg.setText(g.getTenkm());
                getCTGG(g.getMakm());
                modelCTGG.setNumRows(0);
                showTableCTGG(modelCTGG);
            }
        });

        //table chi tiết giảm giá thêm
        tblCTGG = new JTable();
        tblCTGG.setRowHeight(40);
        tblCTGG.setModel(modelCTGG);
        modelCTGG.setColumnIdentifiers(new Object[]{
                "STT", "Tên sản phẩm","% giảm giá"
        });
        i = 0;
        columnspWidth = new int[]{
                50, 250, 100
        };
        for (int width : columnspWidth) {
            TableColumn column = tblCTGG.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneCTGG = new JScrollPane(tblCTGG);
        showTableCTGG(modelCTGG);
        tblCTGG.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                txtphantram.setText(String.valueOf(listCTGG.get(tblCTGG.getSelectedRow()).getPhantramkm()));
            }
        });

        btnDelGG.addActionListener(actionEvent -> {
            try {
                btnDelGGAction(actionEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        btnEditCTGG.addActionListener(actionEvent -> {
            try {
                btnEditCTGGAction(actionEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        btnDelCTGG.addActionListener(actionEvent -> {
            try {
                btnDelCTGGAction(actionEvent);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        add(lbTitle2,BorderLayout.NORTH);
        add(pnTop,BorderLayout.CENTER);

        pnTop.add(jScrollPaneDSGG);
        pnTop.add(pnTopRight);
        pnTop.add(jScrollPaneCTGG);
        pnTopRight.add(pnTopRightName);
        pnTopRight.add(pnTopRightDate);
        pnTopRightName.setLayout(new GridLayout(4,1));
        pnTopRightName.add(lbMagg);
        pnTopRightName.add(txtMagg);
        pnTopRightName.add(lbTengg);
        pnTopRightName.add(txtTengg);
        pnTopRight.add(btnDelGG);
        pnTopRight.add(lbphantram);pnTopRight.add(txtphantram);
        pnTopRight.add(btnEditCTGG);
        pnTopRight.add(btnDelCTGG);



        pnTop.setPreferredSize(new Dimension(1520,500));
        pnTopRight.setPreferredSize(new Dimension(400,440));
        pnTopRightName.setPreferredSize(new Dimension(400,170));
        pnTopRightDate.setPreferredSize(new Dimension( 400,70));

        pnTop.setBackground(NewColor.background);
//        pnBottom.setBackground(NewColor.background);
        pnTopRight.setBackground(NewColor.background);
        pnTopRightName.setBackground(NewColor.background);
        pnTopRightDate.setBackground(NewColor.background);
        lbTitle2.setForeground(new Color(54, 38, 90));
        lbTitle2.setFont(new Font("Segoe UI", 1, 20));
        lbTitle2.setPreferredSize(new Dimension(1520,20));
        lbTitle2.setVerticalAlignment(SwingConstants.CENTER);
        jScrollPaneDSGG.setViewportView(tblDSGG);
        jScrollPaneDSGG.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneDSGG.setPreferredSize(new Dimension(650, 400));
        jScrollPaneCTGG.setViewportView(tblCTGG);
        jScrollPaneCTGG.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneCTGG.setPreferredSize(new Dimension(400, 400));
        tblDSGG.setFont(new Font("Segoe UI", 0, 16));
        tblCTGG.setFont(new Font("Segoe UI",0,16));
    }


    private void btnDelCTGGAction(ActionEvent actionEvent) throws SQLException {
        if(tblCTGG.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(null,"Vui lòng chọn một sản phẩm để xóa");
        else {
            int res = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa, chắc chắn chứ?", "Message", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {

                int i = tblCTGG.getSelectedRow();
                ChitietGiamgiaDTO g = listCTGG.get(i);
                listCTGG.remove(i);
                modelCTGG.removeRow(i);
                for (int j = i; j < modelCTGG.getRowCount(); j++) {
                    modelCTGG.setValueAt(j + 1, j, 0);
                }
                giamGiaBLL.delCTKM(g);
            }
        }
    }

    private void btnEditCTGGAction(ActionEvent actionEvent) throws SQLException {
        if(tblCTGG.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(null,"Vui lòng chọn một sản phẩm để sửa !");
        else {
            int i = tblCTGG.getSelectedRow();
            Pattern pattern = Pattern.compile("\\d*");
            if (txtphantram.getText().equals("") || !pattern.matcher(txtphantram.getText()).matches())
                JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải từ 1 đến 99 !");
            else {
                int giamgia = Integer.parseInt(txtphantram.getText());
                if (giamgia > 99 || giamgia < 1)
                    JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải từ 1 đến 99 !");
                else {
                    listCTGG.get(tblCTGG.getSelectedRow()).setPhantramkm(giamgia);
                    setRowCTGG(modelCTGG, giamgia, tblCTGG.getSelectedRow());
                    giamGiaBLL.updateSoluongCTKM(listCTGG.get(tblCTGG.getSelectedRow()));
                    JOptionPane.showMessageDialog(null,"Cập nhật thành công!");

                }
            }
        }
    }

    private void btnDelGGAction(ActionEvent actionEvent) throws SQLException {
        if(tblDSGG.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(null,"Vui lòng chọn một chương trình giảm giá !");
        else {
            int res = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa, chắc chắn chứ?", "Message", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                int i=tblDSGG.getSelectedRow();
                giamGiaBLL.delKM(QL_Giamgia.listGG.get(i).getMakm(), i);
                modelCTGG.setNumRows(0);
                modelDSGG.removeRow(i);
                for (int j = i; j < modelDSGG.getRowCount(); j++) {
                    modelDSGG.setValueAt(j + 1, j, 0);
                }
            }
        }
    }


    private JLabel lbTitle2 = new JLabel("DANH SÁCH KHUYẾN MÃI", SwingConstants.LEFT);

    private JPanel pnCenter=new JPanel();
    private JPanel pnTop=new JPanel();
    private JPanel pnBottom=new JPanel();
    private JPanel pnTopRight =new JPanel();
    private JPanel pnTopRightName=new JPanel();
    private JPanel pnTopRightDate=new JPanel();
    private JTable tblCTGG;
    private JScrollPane jScrollPaneCTGG=new JScrollPane();
    private JTable tblDSGG;
    private JScrollPane jScrollPaneDSGG;
    private JLabel lbMagg=new LabelCustom("Mã giảm giá");
    private JLabel lbTengg=new LabelCustom("Tên giảm giá");
    private JLabel lbDate=new LabelCustom("Thời gian bắt đầu");
    private JLabel lbDate1=new LabelCustom(" ,thời gian kết thúc");
    private JTextField txtMagg=new TextFieldCustom("");
    private JTextField txtTengg=new TextFieldCustom("");
    private  JButton btnDelGG=new JButton("Xóa giảm giá");
    private JButton btnEditCTGG=new JButton("Sửa");
    private JButton btnDelCTGG=new JButton("Xóa");
    private JTextField txtphantram=new JTextField("",5);
    private JLabel lbphantram=new LabelCustom("% giảm giá:");
}


