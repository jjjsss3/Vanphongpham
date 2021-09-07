package DAL;

import DTO.NhanvienDTO;
import GUI.QL_Nhanvien;

import java.sql.*;
import java.text.SimpleDateFormat;

public class NhanvienDAL {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public NhanvienDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getlistNhanvien(){
        String sql="SELECT a.manv, a.honv, a.tennv,a.gioitinh,a.ngaysinh,a.diachi,a.ngayvaolam,a.sdt,a.machucvu,c.tenchucvu, a.matkhau  FROM nhanvien a, chucvu c WHERE a.machucvu=c.machucvu";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                NhanvienDTO nv=new NhanvienDTO();
                nv.setMa(rs.getString("manv"));
                nv.setHo(rs.getString("honv"));
                nv.setTen(rs.getString("tennv"));
                nv.setGioitinh(rs.getString("gioitinh"));
                nv.setDob(rs.getDate("ngaysinh"));
                nv.setDiachi(rs.getString("diachi"));
                nv.setNgayvaolam(rs.getDate("ngayvaolam"));
                nv.setSdt(rs.getString("sdt"));
                nv.setMachucvu(rs.getInt("machucvu"));
                nv.setTenchucvu(rs.getString("tenchucvu"));
                nv.setMatkhau(rs.getString("matkhau"));
                QL_Nhanvien.listNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Nhanvien.listNV.size()!=0) return true;
        return false;
    }

    public boolean addNhanvien(NhanvienDTO s){
        String sql="INSERT INTO `nhanvien` (`manv`, `honv`, `tennv`, `gioitinh`, `ngaysinh`, `diachi`, `ngayvaolam`, `sdt`, `machucvu`, `matkhau`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getMa());
            ps.setString(2,s.getHo());
            ps.setString(3,s.getTen());
            ps.setString(4,s.getGioitinh());
            ps.setString(5, formatter.format(s.getDob()));
            ps.setString(6,s.getDiachi());
            ps.setString(7, formatter.format(s.getDob()));
            ps.setString(8,s.getSdt());
            ps.setInt(9,s.getMachucvu());
            ps.setString(10,s.getMatkhau());
            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateNhanvien(NhanvienDTO s){
        String sql="UPDATE `nhanvien` SET `honv`=?,`tennv`=?,`gioitinh`=?,`ngaysinh`=?,`diachi`=?,`ngayvaolam`=?,`sdt`=?,`machucvu`=?,`matkhau`=? WHERE `manv`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getHo());
            ps.setString(2,s.getTen());
            ps.setString(3,s.getGioitinh());
            ps.setString(4, formatter.format(s.getDob()));
            ps.setString(5,s.getDiachi());
            ps.setString(6, formatter.format(s.getNgayvaolam()));
            ps.setString(7,s.getSdt());
            ps.setInt(8,s.getMachucvu());
            ps.setString(9,s.getMatkhau());
            ps.setString(10,s.getMa());

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean delNhanvien(NhanvienDTO s){
        String sql="DELETE FROM `nhanvien` WHERE `manv`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getMa());

            if(ps.executeUpdate()>0) return true;
        }catch (Exception e){}
        return false;
    }

}
