package DAL;

import DTO.CTPhieunhaphangDTO;
import DTO.ChitietHoadonDTO;
import DTO.DanhmucDTO;
import DTO.SanphamDTO;
import GUI.QL_HoaDon;
import GUI.QL_NhapHang;
import GUI.QL_Sanpham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CTPhieunhaphangDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;
    private PhieunhaphangDAL phieunhaphangDAL  =new PhieunhaphangDAL();

    public CTPhieunhaphangDAL(){
        if(conn==null){
            conn=con.getConn();
        }
    }
    public boolean getListCTPNH(){
        String sql="SELECT * FROM chitietpnh";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                CTPhieunhaphangDTO sp=new CTPhieunhaphangDTO();
                sp.setMapnh(rs.getInt("mapnh"));
                sp.setMasp(rs.getInt("masp"));
                sp.setSoluong(rs.getInt("soluong"));
                sp.setDongia(rs.getLong("dongianhap"));
                sp.setThanhtien(rs.getLong("thanhtien"));
                QL_NhapHang.listCTPNH.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_NhapHang.listCTPNH.size()!=0) return true;
        return false;
    }
    public boolean addListCTPNH(ArrayList<CTPhieunhaphangDTO> list) throws SQLException {
        int mapnh=phieunhaphangDAL.getPNHAdded();
        int i=0;
        for (CTPhieunhaphangDTO s: list
        ) {
            addCTPNH(s);
            i++;
        }
        return true;
    }
    public boolean addCTPNH(CTPhieunhaphangDTO s) throws SQLException {
        String sql="INSERT INTO `chitietpnh`(`mapnh`, `masp`, `soluong`, `dongianhap`, `thanhtien`) VALUES (?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,s.getMapnh());
        ps.setInt(2,s.getMasp());
        ps.setInt(3,s.getSoluong());
        ps.setLong(4,s.getDongianhap());
        ps.setLong(5,s.getSoluong()*s.getDongianhap());
        return ps.executeUpdate()>0;
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
