package GUI;

import BLL.DangnhapBLL;
import DTO.DangnhapDTO;
import DTO.NhanvienDTO;
import DTO.QuyenDTO;
import com.itextpdf.text.DocumentException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

public class QL_Chung extends JFrame {

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private BufferedImage Pic;
    private JLabel Icon;
    private BufferedImage[] wPic;
    private JLabel[] wIcon;
    private Cursor cs=new Cursor(Cursor.HAND_CURSOR);
    private DangnhapBLL dangnhapBLL=new DangnhapBLL();
    public static ArrayList<QuyenDTO> listQuyen;
    public static ArrayList<DangnhapDTO> listChucvu=new ArrayList<>();
    public QL_Chung(NhanvienDTO tk) throws IOException, FontFormatException, SQLException {
        listQuyen=new ArrayList<>();
        dangnhapBLL.getQuyenNV(tk.getMachucvu());
//        for (QuyenDTO q: listQuyen
//             ) {
//            System.out.println(q);
//        }
        initComponents(tk);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(QL_Chung.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void initComponents(NhanvienDTO tk) throws IOException, SQLException {
        setMinimumSize(new Dimension(1920,1080));
        setBackground(new Color(108,140,213));
        JPanel pnCtn = new JPanel();
        gbc.fill = 2;
        gbc.insets = (new Insets(0, 0, 0, 50));
        int lengthLQuyen=listQuyen.size()+2;
        lbLeft=new JLabel[lengthLQuyen];
        pnCate=new JPanel[lengthLQuyen];
        wPic=new BufferedImage[lengthLQuyen];
        wIcon=new JLabel[lengthLQuyen];
        pnCtn.setLayout(new GridLayout(lengthLQuyen-1,1,0,0));
        //Trang chủ
            wPic[0] = ImageIO.read(this.getClass().getResource("images/icon/1home.png"));
            wIcon[0]= new JLabel(new ImageIcon(wPic[0]));
            wIcon[0].setPreferredSize(new Dimension(100,30));
            pnCate[0]=new JPanel();
            lbLeft[0]=new JLabel("TRANG CHỦ");
            lbLeft[0].setFont(new Font("Segoe UI",Font.BOLD,13));
            lbLeft[0].setPreferredSize(new Dimension(200,60));
            lbLeft[0].setForeground(Color.WHITE);
            lbLeft[0].setCursor(cs);
            pnCate[0].setBackground(new Color(54,38,90));
            pnCate[0].add(wIcon[0]);
            pnCate[0].add(lbLeft[0]);
            clickCate(0);
                lbLeft[0].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        pnCenter.removeAll();
                        try {
                            pnCenter.add(new QL_Home(tk));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        pnCenter.validate();
                        pnCenter.repaint();
                    }
                });
        // thiết lập các phân quyền
        for (QuyenDTO q:listQuyen
             ) {
            int i=q.getMaquyen();
            wPic[i]= ImageIO.read(this.getClass().getResource("images/icon/1"+q.getChitiet()+".png"));
            wIcon[i]= new JLabel(new ImageIcon(wPic[i]));
            wIcon[i].setPreferredSize(new Dimension(100,30));
            pnCate[i]=new JPanel();
            lbLeft[i]=new JLabel(q.getTenquyen());
            lbLeft[i].setFont(new Font("Segoe UI",Font.BOLD,13));
            lbLeft[i].setPreferredSize(new Dimension(200,60));
            lbLeft[i].setForeground(Color.WHITE);
            lbLeft[i].setCursor(cs);
            pnCate[i].setBackground(new Color(54,38,90));
            pnCate[i].add(wIcon[i]);
            pnCate[i].add(lbLeft[i]);
                        clickCate(i);
                        if(q.getTrangthai()==1)
            pnCtn.add(pnCate[i]);
            addPanel(i,q.getChitiet(),q.getTrangthai());
        }
        //đăng xuất
        {
            wPic[lengthLQuyen-1] = ImageIO.read(this.getClass().getResource("images/icon/1dangxuat.png"));
            wIcon[lengthLQuyen-1]= new JLabel(new ImageIcon(wPic[lengthLQuyen-1]));
            wIcon[lengthLQuyen-1].setPreferredSize(new Dimension(100,30));
            pnCate[lengthLQuyen-1]=new JPanel();
            lbLeft[lengthLQuyen-1]=new JLabel("ĐĂNG XUẤT");
            lbLeft[lengthLQuyen-1].setFont(new Font("Segoe UI",Font.BOLD,13));
            lbLeft[lengthLQuyen-1].setPreferredSize(new Dimension(200,60));
            lbLeft[lengthLQuyen-1].setForeground(Color.WHITE);
            lbLeft[lengthLQuyen-1].setCursor(cs);
            pnCate[lengthLQuyen-1].setBackground(new Color(125,85,225));
            pnCate[lengthLQuyen-1].add(wIcon[lengthLQuyen-1]);
            pnCate[lengthLQuyen-1].add(lbLeft[lengthLQuyen-1]);
            pnCtn.add(pnCate[lengthLQuyen-1]);
            clickCate(lengthLQuyen-1);
            lbLeft[lengthLQuyen-1].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    dispose();
                  if(QL_Thongke.dp1!=null) QL_Thongke.dp1.dispose();
                    try {
                        new Dangnhap();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (FontFormatException fontFormatException) {
                        fontFormatException.printStackTrace();
                    }
                }
            });
        }

        Pic = ImageIO.read(this.getClass().getResource("images/logo1.png"));
        Icon = new JLabel(new ImageIcon(Pic));
        {
            pnLeft.add(Icon);
            pnLeft.add(pnCate[0]);
            pnLeft.add(lbCate);
            pnLeft.add(pnCtn);
        }
        pnCenter.add(new QL_Home(tk));
        add(pnLeft,BorderLayout.WEST);
        add(pnCenter,BorderLayout.CENTER);
        {
            lbCate.setPreferredSize(new Dimension(280,30));
            pnLeft.setPreferredSize(new Dimension(300,900));
            pnCenter.setPreferredSize(new Dimension(1620,1080));
            pnCenter.setBackground(new Color(255,255,255));
            pnLeft.setBackground(new Color(54,38,90));

            pnCtn.setBackground(new Color(54,38,90));
            lbCate.setForeground(Color.WHITE);
            lbCate.setVerticalAlignment(SwingConstants.CENTER);
            lbCate.setFont(new Font("",1,20));
            setBackground(new Color(210,215,230));
        }
    }
    private JPanel pnLeft = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel[] pnCate;
    private JLabel[] lbLeft;
    private void clickCate(int index){
        lbLeft[index].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            for (int i = 0; i < listQuyen.size()+1; i++) {
                pnCate[i].setBackground(new Color(54,38,90));
            }
            pnCate[index].setBackground(new Color(85,70,120));
            }
        });
    }
    private JLabel lbCate = new JLabel("_________________________",SwingConstants.LEADING);
    private void addPanel(int i, String tenquyen,int trangthai){
        lbLeft[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            pnCenter.removeAll();
            if (tenquyen.equals("sanpham")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_Sanpham());
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            }
                if (tenquyen.equals("hoadon")&&trangthai==1) {
                    try {
                        pnCenter.add(new QL_HoaDon());
                    } catch (IOException | URISyntaxException ioException) {
                        ioException.printStackTrace();
                    }
                }
            if (tenquyen.equals("nhanvien")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_Nhanvien());
                } catch (IOException | DocumentException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (tenquyen.equals("phanquyen")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_PhanQuyen());
                } catch (SQLException | IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (tenquyen.equals("thongke")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_Thongke());
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (tenquyen.equals("khachhang")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_Khachhang());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (tenquyen.equals("nhacungcap")&&trangthai==1) {
                    pnCenter.add(new QL_Nhacungcap());
            }
                if (tenquyen.equals("khuyenmai")&&trangthai==1) {
                    try {
                        pnCenter.add(new QL_Giamgia());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            if (tenquyen.equals("nhaphang")&&trangthai==1) {
                try {
                    pnCenter.add(new QL_NhapHang());
                } catch (IOException ex) {
                        ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
            else pnCenter.add(new QL_Notification());
            pnCenter.validate();
            pnCenter.repaint();
            }
        });
    }
}
