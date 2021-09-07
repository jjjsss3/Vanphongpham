package BLL;

import DAL.PhanQuyenDAL;
import DTO.DangnhapDTO;
import DTO.NhanvienDTO;
import DTO.QuyenDTO;
import GUI.QL_Chung;
import GUI.QL_Nhanvien;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhanQuyenBLL {
    PhanQuyenDAL phanQuyenDAL=new PhanQuyenDAL();
    public boolean getListChucvu() {
        if(phanQuyenDAL.getListChuvu()) return true;
        return false;
    }
    public boolean addChucvu(DangnhapDTO chucvu, ArrayList<QuyenDTO> list) throws SQLException {
        if(phanQuyenDAL.addChucvu(chucvu,list)){
            QL_Chung.listChucvu.add(chucvu);
            return true;
        }
        return false;
    }
    public boolean updateChucvu(DangnhapDTO chucvu, ArrayList<QuyenDTO> list, int index) throws SQLException {
        if(phanQuyenDAL.updateChucvu(chucvu,list)){
            QL_Chung.listChucvu.set(index, chucvu);
            return true;
        }
        return false;
    }
    public boolean delChucvu(int machucvu, ArrayList<DangnhapDTO> list, int index) throws SQLException {
        if(QL_Nhanvien.listNV.size()==0) new NhanvienBLL().getListNhanvien();
        for (NhanvienDTO s: QL_Nhanvien.listNV
             ) {if(s.getMachucvu()==machucvu) return false;
        }
        if(phanQuyenDAL.delChucvu(machucvu)){
            list.remove(index);
            return true;
        }
        return false;
    }
}
