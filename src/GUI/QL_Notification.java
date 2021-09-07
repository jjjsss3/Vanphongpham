package GUI;

import javax.swing.*;
import java.awt.*;


public class QL_Notification extends JPanel {
    public QL_Notification(){
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        initComponents();
    }
    public void  initComponents(){ ;
    add(lb);
    lb.setFont(new Font("Segoe UI", 1, 50));
    lb.setForeground(new Color(10,30,80));
    lb.setPreferredSize(new Dimension(1200, 200));
    }
    private JLabel lb=new JLabel("HIỆN TẠI ĐANG CẬP NHẬT VÀ PHÁT TRIỂN MỤC NÀY, QUAY LẠI SAU NHÉ");
}
