package DAL;

import DTO.DanhmucDTO;
import GUI.QL_Sanpham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DanhmucDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public DanhmucDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getlistDanhmuc(){
        String sql="SELECT * FROM loaihang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                DanhmucDTO sp=new DanhmucDTO();
                sp.setMaloai(rs.getInt("maloai"));
                sp.setTenloai(rs.getString("tenloai"));
                QL_Sanpham.listDM.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Sanpham.listDM.size()!=0) return true;
        return false;
    }

    public boolean addDanhmuc(DanhmucDTO s){
        String sql="INSERT INTO `loaihang` (`tenloai`) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTenloai());
//            ps.setString(2,s.getChitiet());
            return ps.execute();
        }catch (Exception e){}
        return true;
    }
    public boolean addDanhmucUndo(DanhmucDTO s){
        String sql="INSERT INTO `loaihang`(`maloai`,`tenloai`, `chitiet`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,s.getMaloai());
            ps.setString(2,s.getTenloai());
            ps.setString(3,s.getChitiet());
            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateDanhmuc(DanhmucDTO s){
        String sql="UPDATE `loaihang` SET `tenloai`=?,`chitiet`=? WHERE `maloai`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTenloai());
            ps.setString(2,s.getChitiet());
            ps.setInt(3,s.getMaloai());

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return true;
    }
    public boolean delDanhmuc(DanhmucDTO s){
        String sql = "DELETE from loaihang where maloai=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, s.getMaloai());
            ps.execute();
        }catch (Exception e){}
        return true;
    }
}
