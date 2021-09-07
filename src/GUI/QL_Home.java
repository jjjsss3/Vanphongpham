package GUI;

import DTO.NhanvienDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class QL_Home extends JPanel {
    private JLabel lbSpace=new JLabel(" ");
    private JLabel lbTitle=new JLabel();
    private JLabel lbTitle1=new JLabel();
    private JPanel pnTitle=new JPanel();
    private JPanel pnCtn=new JPanel();
    private String sTitle="";
    private BufferedImage[] wPic;
    private JLabel[] wIcon;
    public QL_Home(NhanvienDTO nv) throws IOException {
        setVisible(true);
        setPreferredSize(new Dimension(1500, 1080));
        setBackground(Color.WHITE);
        GridLayout grlo=new GridLayout(2,1);
        wPic=new BufferedImage[5];
        wIcon=new JLabel[5];
        wPic[1] = ImageIO.read(this.getClass().getResource("images/homepage/1home3.png"));
        wPic[2] = ImageIO.read(this.getClass().getResource("images/homepage/1home4.png"));
        wPic[3] = ImageIO.read(this.getClass().getResource("images/homepage/1home2.png"));
        wPic[4] = ImageIO.read(this.getClass().getResource("images/homepage/1home1.png"));
        for (int i = 1; i <5 ; i++) {
            wIcon[i]= new JLabel(new ImageIcon(wPic[i]));
            pnCtn.add(wIcon[i]);
        }
        pnCtn.setLayout(grlo);
        pnTitle.add(lbTitle);
        pnTitle.add(lbTitle1);
        add(lbSpace);
        add(pnTitle);
        add(pnCtn);
        lbSpace.setPreferredSize(new Dimension(1500,50));
        if(nv.getMachucvu()==1) sTitle="ADMINISTRATOR";
        else sTitle="STAFF";
        sTitle+=" / ";
        sTitle+=nv.getHo().toUpperCase()+" "+nv.getTen().toUpperCase();
        lbTitle.setText(sTitle);
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setFont(new Font("Segoe UI",1,20));
        lbTitle.setPreferredSize(new Dimension(1350,120));
        lbTitle1.setText("CHÀO MỪNG BẠN QUAY TRỞ LẠI VỚI PHẦN MỀM QUẢN LÍ CỦA CỬA HÀNG");
        lbTitle1.setForeground(Color.WHITE);
        lbTitle1.setFont(new Font("Segoe UI",1,30));
        lbTitle1.setVerticalAlignment(SwingConstants.TOP);
        lbTitle1.setPreferredSize(new Dimension(1350,100));
        pnTitle.setPreferredSize(new Dimension(1520,250));
        pnTitle.setBackground(new Color(125,85,225));
        pnCtn.setPreferredSize(new Dimension(1400,700));
        pnCtn.setBackground(Color.WHITE);
    }
}
