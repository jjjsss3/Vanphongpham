package GUI;

import BLL.*;
import DAL.ChitietHoadonDAL;
import DAL.DanhmucDAL;
import DAL.HoaDonDAL;
import DAL.SanphamDAL;
import DTO.*;
import GUI.Components.*;
import com.itextpdf.text.DocumentException;
import utils.CheckInput;
import utils.ExportToPDF;
import utils.ImageFit;
import utils.NewColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class QL_TaoHoaDon extends JPanel{
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SanphamDAL sanphamDAL=new SanphamDAL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private DanhmucDAL danhmucDAL=new DanhmucDAL();
    private DanhmucBLL danhmucBLL=new DanhmucBLL();
    private KhachhangBLL khachhangBLL=new KhachhangBLL();
    private HoaDonBLL hoaDonBLL=new HoaDonBLL();
    private HoaDonDAL hoaDonDAL=new HoaDonDAL();
    private ChitietHoadonBLL chitietHoadonBLL=new ChitietHoadonBLL();
    private GiamGiaBLL giamGiaBLL=new GiamGiaBLL();
    private Select_Khachhang mf;
    private ArrayList<ChitietHoadonDTO> listOD=new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private DefaultTableModel modelSP = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelDM = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelOD= new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private int check=0;
    private ArrayList<SanphamDTO> listsearchsp=new ArrayList<>();
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    private long tongtien=0;
    private long giamgia=0;
    private int[] columnspWidth = {
            80, 100, 690, 80, 100, 300, 150
    };

    public QL_TaoHoaDon() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1000));
        setBackground(new Color(245, 245, 245));
        QL_Sanpham.listDM=new ArrayList<>();
        if (QL_Sanpham.listDM.size() == 0) danhmucBLL.getListDanhmuc();
        QL_Sanpham.listSP=new ArrayList<>();
        if (QL_Sanpham.listSP.size() == 0) sanphamBLL.getListSanpham();
        listsearchsp=QL_Sanpham.listSP;
        if(QL_HoaDon.listHD.size()==0) hoaDonBLL.getListHD();
        if(QL_HoaDon.listCTHD.size()==0) chitietHoadonBLL.getListCTHD();

        giamGiaBLL.getListGG();
        giamGiaBLL.getListCTGG();

        initComponents();

    }

    public void addRow(SanphamDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMasp(), s.getTensp(), s.getMaloai(), s.getSoluong(), s.getDongia(), s.getMancc()
        });
    }

    public void showTableSP(DefaultTableModel model, ArrayList<SanphamDTO> list ) {
        int i = 1;
        for (SanphamDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getTensp(), s.getSoluong(), s.getDongia()
            });
        }
    }
    public void showTableDM(DefaultTableModel model, ArrayList<DanhmucDTO> list ) {
        model.addRow(new Object[]{
                0, 0,"Tất cả"
        });
        int i = 1;
        for (DanhmucDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMaloai(), s.getTenloai()
            });
        }
    }
    public void showTableOD(DefaultTableModel model, ArrayList<ChitietHoadonDTO> list) {

        int i = 1;
        for (ChitietHoadonDTO s : list) {
            model.addRow(new Object[]{
                    i, s.getTensp(), s.getSoluong(), s.getDongia(), s.getGiamgia()
            });
            i++;
        }
    }

    private void initComponents() throws IOException, URISyntaxException {
        gbc.fill = 1;
        gbc.insets = (new Insets(-10, 0, 20, 30));

        //table sản phẩm
        tblDSSP = new JTable();
        tblDSSP.setRowHeight(40);
        tblDSSP.setModel(modelSP);
        modelSP.setColumnIdentifiers(new Object[]{
                "STT", "Tên sản phẩm","Số lượng kho","Đơn giá"
        });
        int i = 0;
        columnspWidth = new int[]{
                50, 400, 110,100
        };
        for (int width : columnspWidth) {
            TableColumn column = tblDSSP.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneSP = new JScrollPane(tblDSSP);
        showTableSP(modelSP,listsearchsp);
        tblDSSP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                SanphamDTO sp=new SanphamDTO();
                { sp= listsearchsp.get(tblDSSP.getSelectedRow());}

                try {
                    showInputSP(sp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //table danh mục

        tblDSDM = new JTable();
        tblDSDM.setRowHeight(40);
        tblDSDM.setModel(modelDM);
        modelDM.setColumnIdentifiers(new Object[]{
                "STT", "Mã loại", "Tên danh mục"
        });
        columnspWidth = new int[]{
                80,80,250
        };
        i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblDSDM.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneDM = new JScrollPane(tblDSDM);
        showTableDM(modelDM, QL_Sanpham.listDM);
        tblDSDM.setRowSelectionAllowed(true);
        tblDSDM.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblDSDM.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

            }
        });

        //table order detail

        tblODetail = new JTable();
        tblODetail.setRowHeight(40);
        tblODetail.setModel(modelOD);
        modelOD.setColumnIdentifiers(new Object[]{
                "STT", "Tên sản phẩm", "Số lượng","Đơn giá", "Giảm giá"
        });
        columnspWidth = new int[]{
                50,310,100,100,100
        };
        i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblODetail.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneODetail = new JScrollPane(tblODetail);
//        showTableDM(modelDM, QL_Sanpham.listDM);
        tblODetail.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int index=tblODetail.getSelectedRow();
                txtODSoluong.setText(String.valueOf(listOD.get(index).getSoluong()));
            }
        });

        //

        // button của sản phẩm


        btnAdd.addActionListener(e->{
            try {
                btnAddActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btnFilter.addActionListener(this::btnFilterActionPerformed);
        btnSelect.addActionListener(this::setBtnSelectActionPerformed);
        btnEdit.addActionListener(this::btnEditActionPerformed);
        btnDel.addActionListener(this::btnDelActionPerformed);
        btnOrder.addActionListener(actionEvent -> {
            try {
                btnOrderActionPerformed(actionEvent);
            } catch (ParseException | SQLException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pnSearch.add(lbTensp); pnSearch.add(txtTensp);
        pnCtnL.add(jScrollPaneDM);pnCtnL.add(pnSearch);
        pnCtnL.add(btnFilter);

        pnCtn.setLayout(new BoxLayout(pnCtn, BoxLayout.X_AXIS));
        pnCtn.add(pnCtnL);
        pnCtn.add(jScrollPaneSP);
        pnCtn.add(pnImg);

        pnOrder.setLayout(new BoxLayout(pnOrder, BoxLayout.X_AXIS));
        pnOrder.add(pnKH);
        pnKH.setLayout(new GridLayout(10,2));
        pnKH.add(lbTenKH); pnKH.add(txtTenKH);
        pnKH.add(lbSDT);    pnKH.add(txtSDT);
        pnKH.add(lbDiachi);pnKH.add(txtDiachi);
        pnKH.add(btnSelect);
        pnOrder.add(pnOrderDetail);
        pnOrderDetail.add(jScrollPaneODetail);
        pnOrderDetail.add(pnODBtn);pnODBtn.add(lbODSoluong);pnODBtn.add(txtODSoluong);pnODBtn.add(btnEdit);pnODBtn.add(btnDel);
        pnOrder.add(pnBtn);
        pnBtn.setLayout(new GridLayout(10,2));
        pnBtn.add(lbTongtien);pnBtn.add(txtTongtien);
        pnBtn.add(lbGiamgia);pnBtn.add(txtGiamgia);
        pnBtn.add(lbThanhtoan);pnBtn.add(txtThanhtoan);
        pnBtn.add(btnOrder);
        add(pnCtn);
        add(lbTitleOrder);
        add(pnOrder);

        pnImg.add(pnLbImg);
        pnImg.add(pnAddBtn);
        pnLbImg.add(lbimg);
        img=ImageIO.read(this.getClass().getResource("images/sanpham/product.png"));
        lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 200, 200)));
        pnAddBtn.add(lbSoluong);pnAddBtn.add(txtSoluong);
        pnAddBtn.add(btnAdd);

        {
            lbTitleOrder.setPreferredSize(new Dimension(1520,80));
            lbTitleOrder.setVerticalAlignment(SwingConstants.CENTER);
            jScrollPaneSP.setViewportView(tblDSSP);
            jScrollPaneSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneSP.setPreferredSize(new Dimension(650, 400));
            jScrollPaneODetail.setViewportView(tblODetail);
            jScrollPaneODetail.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneODetail.setPreferredSize(new Dimension(650, 330));
            jScrollPaneDM.setViewportView(tblDSDM);
            jScrollPaneDM.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneDM.setPreferredSize(new Dimension(400, 400));
            pnImg.setPreferredSize(new Dimension(400, 400));
            pnLbImg.setPreferredSize(new Dimension(400, 400));
            pnLbImg.setBackground(NewColor.background);
            pnDM.setPreferredSize(new Dimension(400,70));
            pnODBtn.setPreferredSize(new Dimension(650,50));
            pnODBtn.setBackground(NewColor.background);

//            pnBtDM.setPreferredSize(new Dimension(400,35));
            jScrollPaneDM.setBackground(NewColor.background);
            pnBtDM.setBackground(NewColor.background);
            pnDM.setBackground(NewColor.background);
//            tblDSSP.setAutoCreateRowSorter(true);
            pnCtnL.setPreferredSize(new Dimension(400, 400));
            pnCtnL.setBackground(NewColor.background);
            pnSearch.setBackground(NewColor.background);
            pnAddBtn.setBackground(NewColor.background);
            pnCtn.setPreferredSize(new Dimension(1500, 450));
            pnCtn.setBackground(NewColor.background);
            pnOrder.setPreferredSize(new Dimension(1500, 400));
            pnOrder.setBackground(NewColor.background);
            pnKH.setPreferredSize(new Dimension(400, 400));
            pnKH.setBackground(NewColor.background);
            pnOrderDetail.setPreferredSize(new Dimension(650, 400));
            pnBtn.setPreferredSize(new Dimension(400,200));
            pnBtn.setBackground(NewColor.background);
            pnOrderDetail.setBackground(NewColor.background);
            lbTitleOrder.setForeground(new Color(54, 38, 90));
            lbTitleOrder.setFont(new Font("Segoe UI", 1, 20));
            tblDSSP.setFont(new Font("Segoe UI", 0, 16));
            tblODetail.setFont(new Font("Segoe UI", 0, 16));
            tblDSDM.setFont(new Font("Segoe UI", 0, 16));
            txtMancc.setEditable(false);
            pnImg.setBackground(NewColor.background);
        }
    }
    private int checkKH(ArrayList<KhachhangDTO>list, String sdt, String name) {
        int i=0;
        for (KhachhangDTO kh: list
             ) {
            if(kh.getSdt().equals(sdt)) {
                if (name.equals(kh.getHo().toLowerCase() + " " + kh.getTen().toLowerCase()))
                    return i;
                else return -2;
            }
            i++;
        }
        return -1;
    }
    private void btnOrderActionPerformed(ActionEvent actionEvent) throws ParseException, SQLException, DocumentException, IOException {
        String tenkh = refresh(txtTenKH.getText());
        String sdt = refresh(txtSDT.getText());
        String diachi = refresh(txtDiachi.getText());
        if (tenkh.equals("") || sdt.equals("") || diachi.equals(""))
            JOptionPane.showMessageDialog(null, "Please fill in the customer information fully!");
        else {
            if (QL_Khachhang.listKH.size() == 0)
                khachhangBLL.getListKhachhang();
            int checkkh = checkKH(QL_Khachhang.listKH, sdt, tenkh.toLowerCase());
            if (checkkh == -2) {
                JOptionPane.showMessageDialog(null, "Số điện thoại đã đăng kí với một cái tên khác!");
            }else {
                int res = JOptionPane.showConfirmDialog(null, "Xác nhận việc đặt đơn này?", "Message", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {

                    KhachhangDTO khadd;

                    if (checkkh == -1) {
                        int i = HoTen(tenkh);
                        khadd = new KhachhangDTO("", tenkh.substring(0, i), tenkh.substring(i + 1, tenkh.length()), diachi, sdt, 0);
                        khadd.setDob(formatter.parse("0-0-0"));
                        khadd.setGioitinh("");
                        khachhangBLL.addKH(khadd);
                        khadd.setMa(String.valueOf(khachhangBLL.getIDKHAdded()));
                    } else {
                        khadd = QL_Khachhang.listKH.get(checkkh);
                    }
                    Date d=Calendar.getInstance().getTime();
                    HoaDonDTO hd=new HoaDonDTO(Dangnhap.taikhoan.getMa(),khadd.getMa(),Calendar.getInstance().getTime(),tongtien,giamgia,0);
                    hoaDonBLL.addHD(hd);
                    chitietHoadonBLL.addListCTHD(listOD);
                    hoaDonBLL.updateSoluong(listOD);
                    hd.setMahd(hoaDonDAL.getHDAdded());
                    JOptionPane.showMessageDialog(null, "Đặt hàng thành công!");

                    new ExportToPDF().exportToPDFHoaDon(modelOD,hd,khadd);
                    afterOrder();
                }
            }
        }


    }
    public void afterOrder(){
        tongtien=0;
        giamgia=0;
        modelOD.setNumRows(0);
        QL_Sanpham.listSP=new ArrayList<>();
        sanphamBLL.getListSanpham();
        listsearchsp=QL_Sanpham.listSP;
        modelSP.setNumRows(0);
        showTableSP(modelSP,listsearchsp);
        listOD=new ArrayList<>();
        txtTongtien.setText("");
        txtGiamgia.setText("");
        txtThanhtoan.setText("");
        txtTenKH.setText("");
        txtDiachi.setText("");
        txtSDT.setText("");
    }

    private void btnDelActionPerformed(ActionEvent actionEvent) {
        int index=tblODetail.getSelectedRow();
        if(index==-1)
            JOptionPane.showMessageDialog(null, "Please choose a product!");
        else {
            txtTongtien.setText(currencyVN.format(tongtien-listOD.get(index).getSoluong()*listOD.get(index).getDongia()));
            txtGiamgia.setText(currencyVN.format(giamgia-listOD.get(index).getSoluong()*listOD.get(index).getGiamgia()));
            txtThanhtoan.setText(currencyVN.format(tongtien-giamgia-listOD.get(index).getSoluong()*(listOD.get(index).getDongia()-listOD.get(index).getGiamgia())));
            listOD.remove(index);
            modelOD.removeRow(index);
            for (int i = 0; i < modelOD.getRowCount(); i++) {
                modelOD.setValueAt(i+1,i,0);
            }
        }
    }

    private void btnEditActionPerformed(ActionEvent actionEvent) {

        int index=tblODetail.getSelectedRow();
        if(index==-1)
            JOptionPane.showMessageDialog(null, "Please choose a product!");
        else {
            if(txtODSoluong.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Amount is null!");
            else {
                Pattern pattern = Pattern.compile("\\d*");
                Matcher matcher = pattern.matcher(txtODSoluong.getText());
                if(matcher.matches()) {
                    int soluong=Integer.parseInt(txtODSoluong.getText());
                    if(soluong<1)
                        JOptionPane.showMessageDialog(null, "Amount can not be under 1!");
                    if(soluong>searchSoluong(QL_Sanpham.listSP,listOD.get(tblODetail.getSelectedRow()).getMasp()))
                        JOptionPane.showMessageDialog(null, "Not enough amounts!!");
                    else {
                        listOD.get(tblODetail.getSelectedRow()).setSoluong(soluong);
                        modelOD.setValueAt(soluong,index,2);
                    }
                }
            }
        }
    }
    private int searchSoluong(ArrayList<SanphamDTO>list, int masp){
        for (SanphamDTO sp: list
             ) {
            if(sp.getMasp()==masp) return sp.getSoluong();
        }
        return 0;
    }
    private void btnFilterActionPerformed(ActionEvent e) {
        int[] selection = tblDSDM.getSelectedRows();
        String str=refresh(txtTensp.getText());
        if(selection.length==0) {
            tblDSDM.setRowSelectionInterval(0,0);
            selection = tblDSDM.getSelectedRows();
        }
        if(IntStream.of(selection).anyMatch(x -> x == 0)) {
            if(str.equals("")) listsearchsp=QL_Sanpham.listSP;
            else{
                for (SanphamDTO sp: QL_Sanpham.listSP) {
                    if(sp.getTensp().toLowerCase().contains(str.toLowerCase())) listsearchsp.add(sp);
                }
            }
            }else{
                listsearchsp=new ArrayList<>();
                for (SanphamDTO sp: QL_Sanpham.listSP
            ) {
                if(sp.getTensp().toLowerCase().contains(str.toLowerCase())||str.equals("")) {
                    if(IntStream.of(selection).anyMatch(x -> x == sp.getMaloai())) listsearchsp.add(sp);
                }
            }
        }
        modelSP.setNumRows(0);
        showTableSP(modelSP,listsearchsp);
    }
    public String refresh(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }
    public int HoTen(String s){
        int l=s.length();
        for (int i = l-1; i >=0 ; i--) {
            if(s.charAt(i)==' ') return i;
        }
        return 0;
    }
    //thiết lập các button

    private void btnAddActionPerformed(ActionEvent evt) throws SQLException {
        try
        {
            int row = tblDSSP.getSelectedRow();
            String Table_click = (tblDSSP.getModel().getValueAt(row, 0).toString());
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(txtSoluong.getText());
            if(matcher.matches()){
                int soluong=Integer.parseInt(txtSoluong.getText());
                int soluongkho=listsearchsp.get(row).getSoluong();
                if(soluong>soluongkho){
                    JOptionPane.showMessageDialog(null, "Not enough amounts!");
                }else{
                    int index=searchSP(listOD,listsearchsp.get(row).getMasp());
                    if(index!=-1){
                        if(listOD.get(index).getSoluong()+soluong>listsearchsp.get(row).getSoluong()){
                            JOptionPane.showMessageDialog(null, "Not enough amounts!");
                        }else {
                            listOD.get(index).setSoluong(listOD.get(index).getSoluong() + soluong);
                            tongtien+=soluong* listOD.get(index).getDongia();
                            giamgia+= soluong *listOD.get(index).getGiamgia();
                        }
                    }else {
                        SanphamDTO sp=listsearchsp.get(row);
                        ChitietHoadonDTO spadd=new ChitietHoadonDTO('\0',sp.getMasp(),Integer.parseInt(txtSoluong.getText()), sp.getDongia(),'\0' );
                        spadd.setTensp(sp.getTensp());
                        listOD.add(0,spadd);
                        int gg=checkgg(spadd.getMasp());
                        spadd.setGiamgia(gg*spadd.getDongia()/100);
                        tongtien+=spadd.getDongia()*spadd.getSoluong();
                        giamgia+=spadd.getGiamgia()*spadd.getSoluong();
                    }

                    modelOD.setNumRows(0);
                    showTableOD(modelOD,listOD);
                    txtTongtien.setText(currencyVN.format(tongtien));
                    txtGiamgia.setText(currencyVN.format(giamgia));
                    txtThanhtoan.setText(currencyVN.format(tongtien-giamgia));
                }
            }else  JOptionPane.showMessageDialog(null, "Amount must be an integer!");


            //... implementation hire

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please choose a product!");
        }
    }
    private int checkgg(int masp) throws ParseException {
        Date now=Calendar.getInstance().getTime();
        for (GiamGiaDTO gg: QL_Giamgia.listGG) {
            if(gg.getNgaybd().compareTo(now)<0&&now.compareTo(gg.getNgaykt())<0){
                for (ChitietGiamgiaDTO c: QL_Giamgia.listCTGG) {
                    if(c.getMasp()==masp) return c.getPhantramkm();
                }
            }

        }
        return 0;
    }
    private void tongtien(ArrayList<SanphamDTO>list, ArrayList listGG ,long tong ,long tongkm){
        int i=0;
        for (SanphamDTO sp:list
             ) {
            tong+=sp.getSoluong()*sp.getDongia();
            tongkm+=Long.parseLong(String.valueOf(listGG.get(i)))*sp.getSoluong();
        }
    }

    private void setBtnSelectActionPerformed(ActionEvent evt){
        while(check==0) {
            mf = new Select_Khachhang();
            check = 1;
        }
        mf.setVisible(true);
        mf.setLocation(775,550);
    }
    private void btnUpdateActionPerformed(ActionEvent evt) {

    }



    //thiết lập các button danh mục






    //khai báo
    private BufferedImage img;
    private ImageIcon imgicon;
    private JLabel lbimg=new JLabel();
    private JPanel pnCtn = new JPanel();
    private JPanel pnCtnL = new JPanel();
    private JPanel pnLbImg =new JPanel();
    private JPanel pnImg=new JPanel();
    private JPanel pnSearch=new JPanel();
    private JPanel pnAddBtn=new JPanel();
    private JPanel pnOrder=new JPanel();
    private JPanel pnKH=new JPanel();
    private JPanel pnOrderDetail=new JPanel();
    private JPanel pnBtn=new JPanel();

    private JTable tblDSSP;
    private JTable tblDSDM;
    private JTable tblODetail;
    private JLabel lbTitleOrder = new JLabel("ĐƠN HÀNG",SwingConstants.LEFT);
    private JLabel lbTensp = new LabelCustom("Tên sản phẩm");
    private JLabel lbSoluong =new LabelCustom("Số lượng");
    private JLabel lbODSoluong=new LabelCustom("Điều chỉnh số lượng");
    private JLabel lbTenKH=new LabelCustom("Tên khách hàng");
    private JLabel lbSDT=new LabelCustom("Số điện thoại");
    private JLabel lbDiachi=new LabelCustom("Địa chỉ");
    private JLabel lbTongtien=new LabelCustom("Tổng tiền");
    private JLabel lbGiamgia=new LabelCustom("Giảm giá");
    private JLabel lbThanhtoan=new LabelCustom("Thanh toán");

    public static JTextField txtMancc = new TextFieldCustom("");
    private JTextField txtTensp=new JTextField("",15);
    public static JTextField txtTenKH=new TextFieldCustom("");
    public static JTextField txtSDT=new TextFieldCustom("");
    public static JTextField txtDiachi=new TextFieldCustom("");
    private JTextField txtTongtien=new TextFieldCustom("");
    private JTextField txtGiamgia=new TextFieldCustom("");
    private JTextField txtThanhtoan=new TextFieldCustom("");


    private JScrollPane jScrollPaneSP;
    private JScrollPane jScrollPaneDM;
    private JScrollPane jScrollPaneODetail;
    private JButton btnAdd=new JButton("Thêm");
    private JButton btnFilter=new JButton("Tìm");
    private JButton btnSelect=new JButton("Chọn trong danh sách");
    private JButton btnDel=new JButton("Xóa");
    private JButton btnEdit=new JButton("Sửa");
    private JButton btnOrder=new JButton("Đặt hàng và xuất hóa đơn");
    private JPanel  pnBtDM=new JPanel();
    private JPanel pnDM=new JPanel();
    private JPanel pnODBtn=new JPanel();


    private JTextField txtSoluong=new JTextField("1",15);
    private JTextField txtODSoluong =new JTextField("",15);


    private void showInputSP(SanphamDTO sp) throws IOException{

        int z=1;
        for (DanhmucDTO s: QL_Sanpham.listDM) {
            if(sp.getMaloai()==s.getMaloai()) break;
            z++;
        }
        tblDSDM.setRowSelectionInterval(z,z);

        URL url = getClass().getResource(sp.getAnhsp());
        File f=new File(String.valueOf(url));
        if(!f.exists())
            img=ImageIO.read(this.getClass().getResource("images/sanpham/product.png"));
        else{
            img = ImageIO.read(new File(url.getPath()));
        }
        lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 200, 200)));
    }


    public static void showKH(KhachhangDTO kh){
        txtTenKH.setText(kh.getHo()+" "+kh.getTen());
        txtSDT.setText(kh.getSdt());
        txtDiachi.setText(kh.getDiachi());
    }
    private int searchSP(ArrayList<ChitietHoadonDTO>list,int masp ){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMasp()==masp)
                return i;
        }
        return -1;
    }
}
