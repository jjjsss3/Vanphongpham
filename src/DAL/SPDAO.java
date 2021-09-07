package DAL;

import DTO.SPDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SPDAO {

    Statement st;
    ResultSet rs;
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public SPDAO(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    public ArrayList <SPDTO> doc(){
        ArrayList <SPDTO> dssp=new ArrayList<>();
        try{
            String sql="SELECT * from sanpham";
            st= conn.createStatement();
            rs=st.executeQuery(sql);
            while (rs.next()){
                SPDTO sp=new SPDTO();
                sp.setMasp(rs.getInt(1));
                sp.setTensp(rs.getString(2));
                sp.setMaloai(rs.getInt(3));
                sp.setAnh(rs.getString(4));
                sp.setDongia(rs.getInt(5));
                sp.setSoluong(rs.getInt(6));
                dssp.add(sp);
            }
        }
        catch (SQLException ex){
            System.out.println("loi doc DAL.SPDAO");
        }
        return dssp;
    }
    public void them(SPDTO sp){
        try{
            String sql="INSER into sanpham(masp,tensp,maloai,anh,dongia,soluong) values("+sp.getMasp()+",'"+sp.getTensp()+"',"+sp.getMaloai()+",'"+sp.getAnh()+"',"+sp.getDongia()+","+sp.getSoluong()+")";
            st= conn.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("loi them DAL.SPDAO");
        }
    }
    public void xoa(int masp){
        try{
            String sql="DELETE from sanpham where masp="+masp+"";
            st= conn.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("loi xoa DAL.SPDAO");
        }
    }
    public void sua(SPDTO sp){
        try{
            String sql="UPDATE sanpham set tensp='"+sp.getTensp()+"',maloai="+sp.getMaloai()+",anh='"+sp.getAnh()+"',dongia="+sp.getDongia()+",soluong="+sp.getSoluong()+" where masp="+sp.getMasp()+" ";
            st= conn.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex){
            System.out.println("loi sua DAL.SPDAO");
        }
    }
}
