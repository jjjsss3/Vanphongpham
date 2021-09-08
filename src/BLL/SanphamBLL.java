package BLL;

import DAL.SanphamDAL;
import DTO.SanphamDTO;
import GUI.QL_Sanpham;

import java.sql.SQLException;
import java.util.ArrayList;

public class SanphamBLL {
    SanphamDAL sanphamDAL=new SanphamDAL();
    public boolean getListSanpham() {
        if(sanphamDAL.getlistSanpham()) return true;
        return false;
    }

    public boolean addSanpham(SanphamDTO sanphamDTO ) throws SQLException {
        boolean result = sanphamDAL.addSanpham(sanphamDTO);
        if(result){
            int masp=sanphamDAL.getSPAdded();
            sanphamDAL.updateImg(masp);
            sanphamDTO.setMasp(masp);
            QL_Sanpham.listSP.add(sanphamDTO);
        }
        return result;
    }
    public boolean addSanphamUndo(SanphamDTO sanphamDTO, int index ) throws SQLException {
        boolean result = sanphamDAL.addSanphamUndo(sanphamDTO);
        if(result){
            QL_Sanpham.listSP.add(index, sanphamDTO);
        }
        return result;
    }
    public boolean updateSanpham(SanphamDTO sanphamDTO, int index) {
            if(sanphamDAL.updateSanpham(sanphamDTO)){
                QL_Sanpham.listSP.set(index, sanphamDTO);
                return true;}
        return false;
    }

    public boolean delSanpham(int masp,int index) {
        if(sanphamDAL.delSanpham(masp)) {
            QL_Sanpham.listSP.remove(index);
            return true;
        }
        return false;
    }


    public int undo(SanphamDTO temp, int index) throws SQLException {
//        if(index<QL_Sanpham.listSP.size()){
//            if(temp.getMasp()==(QL_Sanpham.listSP.get(index).getMasp())){
//                updateSanpham(temp,temp.getMasp(),index);
//                return 1;
//            }else{
//                sanphamDAL.addSanphamUndo(temp);
//                QL_Sanpham.listSP.add(index,temp);
//                return 2;
//            }
//        }else{
//            sanphamDAL.addSanphamUndo(temp);
//            QL_Sanpham.listSP.add(index,temp);
//            return 2;
//        }
        return 0;
    }
    public ArrayList<SanphamDTO> search(SanphamDTO nv, String price, String quantity) {
        ArrayList<SanphamDTO >list=new ArrayList<>();
        {
            for (SanphamDTO s : QL_Sanpham.listSP) {
                if (checkObject(s, nv)&&searchPrice(price,s) &&searchQuantity(quantity,s)) list.add(s);
            }
        }
        return list;
    }
    public boolean checkTensp(String tensp, ArrayList<SanphamDTO> list) {
        for (SanphamDTO s: list) {
            if(s.getTensp().replaceAll("\\s+", "").toLowerCase().equals(tensp.replaceAll("\\s+", "").toLowerCase()))
                return false;
        }
        return true;
    }
    public boolean searchPrice(String price, SanphamDTO sanphamDTO) {
        if(price.equals("")) return true;
        String prices=price.replaceAll("\\s+", "");
        int sosanh=0;
        if(prices.matches("(?m)^([>])\\d+$")||prices.matches("(?m)^([>])\\d{1,3}(?:(\\,\\d{3})+(?!\\d))$")) sosanh=1;
        else  if(prices.matches("(?m)^([<])\\d+$")||prices.matches("(?m)^([<])\\d{1,3}(?:(\\,\\d{3})+(?!\\d))$")) sosanh=2;
        else  if(prices.matches("(?m)^([=])\\d+$")||prices.matches("(?m)^([=])\\d{1,3}(?:(\\,\\d{3})+(?!\\d))$")) sosanh=3;
        if(sosanh!=0){
            prices=prices.replaceAll(",", "");
            long money=Long.parseLong(prices.substring(1,prices.length()));
            if(sosanh==1){
                if(money<sanphamDTO.getDongia()) return true;
                else return false;
            }
            if(sosanh==2){
                if(money>sanphamDTO.getDongia()) return true;
                else return false;
            }
            if(sosanh==3){
                if(money==sanphamDTO.getDongia()) return true;
                else return false;
            }
        }
        return false;
    }
    public boolean searchQuantity(String quantity, SanphamDTO sanphamDTO) {
        if(quantity.equals("")) return true;
        String soluongs=quantity.replaceAll("\\s+", "");
        int sosanh=0;
        if(soluongs.matches("(?m)^([>])\\d+$")) sosanh=1;
        else if(soluongs.matches("(?m)^([<])\\d+$")) sosanh=2;
        else if(soluongs.matches("(?m)^([=])\\d+$")){ sosanh=3;};
        if(sosanh!=0){
            int soluong=Integer.parseInt(soluongs.substring(1,soluongs.length()));
            if(sosanh==1){
                if(soluong<sanphamDTO.getSoluong()) return true;
                else return false;
            }
            if(sosanh==2){
                if(soluong>sanphamDTO.getSoluong()) return true;
                else return false;
            }
            if(sosanh==3){
                if(soluong==sanphamDTO.getSoluong()) return true;
                else return false;
            }
        }
        return false;
    }



    public boolean checkObject(SanphamDTO a, SanphamDTO b){
        int check=0;
        if(b.getTensp()!=null){ if(a.getTensp().toLowerCase().contains(b.getTensp().toLowerCase())) check=1; else return false;
        } else check=1;
        if(check==1){
            if(b.getMaloai()!='\0'){ if(a.getMaloai()==(b.getMaloai())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getMancc()!='\0'){ if(a.getMancc()==(b.getMancc())) check=1;else return false;
            } else check=1;
        }
        if(check==1) return true;
        return false;
    }
}
