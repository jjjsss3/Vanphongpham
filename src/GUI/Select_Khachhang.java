package GUI;

import BLL.KhachhangBLL;
import BLL.NhacungcapBLL;
import DTO.KhachhangDTO;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Select_Khachhang extends  JFrame{
    private DefaultTableModel modelKH = new DefaultTableModel();
    private KhachhangBLL khachhangBLL=new KhachhangBLL();
    private NhacungcapBLL nhacungcapBLL=new NhacungcapBLL();
    private int[] columnspWidth = {
            40, 300, 300, 140, 170
    };
    public Select_Khachhang(){
        setVisible(true);
        setSize(950,400);
        if (QL_Khachhang.listKH.size() == 0) {
            khachhangBLL.getListKhachhang();
        }
        initComponents();
        modelKH = (DefaultTableModel) tblKH.getModel();
        modelKH.setColumnIdentifiers(new Object[]{
                "STT","Họ khách hàng", "Tên khách hàng","SDT","Địa chỉ"
        });

        int i = 0;
        for (int width : columnspWidth) {
            TableColumn column = tblKH.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        showTableKH(modelKH);

    }
    private void initComponents(){
        tblKH = new JTable();

        tblKH.setRowHeight(40);
        tblKH.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                }
        ));
        jScrollPaneKH = new JScrollPane(tblKH);
        tblKH.setModel(modelKH);
        add(jScrollPaneKH, BorderLayout.CENTER);
        add(btnSelect,BorderLayout.SOUTH);
        jScrollPaneKH.setViewportView(tblKH);
        jScrollPaneKH.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        btnSelect.addActionListener(this::btnSelectActionformed);
        setBackground(new NewColor().background);
    }
    private void btnSelectActionformed(ActionEvent evt){
        int index=tblKH.getSelectedRow();
        KhachhangDTO khselected =QL_Khachhang.listKH.get(index);
        QL_TaoHoaDon.showKH(khselected);
        dispose();
    }
    public void showTableKH(DefaultTableModel model ) {
        int i = 1;
        for (KhachhangDTO s : QL_Khachhang.listKH) {
            model.addRow(new Object[]{
                    i++, s.getHo(), s.getTen(),s.getSdt(),s.getDiachi()
            });
        }

    }
    private JButton btnSelect=new JButton("Chọn");
    private JTable tblKH=new JTable();
    private JScrollPane jScrollPaneKH=new JScrollPane();
}

