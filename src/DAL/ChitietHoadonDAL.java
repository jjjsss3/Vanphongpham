package DAL;

import DTO.ChitietHoadonDTO;
import DTO.DanhmucDTO;
import DTO.SanphamDTO;
import GUI.QL_HoaDon;
import GUI.QL_Sanpham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChitietHoadonDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;
    private HoaDonDAL hoaDonDAL=new HoaDonDAL();

    public ChitietHoadonDAL(){
        if(conn==null){
            conn=con.getConn();
        }
    }
    public boolean getListCTHD(){
        String sql="SELECT * FROM chitiethoadon";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ChitietHoadonDTO sp=new ChitietHoadonDTO();
                sp.setMahd(rs.getInt("mahd"));
                sp.setMasp(rs.getInt("masp"));
                sp.setSoluong(rs.getInt("soluong"));
                sp.setGiamgia(rs.getLong("khuyenmai"));
                QL_HoaDon.listCTHD.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_HoaDon.listCTHD.size()!=0) return true;
        return false;
    }
    public boolean addListCTHD(ArrayList<ChitietHoadonDTO> list) throws SQLException {
        int mahd=hoaDonDAL.getHDAdded();
        int i=0;
        for (ChitietHoadonDTO sp: list
        ) {
            addCTHD(sp);
            i++;
        }
        return true;
    }
    public boolean addCTHD(ChitietHoadonDTO s) throws SQLException {
        String sql="INSERT INTO `chitiethoadon`(`mahd`, `masp`, `soluong`, `khuyenmai`, `dongia`) VALUES (?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1,s.getMahd());
        ps.setInt(2,s.getMasp());
        ps.setInt(3,s.getSoluong());
        ps.setLong(4,s.getGiamgia());
        ps.setLong(5,s.getDongia());
        return ps.executeUpdate()>0;
    }
}
