package GUI;

import DTO.*;
import GUI.Components.*;
import utils.NewColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class QL_NhapHang extends JPanel{
    public static ArrayList<PhieunhaphangDTO> listPNH=new ArrayList<>();
    public static ArrayList<CTPhieunhaphangDTO> listCTPNH=new ArrayList<>();


    public QL_NhapHang() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        initComponents();

    }
    private void initComponents() throws IOException, URISyntaxException {
        setLayout(new BorderLayout());


        btnImport.addActionListener(actionEvent -> {
            try {
                btnImportActionPerformed(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        btnImportList.addActionListener(actionEvent -> {
            try {
                btnImportListActionPerformed(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });

        pnTitle.setBackground(NewColor.background);
        pnTitle.setPreferredSize(new Dimension(1520,80));
        pnBtn.setBackground(NewColor.background);
        pnBtn.setPreferredSize(new Dimension(1200,80));
        pnCenter.setPreferredSize(new Dimension(1520,1000));
        pnCenter.setBackground(NewColor.background);

        lbTitle.setForeground(new Color(54, 38, 90));
        lbTitle.setFont(new Font("Segoe UI", 1, 30));
        lbTitle.setPreferredSize(new Dimension(320,80));
        lbTitle.setVerticalAlignment(SwingConstants.CENTER);

        pnTitle.add(lbTitle);
        pnTitle.add(pnBtn);
        pnBtn.add(btnImport);
        pnBtn.add(btnImportList);
        pnCenter.add(new QL_TaoNhapHang());
        add(pnTitle,BorderLayout.NORTH);
        add(pnCenter,BorderLayout.CENTER);

    }

    private void btnImportListActionPerformed(ActionEvent actionEvent) throws IOException, URISyntaxException {
        pnCenter.removeAll();
        pnCenter.add(new QL_TatcaNhapHang());
        pnCenter.validate();
        pnCenter.repaint();
    }

    private void btnImportActionPerformed(ActionEvent actionEvent) throws IOException, URISyntaxException {
        pnCenter.removeAll();
        pnCenter.add(new QL_TaoNhapHang());
        pnCenter.validate();
        pnCenter.repaint();
    }

    //khai báo
    private JLabel lbTitle = new JLabel("QUẢN LÍ NHẬP HÀNG", SwingConstants.LEFT);

    private JPanel pnTitle=new JPanel();
    private JPanel pnCenter=new JPanel();
    private JPanel pnBtn=new JPanel();

    private JButton btnImport=new ButtonFunction("Tạo phiếu nhập");
    private JButton btnImportList=new ButtonFunction("Quản lí phiếu nhập");

}
