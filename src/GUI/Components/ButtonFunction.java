package GUI.Components;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ButtonFunction extends JButton {
    private Image btnIcon;
    private Cursor cs=new Cursor(Cursor.HAND_CURSOR);
    public ButtonFunction(String function){
        String icon = null;
        try{
        if(function=="Thêm"){
            icon="/GUI/images/icon/2add.png";
        }
        if(function=="Sửa"){
            icon="/GUI/images/icon/2update.png";
        }
        if(function=="Xóa"){
            icon="/GUI/images/icon/2del.png";
        }
        if(function=="Hoàn tác"){
            icon="/GUI/images/icon/2undo.png";
        }if(function=="Tìm kiếm"){
            icon="/GUI/images/icon/2search.png";
        }if(function=="Nhập lại"){
            icon="/GUI/images/icon/2retype.png";
        }
        if(function=="Đọc từ server"){
            icon="/GUI/images/icon/2list.png";
        }
        if(function=="Đọc file Excel"){
            icon="/GUI/images/icon/2excel.png";
        }
        if(function=="ĐĂNG NHẬP HỆ THỐNG"){
            icon="/GUI/images/icon/2login.png";
        }
        if(function=="Thoát"){
            icon="/GUI/images/icon/2exit.png";
        }
        if(function=="Xuất file PDF"){
            icon="/GUI/images/icon/2pdf.png";
        }
        if(function=="Tải ảnh"){
            icon="/GUI/images/icon/2upload.png";
        }
        if(function=="Tạo hóa đơn"){
            icon="/GUI/images/icon/2cart.png";
        }
        if(function=="Quản lí hóa đơn"){
            icon="/GUI/images/icon/2listorder.png";
        }
        if(function=="Quản lí phiếu nhập"){
            icon="/GUI/images/icon/2listorder.png";
        }
        if(function=="Tạo phiếu nhập"){
            icon="/GUI/images/icon/2supply.png";
        }
        btnIcon = ImageIO.read(getClass().getResource(icon));
        setText("  "+function);
        setBackground(Color.WHITE);
        setFont(new Font("Segoe UI", 0, 13));
        setPreferredSize(new Dimension(123, 50));
        if(function=="Đọc từ server"||function=="Đọc file Excel"||function=="Xuất file PDF"
                ||function=="Tạo hóa đơn"||function=="Quản lí hóa đơn"||function=="Quản lí phiếu nhập"||function=="Tạo phiếu nhập"){
            setPreferredSize(new Dimension(190, 50));
        }
        setIcon(new ImageIcon(btnIcon));
        setCursor(cs);
    }catch (Exception e){};
    }
}
