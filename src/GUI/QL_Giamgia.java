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

public class QL_Giamgia extends JPanel {
    public static ArrayList<GiamGiaDTO>listGG=new ArrayList<>();
    public static ArrayList<ChitietGiamgiaDTO> listAllCTGG =new ArrayList();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private ArrayList<ChitietGiamgiaDTO> listCTGG=new ArrayList<>();
    private ArrayList<String> listTenSPCTGG=new ArrayList<>();

    private GiamGiaBLL giamGiaBLL=new GiamGiaBLL();
    private int[] columnspWidth = {
            80, 100, 690, 80, 100, 300, 150
    };
    private DefaultTableModel modelSP = new DefaultTableModel(){
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

    public QL_Giamgia() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        listGG=new ArrayList<>(); giamGiaBLL.getListGG();
        listAllCTGG =new ArrayList<>(); giamGiaBLL.getListCTGG();

        initComponents();
    }

    public void setRowCTGG(DefaultTableModel model,int phantram,int index){
        model.setValueAt(phantram,index,2);
    }
    public void showTableSP(DefaultTableModel model, ArrayList<SanphamDTO> list ) {
        int i = 1;
        for (SanphamDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getTensp(), s.getSoluong(), s.getDongia()
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
    private void initComponents() throws IOException, URISyntaxException {
        if(QL_Sanpham.listSP.size()==0) new SanphamBLL().getListSanpham();
        //table sản phẩm
        tblDSSP = new JTable();
        tblDSSP.setRowHeight(40);
        tblDSSP.setModel(modelSP);
        modelSP.setColumnIdentifiers(new Object[]{
                "STT", "Tên sản phẩm","Số lượng kho","Đơn giá"
        });
        int i = 0;
        columnspWidth = new int[]{
                50, 320, 90,100
        };
        for (int width : columnspWidth) {
            TableColumn column = tblDSSP.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneSP = new JScrollPane(tblDSSP);
        showTableSP(modelSP,QL_Sanpham.listSP);
        tblDSSP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                SanphamDTO sp=new SanphamDTO();
//                { sp= listsearchsp.get(tblDSSP.getSelectedRow());}
//
//                try {
//                    showInputSP(sp);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

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

        if(QL_Thongke.dp1==null)QL_Thongke.dp1=new DatePicker();
        QL_Thongke.txtDate1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                QL_Thongke.z=0;
                QL_Thongke.dp1.setVisible(true);
                QL_Thongke.dp1.setLocation(300, 590);
            }
        });
        QL_Thongke.txtDate2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                QL_Thongke.z=1;
                QL_Thongke.dp1.setVisible(true);
                QL_Thongke.dp1.setLocation(300, 590);
            }
        });
        btnAdd.addActionListener(actionEvent -> {
            try {
                btnAddAction(actionEvent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        btnEditCTGG.addActionListener(this::btnEditCTGGAction);
        btnDelCTGG.addActionListener(this::btnDelCTGGAction);
        btnAddgg.addActionListener(actionEvent -> {
            try {
                btnAddggAction(actionEvent);
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            }
        });
        add(lbTitle,BorderLayout.NORTH);
        add(pnCenter,BorderLayout.CENTER);
        pnCenter.add(pnTop);
        pnCenter.add(new QL_TatcaGiamgia());

        pnTop.add(jScrollPaneSP);
        pnTop.add(pnTopRight);
        pnTop.add(jScrollPaneCTGG);
        pnTopRight.add(pnTopRightName);
        pnTopRight.add(pnTopRightDate);
        pnTopRightName.setLayout(new GridLayout(4,1));
        pnTopRightName.add(lbMagg);
        pnTopRightName.add(txtMagg);
        pnTopRightName.add(lbTengg);
        pnTopRightName.add(txtTengg);
        pnTopRightDate.add(lbDate);
        pnTopRightDate.add(QL_Thongke.txtDate1);
        pnTopRightDate.add(lbDate1);
        pnTopRightDate.add(QL_Thongke.txtDate2);
        pnTopRight.add(btnAdd);
        pnTopRight.add(lbphantram);pnTopRight.add(txtphantram);
        pnTopRight.add(btnAddgg);
        pnTopRight.add(btnEditCTGG);
        pnTopRight.add(btnDelCTGG);

        pnCenter.setPreferredSize(new Dimension(1520,1000));
        pnTop.setPreferredSize(new Dimension(1520,450));
        pnBottom.setPreferredSize(new Dimension(1520,500));
        pnTopRight.setPreferredSize(new Dimension(500,440));
        pnTopRightName.setPreferredSize(new Dimension(500,170));
        pnTopRightDate.setPreferredSize(new Dimension( 500,70));


        pnCenter.setBackground(NewColor.background);
        pnTop.setBackground(NewColor.background);
//        pnBottom.setBackground(NewColor.background);
        pnTopRight.setBackground(NewColor.background);
        pnTopRightName.setBackground(NewColor.background);
        pnTopRightDate.setBackground(NewColor.background);
        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 30));
        lbTitle.setPreferredSize(new Dimension(1520,80));
        lbTitle.setVerticalAlignment(SwingConstants.CENTER);
        jScrollPaneSP.setViewportView(tblDSSP);
        jScrollPaneSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneSP.setPreferredSize(new Dimension(550, 400));
        jScrollPaneCTGG.setViewportView(tblCTGG);
        jScrollPaneCTGG.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneCTGG.setPreferredSize(new Dimension(400, 400));
        tblDSSP.setFont(new Font("Segoe UI", 0, 16));
        tblCTGG.setFont(new Font("Segoe UI",0,16));
        QL_Thongke.txtDate1.setEditable(false);
        QL_Thongke.txtDate2.setEditable(false);
        QL_Thongke.txtDate1.setFont(new Font("Segoe UI",0,18));
        QL_Thongke.txtDate2.setFont(new Font("Segoe UI",0,18));
    }

    private void btnAddggAction(ActionEvent actionEvent) throws ParseException, SQLException {
        if(!checkmagg(txtMagg.getText()))
            JOptionPane.showMessageDialog(null,"Mã giảm giá bị trùng lặp !");
        else {
            GiamGiaDTO g=new GiamGiaDTO(txtMagg.getText(),txtTengg.getText(),formatter.parse(QL_Thongke.txtDate1.getText()),formatter.parse(QL_Thongke.txtDate2.getText()));
            for (ChitietGiamgiaDTO c:listCTGG) {
                c.setMakm(refresh(txtMagg.getText()).toLowerCase());
            }
            new GiamGiaBLL().addKM(g,listCTGG);
            JOptionPane.showMessageDialog(null,"Thêm thành công");
        }
    }

    private void btnDelCTGGAction(ActionEvent actionEvent) {
        if(tblCTGG.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(null,"Vui lòng chọn một sản phẩm để xóa");
        else {
            int i=tblCTGG.getSelectedRow();
            listCTGG.remove(i);
            modelCTGG.removeRow(i);
            for (int j = i; j < modelCTGG.getRowCount(); j++) {
                modelCTGG.setValueAt(j + 1, j, 0);
            }
        }
    }

    private void btnEditCTGGAction(ActionEvent actionEvent) {
        if(tblCTGG.getSelectedRow()==1)
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để sửa !");
        else {
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
                }
            }
        }
    }

    private boolean checkmagg(String magg){
        for (GiamGiaDTO g: listGG) {
            if(refresh(magg).toLowerCase().equals(g.getTenkm().toLowerCase()))
                return false;
        }
        return true;
    }
    private void btnAddAction(ActionEvent actionEvent) throws ParseException {
        if(txtTengg.getText().equals("")||txtMagg.equals("")||QL_Thongke.txtDate1.getText().equals("")||QL_Thongke.txtDate2.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin chương trình giảm giá trước tiên !");
        else {
            if (tblDSSP.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để thêm !");
            } else {
                int masp = QL_Sanpham.listSP.get(tblDSSP.getSelectedRow()).getMasp();
                if(!checkSPCTGG(masp))
                    JOptionPane.showMessageDialog(null, "Sản phẩm bạn đã chọn !");
                else {
                    int indexgg = checkdateGGSP(masp);
                    if (indexgg != -1) {
                        String tenmgg = listGG.get(indexgg).getTenkm();
                        String date1 = formatter.format(listGG.get(indexgg).getNgaybd());
                        String date2 = formatter.format(listGG.get(indexgg).getNgaykt());
                        JOptionPane.showMessageDialog(null, "Xin lỗi, sản phẩm này đang chương trình khuyến mãi: " + refresh(tenmgg).toUpperCase() + ", từ " + date1 + " dến " + date2 + " !");
                    } else {
                        Pattern pattern = Pattern.compile("\\d*");
                        if (txtphantram.getText().equals("") || !pattern.matcher(txtphantram.getText()).matches())
                            JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải từ 1 đến 99 !");
                        else {
                            int giamgia = Integer.parseInt(txtphantram.getText());
                            if (giamgia > 99 || giamgia < 1)
                                JOptionPane.showMessageDialog(null, "Phần trăm giảm giá phải từ 1 đến 99 !");
                            else {
                                SanphamDTO s = QL_Sanpham.listSP.get(tblDSSP.getSelectedRow());
                                listTenSPCTGG.add(s.getTensp());
                                listCTGG.add(new ChitietGiamgiaDTO(s.getMasp(), giamgia));
                                modelCTGG.setNumRows(0);
                                showTableCTGG(modelCTGG);
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean checkSPCTGG(int masp){
        for (ChitietGiamgiaDTO c:listCTGG ){
            if(c.getMasp()==masp) return false;
        }
        return true;
    }
    private String refresh(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }
    private int checkdateGGSP(int masp) throws ParseException {
        for (ChitietGiamgiaDTO c: listAllCTGG) {
            if (c.getMasp() == masp) {
                int indexmagg=getindexmagg(c.getMakm());
                Date d2=listGG.get(indexmagg).getNgaykt();
                Date d1=listGG.get(indexmagg).getNgaybd();
                Date d3=formatter.parse(QL_Thongke.txtDate1.getText());
                Date d4=formatter.parse(QL_Thongke.txtDate2.getText());
                int a=d3.compareTo(d2);
                int b= d1.compareTo(d4);
                if(d3.after(d2)||d1.after(d4)){
                    return -1;
                }
                else return indexmagg;
            }
        }
        return -1;
    }
    private int getindexmagg(String magg){
        int i=0;
        for (GiamGiaDTO g: listGG) {
            if(g.getMakm().equals(magg)){
                return i;
            }
            i++;
        }
        return -1;
    }


    private JLabel lbTitle = new JLabel("QUẢN LÍ KHUYẾN MÃI", SwingConstants.LEFT);
    private JLabel lbTitle2 = new JLabel("DANH SÁCH KHUYẾN MÃI", SwingConstants.LEFT);

    private JPanel pnCenter=new JPanel();
    private JPanel pnTop=new JPanel();
    private JPanel pnBottom=new JPanel();
    private JPanel pnTopRight =new JPanel();
    private JPanel pnTopRightName=new JPanel();
    private JPanel pnTopRightDate=new JPanel();
    private JTable tblCTGG;
    private JScrollPane jScrollPaneCTGG=new JScrollPane();
    private JTable tblDSSP;
    private JScrollPane jScrollPaneSP;
    private JLabel lbMagg=new LabelCustom("Mã giảm giá");
    private JLabel lbTengg=new LabelCustom("Tên giảm giá");
    private JLabel lbDate=new LabelCustom("Thời gian bắt đầu");
    private JLabel lbDate1=new LabelCustom(" ,thời gian kết thúc");
    private JTextField txtMagg=new TextFieldCustom("");
    private JTextField txtTengg=new TextFieldCustom("");
    private  JButton btnAdd=new JButton("Thêm");
    private JButton btnEditCTGG=new JButton("Sửa");
    private JButton btnAddgg=new JButton("Thêm chương trình");
    private JButton btnDelCTGG=new JButton("Xóa");
    private JTextField txtphantram=new JTextField("",5);
    private JLabel lbphantram=new LabelCustom("% giảm giá:");
}


