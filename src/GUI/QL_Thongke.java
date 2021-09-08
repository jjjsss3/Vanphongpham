package GUI;

import BLL.DanhmucBLL;
import DAL.MySQLConnect;
import DTO.DanhmucDTO;
import GUI.Components.ButtonFunction;
import GUI.Components.DatePicker;
import GUI.Components.LabelCustom;
import GUI.Components.TextFieldCustom;
import com.itextpdf.text.DocumentException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import utils.ExportToPDF;
import utils.ImageFit;
import utils.NewColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class QL_Thongke extends JPanel {
    private DanhmucBLL danhmucBLL=new DanhmucBLL();
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;
    private ArrayList<String> list_nam;
    private int check=0;
    public static int z=0;
    public QL_Thongke() throws IOException, SQLException {
        if(conn==null){
            conn=con.getConn();
        }
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(NewColor.background);
        initComponents();

    }
//    public void showTable(DefaultTableModel model, ArrayList<NhanvienDTO> list ) {
//        model.setRowCount(0);
//        int i = 1;
//        for (NhanvienDTO s : list) {
//            model.addRow(new Object[]{
//                    i++, s.getMa(), s.getHo(), s.getTen(), s.getGioitinh(), formatshow.format(s.getDob()),
//                    s.getDiachi(), formatshow.format(s.getNgayvaolam()), s.getSdt(), s.getMachucvu(), s.getMatkhau()
//            });
//        }
//    }
    private void initComponents() throws SQLException, IOException {

        if (QL_Sanpham.listDM.size() == 0) danhmucBLL.getListDanhmuc();
        list_nam =new ArrayList<>();
        list_nam = getYear();
        cmbLoai.addItem("Tất cả");
        for (DanhmucDTO dm: QL_Sanpham.listDM
             ) {
            cmbLoai.addItem(dm.getTenloai());
        }
        for (String s: list_nam
        ) {
            cmbYear.addItem(s);
        }

        cpLine=new ChartPanel(lineChart(), true);
        cpBar=new ChartPanel(barChart(list_nam.get(0)),true);

        //table san pham
        tblSP = new JTable();
        tblSP.setRowHeight(40);
        tblSP.setModel(modelSp);
        modelSp.setColumnIdentifiers(new Object[]{
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng đã bán đượng", "Tổng tiền bán được"
        });

        int i = 0;
        for (int width : colModelSP) {
            TableColumn column = tblSP.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jpSP = new JScrollPane(tblSP);
        //
        cmbYear.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cpBar.setChart(barChart(cmbYear.getSelectedItem().toString()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        cmbLoai.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql;
                String timeS=txtDate1.getText();
                String timeE=txtDate2.getText();
                String para="";
                if(!timeS.equals("")) para+=" and h.ngay>='"+timeS+"' ";
                if(!timeE.equals("")) para+=" and h.ngay<='"+timeE+"' ";
                String order="";
                if(cmbOrder.getSelectedIndex()==0) order=" order by tien desc";
                if(cmbOrder.getSelectedIndex()==1) order=" order by tien asc";
                if(cmbOrder.getSelectedIndex()==2) order=" order by sl desc";
                if(cmbOrder.getSelectedIndex()==3) order=" order by sl asc";
                if(cmbLoai.getSelectedIndex()==0){
                    sql="SELECT c.masp,s.tensp, SUM(c.soluong) sl, SUM(c.soluong*(s.dongia-c.khuyenmai)) tien from chitiethoadon c , sanpham s, hoadon h WHERE c.mahd=h.mahd and c.masp=s.masp ";
                    sql+=para+"GROUP by c.masp"+order +"";
                }
                else {
                    String tenloai=cmbLoai.getSelectedItem().toString();
                    para+=" and s.maloai=l.maloai and l.tenloai='"+tenloai+"' ";
                    sql="SELECT c.masp,s.tensp, SUM(c.soluong) sl, SUM(c.soluong*(s.dongia-c.khuyenmai)) tien from chitiethoadon c , sanpham s, hoadon h, loaihang l WHERE c.mahd=h.mahd and c.masp=s.masp";
                    sql+=para+" GROUP by c.masp"+order+ "";
                }
                Long tien=new Long(0);
                int soluong=0;
                int i=1;
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs=ps.executeQuery();
                    modelSp.setRowCount(0);
                    while (rs.next()){
                        modelSp.addRow(new Object[]{
                            i++,
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                currencyVN.format(rs.getBigDecimal(4)),
                        });
                        soluong+= rs.getInt(3);
                        tien+=Long.parseLong(rs.getBigDecimal(4).toString());
                    }
                } catch (Exception evt) {
                }
                txtTongSoluong.setText(String.valueOf(soluong));
                txtTongTien.setText(currencyVN.format((tien)));

            }
        });
        if(dp1==null) dp1=new DatePicker();
        txtDate1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                z=0;
                dp1.setVisible(true);
                dp1.setLocation(300, 590);
            }
        });
        txtDate2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                z=1;
                dp1.setVisible(true);
                dp1.setLocation(300, 590);
            }
        });
        add(lbTitle);
        add(pnChart);
        pnChart.add(cpLine);pnChart.add(pnBar);

        pnBar.add(cpBar);
        pnBar.add(lbBar);
        pnBar.add(cmbYear);

        pnLeft.add(pnLeft_head);
        pnLeft_head.add(pnLeft_headTime);
            pnLeft_headTime.add(lbDate);
            pnLeft_headTime.add(txtDate1);
            pnLeft_headTime.add(lbDate1);
            pnLeft_headTime.add(txtDate2);
        pnLeft_head.add(pnLeft_headSpace);
        pnLeft_head.add(cmbOrder);
        pnLeft_head.add(cmbLoai);
        pnLeft.add(jpSP);
        pnLeft.add(lbTongSoluong);
        pnLeft.add(txtTongSoluong);
        pnLeft.add(lbTongTien);
        pnLeft.add(txtTongTien);
        pnLeft.add(btnPDF);
        add(pnLeft);
        add(pnRight);

        btnPDF.addActionListener(actionEvent -> {
            try {
                btnPDFActionPerformed(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        });
        lbAnh1=new JLabel(new ImageIcon(new ImageFit().fitimage(ImageIO.read(this.getClass().getResource("images/icon/3money.png")), 120, 120)));
        lbAnh2=new JLabel(new ImageIcon(new ImageFit().fitimage(ImageIO.read(this.getClass().getResource("images/icon/3order.png")), 120, 120)));
        lbAnh3=new JLabel(new ImageIcon(new ImageFit().fitimage(ImageIO.read(this.getClass().getResource("images/icon/3customer.png")), 120, 120)));

        pnRight.add(pnRight1);
        pnRight.add(pnRight2);
        pnRight.add(pnRight3);
        pnRight1.add(lbAnh1);
        pnRight2.add(lbAnh2);
        pnRight3.add(lbAnh3);

        tblSP.setFont(new Font("Segoe UI", 0, 16));
        jpSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        cmbYear.setBackground(Color.WHITE);
        cmbYear.setFont(new Font("Segoe UI", 0, 18));
        cmbOrder.setBackground(Color.WHITE);
        cmbOrder.setFont(new Font("Segoe UI", 0, 17));
        cmbLoai.setBackground(Color.WHITE);
        cmbLoai.setFont(new Font("Segoe UI", 0, 17));
        cpLine.setPreferredSize(new Dimension(850,388));
        cpBar.setPreferredSize(new Dimension(650,350));
        pnBar.setPreferredSize(new Dimension(650,400));
        pnChart.setBackground(NewColor.background);
        pnBar.setBackground(NewColor.background);
        pnLeft_head.setBackground(NewColor.background);
        pnLeft_headTime.setBackground(NewColor.background);
        pnLeft_headSpace.setBackground(NewColor.background);
        pnLeft.setBackground(NewColor.background);
        pnRight1.setBackground(NewColor.background);
        pnRight2.setBackground(NewColor.background);
        pnRight3.setBackground(NewColor.background);
        pnRight.setBackground(NewColor.background);

        lbBar.setFont(new Font("Segoe UI",0,18));
        txtDate1.setFont(new Font("Segoe UI",0,15));
        txtDate2.setFont(new Font("Segoe UI",0,15));
        pnChart.setPreferredSize(new Dimension(1550,415));
        lbTitle.setPreferredSize(new Dimension(1550,60));
        pnLeft.setPreferredSize(new Dimension(850,530));
        jpSP.setPreferredSize(new Dimension(840, 400));
        pnLeft_head.setPreferredSize(new Dimension(850,50));
        pnLeft_headTime.setPreferredSize(new Dimension(330,40));
        pnLeft_headSpace.setPreferredSize(new Dimension(100,40));
        pnRight.setPreferredSize(new Dimension(650,528));
        pnRight1.setPreferredSize(new Dimension(650,176));
        pnRight2.setPreferredSize(new Dimension(650,176));
        pnRight3.setPreferredSize(new Dimension(650,176));
        lbTitle.setVerticalAlignment(SwingConstants.TOP);
        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 25));
        txtDate1.setEditable(false);
        txtDate2.setEditable(false);
        txtTongTien.setEditable(false);
        txtTongSoluong.setEditable(false);
        txtTongSoluong.setColumns(10);
    }

    private void btnPDFActionPerformed(ActionEvent actionEvent) throws IOException, DocumentException {
        if(modelSp.getRowCount()>0) {
            new ExportToPDF().exportToPDFThongKe(modelSp, txtDate1.getText(), txtDate2.getText(),
                    cmbOrder.getSelectedItem().toString(), cmbLoai.getSelectedItem().toString(),txtTongTien.getText(),txtTongSoluong.getText());
        }else JOptionPane.showMessageDialog(this, "Không có dữ liệu", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel pnRight1=new JPanel();
    private JPanel pnRight2=new JPanel();
    private JPanel pnRight3=new JPanel();
    private JLabel lbAnh1;
    private JLabel lbAnh2;
    private JLabel lbAnh3;
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    private JLabel lbTongTien=new LabelCustom("Tổng tiền");
    private JLabel lbTongSoluong=new LabelCustom("Tổng số lượng");
    private JTextField txtTongTien=new TextFieldCustom("");
    private JTextField txtTongSoluong=new TextFieldCustom("");
    private JButton btnPDF=new ButtonFunction("Xuất file PDF");
    private JScrollPane jpSP=new JScrollPane();
    private DefaultTableModel modelSp=new DefaultTableModel();
    private JTable tblSP=new JTable();
    private int[] colModelSP = {
            50, 90, 400, 150, 150
    };
    private JButton btnLeft=new JButton("Lọc");
    public static DatePicker dp1;
    public static JTextField txtDate1=new JTextField("",6);
    public static JTextField txtDate2=new JTextField("",6);
    private JPanel pnLeft =new JPanel();
    private JPanel pnLeft_head=new JPanel();
    private JPanel pnLeft_headTime=new JPanel();
    private JPanel pnLeft_headSpace=new JPanel();
    private JPanel pnRight=new JPanel();
    private JLabel lbDate=new LabelCustom("Thời gian từ ");
    private JLabel lbDate1=new LabelCustom(" đến ");
    private JLabel lbTitle = new JLabel("TỔNG HỢP THỐNG KÊ CỬA CỬA HÀNG VĂN PHÒNG PHẨM N1",SwingConstants.LEFT);
    private ChartPanel cpLine;
    private ChartPanel cpBar;
    private JComboBox<String> cmbOrder =new JComboBox<String>(new String[]{
            "Tổng tiền, giảm dần",
            "Tổng tiền, tăng dần",
            "Số lượng, giảm dần",
            "Số lượng, tăng dần"
    });
    private JComboBox<String> cmbLoai =new JComboBox<String>();
    private JComboBox<String> cmbYear =new JComboBox<String>();
    private JPanel pnChart=new JPanel();
    private JPanel pnBar=new JPanel();
    private JLabel lbBar=new LabelCustom("Chọn một năm");

    public JFreeChart barChart(String year) throws SQLException {
        String sql="SELECT v.tenloai, SUM(v.tien) from(SELECT loaihang.maloai,loaihang.tenloai,z.tien from loaihang left join (SELECT c.masp,(c.soluong*(s.dongia-c.khuyenmai)) tien,l.maloai  from chitiethoadon c, sanpham s, hoadon h, loaihang l where h.mahd=c.mahd and YEAR(h.ngay)=? and c.masp=s.masp AND s.maloai=l.maloai) as z  on loaihang.maloai=z.maloai) as v group by v.tenloai";        DefaultCategoryDataset data=data(sql,year);
        JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ DOANH THU CÁC LOẠI HÀNG NĂM "+year,
                "", "Doanh thu", data, PlotOrientation.HORIZONTAL,true,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.WHITE);
        plot.getDomainAxis().setLabelFont(new Font("Arial",Font.PLAIN,13));
        plot.getRangeAxis().setLabelFont(new Font("Arial",Font.PLAIN,13));
        DecimalFormat df1 = new DecimalFormat(",000 VND");
        plot.getRangeAxis().setStandardTickUnits(new NumberTickUnitSource(true,df1));
        return chart;
    }
    private DefaultCategoryDataset data(String sql, String year){
        DefaultCategoryDataset dcd=new DefaultCategoryDataset();
       try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,year);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                dcd.addValue(rs.getBigDecimal(2),"Loại hàng",rs.getString(1) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dcd;
    }
    public JFreeChart lineChart() throws SQLException {
        XYDataset xyDataset=createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("THỐNG KÊ DOANH THU THEO NĂM",
                "", "Doanh thu", xyDataset);
        XYPlot plot = chart.getXYPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.WHITE);
        plot.getDomainAxis().setLabelFont(new Font("Arial",Font.PLAIN,13));
        plot.getRangeAxis().setLabelFont(new Font("Arial",Font.PLAIN,13));
        DecimalFormat df1 = new DecimalFormat(",000 VND");
        plot.getRangeAxis().setStandardTickUnits(new NumberTickUnitSource(true,df1));
        return chart;
    }
    private XYDataset createDataset() throws SQLException {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries[] series=new XYSeries[list_nam.size()];
        int i=0;
        for (XYSeries s: series) {
            s=xySeries(list_nam.get(i++));
            dataset.addSeries(s);
        }
        return dataset;
    }
    private XYSeries xySeries(String year){
        XYSeries s=new XYSeries(year);
        String sql="SELECT MONTH(ngay), SUM(thanhtien) tien from hoadon where YEAR(ngay)=? GROUP by MONTH(ngay)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,year);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                s.add(rs.getInt(1),rs.getBigDecimal(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
    private ArrayList<String> getYear() throws SQLException {
        String sql = "SELECT YEAR(ngay) nam from hoadon GROUP by YEAR(ngay) order by YEAR(ngay) desc";
        ArrayList<String> nam = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            nam.add(rs.getString(1));
        }
        return nam;
    }
}
