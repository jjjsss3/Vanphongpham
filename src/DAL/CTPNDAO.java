package DAL;

import DTO.CTPNDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class CTPNDAO {
    Statement st;
    ResultSet rs;
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public CTPNDAO(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    //them dspn vao database
    public void them(CTPNDTO ctpn){
        try{
            //them vao bang chi tiet
            String sql="INSERT into chitietpnh(mapnh,masp,soluong,dongianhap,thanhtien) values("+ctpn.getMapnh()+","+ctpn.getMasp()+","+ctpn.getSoluong()+","+ctpn.getDongianhap()+","+ctpn.getThanhtien()+")";
            st=conn.createStatement();
            st.executeUpdate(sql);
            try{
                //them vao bang san pham
                String sql1="UPDATE sanpham set soluong=soluong+"+ctpn.getSoluong()+" where masp="+ctpn.getMasp()+"";
                st=conn.createStatement();
                st.executeUpdate(sql1);
            }
            catch (SQLException ex){
                System.out.println("loi them sp vao kho DAL.CTPNDAO");
            }
        }catch (SQLException ex){
            System.out.println("loi them vao DAL.CTPNDAO");
        }

    }
    //doc chi tiet phieu nhap o lich su
    public ArrayList <CTPNDTO> chitietpn(int mapn){
        ArrayList <CTPNDTO> dsctpn=new ArrayList<>();
        try {
            String sql="SELECT * from chitietpnh where mapnh="+mapn+"";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                CTPNDTO ctpn=new CTPNDTO();
                ctpn.setMapnh(rs.getInt(1));
                ctpn.setMasp(rs.getInt(2));
                ctpn.setSoluong(rs.getInt(3));
                ctpn.setDongianhap(rs.getInt(4));
                ctpn.setThanhtien(rs.getInt(5));
                dsctpn.add(ctpn);
            }
        }catch (SQLException ex){
            System.out.println("loi doc DAL.CTPNDAO");
        }
        return dsctpn;
    }
}
