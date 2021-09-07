package BLL;

import DAL.SPDAO;
import DTO.SPDTO;

import java.util.ArrayList;

public class SPBUS {
    ArrayList <SPDTO> dssp;
    static SPDAO spdao=new SPDAO();
    SPBUS(){

    }
    void doc(){
        if(dssp==null){
            dssp=new ArrayList<>();
        }
        dssp=spdao.doc();
    }
    void them(SPDTO sp){
        spdao.them(sp);
    }
    void xoa(int masp){
        spdao.xoa(masp);
    }
    void sua(SPDTO sp){
        spdao.sua(sp);
    }
    SPDTO tim(int masp){
        SPDTO sp=new SPDTO();
        return sp;
    }
}
