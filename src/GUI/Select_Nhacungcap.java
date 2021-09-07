package GUI;

import BLL.NhacungcapBLL;
import DTO.NhacungcapDTO;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Select_Nhacungcap extends JFrame {
    private DefaultTableModel modelNCC = new DefaultTableModel();
    private NhacungcapBLL nhacungcapBLL=new NhacungcapBLL();
    public static NhacungcapDTO ncc=new NhacungcapDTO();
    private int[] columnspWidth = {
            40, 30, 320, 90, 470
    };
    public Select_Nhacungcap(){
        setVisible(true);
        setSize(950,400);
        if (QL_Nhacungcap.listNCC.size() == 0) {
            nhacungcapBLL.getListNhacungcap();
        }
        initComponents();
        modelNCC = (DefaultTableModel) tblNCC.getModel();
        modelNCC.setColumnIdentifiers(new Object[]{
                "STT","ID", "Tên nhà cung cấp","SDT","Địa chỉ"
        });

        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblNCC.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        showTableNCC(modelNCC);

    }
    private void initComponents(){
        tblNCC = new JTable();

            tblNCC.setRowHeight(40);
            tblNCC.setModel(new DefaultTableModel(
                    new Object[][]{
                    },
                    new String[]{
                    }
            ));
            jScrollPaneNCC = new JScrollPane(tblNCC);
        tblNCC.setModel(modelNCC);
        add(jScrollPaneNCC, BorderLayout.CENTER);
        add(btnSelect,BorderLayout.SOUTH);
        jScrollPaneNCC.setViewportView(tblNCC);
        jScrollPaneNCC.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        btnSelect.addActionListener(this::btnSelectActionformed);
        setBackground(new NewColor().background);
    }
    private void btnSelectActionformed(ActionEvent evt){
        int index=tblNCC.getSelectedRow();
        ncc=QL_Nhacungcap.listNCC.get(index);
        QL_TaoNhapHang.txtTenNcc.setText(ncc.getTenncc());
        QL_TaoNhapHang.txtSDTNcc.setText(ncc.getSdt());
        QL_TaoNhapHang.txtMancc.setText(String.valueOf(ncc.getMancc()));
        dispose();
    }
    public void showTableNCC(DefaultTableModel model ) {
        int i = 1;
        for (NhacungcapDTO s : QL_Nhacungcap.listNCC) {
            model.addRow(new Object[]{
                    i++, s.getMancc(), s.getTenncc(),s.getSdt(),s.getDiachi()
            });
        }

    }
    private JButton btnSelect=new JButton("Chọn");
    private JTable tblNCC=new JTable();
    private JScrollPane jScrollPaneNCC=new JScrollPane();
}
