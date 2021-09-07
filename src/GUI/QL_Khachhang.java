package GUI;

import BLL.KhachhangBLL;
import BLL.NhanvienBLL;
import DAL.KhachhangDAL;
import DTO.KhachhangDTO;
import DTO.NhanvienDTO;
import GUI.Components.*;
import utils.CheckInput;
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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class QL_Khachhang extends JPanel {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private KhachhangBLL khachhangBLL= new KhachhangBLL();
    private KhachhangDAL khachhangDAL=new KhachhangDAL();
    private NhanvienBLL nhanvienBLL = new NhanvienBLL();
    private DefaultTableModel model = new DefaultTableModel();
    private int index='\0';
    private int checkundo='\0';
    private int checksearch=0;
    private KhachhangDTO khtemp=new KhachhangDTO();
    public static ArrayList<KhachhangDTO> listKH=new ArrayList<>();
    private ArrayList<KhachhangDTO> listsearch=new ArrayList<>();
    private CheckInput ck=new CheckInput();
    private int[] columnspWidth = {
            100, 130, 200, 150, 100, 200, 280,200, 140
    };
    private BufferedImage wPic;
    private JLabel wIcon;
    public QL_Khachhang() throws IOException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(NewColor.background);

        initComponents();

    }
    public void addRow(KhachhangDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMa(), s.getHo(), s.getTen(), s.getGioitinh(), formatter.format(s.getDob()),
                s.getDiachi(), s.getSdt(), s.getDtl()});
    }

    public void setRow(DefaultTableModel model, KhachhangDTO khachhangDTO , int index){
        model.setValueAt(khachhangDTO.getMa(),index,1);
        model.setValueAt(khachhangDTO.getHo(),index,2);
        model.setValueAt(khachhangDTO.getTen(),index,3);
        model.setValueAt(khachhangDTO.getGioitinh(),index,4);
        model.setValueAt(formatshow.format(khachhangDTO.getDob()),index,5);
        model.setValueAt(khachhangDTO.getDiachi(),index,6);
        model.setValueAt(khachhangDTO.getSdt(),index,7);
        model.setValueAt(khachhangDTO.getDtl(),index,8);
    }
    public void showTable(DefaultTableModel model, ArrayList<KhachhangDTO> list ) {
        int i = 1;
        for (KhachhangDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMa(), s.getHo(), s.getTen(), s.getGioitinh(), formatter.format(s.getDob()),
                    s.getDiachi(), s.getSdt(), s.getDtl()
            });
        }
    }
    private void initComponents() throws IOException {
        gbc.fill = 1;
        gbc.insets = (new Insets(20, 0, 0, 20));

        //table KH
        tblKH = new JTable();
        tblKH.setRowHeight(40);
        tblKH.setModel(model);
        model.setColumnIdentifiers(new Object[]{
                "STT", "Mã khách hàng", "Họ, tên đệm", "Tên", "Giới tính",
                "Ngày sinh", "Địa chỉ", "Di động", "Điểm tích lũy"
        });
        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblKH.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPane = new JScrollPane(tblKH);
        tblKH.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                KhachhangDTO nv = new KhachhangDTO();
                if(checksearch==1){
                    nv = listsearch.get(tblKH.getSelectedRow());
                }else nv = listKH.get(tblKH.getSelectedRow());
                  showInput(nv);
            }
        });
        //button
        btnAdd.addActionListener(evt -> {
            try {
                btnAddActionPerformed(evt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        btnUpdate.addActionListener(evt -> {
            try {
                btnUpdateActionPerformed(evt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            });
        btnDel.addActionListener(this::btnDelActionPerformed);
        btnRead.addActionListener(this::btnReadActionPerformed);
        btnRetype.addActionListener(this::btnRetypeActionPerformed);
        btnUndo.addActionListener(this::btnUndoActionPerformed);
        btnSearch.addActionListener(evt -> {
            try {
                btnSearchActionPerformed(evt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        //thiet lap giao dien
        JPanel pnNgaysinh = new JPanel();
        pnNgaysinh.setBackground(NewColor.background);
        JPanel pnGioitinh = new JPanel();
        pnGioitinh.setBackground(NewColor.background);
        bg.add(rbSex0);
        bg.add(rbSex1);
        bg.add(rbSex2);
        pnGioitinh.add(rbSex1);
        pnGioitinh.add(rbSex2);
        pnGioitinh.add(rbSex0);
        pnNgaysinh.add(cmbNgay);
        pnNgaysinh.add(cmbThang);
        pnNgaysinh.add(cmbNam);
        pnCtnC = new JPanel(gblayout);
        {
            gbc.gridy = 0;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbManv, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(txtManv, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbHo, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(txtHo, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbTen, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(txtTen, gbc);
            gbc.gridy = 1;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbManvE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbHoE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 6;pnCtnC.add(lbTenE, gbc);
            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbGioitinh, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(pnGioitinh, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbNgaysinh, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(pnNgaysinh, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbDiachi, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(txtDiachi, gbc);
            gbc.gridy = 3;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbGioitinhE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbNgaysinhE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 6;pnCtnC.add(lbDiachiE, gbc);
            gbc.gridy = 4;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbDtl, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(txtDtl, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbSdt, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(txtSdt, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbSearchDTL, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(txtSearchDTL, gbc);
            gbc.gridy = 5;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbDtlE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbSdtE, gbc);
        }
        pnCtn.setLayout(new BorderLayout());
        pnCtn.add(pnCtnE, BorderLayout.EAST);
        wPic= ImageIO.read(this.getClass().getResource("images/logo_pdf.png"));
        wIcon= new JLabel(new ImageIcon(wPic));
        pnCtnE.add(wIcon);
        pnCtn.add(pnCtnC, BorderLayout.CENTER);
        pnCtn.add(pnCtnS, BorderLayout.SOUTH);
        pnCtnS.add(btnAdd);
        pnCtnS.add(btnUpdate);
        pnCtnS.add(btnDel);
        pnCtnS.add(btnUndo);
        pnCtnS.add(btnRetype);
        pnCtnS.add(btnSearch);
        pnCtnS.add(btnRead);
        add(lbTitle);
        add(pnCtn);
        add(jScrollPane);
        {

            jScrollPane.setViewportView(tblKH);
            jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPane.setPreferredSize(new Dimension(1500, 440));
            tblKH.setAutoCreateRowSorter(true);
            pnCtnE.setPreferredSize(new Dimension(350, 400));
            pnCtnC.setPreferredSize(new Dimension(1200, 400));
            pnCtnS.setPreferredSize(new Dimension(1620, 100));
            pnCtnS.setBackground(NewColor.background);
            pnCtnE.setBackground(NewColor.background);
            pnCtnC.setBackground(NewColor.background);
            pnCtn.setPreferredSize(new Dimension(1620, 500));
            pnCtn.setBackground(NewColor.background);
            lbTitle.setPreferredSize(new Dimension(1520,80));
            lbTitle.setForeground(new Color(54, 38, 90));
            lbTitle.setFont(new Font("Segoe UI", 1, 30));
            tblKH.setFont(new Font("Segoe UI", 0, 16));
            txtManv.setEditable(false);
        }

    }

    //thiet lap phuong thuc cac button
    private void btnReadActionPerformed(ActionEvent evt) {
        if (listKH.size()==0) khachhangBLL.getListKhachhang();
        model.setRowCount(0);
        showTable(model, listKH);
        checksearch=0;
        checkundo=0;
    }
    private void btnRetypeActionPerformed(ActionEvent evt) { retype(); }
    private void btnAddActionPerformed(ActionEvent evt) throws ParseException {
        if (checkInput()) {
            if (khachhangBLL.checkMaKH(txtManv.getText(), listKH)) {
                KhachhangDTO nv = getInput();
                if (khachhangBLL.addKH(nv)) {
                    addRow(nv, QL_Khachhang.listKH.size() - 1, model);
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    retype();
                } else
                    JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else {
                lbManvE.setText("Mã khách hàng không được trùng lặp");
                lbManvE.setForeground(Color.RED);
            }
        }
    }
    private void btnUpdateActionPerformed(ActionEvent evt) throws ParseException {
        ArrayList<KhachhangDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listKH;
        else listt=listsearch;
        index=tblKH.getSelectedRow();
        if (checkInput()) {
            KhachhangDTO nv = getInput();
            khtemp= listt.get(index);
            if (khachhangBLL.updateKhachhang(nv, index, checksearch)) {
                setRow(model,nv,index);
                JOptionPane.showMessageDialog(this, "Cập nhật liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                checkundo=1;
            } else
                JOptionPane.showMessageDialog(this, "Không thể sửa mã khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnDelActionPerformed(ActionEvent evt){
        ArrayList<KhachhangDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listKH;
        else listt=listsearch;
        index=tblKH.getSelectedRow();
        khtemp= listt.get(index);
        int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            if (khachhangBLL.delKhachhang(index)) {
                model.removeRow(index);
                for (int j = index; j < model.getRowCount(); j++) {
                    model.setValueAt(j + 1, j, 0);
                }
                checkundo = 1;
            } else
                JOptionPane.showMessageDialog(this, "Xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnUndoActionPerformed(ActionEvent evt){
        while(checkundo==1) {
            ArrayList<KhachhangDTO> list;
            if(checksearch==1){
                list=listsearch;
            }else list=listKH;
            if (khachhangBLL.undo(list,khtemp, index,checksearch) == 1) {
                setRow(model, khtemp, index);
            } else {
                addRow(khtemp, index, model);
                for (int j = index; j < model.getRowCount(); j++) {
                    model.setValueAt(j + 1, j, 0);
                }
            }
            tblKH.setRowSelectionInterval(index, index);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            checkundo = 0;
        }
    }
    private void btnSearchActionPerformed(ActionEvent evt) throws ParseException {
        KhachhangDTO nv=getInput();
        listsearch=khachhangBLL.search(nv,txtSearchDTL.getText());
        model.setRowCount(0);
        showTable(model, listsearch);
        checksearch=1;
    }
    private boolean checkInput(){
        if(checkTen(lbHoE,txtHo)
                &&checkTen(lbTenE,txtTen)
                &&checkGioitinh()
                &&checkngay(lbNgaysinhE,cmbNgay,cmbThang,cmbNam)
                &&checkString(lbDiachiE,txtDiachi)
                &&checkSDT()
                &&checkDTL()
            ) return true;
        return false;
    }
    //check input
    public boolean checkTen(JLabel lb, JTextField txt){
        lb.setFont(new Font("Segoe UI",2,13));lb.setForeground(Color.RED);
        if(txt.getText().equals("")){
            lb.setText("Không được để trống");
            return false;
        }else {
            if (ck.checkString(txt.getText())) {
                lb.setText("");
                String s = ck.refreshName(txt.getText());
                txt.setText(s);
                return true;
            } else lb.setText("Vui lòng nhập đúng...");
        }
        return false;
    }
    public boolean checkGioitinh(){
        lbGioitinhE.setFont(new Font("Segoe UI",2,13));lbGioitinhE.setForeground(Color.RED);
        if(rbSex0.isSelected()||rbSex1.isSelected()||rbSex2.isSelected()){
            lbGioitinhE.setText("");
            return true;
        }else lbGioitinhE.setText("Vui lòng chọn giới tính");
        return false;
    }
    public boolean checkngay(JLabel lb, JComboBox cmbd, JComboBox cmbm, JComboBox cmby){
        lb.setFont(new Font("Segoe UI",2,13));lb.setForeground(Color.RED);
        if(cmbd.getSelectedItem().toString()=="Ngày "||cmbm.getSelectedItem().toString()=="Tháng "||cmby.getSelectedItem().toString()=="Năm "){
            lb.setText("Vui lòng chọn đủ ngày tháng năm");
            return false;
        }else {
            String ngay=cmbd.getSelectedItem().toString();
            String thang=cmbm.getSelectedItem().toString();
            String nam=cmby.getSelectedItem().toString();
            String date=nam+"-"+thang+"-"+ngay;
            if(ck.checkDate(date)) {
                lb.setText("");
                return true;
            }else {
                lb.setText("Ngày tháng năm không đúng");
                return false;
            }
        }
    }
    public boolean checkString(JLabel lb, JTextField txt){
        lb.setFont(new Font("Segoe UI",2,13));lb.setForeground(Color.RED);
        if(txt.getText()==""){
            lb.setText("Không được để trống");
            return false;
        }else{
            lb.setText("");
            return true;
        }
    }
    public boolean checkSDT(){
        lbSdtE.setFont(new Font("Segoe UI",2,13));lbSdtE.setForeground(Color.RED);
        if(txtSdt.getText().equals("")){
            lbSdtE.setText("Số di động không được để trống");
            return false;
        }else {
            if (ck.checkPhoneNumber(txtSdt.getText())) {
                lbSdtE.setText(" ");
                return true;
            } else {
                lbSdtE.setText("Vui lòng nhập một số di động đúng đầu số (3,5,7,8,9) ");
                return false;
            }
        }
    }
    public boolean checkDTL(){
        lbDtlE.setFont(new Font("Segoe UI",2,13));
        lbDtlE.setForeground(Color.RED);
        if(txtDtl.getText()==""){
            lbDtlE.setText("Không được để trống");
            return false;
        } else{
            if(ck.checkInt(txtDtl.getText())){
                lbDtlE.setText("");
                return true;
            } else{
                lbDtlE.setText("Dữ liệu phải là số");
                return false;
            }
        }
    }
    private void retype(){
        txtManv.setText(""); lbManvE.setText(" ");
        txtTen.setText("");lbTenE.setText(" ");
        txtHo.setText("");lbHoE.setText(" ");
        txtDiachi.setText(""); lbDiachiE.setText(" ");
        txtSdt.setText(""); lbSdtE.setText(" ");
        lbGioitinhE.setText(" ");
        cmbNgay.setSelectedIndex(0); lbNgaysinhE.setText("");
        cmbThang.setSelectedIndex(0);
        cmbNam.setSelectedIndex(0);
        bg.clearSelection();
        txtDtl.setText("");
        lbDtlE.setText(" ");
    }
    private KhachhangDTO getInput() throws ParseException {
        KhachhangDTO kh=new KhachhangDTO();
        if(!txtManv.getText().equals("")) kh.setMa(txtManv.getText());
        else kh.setMa("-1");
        kh.setHo(txtHo.getText());
        kh.setTen(txtTen.getText());
        if (rbSex1.isSelected()) kh.setGioitinh("Nam");
        else if (rbSex2.isSelected()) kh.setGioitinh("Nữ"); else if (rbSex0.isSelected()) kh.setGioitinh("Khác");
        int ngay=0;
        int thang=0;
        int nam=0;
        if(cmbNam.getSelectedItem().toString()!="Năm ") nam=Integer.parseInt(cmbNam.getSelectedItem().toString());
        if(cmbThang.getSelectedItem().toString()!="Tháng ") thang=Integer.parseInt(cmbThang.getSelectedItem().toString());
        if(cmbNgay.getSelectedItem().toString()!="Ngày ") ngay=Integer.parseInt(cmbNgay.getSelectedItem().toString());
        kh.setDob(formatter.parse(String.valueOf(nam)+"-"+String.valueOf(thang)+"-"+String.valueOf(ngay)));
        kh.setDiachi(txtDiachi.getText());
        kh.setSdt(txtSdt.getText());
        if(txtDtl.getText().matches("^\\d+$")) kh.setDtl(Integer.parseInt(txtDtl.getText()));
        else kh.setDtl(-1);
        return kh;
    }
    private void showInput(KhachhangDTO kh){ LocalDate localDate=LocalDate.now();
        txtManv.setText(kh.getMa());
        txtHo.setText(kh.getHo());
        txtTen.setText(kh.getTen());
        if(kh.getGioitinh().equals("Nam")) rbSex1.setSelected(true); else if(kh.getGioitinh().equals("Nữ")) rbSex2.setSelected(true);else rbSex0.setSelected(true);
        String temp[]=new String[3];
        temp = formatter.format(kh.getDob()).split("-");
        cmbNgay.setSelectedIndex(Integer.parseInt(temp[2]));
        cmbThang.setSelectedIndex(Integer.parseInt(temp[1]));
        cmbNam.setSelectedIndex(localDate.getYear()+1-Integer.parseInt(temp[0]));
        txtSdt.setText(kh.getSdt());
        txtDiachi.setText(kh.getDiachi());
        txtDtl.setText(String.valueOf(kh.getDtl()));
    }
    //khai báo
    private JLabel lbSearchDTL=new LabelCustom("Tìm kiếm ĐTL: >,=,<");
    private JTextField txtSearchDTL=new TextFieldCustom("");
    private JPanel pnCtn = new JPanel();
    private JPanel pnCtnC = new JPanel();
    private JPanel pnCtnE = new JPanel();
    private JPanel pnCtnS = new JPanel();
    private JTable tblKH;
    private JLabel lbTitle = new JLabel("QUẢN LÍ KHÁCH HÀNG",SwingConstants.LEFT);
    private JLabel lbManv = new LabelCustom("Mã khách hàng");
    private JLabel lbDtl = new LabelCustom("Điểm tích lũy");
    private JLabel lbHo = new LabelCustom("Họ, tên đệm");
    private JLabel lbTen = new LabelCustom("Tên");
    private JLabel lbGioitinh = new LabelCustom("Giới tính");
    private JLabel lbNgaysinh = new LabelCustom("Ngày sinh");
    private JLabel lbDiachi = new LabelCustom("Địa chỉ");
    private JLabel lbSdt = new LabelCustom("Di động");
    private JLabel lbManvE = new LabelCustom(" ");
    private JLabel lbDtlE = new LabelCustom(" ");
    private JLabel lbHoE = new LabelCustom(" ");
    private JLabel lbTenE = new LabelCustom(" ");
    private JLabel lbGioitinhE = new LabelCustom(" ");
    private JLabel lbNgaysinhE = new LabelCustom(" ");
    private JLabel lbDiachiE = new LabelCustom(" ");
    private JLabel lbSdtE = new LabelCustom(" ");
    private JTextField txtManv = new TextFieldCustom("");
    private JTextField txtDtl = new TextFieldCustom("");
    private JTextField txtHo = new TextFieldCustom("");
    private JTextField txtTen = new TextFieldCustom("");
    private JTextField txtDiachi = new TextFieldCustom("");
    private JTextField txtSdt = new TextFieldCustom("");
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rbSex1 = new RadioButtonCustom("Nam");
    private JRadioButton rbSex2 = new RadioButtonCustom("Nữ");
    private JRadioButton rbSex0 = new RadioButtonCustom("Khác");
    private CmbDate cmbDate = new CmbDate();
    private JComboBox<String> cmbNgay = cmbDate.ngay();
    private JComboBox<String> cmbThang = cmbDate.thang();
    private JComboBox<String> cmbNam = cmbDate.nam();
    private JScrollPane jScrollPane;
    private JButton btnAdd = new ButtonFunction("Thêm");
    private JButton btnUpdate = new ButtonFunction("Sửa");
    private JButton btnDel = new ButtonFunction("Xóa");
    private JButton btnUndo = new ButtonFunction("Hoàn tác");
    private JButton btnSearch = new ButtonFunction("Tìm kiếm");
    private JButton btnRetype = new ButtonFunction("Nhập lại");
    private JButton btnRead = new ButtonFunction("Đọc từ server");
}
