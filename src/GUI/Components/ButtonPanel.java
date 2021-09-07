package GUI.Components;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ButtonPanel extends JPanel {
    private Image[] btnIcon=new Image[6];
    private Cursor cs=new Cursor(Cursor.HAND_CURSOR);
    public ButtonPanel() throws IOException {
        add(btnAdd);add(btnUpdate);add(btnDel);add(btnUndo);add(btnRetype);
        btnIcon[0] = ImageIO.read(getClass().getResource("images/icon/2add.png"));
        btnIcon[1] = ImageIO.read(getClass().getResource("images/icon/2update.png"));
        btnIcon[2] = ImageIO.read(getClass().getResource("images/icon/2del.png"));
        btnIcon[3] = ImageIO.read(getClass().getResource("images/icon/2undo.png"));
        btnIcon[4] = ImageIO.read(getClass().getResource("images/icon/2retype.png"));
        btnIcon[5] = ImageIO.read(getClass().getResource("images/icon/2search.png"));
        btnAdd.setFont(new Font("Segoe UI", 0, 14));
        btnUpdate.setFont(new Font("Segoe UI", 0, 14));
        btnDel.setFont(new Font("Segoe UI", 0, 14));
        btnUndo.setFont(new Font("Segoe UI", 0, 14));
        btnRetype.setFont(new Font("Segoe UI", 0, 14));
        btnSearch.setFont(new Font("Segoe UI", 0, 14));
        btnRead.setFont(new Font("Segoe UI", 0, 14));
        btnAdd.setCursor(cs);
        btnUpdate.setCursor(cs);
        btnDel.setCursor(cs);
        btnRetype.setCursor(cs);
        btnUndo.setCursor(cs);
        btnRead.setCursor(cs);
        btnAdd.setPreferredSize(new Dimension(130, 50));
        btnUpdate.setPreferredSize(new Dimension(130, 50));
        btnDel.setPreferredSize(new Dimension(130, 50));
        btnRetype.setPreferredSize(new Dimension(130, 50));
        btnUndo.setPreferredSize(new Dimension(130, 50));
        btnSearch.setPreferredSize(new Dimension(130, 50));
        btnRead.setPreferredSize(new Dimension(130, 50));
        btnAdd.setBackground(new Color(255, 255, 255));
        btnUpdate.setBackground(new Color(255, 255, 255));
        btnDel.setBackground(new Color(255, 255, 255));
        btnRetype.setBackground(new Color(255, 255, 255));
        btnRead.setBackground(new Color(255, 255, 255));
        btnUndo.setBackground(new Color(255, 255, 255));
        btnSearch.setBackground(new Color(255, 255, 255));
        btnAdd.setIcon(new ImageIcon(btnIcon[0]));
        btnUpdate.setIcon(new ImageIcon(btnIcon[1]));
        btnDel.setIcon(new ImageIcon(btnIcon[2]));
        btnUndo.setIcon(new ImageIcon(btnIcon[3]));
        btnRetype.setIcon(new ImageIcon(btnIcon[4]));
        btnSearch.setIcon(new ImageIcon(btnIcon[5]));

    }
    private JButton btnAdd=new JButton("  Thêm");
    private JButton btnUpdate=new JButton("  Sửa");
    private JButton btnDel=new JButton("  Xóa");
    private JButton btnUndo=new JButton("  Hoàn tác");
    private JButton btnSearch=new JButton("  Tìm kiếm");
    private JButton btnRetype=new JButton("  Nhập lại");
    private JButton btnRead=new JButton("  Đọc danh sách");
}
