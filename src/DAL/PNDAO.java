package DAL;

import DTO.PNDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PNDAO {
    Statement st;
    ResultSet rs;
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public PNDAO(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    //xuat cai phieu nhap moi tao
    public ArrayList <PNDTO> doc1(){
        ArrayList <PNDTO> dspn=new ArrayList<>();
        try{
            String sql="SELECT * from phieunhaphang where tongtien=0";
            st= conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                PNDTO pn=new PNDTO();
                pn.setMapnh(rs.getInt(1));
                pn.setManv(rs.getInt(2));
                pn.setNgay(rs.getString(3));
                pn.setMancc(rs.getInt(4));
                pn.setTongtien(rs.getInt(5));
                dspn.add(pn);
            }
        }catch (SQLException ex){
            System.out.println("loi doc1 DAL.PNDAO");
        }
        return dspn;
    }
    //tao phieu moi
    public void them(PNDTO pn){
        try{
            String sql="INSERT into phieunhaphang(manv,ngay,mancc,tongtien) values("+pn.getManv()+",'"+pn.getNgay()+"',"+pn.getMancc()+","+pn.getTongtien()+")";
            st=conn.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("loi them DAL.PNDAO");
        }
    }
    //luu tong tien pn moi
    public void luutongtien(int mapnh, int tongtien){
        try{
            String sql="UPDATE phieunhaphang set tongtien="+tongtien+" where mapnh="+mapnh+"";
            st=conn.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("loi luu tong tien DAL.PNDAO");
        }
    }
    //xuat lich su phieu nhap
    public ArrayList <PNDTO> docls(){
        ArrayList <PNDTO> dspn=new ArrayList<>();
        try{
            String sql="SELECT * from phieunhaphang where tongtien>0";
            st= conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                PNDTO pn=new PNDTO();
                pn.setMapnh(rs.getInt(1));
                pn.setManv(rs.getInt(2));
                pn.setNgay(rs.getString(3));
                pn.setMancc(rs.getInt(4));
                pn.setTongtien(rs.getInt(5));
                dspn.add(pn);
            }
        }catch (SQLException ex){
            System.out.println("loi doc ls pn DAL.PNDAO");
        }
        return dspn;
    }
}
