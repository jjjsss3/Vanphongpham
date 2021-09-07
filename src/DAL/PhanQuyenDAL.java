package DAL;

import DTO.DangnhapDTO;
import DTO.QuyenDTO;
import GUI.QL_Chung;

import java.sql.*;
import java.util.ArrayList;

public class PhanQuyenDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;
    public PhanQuyenDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    public boolean getListChuvu(){
        String sql="SELECT * FROM chucvu";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                DangnhapDTO chucvu=new DangnhapDTO();
                chucvu.setMachucvu(rs.getInt(1));
                chucvu.setTenchucvu(rs.getString(2));
                QL_Chung.listChucvu.add(chucvu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Chung.listChucvu.size()!=0) return true;
        return false;
    }
    public boolean addChucvu(DangnhapDTO chucvu, ArrayList<QuyenDTO> list) throws SQLException {
        PreparedStatement ps =null;
        String sql="";
        ResultSet rs=null;
        int machucvu = 0;
        //insert chuc vu
        sql="INSERT INTO chucvu(`tenchucvu`) VALUES (?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1,chucvu.getTenchucvu());
        ps.executeUpdate();
        //get machucvu
        sql="SELECT machucvu from chucvu order by machucvu desc limit 1";
        rs=conn.prepareStatement(sql).executeQuery();
        while (rs.next()){
            chucvu.setMachucvu(rs.getInt(1));
        }
        //insert phanquyen
        for (QuyenDTO q: list) {
            sql="INSERT INTO `phanquyen`(`machucvu`, `maquyen`, `trangthai`) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,chucvu.getMachucvu());
            ps.setInt(2,q.getMaquyen());
            ps.setInt(3,q.getTrangthai());
            if((ps.executeUpdate()<0)) return false;
        }
        return true;
    }
    public boolean updateChucvu(DangnhapDTO chucvu, ArrayList<QuyenDTO> list) throws SQLException {
        PreparedStatement ps =null;
        String sql="";
        sql="UPDATE chucvu SET tenchucvu=? where machucvu=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1,chucvu.getTenchucvu());
        ps.setInt(2,chucvu.getMachucvu());
        ps.executeUpdate();
        for (QuyenDTO q: list) {
            sql="UPDATE phanquyen SET trangthai=? where machucvu=? and maquyen=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,q.getTrangthai());
            ps.setInt(2,chucvu.getMachucvu());
            ps.setInt(3,q.getMaquyen());
            if((ps.executeUpdate()<0)) return false;
        }
        return true;
    }
    public boolean delChucvu(int machucvu) throws SQLException {
        PreparedStatement ps=null;
        String sql="";
        //del chucvu
        sql="DELETE from chucvu where machucvu=?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,machucvu);
        ps.execute();
        //del phan quyen
        sql="DELETE from phanquyen where machucvu=?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,machucvu);
        ps.execute();
        //update nhanvien
        sql="UPDATE nhanvien SET machucvu=0 where machucvu=?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,machucvu);
        ps.executeUpdate();
        return true;
    }
}
