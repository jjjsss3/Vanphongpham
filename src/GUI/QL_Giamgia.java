package GUI;

import BLL.GiamGiaBLL;
import DAL.GiamGiaDAL;
import DTO.ChitietGiamgiaDTO;
import DTO.GiamGiaDTO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class QL_Giamgia extends JPanel {
    public static ArrayList<GiamGiaDTO>listGG=new ArrayList<>();
    public static ArrayList<ChitietGiamgiaDTO>listCTGG=new ArrayList();
    private GiamGiaBLL giamGiaBLL=new GiamGiaBLL();
    public QL_Giamgia() throws IOException, URISyntaxException {
        setVisible(true);
        setPreferredSize(new Dimension(1620, 1080));
        setBackground(new Color(245, 245, 245));
        listGG=new ArrayList<>();
        if (listGG.size() == 0) giamGiaBLL.getListGG();
        listCTGG=new ArrayList<>();
        if (listCTGG.size() == 0) giamGiaBLL.getListCTGG();

        initComponents();
    }

    private void initComponents() {

    }
}
