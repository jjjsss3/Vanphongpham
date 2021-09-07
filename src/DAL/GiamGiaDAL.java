package DAL;

import DTO.*;
import GUI.QL_Chung;
import GUI.QL_Giamgia;
import GUI.QL_Sanpham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GiamGiaDAL {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public GiamGiaDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    public boolean getListGG(){
        String sql="SELECT * FROM khuyenmai";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                GiamGiaDTO gg=new GiamGiaDTO();
                gg.setMakm(rs.getString("makm"));
                gg.setTenkm(rs.getString("tenkm"));
                gg.setNgaybd(rs.getDate("ngaybd"));
                gg.setNgaykt(rs.getDate("ngaykt"));
                QL_Giamgia.listGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Giamgia.listGG.size()!=0) return true;
        return false;
    }
    public boolean getListCTGG(){
        String sql="SELECT * FROM chitietkm";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ChitietGiamgiaDTO gg=new ChitietGiamgiaDTO();
                gg.setMakm(rs.getString("makm"));
                gg.setMasp(rs.getInt("masp"));
                gg.setPhantramkm(rs.getInt("phantramkm"));

                QL_Giamgia.listCTGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Giamgia.listCTGG.size()!=0) return true;
        return false;
    }


}
