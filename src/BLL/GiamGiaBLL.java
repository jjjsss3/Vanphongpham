package BLL;

import DAL.GiamGiaDAL;
import DTO.ChitietGiamgiaDTO;
import DTO.ChitietHoadonDTO;
import DTO.GiamGiaDTO;
import GUI.QL_Giamgia;

import java.sql.SQLException;
import java.util.ArrayList;

public class GiamGiaBLL {
    GiamGiaDAL giamGiaDAL=new GiamGiaDAL();
    public boolean getListGG() {
        if(giamGiaDAL.getListGG()) return true;
        return false;
    }
    public boolean getListCTGG() {
        if(giamGiaDAL.getListCTGG()) return true;
        return false;
    }
    public boolean addKM(GiamGiaDTO g, ArrayList<ChitietGiamgiaDTO> list) throws SQLException {
        if(giamGiaDAL.addKM(g)){
            QL_Giamgia.listGG.add(g);
            for (ChitietGiamgiaDTO c: list) {
                giamGiaDAL.addCTKM(c);
                QL_Giamgia.listAllCTGG.add(c);
            }
            return true;
        }
        return false;
    }
    public boolean delKM(String makm, int index) throws SQLException {
        if(giamGiaDAL.delKM(makm)){
            QL_Giamgia.listGG.remove(index);
            giamGiaDAL.delCTKM(makm);
            QL_Giamgia.listGG.removeIf(giamGiaDTO -> giamGiaDTO.getMakm().equals(makm));
            return true;
        }
        return false;
    }
    public boolean updateSoluongCTKM(ChitietGiamgiaDTO g) throws SQLException {
        return giamGiaDAL.updateCTKM(g);
    }
    public boolean delCTKM(ChitietGiamgiaDTO g) throws SQLException {
        return giamGiaDAL.delCTKM(g);
    }

}
