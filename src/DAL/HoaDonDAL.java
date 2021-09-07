package DAL;

import DTO.ChitietHoadonDTO;
import DTO.HoaDonDTO;
import GUI.QL_HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonDAL {
    private SanphamDAL sanphamDAL=new SanphamDAL();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private MySQLConnect con=new MySQLConnect();
    private Connection conn=null;

    public HoaDonDAL(){

        if(conn==null){
            conn=con.getConn();
        }
    }

    public boolean getListHoadon(){
        String sql="SELECT * FROM hoadon";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                HoaDonDTO hd=new HoaDonDTO();
                hd.setMahd(rs.getInt("mahd"));
                hd.setManv(rs.getString("manv"));
                hd.setMakh(String.valueOf(rs.getInt("makh")));
                hd.setNgay(rs.getDate("ngay"));
                hd.setTongtien(rs.getLong("tongtien"));
                hd.setTongkm(rs.getLong("tongkm"));
                QL_HoaDon.listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(QL_HoaDon.listHD.size()!=0) return true;
        return false;
    }

    public boolean addHD(HoaDonDTO s){
        String sql="INSERT INTO `hoadon`(`manv`, `makh`, `ngay`, `tongtien`, `tongkm`, `thanhtien`,`giamgiakh`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,s.getManv());
            ps.setInt(2,Integer.parseInt(s.getMakh()));
            ps.setString(3,formatter.format(s.getNgay()));
            ps.setLong(4,s.getTongtien());
            ps.setLong(5,s.getTongkm());
            ps.setLong(6,s.getTongtien()-s.getTongkm());
            ps.setInt(7,0);
            return ps.executeUpdate()>0;
        }catch (Exception e){}
        return false;
    }
//    public boolean addChitiet(ArrayList<SanphamDTO>listOD, ArrayList giamgia) throws SQLException {
//        int mahd=getHDAdded();
//        int i=0;
//        for (SanphamDTO sp: listOD
//        ) {
//            addCTHD(sp,mahd, Long.parseLong(String.valueOf(giamgia.get(i))));
//            i++;
//        }
//        return true;
//    }
    public boolean updateSoluong(ArrayList<ChitietHoadonDTO>list){
        for (ChitietHoadonDTO s:list) {
            sanphamDAL.updateSoluongHD(s.getMasp(),s.getSoluong());
        }
        return true;
    }

    public boolean delHD(int mahd){
        String sql = "DELETE from hoadon where mahd=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mahd);
            ps.execute();
            sql="DELETE from chitiethoadon where mahd=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,mahd);
            ps.execute();
        }catch (Exception e){}
        return true;
    }
//    public void addCTHD(SanphamDTO s, int mahd ,long giamgia) throws SQLException {
//        String sql="INSERT INTO `chitiethoadon`(`mahd`, `masp`, `soluong`, `khuyenmai`, `dongia`) VALUES (?,?,?,?,?)";
//        PreparedStatement ps=conn.prepareStatement(sql);
//        ps.setInt(1,mahd);
//        ps.setInt(2,s.getMasp());
//        ps.setInt(3,s.getSoluong());
//        ps.setLong(4,giamgia);
//        ps.setLong(5,s.getDongia());
//        ps.executeUpdate();
//    }

    public int getHDAdded(){
        String sql="SELECT mahd from hoadon order by mahd desc limit 1";
        int mahd='\0';
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
            mahd=rs.getInt("mahd");
            }

        }catch (Exception e){}
        return mahd;
    }
}
