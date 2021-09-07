package GUI.Components;


import javax.swing.*;
import java.awt.*;

public class TextFieldCustom extends JTextField {
    public TextFieldCustom(String text){
        setFont(new Font("Segoe UI", 0, 18));
        setText(text);
        setColumns(15);
    }
}
