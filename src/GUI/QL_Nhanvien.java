package GUI;


import BLL.NhanvienBLL;
import BLL.PhanQuyenBLL;
import DTO.DangnhapDTO;
import DTO.NhanvienDTO;
import GUI.Components.*;
import com.itextpdf.text.DocumentException;
import utils.CheckInput;
import utils.ExcelRW;
import utils.ExportToPDF;
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
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class QL_Nhanvien extends JPanel {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private NhanvienBLL nhanvienBLL = new NhanvienBLL();
    private PhanQuyenBLL phanQuyenBLL=new PhanQuyenBLL();
    private DefaultTableModel model = new DefaultTableModel();
    private int index='\0';
    private int checkundo='\0';
    private int checkList=0;
    private int checksearch=0;
    private NhanvienDTO nvtemp=new NhanvienDTO();
    public static ArrayList<NhanvienDTO> listNV=new ArrayList<>();
    private ArrayList<NhanvienDTO> listsearch=new ArrayList<>();
    private CheckInput ck=new CheckInput();
    private int[] columnspWidth = {
            50, 100, 150, 100, 80, 130, 320,130,130, 160,150
    };
    private ExcelRW excelRW=new ExcelRW();
    private ExportToPDF exportToPDF=new ExportToPDF();
    private BufferedImage wPic;
    private JLabel wIcon;
    public QL_Nhanvien() throws IOException, DocumentException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(NewColor.background);
        listNV=new ArrayList<>();
        if(QL_Chung.listChucvu.size()==0) phanQuyenBLL.getListChucvu();
        initComponents();
    }
    public void addRow(NhanvienDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMa(), s.getHo(), s.getTen(), s.getGioitinh(), formatshow.format(s.getDob()),
                s.getDiachi(), formatshow.format(s.getNgayvaolam()), s.getSdt(), s.getTenchucvu(), s.getMatkhau()
        });
    }
    public void setRow(DefaultTableModel model,NhanvienDTO nhanvienDTO ,int index){
        model.setValueAt(nhanvienDTO.getMa(),index,1);
        model.setValueAt(nhanvienDTO.getHo(),index,2);
        model.setValueAt(nhanvienDTO.getTen(),index,3);
        model.setValueAt(nhanvienDTO.getGioitinh(),index,4);
        model.setValueAt(formatshow.format(nhanvienDTO.getDob()),index,5);
        model.setValueAt(nhanvienDTO.getDiachi(),index,6);
        model.setValueAt(formatshow.format(nhanvienDTO.getNgayvaolam()),index,7);
        model.setValueAt(nhanvienDTO.getSdt(),index,8);
        model.setValueAt(nhanvienDTO.getTenchucvu(),index,9);
        model.setValueAt(nhanvienDTO.getMatkhau(),index,10);
    }
    public void showTable(DefaultTableModel model, ArrayList<NhanvienDTO> list ) {
        model.setRowCount(0);
        int i = 1;
        for (NhanvienDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMa(), s.getHo(), s.getTen(), s.getGioitinh(), formatshow.format(s.getDob()),
                    s.getDiachi(), formatshow.format(s.getNgayvaolam()), s.getSdt(), s.getTenchucvu(), s.getMatkhau()
            });
        }
    }
    private void initComponents() throws IOException {
        gbc.fill = 1;
        gbc.insets = (new Insets(20, 0, 0, 20));

        //table Danh sách nhân viên
        tblDSSV = new JTable();
        tblDSSV.setRowHeight(40);
        tblDSSV.setModel(model);
        model.setColumnIdentifiers(new Object[]{
                "STT", "Mã nhân viên", "Họ, tên đệm", "Tên", "Giới tính",
                "Ngày sinh", "Địa chỉ", "Ngày vào làm", "Di động", "Mã chức vụ", "Mật khẩu"
        });
        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblDSSV.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPane = new JScrollPane(tblDSSV);
        tblDSSV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NhanvienDTO nv=new NhanvienDTO();
                if(checksearch==1){
                    nv = listsearch.get(tblDSSV.getSelectedRow());
                }else nv = listNV.get(tblDSSV.getSelectedRow());
                showInput(nv);
            }
        });

        //Các thao tác với các nút
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
        btnExcel.addActionListener(this::btnExcelActionPerformed);
        btnPDF.addActionListener(this::btnPDFActionPerformed);
        //Cài đặt giao diện
        cmbRole.setModel(new DefaultComboBoxModel<String>(new String[]{"Chọn chức vụ"}));
        for (DangnhapDTO chucvu: QL_Chung.listChucvu
        ) {
            cmbRole.addItem(chucvu.getTenchucvu());
        }
        JPanel pnNgaysinh = new JPanel();pnNgaysinh.setBackground(NewColor.background);
        JPanel pnNgayvao = new JPanel();pnNgayvao.setBackground(NewColor.background);
        JPanel pnGioitinh = new JPanel();pnGioitinh.setBackground(NewColor.background);

        bg.add(rbSex0);
        bg.add(rbSex1);
        bg.add(rbSex2);
        pnGioitinh.add(rbSex1);
        pnGioitinh.add(rbSex2);
        pnGioitinh.add(rbSex0);
        pnNgaysinh.add(cmbNgay);
        pnNgaysinh.add(cmbThang);
        pnNgaysinh.add(cmbNam);
        pnNgayvao.add(cmbNgayvao);
        pnNgayvao.add(cmbThangvao);
        pnNgayvao.add(cmbNamvao);
        pnCtnC = new JPanel(gblayout);
        {
            gbc.gridy = 0;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbManv, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(txtManv, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbMatkhau, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(txtMatkhau, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbChucvu, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(cmbRole, gbc);
            gbc.gridy = 1;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbManvE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbMatkhauE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 6;pnCtnC.add(lbChucvuE, gbc);
            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbHo, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(txtHo, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbTen, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(txtTen, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbNgaysinh, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(pnNgaysinh, gbc);
            gbc.gridy = 3;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbHoE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbTenE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 6;pnCtnC.add(lbNgaysinhE, gbc);
            gbc.gridy = 4;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbGioitinh, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(pnGioitinh, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnC.add(lbSdt, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnC.add(txtSdt, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbNgayvaolam, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(pnNgayvao, gbc);
            gbc.gridy = 5;
            gbc.gridwidth = 3;gbc.gridx = 0;pnCtnC.add(lbGioitinhE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnCtnC.add(lbSdtE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 6;pnCtnC.add(lbNgayvaolamE, gbc);
            gbc.gridy=6;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnC.add(lbDiachi, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnC.add(txtDiachi, gbc);
            gbc.gridwidth = 1;gbc.gridx = 6;pnCtnC.add(lbSearchAge, gbc);
            gbc.gridwidth = 2;gbc.gridx = 7;pnCtnC.add(txtSearchAge, gbc);
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
        pnCtnS.add(btnExcel);
        pnCtnS.add(btnPDF);
        add(lbTitle);
        add(pnCtn);
        add(jScrollPane);
        // thiết lập thông số
        {
            lbTitle.setPreferredSize(new Dimension(1520,80));
            jScrollPane.setViewportView(tblDSSV);
            jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPane.setPreferredSize(new Dimension(1500, 440));
            pnCtnE.setPreferredSize(new Dimension(350, 400));
            pnCtnC.setPreferredSize(new Dimension(1200, 400));
            pnCtnS.setPreferredSize(new Dimension(1620, 100));
            pnCtnS.setBackground(NewColor.background);
            pnCtnE.setBackground(NewColor.background);
            pnCtnC.setBackground(NewColor.background);
            pnCtn.setPreferredSize(new Dimension(1620, 500));
            pnCtn.setBackground(NewColor.background);
            lbTitle.setForeground(new Color(54, 38, 90));
            lbTitle.setFont(new Font("Segoe UI", 1, 30));
            tblDSSV.setFont(new Font("Segoe UI", 0, 16));
            cmbRole.setBackground(Color.WHITE);
            cmbRole.setFont(new Font("Segoe UI", 0, 15));
            txtManv.setEditable(true);
        }
    }

    //thiết lập các button
    private void btnRetypeActionPerformed(ActionEvent evt) { retype(); }
    private void btnAddActionPerformed(ActionEvent evt) throws ParseException {
        if(checkManv(txtManv.getText())) {
            if (checkInput()) {
                NhanvienDTO nv = getInput();
                if (nhanvienBLL.addNhanvien(nv, checkList)) {
                    addRow(nv, listNV.size() - 1, model);
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    retype();
                } else
                    JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }else
            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    }
    private void btnUpdateActionPerformed(ActionEvent evt) throws ParseException {
        ArrayList<NhanvienDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listNV;else listt=listsearch;
        index = tblDSSV.getSelectedRow();
        if (checkInput()) {
            NhanvienDTO nv = getInput();
            nvtemp = listt.get(index);
            if (nhanvienBLL.updateNhanvien(nv,index,checkList,checksearch)) {
                setRow(model, nv, index);
                JOptionPane.showMessageDialog(this, "Cập nhật liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                checkundo = 1;
            } else
                JOptionPane.showMessageDialog(this, "Không thể sửa mã nhân viên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnDelActionPerformed(ActionEvent evt) {
        ArrayList<NhanvienDTO> listt = new ArrayList<>();
        if (checksearch == 0) listt = listNV;else listt = listsearch;
        index = tblDSSV.getSelectedRow();nvtemp = listt.get(index);
        int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            if (nhanvienBLL.delNhanvien(index, checkList)) {
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
            ArrayList<NhanvienDTO> list;
            if(checksearch==1){
                list=listsearch;
            }else list=listNV;
            if (nhanvienBLL.undo(list,nvtemp, index, checkList,checksearch) == 1) {
                setRow(model, nvtemp, index);
            } else {
                addRow(nvtemp, index, model);
                for (int j = index; j < model.getRowCount(); j++) {
                    model.setValueAt(j + 1, j, 0);
                }
            }
            tblDSSV.setRowSelectionInterval(index,index);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            checkundo = 0;
        }
    }
    private void btnSearchActionPerformed(ActionEvent evt) throws ParseException {
        NhanvienDTO nv=getInput();
        listsearch=nhanvienBLL.search(nv,txtSearchAge.getText());
        model.setRowCount(0);
        showTable(model, listsearch);
        checksearch=1;
    }
    private void btnReadActionPerformed(ActionEvent evt) {
        if(checkList==1) {
            listNV=new ArrayList<>();
            nhanvienBLL.getListNhanvien();
        }
        if (listNV.size()==0) nhanvienBLL.getListNhanvien();
        model.setRowCount(0);
        showTable(model, listNV);
        checksearch=0;
        checkList=0;
        checkundo=0;
    }
    private void btnExcelActionPerformed(ActionEvent actionEvent) {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setLocation(100,100);
        dialog.setVisible(true);
        String fileDirectory=dialog.getDirectory()+dialog.getFile();
        if(fileDirectory!=null&&(fileDirectory.endsWith("xlsx")||fileDirectory.endsWith("xls"))) {
            excelRW.getListNVfromExcel(fileDirectory);
            showTable(model,listNV);
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn file có định dạng Excel", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        checkList=1;
//        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//        jfc.setDialogTitle("Choose a directory to save your file: ");
//        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//
//        int returnValue = jfc.showSaveDialog(null);
//        if (returnValue == JFileChooser.APPROVE_OPTION) {
//            if (jfc.getSelectedFile().isDirectory()) {
//                System.out.println("You selected the directory: " + jfc.getSelectedFile());
//            }
//        }
    }
    private void btnPDFActionPerformed(ActionEvent actionEvent) {

        if(checksearch==1) exportToPDF.exportToPDFNhanvien(listsearch);
        else exportToPDF.exportToPDFNhanvien(listNV);
    }

    //khai báo

    private JLabel lbSearchAge=new LabelCustom("Tìm kiếm tuổi: >,=,<");
    private JTextField txtSearchAge=new TextFieldCustom("");
    private JPanel pnCtn = new JPanel();
    private JPanel pnCtnC = new JPanel();
    private JPanel pnCtnE = new JPanel();
    private JPanel pnCtnS = new JPanel();
    private JTable tblDSSV;
    private JLabel lbTitle = new JLabel("QUẢN LÍ NHÂN VIÊN",SwingConstants.LEFT);
    private JLabel lbManv = new LabelCustom("Mã nhân viên");
    private JLabel lbMatkhau = new LabelCustom("Mật khẩu");
    private JLabel lbChucvu = new LabelCustom("Chức vụ");
    private JLabel lbHo = new LabelCustom("Họ, tên đệm");
    private JLabel lbTen = new LabelCustom("Tên");
    private JLabel lbGioitinh = new LabelCustom("Giới tính");
    private JLabel lbNgaysinh = new LabelCustom("Ngày sinh");
    private JLabel lbDiachi = new LabelCustom("Địa chỉ");
    private JLabel lbNgayvaolam = new LabelCustom("Ngày vào làm");
    private JLabel lbSdt = new LabelCustom("Di động");
    private JLabel lbManvE = new LabelCustom(" ");
    private JLabel lbMatkhauE = new LabelCustom(" ");
    private JLabel lbChucvuE = new LabelCustom(" ");
    private JLabel lbHoE = new LabelCustom(" ");
    private JLabel lbTenE = new LabelCustom(" ");
    private JLabel lbGioitinhE = new LabelCustom(" ");
    private JLabel lbNgaysinhE = new LabelCustom(" ");
    private JLabel lbDiachiE = new LabelCustom(" ");
    private JLabel lbNgayvaolamE = new LabelCustom(" ");
    private JLabel lbSdtE = new LabelCustom(" ");
    private JTextField txtManv = new TextFieldCustom("");
    private JTextField txtMatkhau = new TextFieldCustom("");
    private JTextField txtHo = new TextFieldCustom("");
    private JTextField txtTen = new TextFieldCustom("");
    private JTextField txtDiachi = new TextFieldCustom("");
    private JTextField txtSdt = new TextFieldCustom("");
    private ButtonGroup bg = new ButtonGroup();
    private JRadioButton rbSex1 = new RadioButtonCustom("Nam");
    private JRadioButton rbSex2 = new RadioButtonCustom("Nữ");
    private JRadioButton rbSex0 = new RadioButtonCustom("Khác");
    private CmbDate cmbDate = new CmbDate();
    private JComboBox<String> cmbRole = new JComboBox<String>();
    private JComboBox<String> cmbNgay = cmbDate.ngay();
    private JComboBox<String> cmbThang = cmbDate.thang();
    private JComboBox<String> cmbNam = cmbDate.nam();
    private JComboBox<String> cmbNgayvao = cmbDate.ngay();
    private JComboBox<String> cmbThangvao = cmbDate.thang();
    private JComboBox<String> cmbNamvao = cmbDate.nam();
    private JScrollPane jScrollPane;
    private JButton btnAdd = new ButtonFunction("Thêm");
    private JButton btnUpdate = new ButtonFunction("Sửa");
    private JButton btnDel = new ButtonFunction("Xóa");
    private JButton btnUndo = new ButtonFunction("Hoàn tác");
    private JButton btnSearch = new ButtonFunction("Tìm kiếm");
    private JButton btnRetype = new ButtonFunction("Nhập lại");
    private JButton btnRead = new ButtonFunction("Đọc từ server");
    private JButton btnExcel= new ButtonFunction("Đọc file Excel");
    private JButton btnPDF = new ButtonFunction("Xuất file PDF");
    //kiểm tra toàn bộ dữ liệu
    private boolean checkInput  (){
        if(checkMa()&&checkMatkhau()
                &&checkTen(lbHoE,txtHo)
                &&checkTen(lbTenE,txtTen)
                &&checkngay(lbNgaysinhE,cmbNgay,cmbThang,cmbNam)
                &&checkGioitinh()
                &&checkString(lbDiachiE,txtDiachi)
                &&checkChucvu()
                &&checkSDT()
                &&checkngay(lbNgayvaolamE,cmbNgayvao,cmbThangvao,cmbNamvao)
            ) return true;
        return false;
    }
    // check input
    private boolean checkMa(){
        lbManvE.setFont(new Font("Segoe UI",2,13));lbManvE.setForeground(Color.RED);
        if(txtManv.getText().equals("")){
            lbManvE.setText("Mã nhân viên không được để trống");
            return false;
        }else
            if(!ck.checkMaID(txtManv.getText())) {
                lbManvE.setText("Mã nhân viên gồm ít nhất 3 kí tự, 1 chữ cái, 1 chữ số");
                return false;
            }else {
                    lbManvE.setText("");
                    return true;
            }
    }
    private boolean checkMatkhau(){
        lbMatkhauE.setFont(new Font("Segoe UI",2,13));lbMatkhauE.setForeground(Color.RED);
        if(txtMatkhau.getText().equals("")){
            lbMatkhauE.setText("Mật khẩu không được để trống");
            return false;
        }else {
            if (ck.checkMatkhau(txtMatkhau.getText())) {
                lbMatkhauE.setText("");
                return true;
            } else lbMatkhauE.setText("Mật khẩu gồm 4 kí tự, không chứa kí tự đặc biệt");
        }
        return false;
    }
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
    public boolean checkChucvu(){
        lbChucvuE.setFont(new Font("Segoe UI",2,13));lbChucvuE.setForeground(Color.RED);
        if(cmbRole.getSelectedItem().toString()=="Chọn chức vụ") {
            lbChucvuE.setText("Vui lòng chọn chức vụ");
            return false;
        } else{
            lbChucvuE.setText("");
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
    //reset các ô
    private void retype(){
        txtManv.setText(""); lbManvE.setText(" ");
        txtMatkhau.setText(""); lbMatkhauE.setText(" ");
        txtHo.setText("");lbHoE.setText(" ");
        txtTen.setText("");lbTenE.setText(" ");
        txtDiachi.setText(""); lbDiachiE.setText(" ");
        txtSdt.setText(""); lbSdtE.setText(" ");
        lbChucvuE.setText("");
        lbGioitinhE.setText(" ");
        cmbRole.setSelectedIndex(0);
        cmbNgay.setSelectedIndex(0); lbNgaysinhE.setText("");
        cmbThang.setSelectedIndex(0);
        cmbNam.setSelectedIndex(0);
        cmbNgayvao.setSelectedIndex(0); lbNgayvaolamE.setText("");
        cmbThangvao.setSelectedIndex(0);
        cmbNamvao.setSelectedIndex(0);
        cmbRole.setSelectedIndex(0);
        bg.clearSelection();
    }
    //lấy thông tin input
    private NhanvienDTO getInput() throws ParseException {
        NhanvienDTO nv=new NhanvienDTO();
        if(!txtManv.getText().equals("")) nv.setMa(txtManv.getText());
        nv.setMatkhau(txtMatkhau.getText());
        if (cmbRole.getSelectedItem().toString().equals("Chọn chức vụ")) nv.setMachucvu(-1);
        else {nv.setMachucvu(QL_Chung.listChucvu.get(cmbRole.getSelectedIndex()-1).getMachucvu());
        nv.setTenchucvu(QL_Chung.listChucvu.get(cmbRole.getSelectedIndex()-1).getTenchucvu());}
        nv.setHo(txtHo.getText());
        nv.setTen(txtTen.getText());
        if (rbSex1.isSelected()) nv.setGioitinh("Nam");
        else if (rbSex2.isSelected()) nv.setGioitinh("Nữ"); else if (rbSex0.isSelected()) nv.setGioitinh("Khác");
        int ngay=0;int thang=0;int nam=0;
            if(cmbNam.getSelectedItem().toString()!="Năm ") nam=Integer.parseInt(cmbNam.getSelectedItem().toString());
            if(cmbThang.getSelectedItem().toString()!="Tháng ") thang=Integer.parseInt(cmbThang.getSelectedItem().toString());
            if(cmbNgay.getSelectedItem().toString()!="Ngày ") ngay=Integer.parseInt(cmbNgay.getSelectedItem().toString());

        nv.setDob(formatter.parse(String.valueOf(nam)+"-"+String.valueOf(thang)+"-"+String.valueOf(ngay)));
        ngay=0;thang=0;nam=0;
        if(cmbNamvao.getSelectedItem().toString()!="Năm ") nam=Integer.parseInt(cmbNamvao.getSelectedItem().toString());
        if(cmbThangvao.getSelectedItem().toString()!="Tháng ") thang=Integer.parseInt(cmbThangvao.getSelectedItem().toString());
        if(cmbNgayvao.getSelectedItem().toString()!="Ngày ") ngay=Integer.parseInt(cmbNgayvao.getSelectedItem().toString());
        nv.setNgayvaolam(formatter.parse(String.valueOf(nam)+"-"+String.valueOf(thang)+"-"+String.valueOf(ngay)));
        nv.setDiachi(txtDiachi.getText());
        nv.setSdt(txtSdt.getText());
        return nv;
    }
    //hiển hị thông tin lên gui
    private void showInput(NhanvienDTO nv){
        LocalDate localDate=LocalDate.now();
        txtManv.setText(nv.getMa());
        txtMatkhau.setText(nv.getMatkhau());
        txtHo.setText(nv.getHo());
        txtTen.setText(nv.getTen());
        if(nv.getMachucvu()==-1) cmbRole.setSelectedIndex(0);
        int tmp=ck.searchChucvu(QL_Chung.listChucvu,nv.getMachucvu());
        if(tmp!=-1) cmbRole.setSelectedIndex(tmp+1);
        else  cmbRole.setSelectedIndex(0);
        if(nv.getGioitinh().equals("Nam")) rbSex1.setSelected(true); else if(nv.getGioitinh().equals("Nữ")) rbSex2.setSelected(true);else rbSex0.setSelected(true);
        String temp[]=new String[3];
        temp = formatter.format(nv.getDob()).split("-");
        cmbNgay.setSelectedIndex(Integer.parseInt(temp[2]));
        cmbThang.setSelectedIndex(Integer.parseInt(temp[1]));
        cmbNam.setSelectedIndex(localDate.getYear()-Integer.parseInt(temp[0]));
        temp = formatter.format(nv.getNgayvaolam()).split("-");
        cmbNgayvao.setSelectedIndex(Integer.parseInt(temp[2]));
        cmbThangvao.setSelectedIndex(Integer.parseInt(temp[1]));
        cmbNamvao.setSelectedIndex(localDate.getYear()+1-Integer.parseInt(temp[0]));
        txtSdt.setText(nv.getSdt());
        txtDiachi.setText(nv.getDiachi());
    }
    private boolean checkManv(String manv) {
        for (NhanvienDTO nv : listNV) {
            if (manv.equals(nv.getMa())) return false;
        }
        return true;
    }
}
