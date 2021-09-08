package GUI;

import BLL.*;
import DAL.DanhmucDAL;
import DAL.HoaDonDAL;
import DAL.SanphamDAL;
import DTO.*;
import GUI.Components.*;
import com.itextpdf.text.DocumentException;
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

public class QL_TaoNhapHang extends JPanel{
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SanphamDAL sanphamDAL=new SanphamDAL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private DanhmucDAL danhmucDAL=new DanhmucDAL();
    private DanhmucBLL danhmucBLL=new DanhmucBLL();
    private KhachhangBLL khachhangBLL=new KhachhangBLL();
    private HoaDonBLL hoaDonBLL=new HoaDonBLL();
    private HoaDonDAL hoaDonDAL=new HoaDonDAL();
    private PhieunhaphangBLL phieunhaphangBLL=new PhieunhaphangBLL();
    private CTPhieunhaphangBLL ctPhieunhaphangBLL=new CTPhieunhaphangBLL();
    private ChitietHoadonBLL chitietHoadonBLL=new ChitietHoadonBLL();
    private GiamGiaBLL giamGiaBLL=new GiamGiaBLL();
    private Select_Nhacungcap mf=null;
    private ArrayList<ChitietHoadonDTO> listOD=new ArrayList<>();
    private ArrayList<CTPhieunhaphangDTO> listCTPNH=new ArrayList<>();
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
    private DefaultTableModel modelPNH= new DefaultTableModel(){
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

    public QL_TaoNhapHang() throws IOException, URISyntaxException {
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
    public void setRow(DefaultTableModel model,CTPhieunhaphangDTO nhanvienDTO ,int index){
        model.setValueAt((nhanvienDTO.getSoluong()),index,2);
        model.setValueAt(currencyVN.format(nhanvienDTO.getDongianhap()),index,3);
        model.setValueAt(currencyVN.format(nhanvienDTO.getDongianhap()*nhanvienDTO.getSoluong()),index,4);
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
                    i++,s.getMasp(), s.getTensp(), s.getSoluong()
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
    public void showTablePNH(DefaultTableModel model, ArrayList<CTPhieunhaphangDTO> list) {

        int i = 1;
        for (CTPhieunhaphangDTO s : list) {
            model.addRow(new Object[]{
                    i, s.getTensp(), s.getSoluong(), currencyVN.format(s.getDongianhap()), currencyVN.format(s.getDongianhap()*s.getSoluong())
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
                "STT","Mã sãn phẩm" ,"Tên sản phẩm","Số lượng kho"
        });
        int i = 0;
        columnspWidth = new int[]{
                50, 100, 400, 110
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

        tblPNH = new JTable();
        tblPNH.setRowHeight(40);
        tblPNH.setModel(modelPNH);
        modelPNH.setColumnIdentifiers(new Object[]{
                "STT", "Tên sản phẩm", "Số lượng nhập","Đơn giá nhập", "Tổng tiền"
        });
        columnspWidth = new int[]{
                45,345,95,95,130
        };
        i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblPNH.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScorllPanePNH = new JScrollPane(tblPNH);
//        showTableDM(modelDM, QL_Sanpham.listDM);
        tblPNH.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int index=tblPNH.getSelectedRow();
                txtPNHSoluong.setText(String.valueOf(listCTPNH.get(index).getSoluong()));
                txtSoluong.setText(String.valueOf(listCTPNH.get(index).getSoluong()));
                txtTenspNhap.setText(listCTPNH.get(index).getTensp());
                if(listCTPNH.get(index).getCheck()==1)
                    txtDongia.setText(String.valueOf(listCTPNH.get(index).getDongia()));
                txtDongianhap.setText(String.valueOf(listCTPNH.get(index).getDongianhap()));
                txtPNHDongianhap.setText(String.valueOf(listCTPNH.get(index).getDongianhap()));

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
        pnCtn.add(pnInfo);

        pnOrder.setLayout(new BoxLayout(pnOrder, BoxLayout.X_AXIS));
        pnOrder.add(pnKH);
        pnKH.setLayout(new GridLayout(10,2));

        pnKH.add(lbMancc);  pnKH.add(txtMancc);
        pnKH.add(lbTenNcc); pnKH.add(txtTenNcc);
        pnKH.add(lbSDT);    pnKH.add(txtSDTNcc);

        pnKH.add(btnSelect);
        pnOrder.add(pnOrderDetail);
        pnOrderDetail.add(jScorllPanePNH);
        pnOrderDetail.add(pnPNHBtn);pnPNHBtn.add(lbPNHSoluong);pnPNHBtn.add(txtPNHSoluong);pnPNHBtn.add(lbPNHDongianhap);pnPNHBtn.add(txtPNHDongianhap);pnPNHBtn.add(btnEdit);pnPNHBtn.add(btnDel);
        pnOrder.add(pnBtn);
        pnBtn.setLayout(new GridLayout(10,2));
        pnBtn.add(lbTongtien);pnBtn.add(txtTongtien);
        pnBtn.add(lbGiamgia);pnBtn.add(txtGiamgia);
        pnBtn.add(lbThanhtoan);pnBtn.add(txtThanhtoan);
        pnBtn.add(btnOrder);
        add(pnCtn);
        add(lbTitleOrder);
        add(pnOrder);

        pnInfo.add(pnLbImg);
        pnInfo.add(pnInfoSP);

        pnInfo.add(pnAddBtn);
        pnLbImg.add(lbimg);
        img=ImageIO.read(this.getClass().getResource("images/sanpham/product.png"));
        lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 140, 140)));
        pnInfoSP.setLayout(new GridLayout(8,1));
        pnInfoSP.add(lbTenspNhap);pnInfoSP.add(txtTenspNhap);
        pnInfoSP.add(lbDongia); pnInfoSP.add(txtDongia);
        pnInfoSP.add(lbDongianhap);pnInfoSP.add(txtDongianhap);
        pnInfoSP.add(lbSoluong);pnInfoSP.add(txtSoluong);
        pnInfo.add(btnAdd);

        {
            lbTitleOrder.setPreferredSize(new Dimension(1520,80));
            lbTitleOrder.setVerticalAlignment(SwingConstants.CENTER);
            jScrollPaneSP.setViewportView(tblDSSP);
            jScrollPaneSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneSP.setPreferredSize(new Dimension(650, 400));
            jScorllPanePNH.setViewportView(tblPNH);
            jScorllPanePNH.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScorllPanePNH.setPreferredSize(new Dimension(700, 330));
            jScrollPaneDM.setViewportView(tblDSDM);
            jScrollPaneDM.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneDM.setPreferredSize(new Dimension(400, 400));
            pnInfo.setPreferredSize(new Dimension(400, 400));
            pnLbImg.setPreferredSize(new Dimension(400, 150));
            pnInfoSP.setPreferredSize(new Dimension(400,250));
            pnInfoSP.setBackground(NewColor.background);
            pnLbImg.setBackground(NewColor.background);
            pnDM.setPreferredSize(new Dimension(400,70));
            pnPNHBtn.setPreferredSize(new Dimension(650,50));
            pnPNHBtn.setBackground(NewColor.background);

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
            pnKH.setPreferredSize(new Dimension(350, 400));
            pnKH.setBackground(NewColor.background);
            pnOrderDetail.setPreferredSize(new Dimension(700, 400));
            pnBtn.setPreferredSize(new Dimension(400,200));
            pnBtn.setBackground(NewColor.background);
            pnOrderDetail.setBackground(NewColor.background);
            lbTitleOrder.setForeground(new Color(54, 38, 90));
            lbTitleOrder.setFont(new Font("Segoe UI", 1, 20));
            tblDSSP.setFont(new Font("Segoe UI", 0, 16));
            tblPNH.setFont(new Font("Segoe UI", 0, 16));
            tblDSDM.setFont(new Font("Segoe UI", 0, 16));
            pnInfo.setBackground(NewColor.background);
            txtTenNcc.setEditable(false);
            txtSDTNcc.setEditable(false);
            txtMancc.setEditable(false);
            txtTongtien.setEditable(false);
            txtGiamgia.setEditable(false);
            txtThanhtoan.setEditable(false);
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
        NhacungcapDTO ncc=Select_Nhacungcap.ncc;
        if (ncc.getMancc()=='\0')
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhà cung cấp!");
        else {
                if(listCTPNH.size()==0)
                    JOptionPane.showMessageDialog(null, "Vui lòng thêm ít nhất một sản phẩm!");
                else {
                    int res = JOptionPane.showConfirmDialog(null, "Xác nhận việc phiếu nhập hàng này?", "Message", JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        PhieunhaphangDTO pnh = new PhieunhaphangDTO('\0', Dangnhap.taikhoan.getMa(), Calendar.getInstance().getTime(), ncc.getMancc(), tongtien);
                        phieunhaphangBLL.addPNH(pnh);
                        ctPhieunhaphangBLL.addListCTPNH(listCTPNH);
                        phieunhaphangBLL.updateSoluong(listCTPNH);
                        JOptionPane.showMessageDialog(null, "Đặt nhập hàng thành công!");
                        new ExportToPDF().exportToPDFPhieunhaphang(modelPNH,listCTPNH,pnh,ncc);
                        afterOrder();
                    }
                }
        }
    }
    public void afterOrder(){
        tongtien=0;
        giamgia=0;
        modelPNH.setNumRows(0);
        QL_Sanpham.listSP=new ArrayList<>();
        sanphamBLL.getListSanpham();
        listsearchsp=QL_Sanpham.listSP;
        modelSP.setNumRows(0);
        showTableSP(modelSP,listsearchsp);
        listOD=new ArrayList<>();
        txtTongtien.setText("");
        txtGiamgia.setText("");
        txtThanhtoan.setText("");
        txtTenNcc.setText("");
        txtMancc.setText("");
        txtSDTNcc.setText("");
        listCTPNH=new ArrayList<>();
        txtTenspNhap.setText("");
        txtDongia.setText("");
        txtDongianhap.setText("");
        txtSoluong.setText("1");
        txtPNHDongianhap.setText("");
        txtPNHSoluong.setText("");
    }

    private void btnDelActionPerformed(ActionEvent actionEvent) {
        int index=tblPNH.getSelectedRow();
        if(index==-1)
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm!");
        else {
            listCTPNH.remove(index);
            modelPNH.removeRow(index);
            for (int i = 0; i < modelPNH.getRowCount(); i++) {
                modelPNH.setValueAt(i+1,i,0);
            }
            setTongtien();
        }
    }

    private void btnEditActionPerformed(ActionEvent actionEvent) {

        int index=tblPNH.getSelectedRow();
        if(index==-1)
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm!");
        else {
            String soluong=txtPNHSoluong.getText();
            String dongianhap=txtPNHDongianhap.getText();
            if(soluong.equals("")&&dongianhap.equals(""))
                JOptionPane.showMessageDialog(null, "Vui lòng nhập một trường để sửa!");
            else {
                Pattern pattern = Pattern.compile("\\d*");
                Matcher matcher = pattern.matcher(soluong);
                if(soluong.equals("")){
                    if(!pattern.matcher(dongianhap).matches())
                        JOptionPane.showMessageDialog(null, "Đơn giá nhập phải là số");
                    else {
                        listCTPNH.get(tblPNH.getSelectedRow()).setDongianhap(Long.parseLong(dongianhap));
                    }
                }else {
                if(dongianhap.equals("")){
                    if(!pattern.matcher(soluong).matches())
                        JOptionPane.showMessageDialog(null, "Số lượng phải là số");
                    else {
                        listCTPNH.get(tblPNH.getSelectedRow()).setSoluong(Integer.parseInt(soluong));
                    }
                }else {
                    if(pattern.matcher(soluong).matches()&&pattern.matcher(dongianhap).matches()){
                        listCTPNH.get(tblPNH.getSelectedRow()).setSoluong(Integer.parseInt(soluong));
                        listCTPNH.get(tblPNH.getSelectedRow()).setDongianhap(Long.parseLong(dongianhap));
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Số lượng và đơn giá nhập phải là số");
                }}
                setRow(modelPNH,listCTPNH.get(tblPNH.getSelectedRow()),tblPNH.getSelectedRow());
                setTongtien();
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
                listsearchsp=new ArrayList<>();
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
        String tensp=refresh(txtTenspNhap.getText());
        String dongianhap=txtDongianhap.getText();
        String soluong=txtSoluong.getText();
        if(tensp.equals("")||dongianhap.equals("")||soluong.equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hoặc nhập đầy đủ thông tin!");
        }else {
            Pattern pattern = Pattern.compile("\\d*");
            Matcher matcher = pattern.matcher(soluong);
            if(!pattern.matcher(soluong).matches()||!pattern.matcher(dongianhap).matches()){
                JOptionPane.showMessageDialog(null, "Số lượng, đơn giá phải là số!");
            }else {
                int index=checksp(tensp);
                if(index==-1) {
                    String dongiaban=txtDongia.getText();
                    if(dongiaban.equals(""))
                        JOptionPane.showMessageDialog(null, "Vì tên sản phẩm bạn nhập là sản phảm mới nên bạn phải nhập đơn giá bán!");
                    else {
                        if(!pattern.matcher(dongiaban).matches())
                            JOptionPane.showMessageDialog(null, "Số lượng, đơn giá phải là số!");
                        else {
                            if(tblDSDM.getSelectedRow()==-1){
                                JOptionPane.showMessageDialog(null, "Vì sản phẩm bạn nhập là sản phẩm mới nên bạn phải chọn danh mục!");
                            }else {
                                int i=checklistPNH(tensp);
                                if(i==-1) {
                                    CTPhieunhaphangDTO c = new CTPhieunhaphangDTO();
                                    c.setTensp(tensp);
                                    c.setSoluong(Integer.parseInt(soluong));
                                    c.setDongia(Long.parseLong(dongiaban));
                                    c.setDongianhap(Long.parseLong(dongianhap));
                                    c.setMaloai(QL_Sanpham.listDM.get(tblDSDM.getSelectedRow()).getMaloai());
                                    c.setCheck(1);
                                    listCTPNH.add(0, c);
                                    setTongtien();
                                    JOptionPane.showMessageDialog(null, "Vì sản phẩm bạn nhập là sản phẩm mới nên khi xác nhận hóa đơn sẽ tự động thêm vào danh sách sản phẩm!");

                                }
                                else {
                                    listCTPNH.get(i).setSoluong( listCTPNH.get(i).getSoluong()+Integer.parseInt(soluong));
                                    listCTPNH.get(i).setDongianhap(Long.parseLong(dongianhap));
                                    setRow(modelPNH,listCTPNH.get(i),i);
                                    setTongtien();
                                }
                            }
                        }
                    }
                }
                else {
                    int i=checklistPNH(tensp);
                    if(i==-1) {
                        CTPhieunhaphangDTO c = new CTPhieunhaphangDTO();
                        SanphamDTO sp = QL_Sanpham.listSP.get(index);
                        c.setMasp(sp.getMasp());
                        c.setTensp(tensp);
                        c.setSoluong(Integer.parseInt(soluong));
                        c.setDongianhap(Long.parseLong(dongianhap));
                        c.setCheck(0);
                        c.setMaloai(sp.getMaloai());
                        listCTPNH.add(0, c);
                        setTongtien();
                    }
                    else {
                        listCTPNH.get(i).setSoluong( listCTPNH.get(i).getSoluong()+Integer.parseInt(soluong));
                        listCTPNH.get(i).setDongianhap(Long.parseLong(dongianhap));
                        setRow(modelPNH,listCTPNH.get(i),i);
                        setTongtien();
                    }
                }
            }
            modelPNH.setNumRows(0);
            showTablePNH(modelPNH,listCTPNH);
        }


            //... implementation hire

    }
    private int checksp(String str){
        int i=-1;
        for (SanphamDTO s:QL_Sanpham.listSP) {
            i++;
            if(str.toLowerCase().equals(refresh(s.getTensp()).toLowerCase())) return i;
        }
        return -1;
    }
    private int checklistPNH(String str){
        int i=-1;
        for (CTPhieunhaphangDTO s:listCTPNH) {
            i++;
            if(str.toLowerCase().equals(s.getTensp().toLowerCase())) return i;
        }
        return -1;
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
    private void setTongtien(){
        tongtien(listCTPNH);
        txtTongtien.setText(currencyVN.format(tongtien));
        txtThanhtoan.setText(currencyVN.format(tongtien-giamgia));
    }

    private void tongtien(ArrayList<CTPhieunhaphangDTO>list){
        tongtien=0;
        for (CTPhieunhaphangDTO sp:list
        ) {
            tongtien+=sp.getSoluong()*sp.getDongianhap();
        }
    }

    private void setBtnSelectActionPerformed(ActionEvent evt){
        while(check==0) {
            mf = new Select_Nhacungcap();
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
    private JPanel pnInfo=new JPanel();
    private JPanel pnSearch=new JPanel();
    private JPanel pnAddBtn=new JPanel();
    private JPanel pnOrder=new JPanel();
    private JPanel pnKH=new JPanel();
    private JPanel pnOrderDetail=new JPanel();
    private JPanel pnBtn=new JPanel();
    private JPanel pnInfoSP=new JPanel();

    private JTable tblDSSP;
    private JTable tblDSDM;
    private JTable tblPNH;
    private JLabel lbTitleOrder = new JLabel("ĐƠN NHẬP",SwingConstants.LEFT);
    private JLabel lbTensp = new LabelCustom("Tên sản phẩm");
    private JLabel lbSoluong =new LabelCustom("Số lượng");
    private JLabel lbDongianhap=new LabelCustom("Đơn giá nhập");
    private JLabel lbPNHSoluong=new LabelCustom("Điều chỉnh số lượng");
    private JLabel lbPNHDongianhap=new LabelCustom("Điều chỉnh đơn giá");
    private JLabel lbTenNcc=new LabelCustom("Tên nhà cung cấp");
    private JLabel lbSDT=new LabelCustom("Số điện thoại");
    private JLabel lbMancc=new LabelCustom("Mã nhà cung cấp");
    private JLabel lbTongtien=new LabelCustom("Tổng tiền");
    private JLabel lbGiamgia=new LabelCustom("Giảm giá");
    private JLabel lbThanhtoan=new LabelCustom("Thanh toán");
    private JLabel lbTenspNhap=new LabelCustom("Tên sản phẩm");
    private JLabel lbDongia=new LabelCustom("Đơn giá bán");
    private JTextField txtTenspNhap=new TextFieldCustom("");
    private JTextField txtDongia=new TextFieldCustom("");
    private JTextField txtTensp=new JTextField("",15);
    public static JTextField txtTenNcc=new TextFieldCustom("");
    public static JTextField txtSDTNcc=new TextFieldCustom("");
    public static JTextField txtMancc =new TextFieldCustom("");
    private JTextField txtTongtien=new TextFieldCustom("");
    private JTextField txtGiamgia=new TextFieldCustom("");
    private JTextField txtThanhtoan=new TextFieldCustom("");
    private JTextField txtPNHDongianhap=new JTextField("",10);

    private JScrollPane jScrollPaneSP;
    private JScrollPane jScrollPaneDM;
    private JScrollPane jScorllPanePNH;
    private JButton btnAdd=new JButton("Thêm");
    private JButton btnFilter=new JButton("Tìm");
    private JButton btnSelect=new JButton("Chọn trong danh sách");
    private JButton btnDel=new JButton("Xóa");
    private JButton btnEdit=new JButton("Sửa");
    private JButton btnOrder=new JButton("Đặt hàng");
    private JPanel  pnBtDM=new JPanel();
    private JPanel pnDM=new JPanel();
    private JPanel pnPNHBtn=new JPanel();


    private JTextField txtSoluong=new TextFieldCustom("1");
    private JTextField txtPNHSoluong =new JTextField("",5);
    private JTextField txtDongianhap=new TextFieldCustom("");



    private void showInputSP(SanphamDTO sp) throws IOException{

        int z=1;
        for (DanhmucDTO s: QL_Sanpham.listDM) {
            if(sp.getMaloai()==s.getMaloai()) break;
            z++;
        }
        tblDSDM.setRowSelectionInterval(z,z);

        File dir = new File(new File("").getAbsolutePath()+"\\src\\GUI\\"+sp.getAnhsp());
        if(!dir.exists())
            img=ImageIO.read(this.getClass().getResource("images/sanpham/product.png"));
        else{
            URL url = getClass().getResource(sp.getAnhsp());
            img = ImageIO.read(dir);
        }
        lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 140, 140)));
        txtTenspNhap.setText(sp.getTensp());
        txtDongia.setText("");
    }


    public static void showKH(KhachhangDTO kh){
        txtTenNcc.setText(kh.getHo()+" "+kh.getTen());
        txtSDTNcc.setText(kh.getSdt());
        txtMancc.setText(kh.getDiachi());
    }
    private int searchSP(ArrayList<ChitietHoadonDTO>list,int masp ){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMasp()==masp)
                return i;
        }
        return -1;
    }
}
