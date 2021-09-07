package GUI;

import BLL.NhacungcapBLL;
import DTO.NhacungcapDTO;
import GUI.Components.*;
import utils.CheckInput;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class QL_Nhacungcap extends JPanel{

    private GridBagLayout gblayout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private NhacungcapBLL nhacungcapBLL = new NhacungcapBLL();
    private DefaultTableModel model = new DefaultTableModel();
    private int index='\0';
    private int checkundo='\0';
    private int checksearch=0;
    private NhacungcapDTO ncctemp=new NhacungcapDTO();
    public static ArrayList<NhacungcapDTO> listNCC=new ArrayList<>();
    private ArrayList<NhacungcapDTO> listsearch=new ArrayList<>();
    private CheckInput ck=new CheckInput();
    private int[] columnspWidth = {
            100,150,550,150,550
    };

    public QL_Nhacungcap(){
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(NewColor.background);
        initComponents();
    }

    //cac thao tac voi model
    public void addRow(NhacungcapDTO s, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, s.getMancc(), s.getTenncc(), s.getSdt(), s.getDiachi()});
    }

    public void setRow(DefaultTableModel model, NhacungcapDTO s , int index){
        model.setValueAt(s.getMancc(),index,1);
        model.setValueAt(s.getTenncc(),index,2);
        model.setValueAt(s.getSdt(),index,3);
        model.setValueAt(s.getDiachi(),index,4);
    }
    public void showTable(DefaultTableModel model, ArrayList<NhacungcapDTO> list ) {
        int i = 1;
        for (NhacungcapDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMancc(), s.getTenncc(), s.getSdt(), s.getDiachi()
            });
        }
    }

    private void initComponents(){
        gbc.fill = 1;
        gbc.insets = (new Insets(20, 0, 0, 20));
        listNCC = new ArrayList<>();
        if (listNCC.size() == 0) {
            nhacungcapBLL.getListNhacungcap();
        }
        //table NCC
        tableNCC = new JTable();
        tableNCC.setRowHeight(40);
        tableNCC.setModel(model);
        model.setColumnIdentifiers(new Object[]{
                "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ"
        });
        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tableNCC.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jScrollPane = new JScrollPane(tableNCC);
        tableNCC.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                NhacungcapDTO ncc = listNCC.get(tableNCC.getSelectedRow());
                showInput(ncc);
            }
        });
        //button
        btnAdd.addActionListener(this::btnAddActionPerformed);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);
        btnDel.addActionListener(this::btnDelActionPerformed);
        btnRetype.addActionListener(this::btnRetypeActionPerformed);
        btnUndo.addActionListener(this::btnUndoActionPerformed);
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        btnRead.addActionListener(this::btnReadActionPerformed);
        //set panel input
        pnInput = new JPanel(gblayout);
        {
            gbc.gridy = 0;
            gbc.gridwidth = 1;gbc.gridx = 0;pnInput.add(lbMancc, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(txtMancc, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnInput.add(lbTenncc, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnInput.add(txtTenncc, gbc);

            gbc.gridy = 1;
            gbc.gridwidth = 3;gbc.gridx = 0;pnInput.add(lbManccE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnInput.add(lbTennccE, gbc);

            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnInput.add(lbSdt, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(txtSdt, gbc);
            gbc.gridwidth = 1;gbc.gridx = 3;pnInput.add(lbDiachi, gbc);
            gbc.gridwidth = 2;gbc.gridx = 4;pnInput.add(txtDiachi, gbc);

            gbc.gridy = 3;
            gbc.gridwidth = 3;gbc.gridx = 0;pnInput.add(lbSdtE, gbc);
            gbc.gridwidth = 3;gbc.gridx = 3;pnInput.add(lbDiachiE, gbc);
        }
        //set GUI
        pnBtn.add(btnAdd);
        pnBtn.add(btnUpdate);
        pnBtn.add(btnDel);
        pnBtn.add(btnUndo);
        pnBtn.add(btnRetype);
        pnBtn.add(btnSearch);
        pnBtn.add(btnRead);
        add(lbTitle);
        add(pnInput);
        add(pnBtn);
        add(jScrollPane);

        //custom
        jScrollPane.setViewportView(tableNCC);
        jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPane.setPreferredSize(new Dimension(1500, 440));
        pnInput.setBackground(NewColor.background);
        pnBtn.setBackground(NewColor.background);
        pnInput.setPreferredSize(new Dimension(1620, 350));
        pnBtn.setPreferredSize(new Dimension(1620, 100));
        lbTitle.setPreferredSize(new Dimension(1520, 80));
        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 30));
        tableNCC.setFont(new Font("Segoe UI", 0, 16));
        txtMancc.setEditable(false);
        txtTenncc.setColumns(30);
        txtMancc.setColumns(30);
        txtDiachi.setColumns(30);
        txtSdt.setColumns(30);
    }

    //thao tác lấy và hiển thị dữ liệu
    private void showInput(NhacungcapDTO ncc) {
        txtMancc.setText(String.valueOf(ncc.getMancc()));
        txtTenncc.setText(ncc.getTenncc());
        txtSdt.setText(ncc.getSdt());
        txtDiachi.setText(ncc.getDiachi());
    }
    private NhacungcapDTO getInput() {
        NhacungcapDTO ncc =new NhacungcapDTO();
        if(!txtMancc.getText().equals("")) ncc.setMancc(Integer.parseInt(txtMancc.getText()));
        ncc.setTenncc(txtTenncc.getText());
        ncc.setSdt(txtSdt.getText());
        ncc.setDiachi(txtDiachi.getText());
        return ncc;
    }
    //thiết lập các nút
    private void btnAddActionPerformed(ActionEvent evt) {
        if (checkInput()) {
            if (nhacungcapBLL.checkTenncc(txtTenncc.getText(), listNCC)) {
                NhacungcapDTO s = getInput();
                if (nhacungcapBLL.addNCC(s)) {
                    addRow(s, listNCC.size() - 1, model);
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    retype();
                } else
                    JOptionPane.showMessageDialog(this, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void btnUpdateActionPerformed(ActionEvent evt) {
        ArrayList<NhacungcapDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listNCC;
        else listt=listsearch;
        index=tableNCC.getSelectedRow();
        if (checkInput()) {
            NhacungcapDTO s = getInput();
            ncctemp= listt.get(index);
            if (nhacungcapBLL.updateNCC(s, ncctemp.getMancc(), index)) {
                setRow(model,s,index);
                JOptionPane.showMessageDialog(this, "Cập nhật liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                checkundo=1;
            } else
                JOptionPane.showMessageDialog(this, "Không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnDelActionPerformed(ActionEvent evt) {
        ArrayList<NhacungcapDTO> listt=new ArrayList<>();
        if(checksearch==0) listt=listNCC; else listt=listsearch;
        index=tableNCC.getSelectedRow();
        ncctemp= listt.get(index);
        int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Message",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            if (nhacungcapBLL.delNCC(index)) {
                model.removeRow(index);
                for (int j = index; j < model.getRowCount(); j++) {
                    model.setValueAt(j + 1, j, 0);
                }
                checkundo = 1;
            } else
                JOptionPane.showMessageDialog(this, "Xóa không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void btnRetypeActionPerformed(ActionEvent evt) {
        retype();
    }
    private void btnUndoActionPerformed(ActionEvent evt) {
        while(checkundo==1){
            if(nhacungcapBLL.undo(ncctemp,index)==1)
                setRow(model,ncctemp,index);
            else {
                int modelSize=model.getRowCount();
                addRow(ncctemp,index,model);
                for (int j = index; j <modelSize ; j++) {
                    model.setValueAt(j+1,j,0);
                }
            }
            tableNCC.setRowSelectionInterval(index,index);
            JOptionPane.showMessageDialog(this,"Hoàn tác thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            checkundo=0;
        }
    }
    private void btnSearchActionPerformed(ActionEvent evt) {
        NhacungcapDTO nv=getInput();
        listsearch=nhacungcapBLL.search(nv);
        model.setRowCount(0);
        showTable(model, listsearch);
        checksearch=1;
    }
    private void btnReadActionPerformed(ActionEvent evt) {
        if (listNCC.size()==0) nhacungcapBLL.getListNhacungcap();
        model.setRowCount(0);
        showTable(model, listNCC);
        checksearch=0;
    }
    private void retype(){
        txtMancc.setText(""); lbManccE.setText(" ");
        txtTenncc.setText(""); lbTennccE.setText(" ");
        txtSdt.setText(""); lbSdtE.setText(" ");
        txtDiachi.setText(""); lbDiachiE.setText(" ");

    }
    //kiểm tra input
    private boolean checkInput(){
        if(checkTen(lbTennccE,txtTenncc)
                &&checkString(lbDiachiE,txtDiachi)
                &&checkSDT()
        ) return true;
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
    //khai báo và khởi tạo
    private JLabel lbTitle = new JLabel("QUẢN LÍ NHÀ CUNG CẤP",SwingConstants.LEFT);
    private JPanel pnInput=new JPanel();
    private JPanel pnBtn=new JPanel();
    private JLabel lbMancc=new LabelCustom("Mã nhà cung cấp");
    private JLabel lbTenncc=new LabelCustom("Tên nhà cung cấp");
    private JLabel lbDiachi=new LabelCustom("Địa chỉ");
    private JLabel lbSdt=new LabelCustom("Số điện thoại");
    private JLabel lbManccE=new LabelCustom(" ");
    private JLabel lbTennccE=new LabelCustom(" ");
    private JLabel lbSdtE=new LabelCustom(" ");
    private JLabel lbDiachiE=new LabelCustom(" ");
    private JTextField txtMancc = new TextFieldCustom("");
    private JTextField txtTenncc = new TextFieldCustom("");
    private JTextField txtDiachi = new TextFieldCustom("");
    private JTextField txtSdt = new TextFieldCustom("");
    private JTable tableNCC;
    private JScrollPane jScrollPane;
    private JButton btnAdd = new ButtonFunction("Thêm");
    private JButton btnUpdate = new ButtonFunction("Sửa");
    private JButton btnDel = new ButtonFunction("Xóa");
    private JButton btnUndo = new ButtonFunction("Hoàn tác");
    private JButton btnSearch = new ButtonFunction("Tìm kiếm");
    private JButton btnRetype = new ButtonFunction("Nhập lại");
    private JButton btnRead=new ButtonFunction("Đọc từ server");
}