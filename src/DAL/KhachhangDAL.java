package DAL;

import DTO.KhachhangDTO;
import GUI.QL_Khachhang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class KhachhangDAL {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public KhachhangDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getlistKhachhang(){
        String sql="SELECT * FROM khachhang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                KhachhangDTO kh=new KhachhangDTO();
                kh.setMa(rs.getString("makh"));
                kh.setHo(rs.getString("hokh"));
                kh.setTen(rs.getString("tenkh"));
                kh.setGioitinh(rs.getString("gioitinh"));
                kh.setDob(rs.getDate("ngaysinh"));
                kh.setDiachi(rs.getString("diachi"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDtl(rs.getInt("dtl"));
                QL_Khachhang.listKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Khachhang.listKH.size()!=0) return true;
        return false;
    }
    public int getIDKHAdd() throws SQLException {
        String sql="SELECT makh from khachhang order by makh desc limit 1";
        PreparedStatement ps=conn.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        return rs.getInt("makh");
    }

    public boolean addKhachhang(KhachhangDTO s){
        String sql="INSERT INTO `khachhang` (`tenkh`, `hokh`,`gioitinh`, `ngaysinh`, `diachi`, `sdt`, `dtl`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTen());
            ps.setString(2,s.getHo());
            ps.setString(3,s.getGioitinh());
            ps.setString(4, formatter.format(s.getDob()));
            ps.setString(5,s.getDiachi());
            ps.setString(6,s.getSdt());
            ps.setInt(7,(s.getDtl()));
            return ps.executeUpdate()>0;
        }catch (Exception e){}
            return false;
    }
    public boolean updateKhachhang(KhachhangDTO s){
        String sql="UPDATE `khachhang` SET `hokh`=?,`tenkh`=?,`gioitinh`=?,`ngaysinh`=?,`diachi`=?,`sdt`=?,`dtl`=? WHERE `makh`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getHo());
            ps.setString(2,s.getTen());
            ps.setString(3,s.getGioitinh());
            ps.setString(4, formatter.format(s.getDob()));
            ps.setString(5,s.getDiachi());
            ps.setString(6,s.getSdt());
            ps.setInt(7,s.getDtl());
            ps.setString(8,s.getMa());

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean delKhachhang(KhachhangDTO s){
        String sql="DELETE FROM `khachhang` WHERE `makh`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getMa());

            if(ps.executeUpdate()>0) return true;

        }catch (Exception e){}
        return false;
    }
}

