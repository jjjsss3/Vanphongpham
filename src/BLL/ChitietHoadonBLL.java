package BLL;

import DAL.ChitietHoadonDAL;
import DAL.HoaDonDAL;
import DTO.ChitietHoadonDTO;
import DTO.HoaDonDTO;
import GUI.QL_HoaDon;
import GUI.QL_TaoHoaDon;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChitietHoadonBLL {
    ChitietHoadonDAL chitietHoadonDAL=new ChitietHoadonDAL();
    HoaDonDAL hoaDonDAL=new HoaDonDAL();
    public boolean getListCTHD(){
        if(chitietHoadonDAL.getListCTHD()) return true;
        return false;
    }
    public boolean addListCTHD(ArrayList<ChitietHoadonDTO>list) throws SQLException {
        int mahd=hoaDonDAL.getHDAdded();
        for (ChitietHoadonDTO sp: list) {
            sp.setMahd(mahd);
            addCTHD(sp);
        }
        return true;
    }
    public boolean addCTHD(ChitietHoadonDTO hd) throws SQLException {
        boolean result = chitietHoadonDAL.addCTHD(hd);
        if(result){
            QL_HoaDon.listCTHD.add(hd);
        }
        return result;
    }


}
