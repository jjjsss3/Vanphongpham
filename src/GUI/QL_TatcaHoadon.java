package GUI;

import BLL.ChitietHoadonBLL;
import BLL.HoaDonBLL;
import BLL.SanphamBLL;
import DTO.ChitietHoadonDTO;
import DTO.HoaDonDTO;
import DTO.SanphamDTO;
import GUI.Components.DatePicker;
import GUI.Components.LabelCustom;
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


public class QL_TatcaHoadon extends JPanel {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private HoaDonBLL hoaDonBLL=new HoaDonBLL();
    private ChitietHoadonBLL chitietHoadonBLL=new ChitietHoadonBLL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    private ArrayList<ChitietHoadonDTO> listSPCTSP=new ArrayList<>();
    private ArrayList<HoaDonDTO> listHD=new ArrayList<>();
    private DefaultTableModel modelHD = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelCTHD = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private int[] columnspWidth = {
            0
    };

    public QL_TatcaHoadon() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1000));
        setBackground(new Color(245, 245, 245));
        if(QL_HoaDon.listHD.size()==0) hoaDonBLL.getListHD();
        if(QL_HoaDon.listCTHD.size()==0) chitietHoadonBLL.getListCTHD();
        
        initComponents();

    }
    public void showTableHD(DefaultTableModel model, ArrayList<HoaDonDTO> list ) {
        int i = 1;
        for (HoaDonDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMahd(),s.getManv(),s.getMakh() ,formatter.format(s.getNgay()), currencyVN.format(s.getTongtien()),currencyVN.format(s.getTongkm()),currencyVN.format(s.getGiamgiakh()),currencyVN.format(s.getTongtien()-s.getTongkm()-s.getGiamgiakh())
            });
        }
    }
    public void showTableCTHD(DefaultTableModel model, ArrayList<ChitietHoadonDTO> list ) {
        model.setNumRows(0);
        int i = 1;
        for (ChitietHoadonDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMasp(),s.getTensp(),s.getSoluong() ,currencyVN.format(s.getDongia()), currencyVN.format(s.getGiamgia()),currencyVN.format(s.getSoluong()*(s.getDongia()-s.getGiamgia()))
            });
        }
    }
    public ArrayList getSP(int mahd){
        ArrayList<ChitietHoadonDTO> list=new ArrayList<>();
        if(QL_Sanpham.listSP.size()==0) sanphamBLL.getListSanpham();
        for (ChitietHoadonDTO c:QL_HoaDon.listCTHD) {
            if(c.getMahd()==mahd){
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
        listHD=QL_HoaDon.listHD;
        QL_Thongke.z=0;
        QL_HoaDon.listHD=new ArrayList<>();
        hoaDonBLL.getListHD();
        QL_HoaDon.listCTHD=new ArrayList<>();
        chitietHoadonBLL.getListCTHD();
        listSPCTSP=new ArrayList<>();
        //table hoa don

        tblHD = new JTable();
        tblHD.setRowHeight(40);
        tblHD.setModel(modelHD);
        modelHD.setColumnIdentifiers(new Object[]{
                "STT", "Mã hóa đơn","Mã nhân viên","Mã khách hàng","Ngày giờ","Tổng tiền","Khuyến mãi","Giảm giá riêng", "Thanh toán"
        });
        int i = 0;
        columnspWidth = new int[]{
                55, 80, 95, 95, 101, 131, 131, 131, 131
        };
        for (int width : columnspWidth) {
            TableColumn column = tblHD.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneHD = new JScrollPane(tblHD);
        showTableHD(modelHD,listHD);
        tblHD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int mahd=listHD.get(tblHD.getSelectedRow()).getMahd();
                listSPCTSP=new ArrayList<>();
                listSPCTSP=getSP(mahd);
                showTableCTHD(modelCTHD,listSPCTSP);
            }
        });


        //table chi tiet hoa don
        tblCTHD = new JTable();
        tblCTHD.setRowHeight(40);
        tblCTHD.setModel(modelCTHD);
        modelCTHD.setColumnIdentifiers(new Object[]{
                "STT", "Mã sản phẩm","Tên sản phẩm","Số lượng đã mua","Đơn giá","Giảm giá sản phẩm","Tổng tiền"
        });
        i = 0;
        columnspWidth = new int[]{
                55, 130, 245, 130, 130, 130, 130
        };
        i=0;
        for (int width : columnspWidth) {
            TableColumn column = tblCTHD.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneCTHD = new JScrollPane(tblCTHD);
//        showTableCTHD(modelCTHD,QL_HoaDon.listCTHD);
        tblCTHD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
//                SanphamDTO sp=new SanphamDTO();
//                { sp= listsearchsp.get(tblHD.getSelectedRow());}
//
//                try {
//                    showInputSP(sp);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });

        dp1=new DatePicker();
        QL_Thongke.txtDate1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                QL_Thongke.z=0;
                dp1.setVisible(true);
                dp1.setLocation(300, 590);
            }
        });
        QL_Thongke.txtDate2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                QL_Thongke.z=1;
                dp1.setVisible(true);
                dp1.setLocation(300, 590);
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
                    listHD=new ArrayList<>();
                    listHD=orderlist(dates,datee);
                    int l=listHD.size();
                    sort(listHD,0,l-1,cmbOrder.getSelectedIndex());
                    modelHD.setNumRows(0);
                    showTableHD(modelHD,listHD);
                    modelCTHD.setNumRows(0);
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

        pnTopLeft.add(jScrollPaneHD);
        pnTopLeft.add(pnTopLeftFilter);

        pnTopLeftFilter.add(pnTopLeftFilterDate);
        pnTopLeftFilter.add(pnSpace);
        pnTopLeftFilter.add(cmbOrder);
        pnTopLeftFilter.add(btnAllOrder);

        pnTopLeftFilterDate.add(lbDate);
        pnTopLeftFilterDate.add(QL_Thongke.txtDate1);
        pnTopLeftFilterDate.add(lbDate1);
        pnTopLeftFilterDate.add(QL_Thongke.txtDate2);

        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        pnBottom.add(pnBottomLeft);
        pnBottom.add(pnBottomRight);

        pnBottomLeft.add(jScrollPaneCTHD);

        jScrollPaneHD.setViewportView(tblHD);
        jScrollPaneHD.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneHD.setPreferredSize(new Dimension(950, 390));
        jScrollPaneCTHD.setViewportView(tblCTHD);
        jScrollPaneCTHD.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPaneCTHD.setPreferredSize(new Dimension(950, 450));
        tblHD.setFont(new Font("Segoe UI", 0, 16));
        tblCTHD.setFont(new Font("Segoe UI", 0, 16));
        pnTop.setPreferredSize(new Dimension(1620, 480));
        pnBottom.setPreferredSize(new Dimension(1620,520));
        pnTopLeft.setPreferredSize(new Dimension(1000,450));
        pnTopRight.setPreferredSize(new Dimension(620,450));
        pnTopLeftFilter.setPreferredSize(new Dimension(950,55));
        pnBottomLeft.setPreferredSize(new Dimension(1000,450));
        pnBottomRight.setPreferredSize(new Dimension(620,450));
        pnSpace.setPreferredSize(new Dimension(300,55));

        pnTop.setBackground(NewColor.background);
        pnBottom.setBackground(NewColor.background);
        pnTopLeft.setBackground(NewColor.background);
//        pnTopRight.setBackground(NewColor.background);
        pnBottomLeft.setBackground(NewColor.background);
        pnBottomRight.setBackground(NewColor.background);
        pnTopLeftFilterDate.setBackground(NewColor.background);
        cmbOrder.setBackground(Color.WHITE);
        pnTopLeftFilter.setBackground(NewColor.background);
        pnTopLeftFilterDate.setBackground(NewColor.background);
        pnSpace.setBackground(NewColor.background);
        btnAllOrder.setBackground(NewColor.background);

        QL_Thongke.txtDate1.setEditable(false);
        QL_Thongke.txtDate2.setEditable(false);
        QL_Thongke.txtDate1.setFont(new Font("Segoe UI",0,15));
        QL_Thongke.txtDate2.setFont(new Font("Segoe UI",0,15));

        cmbOrder.setFont(new Font("Segoe UI", 0, 17));
        btnAllOrder.setFont(new Font("Segoe UI",0,17));

    }
    private ArrayList orderlist(Date datestart, Date dateend){
        ArrayList<HoaDonDTO> list=new ArrayList<>();
        if(datestart==null&&dateend==null)
            list=QL_HoaDon.listHD;
        else {
            if (datestart == null) {
                for (HoaDonDTO hd : QL_HoaDon.listHD) {
                    if (hd.getNgay().compareTo(dateend) < 0 || hd.getNgay().compareTo(dateend) == 0)
                        list.add(hd);
                }
            }
            if (dateend == null) {
                for (HoaDonDTO hd : QL_HoaDon.listHD) {
                    if (hd.getNgay().compareTo(datestart) > 0 || hd.getNgay().compareTo(datestart) == 0)
                        list.add(hd);
                }
            }

            if (datestart != null && dateend != null)
                for (HoaDonDTO hd : QL_HoaDon.listHD) {
                    if (hd.getNgay().compareTo(datestart) > 0 && hd.getNgay().compareTo(dateend) < 0)
                        list.add(hd);
                    if(hd.getNgay().compareTo(datestart) == 0 || hd.getNgay().compareTo(dateend) == 0)
                        list.add(hd);
                }
        }
        return list;
    }

    int partition(ArrayList<HoaDonDTO> list, int low, int high, int order) {
        HoaDonDTO pivot = list.get(high);
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
    void sort(ArrayList<HoaDonDTO> list, int low, int high, int order) {
        if (low < high) {
            // pi là chỉ mục của chốt, arr[pi] vị trí của chốt
            int pi = partition(list, low, high, order);
            // Sắp xếp đệ quy các phần tử
            // trướcphân vùng và sau phân vùng
            sort(list, low, pi - 1, order);
            sort(list, pi + 1, high, order);
        }
    }

    private JTable tblHD;
    private JScrollPane jScrollPaneHD;
    private JTable tblCTHD;
    private JScrollPane jScrollPaneCTHD;
    private JPanel pnTop=new JPanel();
    private JPanel pnBottom=new JPanel();
    private JPanel pnTopLeft=new JPanel();
    private JPanel pnTopLeftFilter=new JPanel();
    private JPanel pnTopRight=new JPanel();
    private JPanel pnBottomLeft=new JPanel();
    private JPanel pnBottomRight=new JPanel();
    private JPanel pnTopLeftFilterDate=new JPanel();
    private JPanel pnSpace=new JPanel();
    private JLabel lbDate=new LabelCustom("Thời gian từ ");
    private JLabel lbDate1=new LabelCustom(" đến ");
    public static DatePicker dp1;
    private JComboBox<String> cmbOrder =new JComboBox<String>(new String[]{
            "Tổng tiền, tăng dần",
            "Tổng tiền, giảm dần",
    });
    private JButton btnAllOrder=new JButton("Xem tất cả");
}
