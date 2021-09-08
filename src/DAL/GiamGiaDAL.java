package DAL;

import DTO.*;
import GUI.QL_Giamgia;

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

                QL_Giamgia.listAllCTGG.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Giamgia.listAllCTGG.size()!=0) return true;
        return false;
    }
    public boolean addKM(GiamGiaDTO g) throws SQLException {
        String sql="INSERT INTO khuyenmai VALUES(?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,g.getMakm());
        ps.setString(2,g.getTenkm());
        ps.setString(3,formatter.format(g.getNgaybd()));
        ps.setString(4,formatter.format(g.getNgaykt()));
        return ps.executeUpdate()>0;
    }
    public boolean addCTKM(ChitietGiamgiaDTO c) throws SQLException {
        String sql="INSERT INTO chitietkm VALUES(?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,c.getMakm());
        ps.setInt(2,c.getMasp());
        ps.setInt(3,c.getPhantramkm());
        return ps.execute();
    }
    public boolean delCTKM(String makm) throws SQLException {
        String sql="DELETE from chitietkm where makm=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,makm);
        return ps.execute();
    }
    public boolean updateKM(GiamGiaDTO g) throws SQLException {
        String sql="UPDATE khuyenmai set makm=?, tenkm=? where makm=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,g.getMakm());
        ps.setString(2,g.getTenkm());
        ps.setString(3,g.getMakm());
        return ps.executeUpdate()>0;
    }
    public boolean updateCTKM(ChitietGiamgiaDTO g) throws SQLException {
        String sql="UPDATE chitietkm SET phantramkm=? WHERE masp=? and makm=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,g.getPhantramkm());
        ps.setInt(2,g.getMasp());
        ps.setString(3,g.getMakm());
        return ps.executeUpdate()>0;
    }
    public boolean delKM(String makm) throws SQLException {
        String sql="DELETE from khuyenmai where makm=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,makm);
        return ps.executeUpdate()>0;
    }
    public boolean delCTKM(ChitietGiamgiaDTO g) throws SQLException {
        String sql="DELETE from chitietkm where masp=? and makm=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,g.getMasp());
        ps.setString(2,g.getMakm());
        return ps.executeUpdate()>0;
    }
}
