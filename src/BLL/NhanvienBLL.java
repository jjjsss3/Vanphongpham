package BLL;

import DAL.NhanvienDAL;
import DTO.NhanvienDTO;
import GUI.QL_Nhanvien;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class NhanvienBLL extends Component {
    public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    NhanvienDAL nhanvienDAL=new NhanvienDAL();
    public boolean getListNhanvien() {
        if(nhanvienDAL.getlistNhanvien()) return true;
        return false;
    }
    public boolean addNhanvien(NhanvienDTO nhanvienDTO, int checklist) {
        if(checklist==0){
            boolean result = nhanvienDAL.addNhanvien(nhanvienDTO);
            if(result) QL_Nhanvien.listNV.add(nhanvienDTO);
            return result;
        }
        else {
            QL_Nhanvien.listNV.add(nhanvienDTO);
            return true;
        }
    }
    public boolean updateNhanvien(NhanvienDTO nhanvienDTO, int index, int checklist, int checksearch) {
        if(checklist==0){
            if(nhanvienDAL.updateNhanvien(nhanvienDTO)){
                if(checksearch==1) {
                    int count = 0;
                    for (NhanvienDTO s : QL_Nhanvien.listNV) {
                        if (s.getMa().equals(nhanvienDTO.getMa())) {
                            QL_Nhanvien.listNV.set(count, nhanvienDTO);
                            break;
                        }
                        count++;
                    }
                }else
                    QL_Nhanvien.listNV.set(index, nhanvienDTO);
                return true;
            }
        }
        else
            QL_Nhanvien.listNV.set(index, nhanvienDTO);
        return true;
    }

    public boolean delNhanvien(int index, int checklist) {
        NhanvienDTO nv= QL_Nhanvien.listNV.get(index);
        if(checklist==0){
            if(nhanvienDAL.delNhanvien(nv)) {
                QL_Nhanvien.listNV.remove(index);
                return true;
            }
        }
        else
            QL_Nhanvien.listNV.remove(index);
        return true;
    }


    public int undo(ArrayList<NhanvienDTO> list,NhanvienDTO temp, int index, int checklist, int checksearch) {
        if(index<list.size()) {
            if (temp.getMa().equals(list.get(index).getMa())) {
                updateNhanvien(temp, index,checklist,checksearch);
                return 1;
            } else {
                addNhanvien(temp,checklist);
//                QL_Nhanvien.listNV.add(index, temp);
                return 2;
            }
        }else {
            addNhanvien(temp,checklist);
//            QL_Nhanvien.listNV.add(index,temp);
            return 2;
        }
    }
    public ArrayList<NhanvienDTO> search(NhanvienDTO nv, String age) {
        ArrayList<NhanvienDTO >list=new ArrayList<>();
    {
            for (NhanvienDTO s : QL_Nhanvien.listNV) {
                if (checkObject(s, nv)&&searchnam(age,s)) list.add(s);
            }
        }
        return list;
    }
    public boolean searchnam(String age, NhanvienDTO nhanvienDTO) {
        if(age.equals("")) return true;
        String tuoi=age.replaceAll("\\s+", "");
        int sosanh=0;
        if(tuoi.matches("(?m)^([>])\\d{2,3}$")) sosanh=1;
        else if(tuoi.matches("(?m)^([<])\\d{2,3}$")) sosanh=2;
            else if(tuoi.matches("(?m)^([=])\\d{2,3}$")){ sosanh=3;};
        int tuois=0;
        if(sosanh!=0){
            String ngaysinha[]=new String[3];
            ngaysinha = formatshow.format(nhanvienDTO.getDob()).split("-");
            LocalDate date1 = LocalDate.of(Integer.parseInt(ngaysinha[2]),Integer.parseInt(ngaysinha[1]),Integer.parseInt(ngaysinha[0]));
            LocalDate now = LocalDate.now();
            Period diff1 = Period.between(date1, now);
            tuois=Integer.parseInt(tuoi.substring(1,tuoi.length()));

            if(sosanh==1){
                if(diff1.getYears()>tuois) return true;
                else return false;
            }
            if(sosanh==2){
                if(diff1.getYears()<tuois) return true;
                else return false;
            }
            if(sosanh==3){
                if(diff1.getYears()==tuois) return true;
                else return false;
            }
        }
       return false;
    }
    public boolean checkObject(NhanvienDTO a, NhanvienDTO b){
        int check=0;
        if(b.getMa()!=null){ if(a.getMa().toLowerCase().contains(b.getMa().toLowerCase())) check=1; else return false;
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
        if(check==1){
            if(b.getMachucvu()!=-1)
            { if(a.getMachucvu()==b.getMachucvu()) check=1;
            else return false;
            }else check=1;
        }
        if(check==1) return true;
        return false;
    }
}
