package GUI;

import BLL.*;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class QL_TatcaNhapHang extends JPanel {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private PhieunhaphangBLL phieunhaphangBLL=new PhieunhaphangBLL();
    private CTPhieunhaphangBLL ctPhieunhaphangBLL=new CTPhieunhaphangBLL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    
    private ArrayList<CTPhieunhaphangDTO> listSPCTPNH=new ArrayList<>();
    private ArrayList<PhieunhaphangDTO> listPNH=new ArrayList<>();

    
    private DefaultTableModel modelPNH = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelCTPNH = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private int[] columnspWidth = {
            0
    };

    public QL_TatcaNhapHang() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1000));
        setBackground(new Color(245, 245, 245));
        QL_NhapHang.listPNH=new ArrayList<>();
        QL_NhapHang.listCTPNH=new ArrayList<>();
        phieunhaphangBLL.getListPNH();
        ctPhieunhaphangBLL.getListCTPNH();
        initComponents();
    }
    public void showTablePNH(DefaultTableModel model, ArrayList<PhieunhaphangDTO> list ) {
        int i = 1;
        for (PhieunhaphangDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMapnh(),s.getManv(),formatter.format(s.getNgay()),s.getMancc() ,currencyVN.format(s.getTongtien())
            });
        }
    }
    public void showTableCTPNH(DefaultTableModel model, ArrayList<CTPhieunhaphangDTO> list ) {
        model.setNumRows(0);
        int i = 1;
        for (CTPhieunhaphangDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMasp(),s.getTensp(),s.getSoluong() ,currencyVN.format(s.getDongia()), currencyVN.format(s.getSoluong()*s.getDongia())
            });
        }
    }
    public ArrayList getSP(int mapnh){
        ArrayList<CTPhieunhaphangDTO> list=new ArrayList<>();
        if(QL_Sanpham.listSP.size()==0) sanphamBLL.getListSanpham();
        for (CTPhieunhaphangDTO c:QL_NhapHang.listCTPNH) {
            if(c.getMapnh()==mapnh){
                c.setTensp(searchTenSP(QL_Sanpham.listSP,c.getMasp()));
                list.add(c);
            }
        }
        return list;

    }
    public String searchTenSP(ArrayList<SanphamDTO>list, int masp){
        for (SanphamDTO s:list) {
            if(s.getMasp()==masp) return s.getTensp();
        }
        return "";
    }


    private void initComponents() {

        listPNH=QL_NhapHang.listPNH;
        
        listPNH=QL_NhapHang.listPNH;
        QL_Thongke.z=0;
        QL_NhapHang.listPNH=new ArrayList<>(); phieunhaphangBLL.getListPNH();
        
        QL_NhapHang.listCTPNH=new ArrayList<>(); ctPhieunhaphangBLL.getListCTPNH();
        listSPCTPNH=new ArrayList<>();
        listSPCTPNH=new ArrayList<>();
        //table hoa don

        tblPNH = new JTable();
        tblPNH.setRowHeight(40);
        tblPNH.setModel(modelPNH);
        modelPNH.setColumnIdentifiers(new Object[]{
                "STT", "Mã phiếu nhập hàng","Mã nhân viên","Ngày giờ","Mã nhà cung cấp","Tổng tiền"
        });
        int i = 0;
        columnspWidth = new int[]{
                55, 180, 180, 175, 180, 180
        };
        for (int width : columnspWidth) {
            TableColumn column = tblPNH.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPanePNH = new JScrollPane(tblPNH);
        showTablePNH(modelPNH,listPNH);
        tblPNH.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int mapnh=listPNH.get(tblPNH.getSelectedRow()).getMapnh();
                listSPCTPNH=new ArrayList<>();
                listSPCTPNH=getSP(mapnh);
                showTableCTPNH(modelCTPNH,listSPCTPNH);
                NhanvienDTO nv=getNhanvien(listPNH.get(tblPNH.getSelectedRow()).getManv());
                txtTennv.setText(nv.getHo()+" "+nv.getTen());
                NhacungcapDTO ncc=getNhacungcap(listPNH.get(tblPNH.getSelectedRow()).getMancc());
                txtTenncc.setText(ncc.getTenncc());
                txtSDTncc.setText(ncc.getSdt());
            }
        });


        //table chi tiet hoa don
        tblCTPNH = new JTable();
        tblCTPNH.setRowHeight(40);
        tblCTPNH.setModel(modelCTPNH);
        modelCTPNH.setColumnIdentifiers(new Object[]{
                "STT", "Mã sản phẩm","Tên sản phẩm","Số lượng đã nhập","Đơn giá nhập","Tổng tiền"
        });
        i = 0;
        columnspWidth = new int[]{
                55, 120, 295, 130, 170, 170
        };
        i=0;
        for (int width : columnspWidth) {
            TableColumn column = tblCTPNH.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneCTPNH = new JScrollPane(tblCTPNH);
//        showTableCTPNH(modelCTPNH,QL_NhapHang.listCTPNH);
        tblCTPNH.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                SanphamDTO sp=new SanphamDTO();
//                { sp= listsearchsp.get(tblPNH.getSelectedRow());}
//
//                try {
//                    showInputSP(sp);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });

        if(QL_Thongke.dp1==null )QL_Thongke.dp1=new DatePicker();
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
        cmbOrder.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql;
                String timeS=QL_Thongke.txtDate1.getText();
                String timeE=QL_Thongke.txtDate2.getText();
                try {
                    Date dates = null;
                    Date datee = null;
                    if(!timeS.equals("")) dates=formatter.parse(timeS);
                    if(!timeE.equals("")) datee=formatter.parse(timeE);
                    listPNH=new ArrayList<>();
                    listPNH=orderlist(dates,datee);
                    int l=listPNH.size();
                    sort(listPNH,0,l-1,cmbOrder.getSelectedIndex());
                    modelPNH.setNumRows(0);
                    showTablePNH(modelPNH,listPNH);
                    modelCTPNH.setNumRows(0);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(pnTop);
        add(pnBottom);

        pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));
        pnTop.add(pnTopLeft);
        pnTop.add(pnTopRight);

        pnTopLeft.add(jScrollPanePNH);
        pnTopLeft.add(pnTopLeftFilter);

        pnTopLeftFilter.add(pnTopLeftFilterDate);
        pnTopLeftFilter.add(pnSpace);
        pnTopLeftFilter.add(cmbOrder);
        pnTopLeftFilter.add(btnAllOrder);

        pnTopLeftFilterDate.add(lbDate);
        pnTopLeftFilterDate.add(QL_Thongke.txtDate1);
        pnTopLeftFilterDate.add(lbDate1);
        pnTopLeftFilterDate.add(QL_Thongke.txtDate2);

        pnTopRight.add(pnTopRightInfor);
        pnTopRightInfor.setLayout(new GridLayout(6,1));
        pnTopRightInfor.add(lbTenncc);pnTopRightInfor.add(txtTenncc);
        pnTopRightInfor.add(lbSDTncc);pnTopRightInfor.add(txtSDTncc);
        pnTopRightInfor.add(lbTennv);pnTopRightInfor.add(txtTennv);

        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        pnBottom.add(pnBottomLeft);
        pnBottom.add(pnBottomRight);

        pnBottomLeft.add(jScrollPaneCTPNH);

        jScrollPanePNH.setViewportView(tblPNH);
        jScrollPanePNH.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPanePNH.setPreferredSize(new Dimension(950, 390));
        jScrollPaneCTPNH.setViewportView(tblCTPNH);
        jScrollPaneCTPNH.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneCTPNH.setPreferredSize(new Dimension(950, 450));
        tblPNH.setFont(new Font("Segoe UI", 0, 16));
        tblCTPNH.setFont(new Font("Segoe UI", 0, 16));
        pnTop.setPreferredSize(new Dimension(1620, 480));
        pnBottom.setPreferredSize(new Dimension(1620,520));
        pnTopLeft.setPreferredSize(new Dimension(1000,450));
        pnTopRight.setPreferredSize(new Dimension(620,450));
        pnTopLeftFilter.setPreferredSize(new Dimension(950,55));
        pnTopRightInfor.setPreferredSize(new Dimension(600,300));
        pnBottomLeft.setPreferredSize(new Dimension(1000,450));
        pnBottomRight.setPreferredSize(new Dimension(620,450));
        pnSpace.setPreferredSize(new Dimension(300,55));

        pnTop.setBackground(NewColor.background);
        pnBottom.setBackground(NewColor.background);
        pnTopLeft.setBackground(NewColor.background);
        pnTopRight.setBackground(NewColor.background);
        pnBottomLeft.setBackground(NewColor.background);
        pnBottomRight.setBackground(NewColor.background);
        pnTopLeftFilterDate.setBackground(NewColor.background);
        cmbOrder.setBackground(Color.WHITE);
        pnTopLeftFilter.setBackground(NewColor.background);
        pnTopLeftFilterDate.setBackground(NewColor.background);
        pnSpace.setBackground(NewColor.background);
        btnAllOrder.setBackground(NewColor.background);
        pnTopRightInfor.setBackground(NewColor.background);

        QL_Thongke.txtDate1.setEditable(false);
        QL_Thongke.txtDate2.setEditable(false);
        QL_Thongke.txtDate1.setFont(new Font("Segoe UI",0,15));
        QL_Thongke.txtDate2.setFont(new Font("Segoe UI",0,15));

        txtTenncc.setEditable(false);
        txtSDTncc.setEditable(false);
        txtTennv.setEditable(false);

        cmbOrder.setFont(new Font("Segoe UI", 0, 17));
        btnAllOrder.setFont(new Font("Segoe UI",0,17));

    }
    private ArrayList orderlist(Date datestart, Date dateend){
        ArrayList<PhieunhaphangDTO> list=new ArrayList<>();
        if(datestart==null&&dateend==null)
            list=QL_NhapHang.listPNH;
        else {
            if (datestart == null) {
                for (PhieunhaphangDTO hd : QL_NhapHang.listPNH) {
                    if (hd.getNgay().compareTo(dateend) < 0 || hd.getNgay().compareTo(dateend) == 0)
                        list.add(hd);
                }
            }
            if (dateend == null) {
                for (PhieunhaphangDTO hd : QL_NhapHang.listPNH) {
                    if (hd.getNgay().compareTo(datestart) > 0 || hd.getNgay().compareTo(datestart) == 0)
                        list.add(hd);
                }
            }

            if (datestart != null && dateend != null)
                for (PhieunhaphangDTO hd : QL_NhapHang.listPNH) {
                    if (hd.getNgay().compareTo(datestart) > 0 && hd.getNgay().compareTo(dateend) < 0)
                        list.add(hd);
                    if(hd.getNgay().compareTo(datestart) == 0 || hd.getNgay().compareTo(dateend) == 0)
                        list.add(hd);
                }
        }
        return list;
    }
    private NhanvienDTO getNhanvien(String manv){
        if(QL_Nhanvien.listNV.size()==0) new NhanvienBLL().getListNhanvien();
        for (NhanvienDTO n:QL_Nhanvien.listNV) {
            if(n.getMa().equals(manv)) return n;
        }
        return null;
    }
    private NhacungcapDTO getNhacungcap(int mancc){
        if(QL_Nhacungcap.listNCC.size()==0) new NhacungcapBLL().getListNhacungcap();
        for (NhacungcapDTO n:QL_Nhacungcap.listNCC) {
            if(n.getMancc()==mancc) return n;
        }
        return null;
    }
    int partition(ArrayList<PhieunhaphangDTO> list, int low, int high, int order) {
        PhieunhaphangDTO pivot = list.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {

            // Nếu phần tử hiện tại nhỏ hơn chốt
            if(order==0) {
                if (list.get(j).getTongtien() < pivot.getTongtien()) {
                    i++;
                    // swap arr[i] và arr[j]
                    Collections.swap(list, i,j);
                }
            }else
            if(order==1){
                if (list.get(j).getTongtien() > pivot.getTongtien()) {
                    i++;
                    // swap arr[i] và arr[j]
                    Collections.swap(list, i,j);
                }
            }
        }

        // swap arr[i+1] và arr[high] (hoặc pivot)
        Collections.swap(list, i+1,high);
        return i + 1;
    }
    void sort(ArrayList<PhieunhaphangDTO> list, int low, int high, int order) {
        if (low < high) {
            // pi là chỉ mục của chốt, arr[pi] vị trí của chốt
            int pi = partition(list, low, high, order);
            // Sắp xếp đệ quy các phần tử
            // trướcphân vùng và sau phân vùng
            sort(list, low, pi - 1, order);
            sort(list, pi + 1, high, order);
        }
    }

    private JTable tblPNH;
    private JScrollPane jScrollPanePNH;
    private JTable tblCTPNH;
    private JScrollPane jScrollPaneCTPNH;
    private JPanel pnTop=new JPanel();
    private JPanel pnBottom=new JPanel();
    private JPanel pnTopLeft=new JPanel();
    private JPanel pnTopLeftFilter=new JPanel();
    private JPanel pnTopRight=new JPanel();
    private JPanel pnTopRightInfor=new JPanel();
    private JPanel pnBottomLeft=new JPanel();
    private JPanel pnBottomRight=new JPanel();
    private JPanel pnTopLeftFilterDate=new JPanel();
    private JPanel pnSpace=new JPanel();
    private JLabel lbDate=new LabelCustom("Thời gian từ ");
    private JLabel lbDate1=new LabelCustom(" đến ");
    private JLabel lbTenncc=new LabelCustom("Tên nhà cung cấp");
    private JLabel lbSDTncc=new LabelCustom("SDT nhà cung cấp");
    private JLabel lbTennv=new LabelCustom("Tên nhân viên nhập");
    private JTextField txtTenncc=new TextFieldCustom("");
    private JTextField txtSDTncc=new TextFieldCustom("");
    private JTextField txtTennv=new TextFieldCustom("");
    private JComboBox<String> cmbOrder =new JComboBox<String>(new String[]{
            "Tổng tiền, tăng dần",
            "Tổng tiền, giảm dần",
    });
    private JButton btnAllOrder=new JButton("Xem tất cả");
}
