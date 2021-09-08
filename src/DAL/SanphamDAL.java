package DAL;

import DTO.SanphamDTO;
import GUI.QL_Sanpham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanphamDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public SanphamDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getlistSanpham(){
        String sql="SELECT * FROM sanpham";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                SanphamDTO sp=new SanphamDTO();
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setMaloai(rs.getInt("maloai"));
                sp.setAnhsp(rs.getString("anh"));
                sp.setDongia(rs.getLong("dongia"));
                sp.setSoluong(rs.getInt("soluong"));

                QL_Sanpham.listSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Sanpham.listSP.size()!=0) return true;
        return false;
    }

    public boolean addSanpham(SanphamDTO s) throws SQLException {
        String sql="INSERT INTO `sanpham` (`tensp`, `maloai`, `dongia`, `soluong`, `anh` ) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTensp());
            ps.setInt(2,s.getMaloai());
            ps.setLong(3, s.getDongia());
            ps.setInt(4,s.getSoluong());
            ps.setString(5,"");
            ps.execute();
//        updateImg(s.getAnhsp());
        return true;
    }
    public boolean addSanphamUndo(SanphamDTO s) throws SQLException {
        String sql="INSERT INTO `sanpham` (`tensp`, `maloai`, `dongia`, `soluong`,`anh`,`masp`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTensp());
            ps.setInt(2,s.getMaloai());
            ps.setLong(3, s.getDongia());
            ps.setInt(4,s.getSoluong());
            ps.setString(5,s.getAnhsp());
            ps.setInt(6,s.getMasp());
            ps.execute();
        }catch (Exception e){}
        return true;
    }
    public boolean updateImg(int masp) throws SQLException {
         String sql="UPDATE sanpham set anh=? where masp=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"images/sanpham/"+String.valueOf(masp)+".png");
        ps.setInt(2,masp);
        ps.execute();
        return true;
    }

    public boolean updateSanpham(SanphamDTO s){
        String sql="UPDATE `sanpham` SET `tensp`=?,`maloai`=?,`dongia`=?,`soluong`=? WHERE `masp`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTensp());
            ps.setInt(2,s.getMaloai());
            ps.setLong(3, s.getDongia());
            ps.setInt(4,s.getSoluong());
            ps.setInt(5,s.getMasp());

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateSoluongHD(int masp, int soluong){
        String sql="UPDATE `sanpham` SET `soluong`=`soluong`-? WHERE `masp`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,soluong);
            ps.setInt(2,masp);

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateSoluongPNH(int masp, int soluong){
        String sql="UPDATE `sanpham` SET `soluong`=`soluong`+? WHERE `masp`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,soluong);
            ps.setInt(2,masp);

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean delSanpham(int masp){
        String sql="DELETE FROM `sanpham` WHERE `masp`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,masp);
            ps.execute();
        }catch (Exception e){}
        return true;
    }
    public int getSPAdded(){
        String sql="SELECT masp from sanpham order by masp desc limit 1";
        int masp='\0';
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                masp=rs.getInt("masp");
            }

        }catch (Exception e){}
        return masp;
    }
}
