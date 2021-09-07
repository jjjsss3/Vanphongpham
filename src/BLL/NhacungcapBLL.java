package BLL;

import DAL.NhacungcapDAL;
import DTO.KhachhangDTO;
import DTO.NhacungcapDTO;
import GUI.QL_Khachhang;
import GUI.QL_Nhacungcap;

import java.util.ArrayList;

public class NhacungcapBLL {
    NhacungcapDAL nhacungcapDAL=new NhacungcapDAL();
    public boolean getListNhacungcap() {
        if(nhacungcapDAL.getlistNhacungcap()) return true;
        return false;
    }
    public boolean checkTenncc(String tenncc, ArrayList<NhacungcapDTO> list) {
        for (NhacungcapDTO s: list
        ) {
            if(s.getTenncc().toLowerCase().equals(tenncc.toLowerCase().replace("\\+s",""))) return false;
        }
        return true;
    }
    public boolean addNCC(NhacungcapDTO nhacungcapDTO){
        boolean result = nhacungcapDAL.addNCC(nhacungcapDTO);
        if (result) {
            QL_Nhacungcap.listNCC.add(nhacungcapDTO);
        }
        return result;
    }
    public boolean updateNCC(NhacungcapDTO nhacungcapDTO, int mancc, int index) {
        if(mancc==(nhacungcapDTO.getMancc())) {
            if(nhacungcapDAL.updateNCC(nhacungcapDTO)){
                QL_Nhacungcap.listNCC.set(index, nhacungcapDTO);
                return true;}
        }
        return false;
    }
    public boolean delNCC(int index) {
        NhacungcapDTO kh = QL_Nhacungcap.listNCC.get(index);
        if (nhacungcapDAL.delNCC(kh)) {
            QL_Nhacungcap.listNCC.remove(index);
            return true;
        }
        return false;
    }
    public int undo(NhacungcapDTO temp, int index) {
        if(index< QL_Nhacungcap.listNCC.size()){
            if(temp.getMancc()==(QL_Nhacungcap.listNCC.get(index).getMancc())){
                updateNCC(temp,temp.getMancc(),index);
                return 1;
            }else{
                nhacungcapDAL.addNCC(temp);
                QL_Nhacungcap.listNCC.add(index,temp);
                return 2;
            }
        }else {
            nhacungcapDAL.addNCC(temp);
            QL_Nhacungcap.listNCC.add(index,temp);
            return 2;
        }
    }
    public ArrayList<NhacungcapDTO> search(NhacungcapDTO nv) {
        ArrayList<NhacungcapDTO >list=new ArrayList<>();
        {
            for (NhacungcapDTO s : QL_Nhacungcap.listNCC) {
                if (checkObject(s, nv)) list.add(s);
            }
        }
        return list;
    }
    public boolean checkObject(NhacungcapDTO a, NhacungcapDTO b){
        int check=0;
        if(b.getMancc()!='\0'){ if(a.getMancc()==b.getMancc()) check=1; else return false;
        } else check=1;
        if(check==1){
            if(b.getTenncc()!=null){ if(a.getTenncc().toLowerCase().contains(b.getTenncc().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getSdt()!=null){ if(a.getSdt().toLowerCase().contains(b.getSdt().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if(check==1){
            if(b.getDiachi()!=null){ if(a.getDiachi().toLowerCase().contains(b.getDiachi().toLowerCase())) check=1;else return false;
            } else check=1;
        }
        if (check == 1) return true;
        return false;
    }
}
