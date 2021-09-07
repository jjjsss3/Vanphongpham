package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Application extends JFrame{

    public static void main(String[] args) throws IOException, FontFormatException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Dangnhap();
    }
}
