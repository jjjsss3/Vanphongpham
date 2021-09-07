package GUI;

import BLL.DanhmucBLL;
import BLL.SanphamBLL;
import DAL.DanhmucDAL;
import DAL.SanphamDAL;
import DTO.DanhmucDTO;
import DTO.SanphamDTO;
import GUI.Components.*;
import utils.CheckInput;
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
import java.util.ArrayList;

public class QL_Sanpham extends JPanel{
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SanphamDAL sanphamDAL=new SanphamDAL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private DanhmucDAL danhmucDAL=new DanhmucDAL();
    private DanhmucBLL danhmucBLL=new DanhmucBLL();
    private DefaultTableModel modelSP = new DefaultTableModel();
    private DefaultTableModel modelDM = new DefaultTableModel();
    private int index='\0';
    private int checkundo='\0';
    private int checkundo1='\0';
    private int check=0;
    private int checksearch=0;
    private Select_Nhacungcap mf;
    private SanphamDTO sptemp=new SanphamDTO();
    private DanhmucDTO dmtemp=new DanhmucDTO();
    public static ArrayList<SanphamDTO> listSP=new ArrayList<>();
    public static ArrayList<DanhmucDTO> listDM=new ArrayList<>();
    private ArrayList<SanphamDTO> listsearchsp=new ArrayList<>();
    private int[] columnspWidth = {
            80, 100, 690, 80, 100, 300, 150
    };
    private CheckInput ck=new CheckInput();
    public QL_Sanpham() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        listDM=new ArrayList<>();
        if (listDM.size() == 0) danhmucBLL.getListDanhmuc();

        initComponents();
    }
    public void addRow(SanphamDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMasp(), s.getTensp(), s.getMaloai(), s.getSoluong(), s.getDongia(), s.getMancc()
        });
    }
    public void addRowDM(DanhmucDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMaloai(), s.getTenloai()
        });
    }
    public void setRowDM(DefaultTableModel model,DanhmucDTO s ,int index){
        model.setValueAt(s.getMaloai(),index,1);
        model.setValueAt(s.getTenloai(),index,2);
    }
    public void setRow(DefaultTableModel model,SanphamDTO s ,int index){
        model.setValueAt(s.getMasp(),index,1);
        model.setValueAt(s.getTensp(),index,2);
        model.setValueAt(s.getMaloai(),index,3);
        model.setValueAt(s.getSoluong(),index,4);
        model.setValueAt(s.getDongia(),index,5);
        model.setValueAt(s.getMancc(),index,6);
    }

    public void showTableSP(DefaultTableModel model, ArrayList<SanphamDTO> list ) {
        int i = 1;
        for (SanphamDTO s : list) {
           model.addRow(new Object[]{
                    i++, s.getMasp(), s.getTensp(), s.getMaloai(), s.getSoluong(), s.getDongia(), s.getMancc()
            });
        }
    }
    public void showTableDM(DefaultTableModel model, ArrayList<DanhmucDTO> list ) {
        int i = 1;
        for (DanhmucDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMaloai(), s.getTenloai()
            });
        }
    }

    private void initComponents() throws IOException, URISyntaxException {
        listSP=new ArrayList<>();
        gbc.fill = 1;
        gbc.insets = (new Insets(-10, 0, 20, 30));

        //table sản phẩm
        tblDSSP = new JTable();
        tblDSSP.setRowHeight(40);
        tblDSSP.setModel(modelSP);
        modelSP.setColumnIdentifiers(new Object[]{
                "STT", "ID", "Name", "Kind","Amount" ,"Price",
                "Supply"
        });
        int i = 0;
        columnspWidth = new int[]{
                50, 70, 570, 70, 70, 200, 70
        };
        for (int width : columnspWidth) {
            TableColumn column = tblDSSP.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneSP = new JScrollPane(tblDSSP);
        tblDSSP.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                SanphamDTO sp=new SanphamDTO();
                if(checksearch==0)
                { sp= listSP.get(tblDSSP.getSelectedRow());}
                else
                { sp= listsearchsp.get(tblDSSP.getSelectedRow());}
                try {
                    showInputSP(sp);
                } catch (IOException | URISyntaxException e) {
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
                80,80,240
        };
        i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblDSDM.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPaneDM = new JScrollPane(tblDSDM);
        showTableDM(modelDM, listDM);
        tblDSDM.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                DanhmucDTO dm = listDM.get(tblDSDM.getSelectedRow());
                showInputDM(dm);
            }
        });

        // button của sản phẩm
        btnAdd.addActionListener(evt -> {
            try {
                btnAddActionPerformed(evt);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        btnDel.addActionListener(this::btnDelActionPerformed);
        btnRead.addActionListener(this::btnReadActionPerformed);
        btnRetype.addActionListener(this::btnRetypeActionPerformed);
        btnUndo.addActionListener(evt -> {
            try {
                btnUndoActionPerformed(evt);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        btnSearch.addActionListener((this::btnSearchActionPerformed));
        btnNcc.addActionListener(this::btnNccActionPerformed);
        btnUploadImg.addActionListener(actionEvent -> {
            try {
                btnUploadImg(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //button danh mục
        btnAddDM.addActionListener(this::btnAddDMActionPerformed);
        btnUpdateDM.addActionListener(this::btnUpdateDMActionPerformed);
        btnDelDM.addActionListener(actionEvent -> {
            try {
                btnDelDMActionPerformed(actionEvent);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnUndoDM.addActionListener(actionEvent -> {
            try {
                btnUndoDMActionPerformed(actionEvent);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        pnCtnCSp = new JPanel(gblayout);
        {
            gbc.gridy = 0;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbMasp, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtMasp, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbTensp, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtTensp, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(lbMaspE, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(lbTenspE, gbc);

            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbMaloai, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtMaloai, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbMancc, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtMancc, gbc);
            gbc.gridwidth = 1;gbc.gridx = 7;pnCtnCSp.add(btnNcc, gbc);
            gbc.gridy = 3;
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(lbMaloaiE, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(lbManccE, gbc);

            gbc.gridy = 4;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbDongia, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtDongia, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbSoluong, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtSoluong, gbc);

            gbc.gridy = 5;
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(lbDongiaE, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(lbSoluongE, gbc);
            gbc.gridy = 6;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbSearchPrice, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtSearchPrice, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbSearchQuantity, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtSearchQuantity, gbc);
        }
        pnCtnC.add(pnCtnCSp);
        pnCtnC.add(pnCtnCBt);
            pnCtnCBt.setLayout(new GridLayout(2,2,00,00));
            pnCtnCBt.add(btnAdd);pnCtnCBt.add(btnUpdate);
            pnCtnCBt.add(btnDel);pnCtnCBt.add(btnUndo);
            pnCtnCBt.add(btnRetype);pnCtnCBt.add(btnSearch);pnCtnCBt.add(btnRead);
        pnCtnE.add(jScrollPaneDM);
        pnCtnE.add(pnDM);
            pnDM.setLayout(new GridLayout(2,2));
            pnDM.add(lbMaDM);pnDM.add(txtMaDM);pnDM.add(lbTenDM);pnDM.add(txtTenDM);
        pnCtnE.add(pnBtDM);
            pnBtDM.add(btnAddDM);pnBtDM.add(btnUpdateDM);
            pnBtDM.add(btnDelDM);pnBtDM.add(btnUndoDM);
        pnCtn.setLayout(new BorderLayout());
            pnCtn.add(pnCtnE, BorderLayout.EAST);
            pnCtn.add(pnCtnC, BorderLayout.CENTER);
        add(lbTitle);
        add(pnCtn);
        add(jScrollPaneSP);
        add(pnImg);
        pnImg.add(pnLbImg);
            pnLbImg.add(lbimg);
        pnImg.add(btnUploadImg);
        {
            lbTitle.setPreferredSize(new Dimension(1520,80));
            lbTitle.setVerticalAlignment(SwingConstants.CENTER);
            jScrollPaneSP.setViewportView(tblDSSP);
            jScrollPaneSP.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneSP.setPreferredSize(new Dimension(1100, 440));
            jScrollPaneDM.setViewportView(tblDSDM);
            jScrollPaneDM.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
            jScrollPaneDM.setPreferredSize(new Dimension(400, 350));
            pnImg.setPreferredSize(new Dimension(400, 440));
            pnLbImg.setPreferredSize(new Dimension(380, 380));
            pnLbImg.setBackground(NewColor.background);
            pnDM.setPreferredSize(new Dimension(400,70));
//            pnBtDM.setPreferredSize(new Dimension(400,35));
            jScrollPaneDM.setBackground(NewColor.background);
            pnBtDM.setBackground(NewColor.background);
            pnDM.setBackground(NewColor.background);
//            tblDSSP.setAutoCreateRowSorter(true);
            pnCtnE.setPreferredSize(new Dimension(400, 550));
            pnCtnC.setPreferredSize(new Dimension(1180, 550));
            pnCtnCSp.setPreferredSize(new Dimension(1100, 350));
            pnCtnCBt.setPreferredSize(new Dimension(900, 100));
            pnCtnCBt.setBackground(NewColor.background);
            pnCtnE.setBackground(NewColor.background);
            pnCtnC.setBackground(NewColor.background);
            pnCtnCSp.setBackground(NewColor.background);
            pnCtn.setPreferredSize(new Dimension(1500, 480));
            pnCtn.setBackground(NewColor.background);
            lbTitle.setForeground(new Color(54, 38, 90));
            lbTitle.setFont(new Font("Segoe UI", 1, 30));
            tblDSSP.setFont(new Font("Segoe UI", 0, 16));
            txtMasp.setEditable(false);
            txtMancc.setEditable(false);
            txtMaloai.setEditable(false);
            txtMaDM.setEditable(false);
            pnImg.setBackground(NewColor.background);
        }
    }

    private void btnUploadImg(ActionEvent actionEvent) throws IOException {
        if(!txtMasp.getText().equals("")) {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setLocation(100, 100);
            dialog.setVisible(true);
            String fileDirectory = dialog.getDirectory() + dialog.getFile();
            if (fileDirectory.endsWith("jpeg") || fileDirectory.endsWith("jpg") || fileDirectory.endsWith("png") || fileDirectory.endsWith("gif") || fileDirectory.endsWith("tiff") || fileDirectory.endsWith("bmp")) {
                File destinationFolder = new File(getClass().getResource("images/sanpham").getPath());
                File filesource = new File(fileDirectory);
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs();
                }
                copy(filesource.toString(), destinationFolder.toString() + "\\" + txtMasp.getText() + ".png");
                img = ImageIO.read(new File(destinationFolder + "\\" + txtMasp.getText() + ".png"));
                lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 360, 360)));
            } else
                JOptionPane.showMessageDialog(this, "Vui lòng chọn file ảnh", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    //thiết lập các button
    private void btnReadActionPerformed(ActionEvent evt) {
        modelSP.setRowCount(0);
        if (listSP.size() == 0) {
            sanphamBLL.getListSanpham();
        }
        showTableSP(modelSP, listSP);
        checksearch=0;
    }
    private void btnRetypeActionPerformed(ActionEvent evt) {
        retype();
    }
    private void btnAddActionPerformed(ActionEvent evt) throws SQLException {
        if (checkSP()) {
            if (sanphamBLL.checkTensp(txtTensp.getText(), listSP)) {
                SanphamDTO sanphamDTO = getInputSP();
                if (sanphamBLL.addSanpham(sanphamDTO)) {
                    addRow(sanphamDTO, QL_Sanpham.listSP.size() - 1, modelSP);
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    retype();
                } else JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnNccActionPerformed(ActionEvent evt){
        while(check==0) {
            mf = new Select_Nhacungcap();
            check = 1;
        }
        mf.setVisible(true);
        mf.setLocation(975,300);
    }
    private void btnUpdateActionPerformed(ActionEvent evt) {
        ArrayList<SanphamDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listSP;
        else listt=listsearchsp;
        index = tblDSSP.getSelectedRow();
            if (checkSP()) {
                SanphamDTO s = getInputSP();
                sptemp = listt.get(index);
                if (sanphamBLL.updateSanpham(s, sptemp.getMasp(), index)) {
                    setRow(modelSP, s, index);
                    JOptionPane.showMessageDialog(this, "Cập nhật liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    checkundo = 1;
                } else
                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

    }
    private void btnDelActionPerformed(ActionEvent evt){
        ArrayList<SanphamDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=QL_Sanpham.listSP;
        else listt=listsearchsp;
        index=tblDSSP.getSelectedRow();
        sptemp= listt.get(index);
        int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            if (sanphamBLL.delSanpham(index)) {
                modelSP.removeRow(index);
                for (int j = index; j < modelSP.getRowCount(); j++) {
                    modelSP.setValueAt(j + 1, j, 0);
                }
                checkundo = 1;
            } else
                JOptionPane.showMessageDialog(this, "Xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnUndoActionPerformed(ActionEvent evt) throws SQLException, IOException, URISyntaxException {
        while(checkundo==1) {
            if (sanphamBLL.undo(sptemp, index) == 1) {
                setRow(modelSP, sptemp, index);
            } else {
                addRow(sptemp, index, modelSP);
                for (int j = index; j < modelSP.getRowCount(); j++) {
                    modelSP.setValueAt(j + 1, j, 0);
                }
            }
            tblDSSP.setRowSelectionInterval(index,index);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            showInputSP(sptemp);
            checkundo = 0;
        }
    }
    private void btnSearchActionPerformed(ActionEvent evt){
        SanphamDTO s=getInputSP();
        listsearchsp=sanphamBLL.search(s,txtSearchPrice.getText(), txtSearchQuantity.getText());
        modelSP.setRowCount(0);
        showTableSP(modelSP, listsearchsp);
        checksearch=1;
    }
    //thiết lập các button danh mục
    private void btnUndoDMActionPerformed(ActionEvent actionEvent) throws SQLException {
        while(checkundo1==1) {
            if (danhmucBLL.undo(dmtemp, index) == 1) {
                setRowDM(modelDM, dmtemp, index);
            } else {
                addRowDM(dmtemp, index, modelDM);
                for (int j = index; j < modelDM.getRowCount(); j++) {
                    modelDM.setValueAt(j + 1, j, 0);
                }
            }
            tblDSDM.setRowSelectionInterval(index,index);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            txtMaDM.setText(String.valueOf(dmtemp.getMaloai()));
            txtTenDM.setText(dmtemp.getTenloai());
            checkundo1 = 0;
        }
    }

    private void btnDelDMActionPerformed(ActionEvent actionEvent) throws SQLException {
        index=tblDSDM.getSelectedRow();
        dmtemp= listDM.get(index);
        int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            if(listSP.size()!=0) {
                if (checkDelLoai(dmtemp.getMaloai())) {
                    if (danhmucBLL.delDM(index)) {
                        modelDM.removeRow(index);
                        for (int j = index; j < modelDM.getRowCount(); j++) {
                            modelDM.setValueAt(j + 1, j, 0);
                        }
                        checkundo1 = 1;
                    } else JOptionPane.showMessageDialog(this, "Xóa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else JOptionPane.showMessageDialog(this, "Loại hàng này vẫn còn sản phẩm, không thể xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Vui lòng hiển thị danh sách sản phẩm trước", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private boolean checkDelLoai(int maloai){
        for (SanphamDTO s: listSP) {
            if(s.getMaloai()==maloai) return false;
        }
        return true;
    }
    private void btnUpdateDMActionPerformed(ActionEvent actionEvent) {
        index = tblDSDM.getSelectedRow();
        if (!txtMaDM.getText().equals("")) {
            DanhmucDTO d = new DanhmucDTO(Integer.parseInt(txtMaDM.getText()),txtTenDM.getText());
            dmtemp = listDM.get(index);
            if (danhmucBLL.updateDanhmuc(d, dmtemp.getMaloai(), index)) {
                setRowDM(modelDM, d, index);
                JOptionPane.showMessageDialog(this, "Cập nhật liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                checkundo1 = 1;
            } else JOptionPane.showMessageDialog(this, "Cập nhật không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 danh mục", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    private void btnAddDMActionPerformed(ActionEvent actionEvent) {
        if (!txtTenDM.getText().equals("")) {
            if (danhmucBLL.checkTensp(txtTensp.getText(), listDM)) {
                DanhmucDTO d = new DanhmucDTO(txtTenDM.getText());
                if (danhmucBLL.addDM(d)) {
                    addRowDM(d, QL_Sanpham.listDM.size() - 1, modelDM);
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    retype();
                } else JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Danh mục đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(this, "Vui lòng nhập tên", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    //khai báo
    private BufferedImage img;
    private ImageIcon imgicon;
    private JLabel lbimg=new JLabel();
    private JPanel pnCtn = new JPanel();
    private JPanel pnCtnC = new JPanel();
    private JPanel pnCtnCSp = new JPanel();
    private JPanel pnCtnCBt = new JPanel();
    private JPanel pnCtnE = new JPanel();
    private JPanel pnLbImg =new JPanel();
    private JPanel pnImg=new JPanel();
    private JTable tblDSSP;
    private JTable tblDSDM;
    private JLabel lbSearchPrice=new LabelCustom("Tìm đơn giá: >,=,<");
    private JTextField txtSearchPrice=new TextFieldCustom("");
    private JLabel lbSearchQuantity=new LabelCustom("Tìm số lượng: >,=,<");
    private JTextField txtSearchQuantity=new TextFieldCustom("");
    private JLabel lbTitle = new JLabel("QUẢN LÍ SẢN PHẨM",SwingConstants.LEFT);
    private JLabel lbMasp = new LabelCustom("Mã sản phẩm");
    private JLabel lbTensp = new LabelCustom("Tên sản phẩm");
    private JLabel lbMaloai = new LabelCustom("Mã loại");
    private JLabel lbSoluong = new LabelCustom("Số lượng");
    private JLabel lbDongia = new LabelCustom("Đơn giá");
    private JLabel lbMancc = new LabelCustom("Mã nhà cung cấp");

    private JLabel lbMaspE = new LabelCustom(" ");
    private JLabel lbTenspE = new LabelCustom(" ");
    private JLabel lbMaloaiE = new LabelCustom(" ");
    private JLabel lbSoluongE = new LabelCustom(" ");
    private JLabel lbDongiaE = new LabelCustom(" ");
    private JLabel lbManccE = new LabelCustom(" ");
    private JTextField txtMasp = new TextFieldCustom("");
    private JTextField txtTensp = new TextFieldCustom("");
    private JTextField txtMaloai = new TextFieldCustom("");
    private JTextField txtSoluong = new TextFieldCustom("");
    private JTextField txtDongia = new TextFieldCustom("");
    public static JTextField txtMancc = new TextFieldCustom("");
    private JTextField test=new JTextField("",15);
    private JScrollPane jScrollPaneSP;
    private JScrollPane jScrollPaneDM;
    private JButton btnAdd = new ButtonFunction("Thêm");
    private JButton btnUploadImg = new ButtonFunction("Tải ảnh");
    private JButton btnUpdate = new ButtonFunction("Sửa");
    private JButton btnDel = new ButtonFunction("Xóa");
    private JButton btnUndo = new ButtonFunction("Hoàn tác");
    private JButton btnSearch = new ButtonFunction("Tìm kiếm");
    private JButton btnRetype = new ButtonFunction("Nhập lại");
    private JButton btnRead = new ButtonFunction("Đọc từ server");
    private JButton btnNcc = new JButton("Chọn");
    private JButton btnAddDM=new JButton("Thêm");
    private JButton btnUpdateDM =new JButton("Sửa");
    private JButton btnDelDM=new JButton("Xóa");
    private JButton btnUndoDM=new JButton("Hoàn tác");
    private JPanel  pnBtDM=new JPanel();
    private JPanel pnDM=new JPanel();
    private JLabel lbMaDM=new JLabel("Mã loại");
    private JLabel lbTenDM=new JLabel("Tên danh mục");
    private JLabel lbCtDM=new JLabel("");
    private JTextField txtCtDM=new TextFieldCustom("");

    private JTextField txtMaDM=new JTextField("");
    private JTextField txtTenDM=new TextFieldCustom("");
    private boolean checkMa(){
     return true;
    }
    private boolean checkSP(){
        if(ck.checkTen1(lbTenspE,txtTensp)&&
                ck.checkMoney(lbDongiaE, txtDongia)&&
                ck.checkSo(lbSoluongE,txtSoluong)
        ) return true;
        else return false;
    }
    private void retype(){
        txtSearchPrice.setText("");txtSearchQuantity.setText("");
        txtMasp.setText("");lbMaspE.setText(" ");
        txtTensp.setText("");lbTenspE.setText(" ");
        txtMaloai.setText("");lbMaloaiE.setText(" ");
        txtMancc.setText("");lbManccE.setText(" ");
        txtDongia.setText("");lbDongiaE.setText(" ");
        txtSoluong.setText("");lbSoluongE.setText(" ");
    }
    private SanphamDTO getInputSP(){
        SanphamDTO sp=new SanphamDTO();
        if(!txtMasp.getText().equals("")) sp.setMasp(Integer.parseInt(txtMasp.getText()));
        sp.setTensp(txtTensp.getText());
        if(!txtMaloai.getText().equals("")) sp.setMaloai(Integer.parseInt(txtMaloai.getText()));
        if(!txtMancc.getText().equals("")) sp.setMancc(Integer.parseInt(txtMancc.getText()));
        sp.setDongia(ck.checkMoney(txtDongia));
        if(!txtSoluong.getText().equals("")) sp.setSoluong(Integer.parseInt(txtSoluong.getText()));
        return sp;
    }
    private void showInputSP(SanphamDTO sp) throws IOException, URISyntaxException {
        txtMasp.setText(String.valueOf(sp.getMasp()));
        txtTensp.setText(sp.getTensp());
        txtMaloai.setText(String.valueOf(sp.getMaloai()));
        txtMancc.setText(String.valueOf(sp.getMancc()));
        txtDongia.setText(String.valueOf(sp.getDongia()));
        txtSoluong.setText(String.valueOf(sp.getSoluong()));
        int z=0;
        for (DanhmucDTO s: listDM) {
            if(s.getMaloai()==sp.getMaloai()) break;
            z++;
        }
        tblDSDM.setRowSelectionInterval(z,z);

        URL url = getClass().getResource(sp.getAnhsp());
        if(url==null)
            img=ImageIO.read(this.getClass().getResource("images/sanpham/product.png"));
        else{
            img = ImageIO.read(new File(url.getPath()));
        }
        lbimg.setIcon(new ImageIcon(new ImageFit().fitimage(img, 360, 360)));
//        pnLbImg.add(lbimg);
    }
    private void showInputDM(DanhmucDTO sp){
        txtMaDM.setText(String.valueOf(sp.getMaloai()));
        txtTenDM.setText(sp.getTenloai());
        txtMaloai.setText(String.valueOf(sp.getMaloai()));
    }
    private static void copy(String src, String dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            // buffer size 1K
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
