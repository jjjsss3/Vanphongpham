package DAL;
import DTO.ChiTietCTKMDTO;
import DTO.NhanvienDTO;
import DTO.ChuongTrinhKMDTO;
import GUI.QL_Nhanvien;
import GUI.QL_Sanpham;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietCTKMDAL {
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;
    public Statement st=null;
    public ResultSet rs=null;
    public ChiTietCTKMDAL(){
        if(conn==null){
            conn=con.getConn();
        }
    }
    public ArrayList<ChiTietCTKMDTO> database_DSCTKM(){
        ArrayList dt_loaisp = new ArrayList<ChiTietCTKMDTO>();
        try{
            String qry="SELECT * FROM chitietkm";
            st=conn.createStatement();
            rs=st.executeQuery(qry);

            while(rs.next()){
                ChiTietCTKMDTO lp = new ChiTietCTKMDTO();
                lp.setMaCTKM(rs.getString(1));
                lp.setMasp(Integer.parseInt(rs.getString(2)));
                lp.setPhanTramKM(rs.getInt(3));
                lp.setTinhTrang(rs.getInt(4));
                dt_loaisp.add(lp);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(NhanvienDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt_loaisp;
    }
    public void them(ChiTietCTKMDTO lp ){
        String qry="INSERT INTO `chitietkm` (`makm`, `masp`, `phantramkm`) VALUES ('"+lp.getMaCTKM()+"','"+lp.getMasp()+"',"+lp.getPhanTramKM()+")";
        System.out.println("qry");
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại !");
        }
    }
    public void sua(ChiTietCTKMDTO lp ){
        String qry="UPDATE `chitietkm` SET `makm`='"+lp.getMaCTKM()+"',`masp`='"+lp.getMasp()+" WHERE `makm`='"+lp.getMaCTKM()+"'";
        System.out.println(qry);
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa thất bại !");
        }
    }
    public void xoa(ChiTietCTKMDTO lp ){
        String qry="UPDATE `chitietkm` SET `tinhtrang`="+0+" WHERE `makm`='"+lp.getMaCTKM()+"' AND `masp`='"+lp.getMasp()+"'";
        System.out.println(qry);
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại !");
        }
    }
}
