package GUI;

import BLL.*;
import DAL.DanhmucDAL;
import DAL.SanphamDAL;
import DTO.*;
import GUI.Components.*;
import utils.CheckInput;
import utils.ImageFit;
import utils.NewColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class QL_HoaDon extends JPanel{
    public static ArrayList<HoaDonDTO> listHD=new ArrayList<>();
    public static ArrayList<ChitietHoadonDTO> listCTHD=new ArrayList<>();


    public QL_HoaDon() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        initComponents();

    }
    private void initComponents() throws IOException, URISyntaxException {
        setLayout(new BorderLayout());


        btnOrder.addActionListener(actionEvent -> {
            try {
                btnOrderActionPerformed(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        btnOrderList.addActionListener(actionEvent -> {
            try {
                btnOrderListActionPerformed(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        pnTitle.setBackground(NewColor.background);
        pnTitle.setPreferredSize(new Dimension(1520,80));
        pnBtn.setBackground(NewColor.background);
        pnBtn.setPreferredSize(new Dimension(1240,80));
        pnCenter.setPreferredSize(new Dimension(1520,1000));
        pnCenter.setBackground(NewColor.background);

        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 30));
        lbTitle.setPreferredSize(new Dimension(280,80));
        lbTitle.setVerticalAlignment(SwingConstants.CENTER);

        pnTitle.add(lbTitle);
        pnTitle.add(pnBtn);
        pnBtn.add(btnOrder);
        pnBtn.add(btnOrderList);
        pnCenter.add(new QL_TaoHoaDon());
        add(pnTitle,BorderLayout.NORTH);
        add(pnCenter,BorderLayout.CENTER);

    }

    private void btnOrderListActionPerformed(ActionEvent actionEvent) throws IOException, URISyntaxException {
        pnCenter.removeAll();
        pnCenter.add(new QL_TatcaHoadon());
        pnCenter.validate();
        pnCenter.repaint();
    }

    private void btnOrderActionPerformed(ActionEvent actionEvent) throws IOException, URISyntaxException {
        pnCenter.removeAll();
        pnCenter.add(new QL_TaoHoaDon());
        pnCenter.validate();
        pnCenter.repaint();
    }

    //khai báo
    private JLabel lbTitle = new JLabel("QUẢN LÍ HÓA ĐƠN", SwingConstants.LEFT);

    private JPanel pnTitle=new JPanel();
    private JPanel pnCenter=new JPanel();
    private JPanel pnBtn=new JPanel();

    private JButton btnOrder=new ButtonFunction("Tạo hóa đơn");
    private JButton btnOrderList=new ButtonFunction("Quản lí hóa đơn");

}
