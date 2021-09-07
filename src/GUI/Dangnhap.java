
package GUI;

import BLL.DangnhapBLL;
import DTO.NhanvienDTO;
import GUI.Components.LabelCustom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class Dangnhap extends JFrame {
    private BufferedImage wPic;
    private JLabel wIcon;
    public static NhanvienDTO taikhoan = new NhanvienDTO();
    public Dangnhap() throws IOException, FontFormatException {

        initComponents();
        setLocationRelativeTo(null);
        //setMinimumSize(new Dimension(1600,900));
        setResizable(false);
        setDefaultCloseOperation(Dangnhap.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void initComponents() throws IOException, FontFormatException {
        setSize(600, 400);
        setBackground(new Color(108,140,213));
        setLayout(new GridLayout(1,0));
        wPic = ImageIO.read(this.getClass().getResource("/GUI/images/icon/2login.png"));
        wIcon= new JLabel(new ImageIcon(wPic));
        gbc.fill = 2;
        gbc.insets = (new Insets(20, 0, 20, 20));
        {
            pnInput.setLayout(gblo);
            gbc.gridy = 0;
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(pnTitle, gbc);
            pnTitle.add(wIcon); pnTitle.add(lbTitle);
            gbc.gridy = 1;
            gbc.gridwidth = 1;gbc.gridx = 0;pnInput.add(lbTaikhoan, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(txtTaikhoan, gbc);
            gbc.gridy = 2;
            gbc.gridwidth = 1;gbc.gridx = 0;pnInput.add(lbMatkhau, gbc);
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(txtMatkhau, gbc);
            gbc.gridy = 3;
            gbc.gridwidth = 2;gbc.gridx = 1;pnInput.add(pnBtn, gbc);
        }
        pnBtn.setLayout(new GridLayout(1,0));
        pnBtn.add(btDn);pnBtn.add(btExit);
        add(pnInput);
        lbTitle.setForeground(new Color(10,30,80));
        lbTitle.setFont(new Font("Segoe UI",1,25));
        txtTaikhoan.setFont(new Font("Segoe UI",0,18));
        txtMatkhau.setFont(new Font("Segoe UI",0,18));
        txtTaikhoan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    txtMatkhau.requestFocus();
                }
            }
        });
        txtMatkhau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                                btDn.doClick();
                }
            }
        });
        btDn.addActionListener(arg0 -> {
            DangnhapBLL dangnhapBLL=new DangnhapBLL();
            boolean res= dangnhapBLL.dangnhap(txtTaikhoan.getText(),txtMatkhau.getText(), taikhoan);
            if (res&&txtTaikhoan.getText().equals(taikhoan.getMa()) && txtMatkhau.getText().equals(taikhoan.getMatkhau())) {
                try {
                    new QL_Chung(taikhoan);
                    System.out.println(taikhoan);
                } catch (IOException | FontFormatException | SQLException e) {
                    e.printStackTrace();
                }
                dispose();
                JOptionPane.showMessageDialog(this,"Đăng nhập thành công");
            } else
                JOptionPane.showMessageDialog(this,"Đăng nhập thất bại");
        });
        btExit.addActionListener(
                e -> System.exit(0)
        );
        pnTitle.setBackground(new Color(240,240,240));
        wIcon.setPreferredSize(new Dimension(60, 40));
        btDn.setPreferredSize(new Dimension(40, 40));
        btDn.setBackground(Color.WHITE);
        btExit.setBackground(new Color(184, 85, 85));
        btExit.setForeground(new Color(255, 255, 255));
        pnInput.setBackground(new Color(240,240,240));
        pnBtn.setBackground(new Color(240,240,240));
    }
    private GridBagLayout gblo = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JPanel pnTitle=new JPanel();
    private JPanel pnBtn=new JPanel();
    private JPanel pnInput=new JPanel();
    private JLabel lbTaikhoan = new LabelCustom("Mã nhân viên");
    private JLabel lbMatkhau = new LabelCustom("Mật khẩu");
    private JLabel lbTitle = new LabelCustom("ĐĂNG NHẬP HỆ THỐNG");
    private JButton btDn=new JButton("Đăng nhập");
    private JButton btExit=new JButton("Thoát");
    private JTextField txtTaikhoan = new JTextField("", 15);
    private JPasswordField txtMatkhau = new JPasswordField("", 15);
}
