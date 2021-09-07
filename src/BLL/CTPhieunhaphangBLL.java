package BLL;

import DAL.*;
import DTO.CTPhieunhaphangDTO;
import DTO.ChitietHoadonDTO;
import DTO.HoaDonDTO;
import DTO.SanphamDTO;
import GUI.QL_HoaDon;
import GUI.QL_NhapHang;
import GUI.QL_TaoHoaDon;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CTPhieunhaphangBLL {
    CTPhieunhaphangDAL ctPhieunhaphangDAL=new CTPhieunhaphangDAL();
    PhieunhaphangDAL phieunhaphangDAL=new PhieunhaphangDAL();
    SanphamBLL sanphamBLL=new SanphamBLL();
    public boolean getListCTPNH(){
        if(ctPhieunhaphangDAL.getListCTPNH()) return true;
        return false;
    }
    public boolean addListCTPNH(ArrayList<CTPhieunhaphangDTO>list) throws SQLException {
        int mapnh=phieunhaphangDAL.getPNHAdded();
        for (CTPhieunhaphangDTO sp: list) {
            sp.setMapnh(mapnh);
            if(sp.getCheck()==1){
                SanphamDTO s=new SanphamDTO('\0',sp.getTensp(),sp.getMaloai(),sp.getSoluong(),sp.getDongia(),"");
                sanphamBLL.addSanpham(s);
                int masp=new SanphamDAL().getSPAdded();
                new SanphamDAL().updateImg();
                sp.setMasp(masp);
                addCTHD(sp);
            }else
                addCTHD(sp);
        }
        return true;
    }
    public boolean addCTHD(CTPhieunhaphangDTO h) throws SQLException {
        boolean result = ctPhieunhaphangDAL.addCTPNH(h);
        if(result){
            QL_NhapHang.listCTPNH.add(h);
        }
        return result;
    }


}
