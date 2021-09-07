package BLL;

import DAL.ChiTietCTKMDAL;
import DAL.SanphamDAL;
import DTO.ChiTietCTKMDTO;
import DTO.SanphamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ChiTietCTKMBLL {
        public static ArrayList<ChiTietCTKMDTO> dsctkm;
        public static String ptucui8;
        public static void DocDSCTKM() throws Exception{
                ChiTietCTKMDAL data_loaiphong =new ChiTietCTKMDAL();
                if(dsctkm == null){
                        dsctkm=new ArrayList<ChiTietCTKMDTO>();
                        dsctkm=data_loaiphong.database_DSCTKM();}
        }
        public void them(ChiTietCTKMDTO lp ) throws Exception{
                ChiTietCTKMDAL lpDAO = new ChiTietCTKMDAL();
                lpDAO.them(lp);
                int i =ChiTietCTKMBLL.dsctkm.size();
                ChiTietCTKMBLL.dsctkm.add(lp);
                int i1 =ChiTietCTKMBLL.dsctkm.size();
                if(i1>i)
                        JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
        public void sua(ChiTietCTKMDTO lp ) throws Exception{
                ChiTietCTKMDAL lpDAO = new ChiTietCTKMDAL();
                lpDAO.sua(lp);
                int i =0;
                for(ChiTietCTKMDTO lp2 : ChiTietCTKMBLL.dsctkm){
                        if(lp2.getMaCTKM().equals(lp.getMaCTKM())){
                                ChiTietCTKMBLL.dsctkm.set(i, lp);
                                break;
                        }
                        i++;
                }
                JOptionPane.showMessageDialog(null, "Sửa thành công");
        }
        public void xoa(ChiTietCTKMDTO lp ) throws Exception{
                ChiTietCTKMDAL lpDAO = new ChiTietCTKMDAL();
                lpDAO.xoa(lp);
                int i =0;
                for(ChiTietCTKMDTO lp2 : ChiTietCTKMBLL.dsctkm){
                        if(lp2.getMaCTKM().equals(lp.getMaCTKM())){
                                ChiTietCTKMBLL.dsctkm.set(i, lp);
                                break;
                        }
                        i++;
                }
                JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
}
