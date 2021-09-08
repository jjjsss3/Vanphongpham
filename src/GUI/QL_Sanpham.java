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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class QL_Sanpham extends JPanel{
    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private SanphamDAL sanphamDAL=new SanphamDAL();
    private SanphamBLL sanphamBLL=new SanphamBLL();
    private DanhmucDAL danhmucDAL=new DanhmucDAL();
    private DanhmucBLL danhmucBLL=new DanhmucBLL();
    private DefaultTableModel modelSP = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private DefaultTableModel modelDM = new DefaultTableModel(){
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    private int indexdelundo=-1;
    private int indextableundo =-1;
    private int index='\0';
    private int checkundo=0;
    private int checkundo1='\0';
    private int check=0;
    private int checksearch=0;
    private Select_Nhacungcap mf;
    private SanphamDTO sptemp=new SanphamDTO();
    private DanhmucDTO dmtemp=new DanhmucDTO();
    public static ArrayList<SanphamDTO> listSP=new ArrayList<>();
    public static ArrayList<DanhmucDTO> listDM=new ArrayList<>();
    private ArrayList<SanphamDTO> listsearchsp=new ArrayList<>();
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
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
        if (listSP.size() == 0) sanphamBLL.getListSanpham();
        initComponents();
    }
    public void addRow(SanphamDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMasp(), s.getTensp(), s.getMaloai(), s.getSoluong(), currencyVN.format(s.getDongia())
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
        model.setValueAt(currencyVN.format(s.getDongia()),index,5);
    }

    public void showTableSP(DefaultTableModel model, ArrayList<SanphamDTO> list ) {
        int i = 1;
        for (SanphamDTO s : list) {
           model.addRow(new Object[]{
                    i++, s.getMasp(), s.getTensp(), s.getMaloai(), s.getSoluong(), currencyVN.format(s.getDongia())
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
        if (listSP.size()==0) sanphamBLL.getListSanpham();
        listsearchsp=listSP;
        gbc.fill = 1;
        gbc.insets = (new Insets(-10, 0, 20, 30));

        //table sản phẩm
        tblDSSP = new JTable();
        tblDSSP.setRowHeight(40);
        tblDSSP.setModel(modelSP);
        modelSP.setColumnIdentifiers(new Object[]{
                "STT", "ID", "Name", "Kind","Amount" ,"Price",
        });
        int i = 0;
        columnspWidth = new int[]{
                50, 90, 580, 90, 90, 200
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
//                btnAddActionPerformed(evt);
                btnAddAction(evt);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnUpdate.addActionListener(evt1 -> {
            try {
                btnEditAction(evt1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        btnDel.addActionListener(this::btnDelAction);
        btnRead.addActionListener(this::btnReadActionPerformed);
        btnRetype.addActionListener(this::btnRetypeActionPerformed);
        btnUndo.addActionListener(evt -> {
            //                btnUndoActionPerformed(evt);
            try {
                btnUndoAction(evt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        btnSearch.addActionListener((this::btnSearchActionPerformed));
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
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbTensp, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtTensp, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbMaloai, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtMaloai, gbc);


            gbc.gridy = 1;
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(lbTenspE, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(lbMaloaiE, gbc);

            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnCtnCSp.add(lbDongia, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(txtDongia, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnCtnCSp.add(lbSoluong, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(txtSoluong, gbc);

            gbc.gridy = 3;
            gbc.gridwidth = 2;gbc.gridx = 1;pnCtnCSp.add(lbDongiaE, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnCtnCSp.add(lbSoluongE, gbc);
            gbc.gridy = 4;

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
            txtTensp.setColumns(25);

            txtMaloai.setEditable(false);
            txtMaDM.setEditable(false);
            pnImg.setBackground(NewColor.background);
        }
    }

    private void btnDelAction(ActionEvent actionEvent) {
        if (listsearchsp.size() == 0) {
            sanphamBLL.getListSanpham();
            listsearchsp = listSP;
            showTableSP(modelSP, listsearchsp);
        }
        int i = tblDSSP.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa!");
        } else {
            SanphamDTO s = listsearchsp.get(i);
            if (!refresh(txtTensp.getText()).toLowerCase().equals(s.getTensp().toLowerCase())
                    || Integer.parseInt(txtMaloai.getText()) != s.getMaloai()
                    || Integer.parseInt(txtSoluong.getText()) != s.getSoluong()
                    || Long.parseLong(txtDongia.getText()) != s.getDongia() ) {
                JOptionPane.showMessageDialog(null, "Vui lòng trùng khớp thông tin để xóa!");
            }
            else {
                sptemp = s;
                indexdelundo=getIndexSP(listSP,sptemp.getMasp());
                sanphamBLL.delSanpham(sptemp.getMasp(), getIndexSP(listSP, sptemp.getMasp()));
//                System.out.println(listsearchsp.size());
                listsearchsp.remove(i);
                modelSP.removeRow(i);
                indextableundo=i;

                for (int j = index; j < modelSP.getRowCount(); j++) {
                    modelSP.setValueAt(j + 1, j, 0);
                }
                JOptionPane.showMessageDialog(null, "Xóa thành công!");
                checkundo = 2;
                retype();
            }
        }
    }
//            ArrayList<SanphamDTO> listt=new ArrayList<>();
//            if(checksearch==0) listt=QL_Sanpham.listSP;
//            else listt=listsearchsp;
//            index=tblDSSP.getSelectedRow();
//            sptemp= listt.get(index);
//            int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message", JOptionPane.YES_NO_OPTION);
//            if (res == JOptionPane.YES_OPTION) {
//                if (sanphamBLL.delSanpham(index)) {
//                    modelSP.removeRow(index);
//                    for (int j = index; j < modelSP.getRowCount(); j++) {
//                        modelSP.setValueAt(j + 1, j, 0);
//                    }
//                    checkundo = 1;
//                } else
//                    JOptionPane.showMessageDialog(this, "Xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            }

//    }

    private void btnUndoAction(ActionEvent evt) throws SQLException {
        if(checkundo==0){
            JOptionPane.showMessageDialog(null,"Không thể hoàn tác!");
        }else {
            int i=getIndexSP(listSP,sptemp.getMasp());
            int j=getIndexSP(listsearchsp,sptemp.getMasp());
            if(checkundo==1){
                sanphamBLL.updateSanpham(sptemp,i);
                listsearchsp.set(j, sptemp);
                setRow(modelSP,sptemp,j);
            }else {
                if(checkundo==2){
                    sanphamBLL.addSanphamUndo(sptemp,indexdelundo);
                    listsearchsp.add(indextableundo, sptemp);
                    addRow(sptemp,indexdelundo,modelSP);
                }
            }
            JOptionPane.showMessageDialog(null,"Hoàn tác từ thành công");

        }
        checkundo=0;
    }

    private String refresh(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }
    private void btnAddAction(ActionEvent evt) throws SQLException {
        if(listsearchsp.size()==0) {
            sanphamBLL.getListSanpham();
            listsearchsp=listSP;
            showTableSP(modelSP,listsearchsp);
        }

        if(checkTextfiledAdd()){
            SanphamDTO sp = getInputSP();
            sanphamBLL.addSanpham(sp);
            listsearchsp=listSP;
            modelSP.setNumRows(0);
            showTableSP(modelSP,listsearchsp);
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
            retype();
        }
    }
    private void btnEditAction(ActionEvent evt) throws SQLException {
        if(listsearchsp.size()==0) {
            sanphamBLL.getListSanpham();
            listsearchsp=listSP;
            showTableSP(modelSP,listsearchsp);
        }
        if(checkTextfiledUpdate()){
            int index=tblDSSP.getSelectedRow();
            sptemp=listsearchsp.get(index);
            SanphamDTO sp = getInputSP();
            sanphamBLL.updateSanpham(sp,getIndexSP(listSP,sp.getMasp()));
            listsearchsp.set(index, sp);
            setRow(modelSP,sp,index);
//            listsearchsp=listSP;
//            modelSP.setNumRows(0);
//            showTableSP(modelSP,listsearchsp);
            JOptionPane.showMessageDialog(null, "Sửa thành công!");
            checkundo=1;
            retype();
        }
    }
    private int getIndexSP(ArrayList<SanphamDTO> list,int masp){
        int i=-1;
        for (SanphamDTO s: list) {
            i++;
            if(s.getMasp()==masp) return i;
        }
        return -1;
    }
    private boolean checkTextfiledAdd(){
        if(txtTensp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hoặc nhập tên sản phẩm!");
            return false;
        }
        else {
            if(!checksp(refresh(txtTensp.getText()))) {
                JOptionPane.showMessageDialog(null, "Sản phẩm đã tồn tại!");
                return false;
            }
            else {
                if (txtMaloai.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn mã loại sản phẩm!");
                    return false;
                }
                else {
                    Pattern pattern = Pattern.compile("\\d*");
                    if (txtDongia.getText().equals("")||!pattern.matcher(txtDongia.getText()).matches()){
                        JOptionPane.showMessageDialog(null, "Đơn giá phải là số!");
                        return false;
                    }
                    else {
                        if (!txtSoluong.getText().equals("") && !pattern.matcher(txtSoluong.getText()).matches()) {
                            JOptionPane.showMessageDialog(null, "Số lượng phải là số!");
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
            }
        }
    }
    private boolean checkTextfiledUpdate(){
        if(tblDSSP.getSelectedRow()==-1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm ở bảng dưới để sửa!");
            return false;
        }else {
            int i=tblDSSP.getSelectedRow();
            if(txtTensp.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm sửa!");
                return false;
            }else {
                int j=getIndexSP(listSP, listsearchsp.get(i).getMasp());
                if(!checkspUpdate(txtTensp.getText(),j)){
                    JOptionPane.showMessageDialog(null, "Tên bạn muốn sửa trung tên với sản phẩm có sẵn!");
                    return false;
                }else {
                    if (txtMaloai.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn mã loại sản phẩm!");
                        return false;
                    }
                    else {
                        Pattern pattern = Pattern.compile("\\d*");
                        if (txtDongia.getText().equals("")||!pattern.matcher(txtDongia.getText()).matches()){
                            JOptionPane.showMessageDialog(null, "Đơn giá phải là số!");
                            return false;
                        }
                        else {
                            if (!txtSoluong.getText().equals("") && !pattern.matcher(txtSoluong.getText()).matches()) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số!");
                                return false;
                            }
                            else {
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }
    private boolean checksp(String str){
        for (SanphamDTO s:listSP) {
            if(str.toLowerCase().equals(refresh(s.getTensp()).toLowerCase())) return false;
        }
        return true;
    }
    private boolean checkspUpdate(String str, int index){
        String n=listSP.get(index).getTensp();
        if(str.equals(n)){
            return true;
        }
        for (SanphamDTO s:listSP) {
            if(str.toLowerCase().equals(refresh(s.getTensp()).toLowerCase()))
                return false;
        }
        return true;
    }

    private void btnUploadImg(ActionEvent actionEvent) throws IOException {
        if(tblDSSP.getSelectedRow()!=-1) {
            int masp=listsearchsp.get(tblDSSP.getSelectedRow()).getMasp();
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
                copy(filesource.toString(), destinationFolder.toString() + "\\" + masp + ".png");
                img = ImageIO.read(new File(destinationFolder + "\\" + masp + ".png"));
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
            listsearchsp=listSP;
        }
        showTableSP(modelSP, listsearchsp);
        checksearch=0;
    }
    private void btnRetypeActionPerformed(ActionEvent evt) {
        retype();
    }
    private void btnSearchActionPerformed(ActionEvent evt){
        SanphamDTO s=getInputSP();
        listsearchsp=sanphamBLL.search(s,txtSearchPrice.getText(), txtSearchQuantity.getText());
        modelSP.setRowCount(0);
        showTableSP(modelSP, listsearchsp);
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
    private JLabel lbTenspE = new LabelCustom(" ");
    private JLabel lbMaloaiE = new LabelCustom(" ");
    private JLabel lbSoluongE = new LabelCustom(" ");
    private JLabel lbDongiaE = new LabelCustom(" ");
    private JTextField txtTensp = new TextFieldCustom("");
    private JTextField txtMaloai = new TextFieldCustom("");
    private JTextField txtSoluong = new TextFieldCustom("");
    private JTextField txtDongia = new TextFieldCustom("");

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
        txtTensp.setText("");lbTenspE.setText(" ");
        txtMaloai.setText("");lbMaloaiE.setText(" ");
        txtDongia.setText("");lbDongiaE.setText(" ");
        txtSoluong.setText("");lbSoluongE.setText(" ");
    }
    private SanphamDTO getInputSP(){
        SanphamDTO sp=new SanphamDTO();
        int index=tblDSSP.getSelectedRow();
        if(index!=-1) {
            int masp=listsearchsp.get(index).getMasp();
            sp.setMasp(masp);
        }
        sp.setTensp(refresh(txtTensp.getText()));
        if(!txtMaloai.getText().equals("")) sp.setMaloai(Integer.parseInt(txtMaloai.getText()));
        if(!txtDongia.getText().equals(""))sp.setDongia(Long.parseLong(txtDongia.getText()));
        if(!txtSoluong.getText().equals("")) sp.setSoluong(Integer.parseInt(txtSoluong.getText()));
        else sp.setSoluong(0);
        return sp;
    }
    private void showInputSP(SanphamDTO sp) throws IOException, URISyntaxException {
        txtTensp.setText(sp.getTensp());
        txtMaloai.setText(String.valueOf(sp.getMaloai()));
        txtDongia.setText(String.valueOf(sp.getDongia()));
        txtSoluong.setText(String.valueOf(sp.getSoluong()));
        int z=0;
        for (DanhmucDTO s: listDM) {
            if(s.getMaloai()==sp.getMaloai()) break;
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
