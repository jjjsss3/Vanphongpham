package BLL;

import DAL.KhachhangDAL;
import DTO.KhachhangDTO;
import DTO.NhanvienDTO;
import GUI.QL_Khachhang;
import GUI.QL_Nhanvien;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhachhangBLL {
    public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");

    KhachhangDAL khachhangDAL = new KhachhangDAL();
    public boolean getListKhachhang() {
        if (khachhangDAL.getlistKhachhang()) return true;
        return false;
    }
    public boolean checkMaKH(String makh, ArrayList<KhachhangDTO> list) {
        for (KhachhangDTO s: list
        ) {
            if(s.getMa().equals(makh)) return false;
        }
        return true;
    }
    public boolean updateKhachhang(KhachhangDTO khachhangDTO, int index, int checksearch) {
            if(khachhangDAL.updateKhachhang(khachhangDTO)){
                if(checksearch==1) {
                    int count = 0;
                    for (KhachhangDTO s : QL_Khachhang.listKH) {
                        if (s.getMa().equals(khachhangDTO.getMa())) {
                            QL_Khachhang.listKH.set(count, khachhangDTO);
                            break;
                        }
                        count++;
                    }
                }else
                    QL_Khachhang.listKH.set(index, khachhangDTO);
                return true;
            }
            else return false;
    }
    public boolean delKhachhang(int index) {
        KhachhangDTO kh = QL_Khachhang.listKH.get(index);
        if (khachhangDAL.delKhachhang(kh)) {
            QL_Khachhang.listKH.remove(index);
            return true;
        }
        return false;
    }
    public boolean addKH(KhachhangDTO khachhangDTO){
        boolean result = khachhangDAL.addKhachhang(khachhangDTO);
        if (result) {
            QL_Khachhang.listKH.add(khachhangDTO);
        }
        return result;
    }
    public int getIDKHAdded() throws SQLException {
        return khachhangDAL.getIDKHAdd();
    }
    public int undo(ArrayList<KhachhangDTO> list,KhachhangDTO temp, int index, int checksearch) {
        if(index< QL_Khachhang.listKH.size()){
            if(temp.getMa().equals(list.get(index).getMa())){
                updateKhachhang(temp,index, checksearch);
                return 1;
            }else{
                addKH(temp);
                return 2;
            }
        }else {
            addKH(temp);
            return 2;
        }
    }
    public ArrayList<KhachhangDTO> search(KhachhangDTO nv, String dtl) {
        ArrayList<KhachhangDTO >list=new ArrayList<>();
        {
            for (KhachhangDTO s : QL_Khachhang.listKH) {
                if (checkObject(s, nv)&&searchDTL(dtl,s)) list.add(s);
            }
        }
        return list;
    }
    public boolean searchDTL(String dtl, KhachhangDTO khachhangDTO) {
        if(dtl.equals("")) return true;
        String diem=dtl.replaceAll("\\s+", "");
        int sosanh=0;
        if(diem.matches("(?m)^([>])\\d{1,}$")) sosanh=1;
        else if(diem.matches("(?m)^([<])\\d{1,}$")) sosanh=2;
        else if(diem.matches("(?m)^([=])\\d{1,}$")){ sosanh=3;};
        int diems=0;
        if(sosanh!=0){
            diems=khachhangDTO.getDtl();
            int diema=Integer.parseInt(diem.substring(1,diem.length()));
            if(sosanh==1){
                if(diema<diems) return true;
                else return false;
            }
            if(sosanh==2){
                if(diema>diems) return true;
                else return false;
            }
            if(sosanh==3){
                if(diema==diems) return true;
                else return false;
            }
        }
        return false;
    }
    public boolean checkObject(KhachhangDTO a, KhachhangDTO b){
        int check=0;
        if(b.getMa()!=null&& b.getMa()!="-1"){ if(a.getMa().toLowerCase().contains(b.getMa().toLowerCase())) check=1; else return false;
        } else check=1;
        if(check==1){
            if(b.getHo()!=null){ if(a.getHo().toLowerCase().contains(b.getHo().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getTen()!=null){ if(a.getTen().toLowerCase().contains(b.getTen().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getGioitinh()!=null){ if(a.getGioitinh().toLowerCase().contains(b.getGioitinh().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getSdt()!=null){
                if(a.getSdt().contains(b.getSdt())) check=1;else return false;
            } else check=1;
        }
        if (check == 1) {
            if (b.getDtl() != -1) {
                if (a.getDtl() == b.getDtl()) check = 1;
                else return false;
            } else check = 1;
        }
        if (check == 1) return true;
        return false;
    }
}
