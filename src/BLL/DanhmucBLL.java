package BLL;

import DAL.DanhmucDAL;
import DTO.DanhmucDTO;
import GUI.QL_Sanpham;

import java.sql.SQLException;
import java.util.ArrayList;

public class DanhmucBLL {
    DanhmucDAL danhmucDAL=new DanhmucDAL();
    public boolean getListDanhmuc() {
        if(danhmucDAL.getlistDanhmuc()) return true;
        return false;
    }
    public boolean addDM(DanhmucDTO danhmucDTO) {
        boolean result = danhmucDAL.addDanhmuc(danhmucDTO);
        if(result){
            QL_Sanpham.listDM.add(danhmucDTO);
        }
        return result;
    }
    public boolean updateDanhmuc(DanhmucDTO danhmucDTO, int maloai,  int index) {
        if(maloai==(danhmucDTO.getMaloai())) {
            if(danhmucDAL.updateDanhmuc(danhmucDTO)){
                QL_Sanpham.listDM.set(index, danhmucDTO);
                return true;}
        }
        return false;
    }
    public boolean delDM(int index) throws SQLException {
        DanhmucDTO sp= QL_Sanpham.listDM.get(index);
        if(danhmucDAL.delDanhmuc(sp)) {
            QL_Sanpham.listDM.remove(index);
            return true;
        }
        return false;
    }public int undo(DanhmucDTO temp, int index) throws SQLException {
        if(index<QL_Sanpham.listDM.size()){
            if(temp.getMaloai()==(QL_Sanpham.listDM.get(index).getMaloai())){ updateDanhmuc(temp,temp.getMaloai(),index);
                return 1;
            }else{
                danhmucDAL.addDanhmucUndo(temp);
                QL_Sanpham.listDM.add(index,temp);
                return 2;
            }
        }else{
            danhmucDAL.addDanhmucUndo(temp);
            QL_Sanpham.listDM.add(index,temp);
            return 2;
        }
    }
    public boolean checkTensp(String tensp, ArrayList<DanhmucDTO> list) {
        for (DanhmucDTO s: list) {
            if(s.getTenloai().replaceAll("\\s+", "").toLowerCase().equals(tensp.replaceAll("\\s+", "").toLowerCase()))
                return false;
        }
        return true;
    }
}
