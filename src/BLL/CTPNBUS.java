package BLL;

import DAL.CTPNDAO;
import DTO.CTPNDTO;

import java.util.ArrayList;

public class CTPNBUS {
    CTPNDAO ctpndao=new CTPNDAO();
    public static ArrayList <CTPNDTO> dsctpn=new ArrayList<>();
    public CTPNBUS(){}
    //them ctpn vao dsctpn nhung ko them vao database
    public void them(CTPNDTO ctpn){
        dsctpn.add(ctpn);
    }
    public void sua(CTPNDTO ctpn){
        for(int i=0;i<dsctpn.size();i++){
            if(ctpn.getMapnh()==dsctpn.get(i).getMapnh() && ctpn.getMasp()==dsctpn.get(i).getMasp()){
                dsctpn.get(i).setSoluong(ctpn.getSoluong());
                dsctpn.get(i).setThanhtien(ctpn.getThanhtien());
            }
        }
    }
    public void xoa(int mapnh, int masp){
        for(int i=0;i<dsctpn.size();i++){
            if(mapnh==dsctpn.get(i).getMapnh() && masp==dsctpn.get(i).getMasp()){
                dsctpn.remove(i);
            }
        }
    }
    public void reset(int mapnh){
        for(int i=0;i<dsctpn.size();i++){
            dsctpn.remove(i);
        }
    }
    // them ctpn vao database
    public void add(CTPNDTO ctpn){
        ctpndao.them(ctpn);
    }

    //doc chi tiet phieu nhap o lich su
    public void chitietpn(int mapn){
        dsctpn=new ArrayList<CTPNDTO>();
        dsctpn=ctpndao.chitietpn(mapn);
    }

}
