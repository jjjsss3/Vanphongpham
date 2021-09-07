package GUI;

import BLL.CTPNBUS;
import BLL.PNBUS;
import DTO.CTPNDTO;
import DTO.PNDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

//xem lich su phieu nhap va chi tiet
public class LSNFRAME implements ActionListener {
    DefaultTableModel model1=new DefaultTableModel();
    DefaultTableModel model2=new DefaultTableModel();
    JFrame frame;
    JPanel p1,p2;
    JLabel lh1,lh2;
    JButton bt;//xuat danh sach pn
    JTable tb1,tb2;//pn,chi tiet pn
    JScrollPane sp1,sp2;
    LSNFRAME(){
        frame=new JFrame("Lich su nhap");
        p1=new JPanel();
        p2=new JPanel();
        lh1=new JLabel("Danh sach phieu nhap");
        lh2=new JLabel("Danh sach chi tiet");
        bt=new JButton("xuat");
        bt.addActionListener(this);
        tb1=new JTable();
        tb1.setModel(model1);
        model1.addColumn("Ma pn");
        model1.addColumn("Ma nv");
        model1.addColumn("Ngay nhap");
        model1.addColumn("Ma ncc");
        model1.addColumn("tong tien");
        sp1=new JScrollPane(tb1);
        tb2=new JTable();
        tb2.setModel(model2);
        model2.addColumn("Ma pn");
        model2.addColumn("Ma sp");
        model2.addColumn("sl");
        model2.addColumn("Gia nhap");
        model2.addColumn("Thanh tien");
        sp2=new JScrollPane(tb2);

        frame.setSize(1000,500);
        frame.setLayout(new BorderLayout());
        p1.setPreferredSize(new Dimension(500,500));
        p2.setPreferredSize(new Dimension(500,500));
        frame.add(p1,BorderLayout.WEST);
        frame.add(p2,BorderLayout.EAST);
        p1.add(lh1);
        p1.add(bt);
        p1.add(sp1);
        p2.add(lh2);
        p2.add(sp2);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt){
            model1.setRowCount(0);
            PNBUS pnbus=new PNBUS();
            pnbus.docls();
            for(PNDTO pn :PNBUS.dspn){
                Vector vt=new Vector();
                vt.add(pn.getMapnh());
                vt.add(pn.getMancc());
                vt.add(pn.getNgay());
                vt.add(pn.getMancc());
                vt.add(pn.getTongtien());
                model1.addRow(vt);
            }
        }
        //xem chi tiet
        tb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int  i=tb1.getSelectedRow();
                int mapn=Integer.valueOf(model1.getValueAt(i,0).toString());
                model2.setRowCount(0);
                CTPNBUS ctpnbus=new CTPNBUS();
                ctpnbus.chitietpn(mapn);
                for(CTPNDTO ctpn: CTPNBUS.dsctpn){
                    Vector vt=new Vector();
                    vt.add(ctpn.getMapnh());
                    vt.add(ctpn.getMasp());
                    vt.add(ctpn.getSoluong());
                    vt.add(ctpn.getDongianhap());
                    vt.add(ctpn.getThanhtien());
                    model2.addRow(vt);
                }
            }
        });
    }
}
