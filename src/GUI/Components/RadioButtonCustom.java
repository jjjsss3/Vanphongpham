package GUI.Components;


import javax.swing.*;
import java.awt.*;

public class RadioButtonCustom extends JRadioButton {
    public RadioButtonCustom(String text){
        setFont(new Font("Segoe UI", 0, 15));
        setText(text);
    }
}
