package GUI;//tao phieu nhap moi va them san pham
import BLL.CTPNBUS;
import BLL.PNBUS;
import DTO.CTPNDTO;
import DTO.PNDTO;
import utils.NewColor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Vector;

public class PNFRAME extends JPanel implements ActionListener {
    DefaultTableModel model1=new DefaultTableModel();
    DefaultTableModel model2=new DefaultTableModel();
//    JFrame frame;
    JPanel p1,p2;
    JLabel lh1,lh2;
    JLabel lb1,lb2,lb3,lb4;//mapnh,masp,sl,gianhap
    JTextField tx1,tx2,tx3,tx4;
    JButton bt1,bt2,bt3,bt4;//them,sua,xoa,reset
    JLabel lb5,lb6;//manv,mancc
    JTextField tx5,tx6;
    JButton bt5,bt6,bt7;//xuadlpn,taopn,luupn;
    JTable tb1,tb2;//chitiet,pn;
    JScrollPane sp1,sp2;

    public PNFRAME(){
//        frame=new JFrame("Them phieu moi");
        p1=new JPanel();
        p2=new JPanel();
        lh1=new JLabel("Chi tiet phieu nhap");
        lh2=new JLabel("Phieu nhap");
        lb1=new JLabel("Ma pn");
        lb2=new JLabel("Ma sp");
        lb3=new JLabel("sl");
        lb4=new JLabel("gia nhap");
        lb5=new JLabel("Ma ncc");
        lb6=new JLabel("Ma nv");
        tx1=new JTextField("",5);
        tx2=new JTextField("",5);
        tx3=new JTextField("",5);
        tx4=new JTextField("",5);
        tx5=new JTextField("",5);
        tx6=new JTextField("",5);
        bt1=new JButton("Them");
        bt2=new JButton("Sua");
        bt3=new JButton("Xoa");
        bt4=new JButton("Reset");
        bt5=new JButton("Xuat ds");
        bt6=new JButton("Tao pn moi");
        bt7=new JButton("Luu pn");
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
        bt6.addActionListener(this);
        bt7.addActionListener(this);
        tb1=new JTable();
        tb1.setModel(model1);
        model1.addColumn("Ma pn");
        model1.addColumn("Ma sp");
        model1.addColumn("sl");
        model1.addColumn("Gia nhap");
        model1.addColumn("Thanh tien");
        sp1=new JScrollPane(tb1);

        tb2=new JTable();
        tb2.setModel(model2);
        model2.addColumn("Ma pn");
        model2.addColumn("Ma nv");
        model2.addColumn("Ngay nhap");
        model2.addColumn("Ma ncc");
        model2.addColumn("tong tien");
        sp2=new JScrollPane(tb2);

//        frame.setSize(1200,500);
//        frame.setLayout(new BorderLayout());
        p1.setPreferredSize(new Dimension(600,500));
        p2.setPreferredSize(new Dimension(600,500));
//        frame.add(p1,BorderLayout.WEST);
//        frame.add(p2,BorderLayout.EAST);

        setSize(1200,500);
        setLayout(new BorderLayout());
        add(p1,BorderLayout.WEST);
        add(p2,BorderLayout.EAST);
        p1.add(lh1);
        p1.add(lb1);
        p1.add(tx1);
        p1.add(lb2);
        p1.add(tx2);
        p1.add(lb3);
        p1.add(tx3);
        p1.add(lb4);
        p1.add(tx4);
        p1.add(bt1);
        p1.add(bt2);
        p1.add(bt3);
        p1.add(bt4);
        p1.add(sp1);

        p2.add(lh2);
        p2.add(lb5);
        p2.add(tx5);
        p2.add(bt6);
        p2.add(tx6);
        p2.add(bt5);
        p2.add(bt6);
        p2.add(bt7);
        p2.add(sp2);

        setVisible(true);
//        setPreferredSize(new Dimension(1620,1080));
        p1.setBackground(Color.WHITE);
        p2.setBackground(Color.WHITE);
//        frame.setVisible(true);
//        frame.setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //xuat ds phieu moi
        if(e.getSource()==bt5){
            model2.setRowCount(0);
            PNBUS bus=new PNBUS();
            bus.doc();
            for(PNDTO pn :PNBUS.dspn){
                Vector vt=new Vector();
                vt.add(pn.getMapnh());
                vt.add(pn.getManv());
                vt.add(pn.getNgay());
                vt.add(pn.getMancc());
                vt.add(pn.getTongtien());
                model2.addRow(vt);
            }
        }
        //tao phieu moi
        if(e.getSource()==bt6){
            int mancc=Integer.valueOf(tx5.getText());
            int manv=Integer.valueOf(tx6.getText());
            String date=String.valueOf(LocalDate.now());
            PNDTO pn=new PNDTO();
            pn.setManv(manv);
            pn.setNgay(date);
            pn.setMancc(mancc);
            pn.setTongtien(0);
            PNBUS pnbus=new PNBUS();
            pnbus.them(pn);
            Vector vt=new Vector();
            vt.add(pn.getMapnh());
            vt.add(pn.getManv());
            vt.add(pn.getNgay());
            vt.add(pn.getMancc());
            vt.add(pn.getTongtien());
            model2.addRow(vt);
            JOptionPane.showMessageDialog(null,"Tao phieu moi thanh cong");
        }
        //click vao phieu nhap
        tb2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int  j=tb2.getSelectedRow();
                String mapnh=model2.getValueAt(j,0).toString();
                tx1.setText(mapnh);
                model1.setRowCount(0);
                for(int i = 0; i< CTPNBUS.dsctpn.size(); i++){
                    if(CTPNBUS.dsctpn.get(i).getMapnh()==Integer.valueOf(mapnh)){
                        Vector vt=new Vector();
                        vt.add(CTPNBUS.dsctpn.get(i).getMapnh());
                        vt.add(CTPNBUS.dsctpn.get(i).getMasp());
                        vt.add(CTPNBUS.dsctpn.get(i).getSoluong());
                        vt.add(CTPNBUS.dsctpn.get(i).getDongianhap());
                        vt.add(CTPNBUS.dsctpn.get(i).getThanhtien());
                        model1.addRow(vt);
                    }
                }
            }
        });
        //them sp,them sl vao bang chi tiet
        if(e.getSource()==bt1){
            int mapnh=Integer.valueOf(tx1.getText());
            int masp=Integer.valueOf(tx2.getText());
            int soluong=Integer.valueOf(tx3.getText());
            int gianhap=Integer.valueOf(tx4.getText());
            int thanhtien=soluong*gianhap;
            CTPNDTO ctpn=new CTPNDTO();
            ctpn.setMapnh(mapnh);
            ctpn.setMasp(masp);
            ctpn.setSoluong(soluong);
            ctpn.setDongianhap(gianhap);
            ctpn.setThanhtien(thanhtien);
            for(int i=0;i<CTPNBUS.dsctpn.size();i++){
                if(mapnh==CTPNBUS.dsctpn.get(i).getMapnh() && masp==CTPNBUS.dsctpn.get(i).getMasp()){
                    int sl=CTPNBUS.dsctpn.get(i).getSoluong()+soluong;
                    CTPNBUS.dsctpn.get(i).setSoluong(sl);
                    int tt=sl*gianhap;
                    CTPNBUS.dsctpn.get(i).setThanhtien(tt);
                    for(int j=0;j<tb1.getRowCount();j++){
                        if(Integer.valueOf(model1.getValueAt(j,0).toString())==mapnh && Integer.valueOf(model1.getValueAt(j,1).toString())==masp){
                            model1.setValueAt(CTPNBUS.dsctpn.get(i).getSoluong(),j,2);
                            model1.setValueAt(tt,j,4);
                        }
                    }
                    return;
                }
            }
            CTPNBUS ctpnbus=new CTPNBUS();
            ctpnbus.them(ctpn);//them vao dspn nhung ko them vao database
            Vector vt=new Vector();
            vt.add(ctpn.getMapnh());
            vt.add(ctpn.getMasp());
            vt.add(ctpn.getSoluong());
            vt.add(ctpn.getDongianhap());
            vt.add(ctpn.getThanhtien());
            model1.addRow(vt);
        }
        //click phieu chi tiet
        tb1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int  i=tb1.getSelectedRow();
                tx1.setText(model1.getValueAt(i,0).toString());//mapnh
                tx2.setText(model1.getValueAt(i,1).toString());//masp
                tx3.setText(model1.getValueAt(i,2).toString());//sl
                tx4.setText(model1.getValueAt(i,3).toString());//gianhap
            }
        });
        //sua
        if(e.getSource()==bt2){
            int mapnh=Integer.valueOf(tx1.getText());
            int masp=Integer.valueOf(tx2.getText());
            int soluong=Integer.valueOf(tx3.getText());
            int gianhap=Integer.valueOf(tx4.getText());
            int thanhtien=soluong*gianhap;
            CTPNDTO ctpn=new CTPNDTO();
            ctpn.setMapnh(mapnh);
            ctpn.setMasp(masp);
            ctpn.setSoluong(soluong);
            ctpn.setDongianhap(gianhap);
            ctpn.setThanhtien(thanhtien);
            CTPNBUS ctpnbus=new CTPNBUS();
            ctpnbus.sua(ctpn);
            for(int j=0;j<tb1.getRowCount();j++){
                if(Integer.valueOf(model1.getValueAt(j,0).toString())==mapnh && Integer.valueOf(model1.getValueAt(j,1).toString())==masp){
                    model1.setValueAt(soluong,j,2);
                    model1.setValueAt(thanhtien,j,4);
                }
            }
            return;
        }
        //xoa
        if(e.getSource()==bt3){
            int mapnh=Integer.valueOf(JOptionPane.showInputDialog(null,"nhap ma phieu nhap can xoa"));
            int masp=Integer.valueOf(JOptionPane.showInputDialog(null,"nhap ma san pham can xoa"));
            CTPNBUS ctpnbus=new CTPNBUS();
            ctpnbus.xoa(mapnh,masp);
            for(int j=0;j<tb1.getRowCount();j++){
                if(Integer.valueOf(model1.getValueAt(j,0).toString())==mapnh && Integer.valueOf(model1.getValueAt(j,1).toString())==masp){
                    model1.removeRow(j);
                }
            }
            return;
        }
        //reset
        if(e.getSource()==bt4) {
            int mapnh=Integer.valueOf(tx1.getText());
            model1.setRowCount(0);
            CTPNBUS ctpnbus=new CTPNBUS();
            ctpnbus.reset(mapnh);
            return;


        }


        //luu phieu moi
        if(e.getSource()==bt7){
            int tongtien=0;
            CTPNDTO ctpn=new CTPNDTO();
            for(int i=0;i<tb1.getRowCount();i++){
                ctpn.setMapnh(Integer.valueOf(model1.getValueAt(i,0).toString()));
                ctpn.setMasp(Integer.valueOf(model1.getValueAt(i,1).toString()));
                ctpn.setSoluong(Integer.valueOf(model1.getValueAt(i,2).toString()));
                ctpn.setDongianhap(Integer.valueOf(model1.getValueAt(i,3).toString()));
                ctpn.setThanhtien(Integer.valueOf(model1.getValueAt(i,4).toString()));
                tongtien+=ctpn.getThanhtien();
                CTPNBUS ctpnbus=new CTPNBUS();
                ctpnbus.add(ctpn);//them vao database
            }
            PNBUS pnbus=new PNBUS();
            pnbus.luutongtien(ctpn.getMapnh(),tongtien);
            JOptionPane.showMessageDialog(null,"luu phieu nhap"+ctpn.getMapnh()+"thanh cong");
            return;
        }
    }
}
