package GUI.Components;


import javax.swing.*;
import java.awt.*;

public class LabelCustom extends JLabel {
    public LabelCustom(String text){
        setFont(new Font("Segoe UI", 0, 16));
        setText(text);
    }
}
