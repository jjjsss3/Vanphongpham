package DAL;

import DTO.NhacungcapDTO;
import GUI.QL_Nhacungcap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NhacungcapDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public NhacungcapDAL(){
        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getlistNhacungcap(){
        String sql="SELECT * FROM nhacungcap";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                NhacungcapDTO sp=new NhacungcapDTO();
                sp.setMancc(rs.getInt("mancc"));
                sp.setTenncc(rs.getString("tenncc"));
                sp.setSdt(rs.getString("sdt"));
                sp.setDiachi(rs.getString("diachi"));

                QL_Nhacungcap.listNCC.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_Nhacungcap.listNCC.size()!=0) return true;
        return false;
    }

    public boolean addNCC(NhacungcapDTO s){
        String sql="INSERT INTO `nhacungcap`(`tenncc`, `sdt`, `diachi`) VALUES (?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTenncc());
            ps.setString(2,s.getSdt());
            ps.setString(3,s.getDiachi());
            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean updateNCC(NhacungcapDTO s){
        String sql="UPDATE `nhacungcap` SET `tenncc`=?,`sdt`=?,`diachi`=? WHERE `mancc`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getTenncc());
            ps.setString(2,s.getSdt());
            ps.setString(3,s.getDiachi());
            ps.setInt(4, s.getMancc());

            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
    public boolean delNCC(NhacungcapDTO s){
        String sql="DELETE FROM `nhacungcap` WHERE `mancc`=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,s.getMancc());

            if(ps.executeUpdate()>0) return true;

        }catch (Exception e){}
        return false;
    }
}
