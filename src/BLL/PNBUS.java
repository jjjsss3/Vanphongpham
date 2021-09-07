package BLL;

import DAL.PNDAO;
import DTO.PNDTO;

import java.util.ArrayList;

public class PNBUS {
    PNDAO pndao=new PNDAO();
    public static ArrayList <PNDTO> dspn=new ArrayList<>();
    public PNBUS(){}
    //xuat danh sach pn moi tao
    public void doc(){
        dspn=pndao.doc1();
    }
    //them ctpn vao dsctpn nhung ko them vao database
    public void them(PNDTO pn){
        pndao.them(pn);
    }
    //luu tong tien cua phieu nhap moi
    public void luutongtien(int mapnh, int tongtien){
        pndao.luutongtien(mapnh,tongtien);
    }
    //xuat lich su phieu nhap
    public void docls(){
        dspn=pndao.docls();
    }
}
