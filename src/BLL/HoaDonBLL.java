package BLL;

import DAL.HoaDonDAL;
import DTO.ChitietHoadonDTO;
import DTO.HoaDonDTO;
import DTO.SanphamDTO;
import GUI.QL_HoaDon;
import GUI.QL_TaoHoaDon;

import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDonBLL {
    HoaDonDAL hoaDonDAL=new HoaDonDAL();
    public boolean getListHD() {
        if(hoaDonDAL.getListHoadon()) return true;
        return false;
    }
    public boolean addHD(HoaDonDTO hd) {
        boolean result = hoaDonDAL.addHD(hd);
        if(result){
            hd.setMahd(hoaDonDAL.getHDAdded());
            QL_HoaDon.listHD.add(hd);
        }
        return result;
    }

    public boolean updateSoluong(ArrayList<ChitietHoadonDTO>listOD) {
        boolean result = hoaDonDAL.updateSoluong(listOD);
        return result;
    }

    public boolean delHD(int mahd) throws SQLException {

        if(hoaDonDAL.delHD(mahd)) {
            int i=-1;
            for (HoaDonDTO h: QL_HoaDon.listHD
                 ) {
                i++;
                if(h.getMahd()==mahd) QL_HoaDon.listHD.remove(i);
                return true;
            }
        }
        return false;
    }
}
