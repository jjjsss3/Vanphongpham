package BLL;

import DAL.DangnhapDAL;
import DTO.NhanvienDTO;
import DTO.QuyenDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public class DangnhapBLL {
    DangnhapDAL dangnhapDAL=new DangnhapDAL();

    public boolean dangnhap(String manv, String matkhau, NhanvienDTO nv) {
        boolean result = dangnhapDAL.dangnhap(manv, matkhau,nv );
        return result;
    }
    public boolean getQuyenNV(int machucvu) throws SQLException {
        if(dangnhapDAL.getQuyenNV(machucvu)) return true;
        return false;
    }
    public boolean getQuyen(ArrayList<QuyenDTO> list) throws SQLException {
        if(dangnhapDAL.getQuyen()!=null){
            list=dangnhapDAL.getQuyen();
            return true;}
        return false;
    }


}
