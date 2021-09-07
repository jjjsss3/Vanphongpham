package DAL;

import DTO.NhanvienDTO;
import DTO.QuyenDTO;
import GUI.QL_Chung;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DangnhapDAL extends Component {

    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public DangnhapDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }
    public boolean dangnhap(String manv, String matkhau, NhanvienDTO nv){
        String sql="SELECT * FROM nhanvien WHERE manv=? AND matkhau=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,manv);
            ps.setString(2,matkhau);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                nv.setMa(rs.getString(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setGioitinh(rs.getString(4));
                nv.setDob(rs.getDate(5));
                nv.setDiachi(rs.getString(6));
                nv.setNgayvaolam(rs.getDate(7));
                nv.setSdt(rs.getString(8));
                nv.setMachucvu(rs.getInt(9));
                nv.setMatkhau(rs.getString(10));

                return true;
            }else{
            return false;
            }

        }catch (Exception e){}
        return false;
    }
    public boolean getQuyenNV(int machucvu) throws SQLException {
        String sql="SELECT quyen.maquyen , quyen.tenquyen, phanquyen.trangthai, quyen.chitiet FROM quyen,chucvu,phanquyen WHERE chucvu.machucvu=? and quyen.maquyen=phanquyen.maquyen and chucvu.machucvu=phanquyen.machucvu ORDER BY quyen.maquyen";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, machucvu);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    QuyenDTO q = new QuyenDTO();
                    q.setMaquyen(rs.getInt(1));
                    q.setTenquyen(rs.getString(2));
                    q.setTrangthai(rs.getInt(3));
                    q.setChitiet(rs.getString(4));
                    QL_Chung.listQuyen.add(q);
                }
        if( QL_Chung.listQuyen.size()!=0) return true;
        return false;
    }
    public ArrayList<QuyenDTO> getQuyen() throws SQLException {
        ArrayList<QuyenDTO> quyen=new ArrayList<>();
        String sql="SELECT * from quyen";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            QuyenDTO q = new QuyenDTO();
            q.setMaquyen(rs.getInt(1));
            q.setTenquyen(rs.getString(2));
            q.setChitiet(rs.getString(3));
            quyen.add(q);
        }
        return quyen;
    }

    public NhanvienDTO getNhanvien(String manv) throws SQLException {
        NhanvienDTO nv=new NhanvienDTO();

        String sql="SELECT * FROM nhanvien WHERE manv=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,manv);

        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            nv.setMa(rs.getString(1));
            nv.setHo(rs.getString(2));
            nv.setTen(rs.getString(3));
            nv.setGioitinh(rs.getString(4));
            nv.setDob(rs.getDate(5));
            nv.setDiachi(rs.getString(6));
            nv.setNgayvaolam(rs.getDate(7));
            nv.setSdt(rs.getString(8));
            nv.setMachucvu(rs.getInt(9));
            nv.setMatkhau(rs.getString(10));
        }
        conn.close();
        return nv;
    }
}
