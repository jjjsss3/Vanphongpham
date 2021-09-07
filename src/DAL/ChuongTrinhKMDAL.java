package DAL;
import DTO.NhanvienDTO;
import DTO.ChuongTrinhKMDTO;
import GUI.QL_Nhanvien;
import GUI.QL_Sanpham;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChuongTrinhKMDAL {
        private MySQLConnect con=new MySQLConnect();
        private Connection conn=null;
        public Statement st=null;
        public ResultSet rs=null;
        public ChuongTrinhKMDAL(){

            if(conn==null){
                conn=con.getConn();
            }
        }
    public ArrayList<ChuongTrinhKMDTO> database_DSCTKM(){
        ArrayList dt_chucnang = new ArrayList<ChuongTrinhKMDTO>();
        try{
            String qry="SELECT * FROM khuyenmai";
            st=conn.createStatement();
            rs=st.executeQuery(qry);

            while(rs.next()){
                ChuongTrinhKMDTO quyen = new ChuongTrinhKMDTO();
                quyen.setMaCTKM(rs.getString(1));
                quyen.setTenCTKM(rs.getString(2));
                quyen.setNgayBD(rs.getString(3));
                quyen.setNgayKT(rs.getString(4));
                quyen.setTinhTrang(rs.getInt(5));
                dt_chucnang.add(quyen);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ChuongTrinhKMDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt_chucnang;
    }
    public void themchuongtrinh(ChuongTrinhKMDTO lp ){
        String qry="INSERT INTO `khuyenmai` (`makm`, `tenkm`, `ngaybd`, `ngaykt`, `tinhtrang`) VALUES   ('"+lp.getMaCTKM()+"','"+lp.getTenCTKM()+"','"+lp.getNgayBD()+"','"+lp.getNgayKT()+"',"+lp.getTinhTrang()+")";
        System.out.println(qry);
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại !");
        }

    }
    public void suachuongtrinh(ChuongTrinhKMDTO lp ){
        String qry="UPDATE `khuyenmai` SET `makm`='"+lp.getMaCTKM()+"',`tenkm`='"+lp.getTenCTKM()+"',`ngaybd`='"+lp.getNgayBD()+"',`ngaykt`='"+lp.getNgayKT()+"',`tinhtrang`="+lp.getTinhTrang()+" WHERE `makm`='"+lp.getMaCTKM()+"'";
        System.out.println(qry);
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sửa thất bại !");
        }

    }
    public void xoaloaiphong(ChuongTrinhKMDTO lp ){
        String qry="UPDATE `khuyenmai` SET `tinhtrang`="+0+" WHERE `makm`='"+lp.getMaCTKM()+"'";
        try {
            st = conn.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại !");
        }

    }
    public String database_PTcui(){
        String t=null;

        try{
            String qry="SELECT makm FROM khuyenmai ORDER BY makm DESC LIMIT 1";
            st=conn.createStatement();
            rs=st.executeQuery(qry);

            while(rs.next()){
                ChuongTrinhKMDTO lp = new ChuongTrinhKMDTO();
                lp.setMaCTKM(rs.getString(1));

                t=lp.getMaCTKM();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(NhanvienDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

}
