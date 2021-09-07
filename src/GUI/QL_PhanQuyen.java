package GUI;

import BLL.DangnhapBLL;
import BLL.PhanQuyenBLL;
import DTO.DangnhapDTO;
import DTO.QuyenDTO;
import GUI.Components.ButtonFunction;
import GUI.Components.LabelCustom;
import utils.CheckInput;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class QL_PhanQuyen extends JPanel {
    private DangnhapBLL dangnhapBLL=new DangnhapBLL();
    private PhanQuyenBLL phanQuyenBLL=new PhanQuyenBLL();
    private JCheckBox[] ckb;
    private CheckInput ck=new CheckInput();
    private ArrayList<QuyenDTO> quyen=new ArrayList<>();
    private int[] columnspWidth = {
            150,150,400
    };
    public QL_PhanQuyen() throws SQLException, IOException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        QL_Chung.listChucvu=new ArrayList<>();
        if(QL_Chung.listChucvu.size()==0) phanQuyenBLL.getListChucvu();
        dangnhapBLL.getQuyen(quyen);
        initComponents();
    }
    public void addRow(DangnhapDTO chucvu, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, chucvu.getMachucvu(),chucvu.getTenchucvu()
        });
    }
    public void setRow(DefaultTableModel model, DangnhapDTO chucvu , int index){
        model.setValueAt(chucvu.getMachucvu(),index,1);
        model.setValueAt(chucvu.getTenchucvu(),index,2);
    }
    public void showTable(DefaultTableModel model, ArrayList<DangnhapDTO> list ) {
        model.setRowCount(0);
        int i = 1;
        for (DangnhapDTO s : list) {
            model.addRow(new Object[]{
                    i++, s.getMachucvu(),s.getTenchucvu()
            });
        }
    }
    public void addRowQuyen(QuyenDTO q, int i, DefaultTableModel model) {
        model.insertRow(i++, new Object[]{
                i++, q.getMaquyen(),q.getTenquyen()
        });
    }
    public void showTableQuyen(DefaultTableModel model, ArrayList<QuyenDTO> list ) {
        model.setRowCount(0);
        int i = 1;
        for (QuyenDTO q : list) {
            if(q.getTrangthai()==1) {
                model.addRow(new Object[]{
                        i++, q.getMaquyen(), q.getTenquyen()
                });
            }
        }
    }
    private void initComponents() {
        //table chucvu
        tblChucvu = new JTable();
        tblChucvu.setRowHeight(40);
        tblChucvu.setModel(modelChucvu);
        modelChucvu.setColumnIdentifiers(new Object[]{
                "STT","Mã chức vụ", "Tên chức vụ"
        });
        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblChucvu.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        jpChucvu = new JScrollPane(tblChucvu);
        showTable(modelChucvu, QL_Chung.listChucvu);
        jpChucvu.setViewportView(tblChucvu);
        tblChucvu.setFont(new Font("Segoe UI", 0, 16));
        tblChucvu.setRowHeight(45);
        jpChucvu.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jpChucvu.setPreferredSize(new Dimension(700, 480));
        tblChucvu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                DangnhapDTO chucvu = QL_Chung.listChucvu.get(tblChucvu.getSelectedRow());
                txtMachucvu.setText(String.valueOf(chucvu.getMachucvu()));
                txtTenchucvu.setText(chucvu.getTenchucvu());
                QL_Chung.listQuyen=new ArrayList<>();
                try {
                    dangnhapBLL.getQuyenNV(chucvu.getMachucvu());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                showTableQuyen(modelPhanQuyen, QL_Chung.listQuyen);
                int i=0;
                for (QuyenDTO q: QL_Chung.listQuyen) {
                    ckb[i].setSelected(false);
                    if(q.getTrangthai()==1)
                        ckb[i].setSelected(true);
                    i++;
                }
            }
        });

        //table phanquyen
        tblPhanQuyen = new JTable();
        tblPhanQuyen.setRowHeight(40);
        tblPhanQuyen.setModel(modelPhanQuyen);
        modelPhanQuyen.setColumnIdentifiers(new Object[]{
                "STT","Mã quyền", "Tên các quyền được thực hiện"
        });
        columnspWidth = new int[]{200,200,400};
        i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblPhanQuyen.getColumnModel().getColumn(i++);
            column.setMinWidth(width);column.setMaxWidth(width);column.setPreferredWidth(width);
        }
        jpPhanQuyen = new JScrollPane(tblPhanQuyen);
        jpPhanQuyen.setViewportView(tblPhanQuyen);
        tblPhanQuyen.setFont(new Font("Segoe UI", 0, 16));
        tblPhanQuyen.setRowHeight(45);
        jpPhanQuyen.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jpPhanQuyen.setPreferredSize(new Dimension(800, 480));

        //phanquyen
        ckb=new JCheckBox[QL_Chung.listQuyen.size()];
        pnQuyen.setLayout(new GridLayout(QL_Chung.listQuyen.size()/2,2,0,0));
        i=0;
        for (QuyenDTO q: QL_Chung.listQuyen) {
            ckb[i]=new JCheckBox(q.getTenquyen());
            ckb[i].setPreferredSize(new Dimension(500,50));
            ckb[i].setFont(new Font("Segoe UI",0,18));
            pnQuyen.add(ckb[i]);
            i++;
        }
        //button
        btnAdd.addActionListener(evt1 -> {
            try {
                btnAddActionPerformed(evt1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnUpdate.addActionListener(evt -> {
            try {
                btnUpdateActionPerformed(evt);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        btnDel.addActionListener(evt -> {
            try {
                btnDelActionPerformed(evt);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //gui
        pnBtn.add(btnAdd);pnBtn.add(btnUpdate);pnBtn.add(btnDel);
        pnCtn.add(jpChucvu);
        pnCtn.add(jpPhanQuyen);
        pnInput.add(lbTitleAdd);
        pnInput.add(lbMachucvu);
        pnInput.add(txtMachucvu);
        pnInput.add(lbTenchucvu);
        pnInput.add(txtTenchucvu);
        pnInput.add(pnBtn);
        add(lbTitle);
        add(pnCtn);
        add(pnInput);
        add(pnQuyen);
        pnInput.setBackground(NewColor.background);
        pnCtn.setBackground(NewColor.background);
        pnCtn.setPreferredSize(new Dimension(1520,500));
        jpChucvu.setBackground(NewColor.background);
        pnQuyen.setBackground(NewColor.background);
        pnInput.setPreferredSize(new Dimension(800,400));
        pnQuyen.setPreferredSize(new Dimension(700,400));
        lbTitle.setPreferredSize(new Dimension(1520,80));
        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 30));
        lbTitleAdd.setPreferredSize(new Dimension(800,80));
        lbTitleAdd.setForeground(new Color(54, 38, 90));
        lbTitleAdd.setFont(new Font("Segoe UI", 1, 20));
        lbMachucvu.setPreferredSize(new Dimension(200,45));
        txtMachucvu.setPreferredSize(new Dimension(400,45));
        lbTenchucvu.setPreferredSize(new Dimension(200,100));
        txtTenchucvu.setPreferredSize(new Dimension(400,45));
        pnBtn.setPreferredSize(new Dimension(800,100));
        pnBtn.setBackground(NewColor.background);
        txtMachucvu.setEditable(false);
        txtMachucvu.setFont(new Font("Segoe UI",0,17));
        txtTenchucvu.setFont(new Font("Segoe UI",0,17));
    }
    private void btnAddActionPerformed(ActionEvent evt) throws SQLException {
        DangnhapDTO chucvu=new DangnhapDTO(ck.refresh(txtTenchucvu.getText()));
        if(!txtTenchucvu.getText().equals("")){
            if(checkTenQuyen(chucvu)) {
                if (checkSelectedCheckbox(ckb)) {
                    setSelected(QL_Chung.listQuyen, ckb);
                    phanQuyenBLL.addChucvu(chucvu, QL_Chung.listQuyen);
                    addRow(chucvu, QL_Chung.listChucvu.size() - 1, modelChucvu);
                    tblChucvu.setRowSelectionInterval(QL_Chung.listChucvu.size()-1, QL_Chung.listChucvu.size()-1);
                    showTableQuyen(modelPhanQuyen, QL_Chung.listQuyen);
//                    System.out.println(Admin.listQuyen);
                    JOptionPane.showMessageDialog(this, "Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else JOptionPane.showMessageDialog(this, "Chọn ít nhất một quyền", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Tên chức vụ đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên chức vụ mới", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    private void btnUpdateActionPerformed(ActionEvent evt) throws SQLException {
        DangnhapDTO chucvu=new DangnhapDTO(Integer.parseInt(txtMachucvu.getText()),ck.refresh(txtTenchucvu.getText()));
        int index=tblChucvu.getSelectedRow();
        if(!txtMachucvu.getText().equals("")){
            if(checkTenQuyen(chucvu)) {
                if (checkSelectedCheckbox(ckb)) {
                    setSelected(QL_Chung.listQuyen, ckb);
                    phanQuyenBLL.updateChucvu(chucvu, QL_Chung.listQuyen, index);
                    setRow(modelChucvu,chucvu,index);
                    txtTenchucvu.setText(chucvu.getTenchucvu());
                    showTableQuyen(modelPhanQuyen, QL_Chung.listQuyen);
                    JOptionPane.showMessageDialog(this, "Thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else JOptionPane.showMessageDialog(this, "Chọn ít nhất một quyền", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else JOptionPane.showMessageDialog(this, "Tên chức vụ đã tồn tại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }else JOptionPane.showMessageDialog(this, "Bạn phải chọn một chức vụ để sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    private void btnDelActionPerformed(ActionEvent evt) throws SQLException {
        if(!txtMachucvu.getText().equals("")){
            int index=tblChucvu.getSelectedRow();
            int res=JOptionPane.showConfirmDialog(null, "Are you sure?", "Message",
                    JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){
                if(phanQuyenBLL.delChucvu(Integer.parseInt(txtMachucvu.getText()), QL_Chung.listChucvu,index)){
                    modelChucvu.removeRow(index);
                    for(int j=index;j<modelChucvu.getRowCount();j++){
                        modelChucvu.setValueAt(j+1,j,0);
                    }
                    modelPhanQuyen.setRowCount(0);
                }else JOptionPane.showMessageDialog(this, "Hiện tại vẫn còn nhân viên thuộc chức vụ này, không thể xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            }
        }else JOptionPane.showMessageDialog(this, "Chọn một chức vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    private JButton btnAdd=new ButtonFunction("Thêm");
    private JButton btnUpdate =new ButtonFunction("Sửa");
    private JButton btnDel=new ButtonFunction("Xóa");
    private JLabel lbMachucvu = new LabelCustom("Mã chức vụ");
    private JTextField txtMachucvu = new JTextField("",35);
    private JLabel lbTenchucvu = new LabelCustom("Tên chức vụ");
    private JTextField txtTenchucvu = new JTextField("",35);
    private JPanel pnBtn=new JPanel();
    private JLabel lbTitle = new JLabel("PHÂN QUYỀN CHỨC NĂNG",SwingConstants.LEFT);
    private JLabel lbTitleAdd = new JLabel("QUẢN LÍ CHỨC VỤ",SwingConstants.LEFT);
    private JPanel pnCtn=new JPanel();
    private JPanel pnInput=new JPanel();
    private JPanel pnQuyen=new JPanel();
    private JTable tblChucvu=new JTable();
    private JTable tblPhanQuyen=new JTable();
    private JScrollPane jpChucvu=new JScrollPane();
    private JScrollPane jpPhanQuyen=new JScrollPane();
    private DefaultTableModel modelChucvu=new DefaultTableModel();
    private DefaultTableModel modelPhanQuyen=new DefaultTableModel();
    private boolean checkTenQuyen(DangnhapDTO chucvu){
        for (DangnhapDTO c: QL_Chung.listChucvu) {
            if(ck.refresh(chucvu.getTenchucvu()).toLowerCase().equals(c.getTenchucvu().toLowerCase()) && chucvu.getMachucvu()!=c.getMachucvu())
                return false;
        }
        return true;
    }
    private boolean checkSelectedCheckbox(JCheckBox jCheckBox[]){
        for (JCheckBox ckb: jCheckBox
             ) {
            if(ckb.isSelected()) return true;
        }
        return false;
    }
    private void setSelected(ArrayList<QuyenDTO> list, JCheckBox ckb[]){
        int i=0;
        for (QuyenDTO q: list) {
            if(ckb[i].isSelected()) {
                q.setTrangthai(1);
            } else q.setTrangthai(0);
            i++;
        }
    }
}
