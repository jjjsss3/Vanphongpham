package DAL;

import DTO.*;
import GUI.QL_NhapHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieunhaphangDAL {
    private SanphamDAL sanphamDAL=new SanphamDAL();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public PhieunhaphangDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getListPNH(){
        String sql="SELECT * FROM phieunhaphang";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                PhieunhaphangDTO p=new PhieunhaphangDTO();
                p.setMapnh(rs.getInt("mapnh"));
                p.setManv(rs.getString("manv"));
                p.setNgay(rs.getDate("ngay"));
                p.setMancc(rs.getInt("mancc"));
                p.setTongtien(rs.getLong("tongtien"));
                QL_NhapHang.listPNH.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_NhapHang.listPNH.size()!=0) return true;
        return false;
    }

    public boolean addHD(PhieunhaphangDTO s){
        String sql="INSERT INTO `phieunhaphang`(`manv`, `ngay`, `mancc`, `tongtien`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getManv());
            ps.setString(2,formatter.format(s.getNgay()));
            ps.setInt(3,s.getMancc());
            ps.setLong(4,s.getTongtien());
            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateSoluong(ArrayList<CTPhieunhaphangDTO>list){
        for (CTPhieunhaphangDTO s:list) {
            if(s.getCheck()!=1)
            sanphamDAL.updateSoluongPNH(s.getMasp(),s.getSoluong());
        }
        return true;
    }

    public boolean delPNH(int mapnh){
        String sql = "DELETE from phieunhaphang where mapnh=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mapnh);
            ps.execute();
            sql="DELETE from chitietpnh where mapnh=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,mapnh);
            ps.execute();
        }catch (Exception e){}
        return true;
    }


    public int getPNHAdded(){
        String sql="SELECT mapnh from phieunhaphang order by mapnh desc limit 1";
        int mapnh='\0';
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                mapnh=rs.getInt("mapnh");
            }

        }catch (Exception e){}
        return mapnh;
    }
}
