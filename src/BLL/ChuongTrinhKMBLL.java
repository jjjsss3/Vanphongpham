package BLL;


import DAL.ChuongTrinhKMDAL;
import DTO.ChuongTrinhKMDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;



    public class ChuongTrinhKMBLL {
        public static ArrayList<ChuongTrinhKMDTO> dsctkm;
        public static String ptucui4;
        public ChuongTrinhKMBLL(){

        }
        public void DocDSCTKM() throws Exception{
            ChuongTrinhKMDAL data = new ChuongTrinhKMDAL();
            if(dsctkm == null)

                dsctkm=new ArrayList<ChuongTrinhKMDTO>();
            dsctkm=data.database_DSCTKM();
        }
        public static void DocDS_PTCui() throws Exception{
            ChuongTrinhKMDAL data =new ChuongTrinhKMDAL();
            ptucui4 = data.database_PTcui();


        }
        public void themCTKM(ChuongTrinhKMDTO lp ) throws Exception{
            ChuongTrinhKMDAL lpDAO = new ChuongTrinhKMDAL();
            lpDAO.themchuongtrinh(lp);
            int i = ChuongTrinhKMBLL.dsctkm.size();
            ChuongTrinhKMBLL.dsctkm.add(lp);
            int i1 = ChuongTrinhKMBLL.dsctkm.size();
            if(i1>i)
                JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
        public void suaCTKM(ChuongTrinhKMDTO lp ) throws Exception{
            ChuongTrinhKMDAL lpDAO = new ChuongTrinhKMDAL();
            lpDAO.suachuongtrinh(lp);
            int i =0;
            for(ChuongTrinhKMDTO lp2 : ChuongTrinhKMBLL.dsctkm){
                if(lp2.getMaCTKM().equals(lp.getMaCTKM())){
                    ChuongTrinhKMBLL.dsctkm.set(i, lp);
                    break;
                }
                i++;
            }
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        }
        public void xoaCTKM(ChuongTrinhKMDTO lp ) throws Exception{
            ChuongTrinhKMDAL lpDAO = new ChuongTrinhKMDAL();
            lpDAO.xoaloaiphong(lp);
            int i =0;
            for(ChuongTrinhKMDTO lp2 : ChuongTrinhKMBLL.dsctkm){
                if(lp2.getMaCTKM().equals(lp.getMaCTKM())){
                    ChuongTrinhKMBLL.dsctkm.set(i, lp);
                    break;
                }
                i++;
            }
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
        public ArrayList<ChuongTrinhKMDTO> timkiemtheoMa(String s){
            int ktra=0;
            ArrayList<ChuongTrinhKMDTO> dsp = new ArrayList<>();
            for(ChuongTrinhKMDTO p : ChuongTrinhKMBLL.dsctkm){
                if(p.getMaCTKM().equals(s)){
                    dsp.add(p);
                    ktra=1;
                }
            }
            if(ktra==0){
                JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu cần tìm !!!");
            }

            return dsp;
        }


}

