package BLL;

import DAL.HoaDonDAL;
import DAL.PhieunhaphangDAL;
import DTO.*;
import GUI.QL_HoaDon;
import GUI.QL_NhapHang;
import GUI.QL_TaoHoaDon;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhieunhaphangBLL {
    PhieunhaphangDAL phieunhaphangDAL=new PhieunhaphangDAL();
    public boolean getListPNH() {
        if(phieunhaphangDAL.getListPNH()) return true;
        return false;
    }
    public boolean addPNH(PhieunhaphangDTO h) {
        boolean result = phieunhaphangDAL.addHD(h);
        if(result){
            h.setMapnh(phieunhaphangDAL.getPNHAdded());
            QL_NhapHang.listPNH.add(h);
        }
        return result;
    }

    public boolean updateSoluong(ArrayList<CTPhieunhaphangDTO>list) {
        boolean result = phieunhaphangDAL.updateSoluong(list);
        return result;
    }

    public boolean delPNH(int mapnh) throws SQLException {
        if(phieunhaphangDAL.delPNH(mapnh)) {
            int i=0;
            for (PhieunhaphangDTO h: QL_NhapHang.listPNH
            ) {
                if(h.getMapnh()==mapnh) QL_NhapHang.listPNH.remove(i);
                i++;
            }
            for (CTPhieunhaphangDTO h: QL_NhapHang.listCTPNH
            ) {
                i++;
                if(h.getMapnh()==mapnh) QL_NhapHang.listCTPNH.remove(i);
            }
        }
        return true;
    }
}
