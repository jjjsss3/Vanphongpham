package GUI.Components;


import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class CmbDate extends JComboBox {

    public CmbDate(){
    }

    public JComboBox ngay(){
        JComboBox<String> cmb=new JComboBox();cmb.addItem("Ngày ");
        for(int i=1;i<=31;i++){
            cmb.addItem(String.valueOf(i));
        }
        cmb.setBackground(Color.WHITE);
        cmb.setFont(new Font("Segoe UI", 0, 15));
        return cmb;
    }
    public JComboBox thang(){
        JComboBox<String> cmb=new JComboBox();cmb.addItem("Tháng ");
        for(int i=1;i<=12;i++){
            cmb.addItem(String.valueOf(i));
        }
        cmb.setBackground(Color.WHITE);
        cmb.setFont(new Font("Segoe UI", 0, 15));
        return cmb;
    }
    public JComboBox nam(){
        LocalDate localDate= LocalDate.now();
        JComboBox<String> cmb=new JComboBox();cmb.addItem("Năm ");
        for(int i=localDate.getYear();i>=1950;i--){
            cmb.addItem(String.valueOf(i));
        }
        cmb.setBackground(Color.WHITE);
        cmb.setFont(new Font("Segoe UI", 0, 15));
        return cmb;
    }
}
