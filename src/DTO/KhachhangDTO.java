package DTO;

import java.util.Date;

public class KhachhangDTO extends NhanvienDTO{
    private int dtl;

    public KhachhangDTO(int dtl) {
        this.dtl = dtl;
    }

    public KhachhangDTO(String manv, String matkhau, int machucvu, String honv, String tennv, String gioitinh, String diachi, Date ngayvaolam, String sdt, Date dob, int dtl) {
        super(manv, matkhau, machucvu, honv, tennv, gioitinh, diachi, ngayvaolam, sdt, dob);
        this.dtl = dtl;
    }
    public KhachhangDTO(String manv,String honv, String tennv, String diachi, String sdt,int dtl){
        super(manv,  honv, tennv, diachi,  sdt);
        this.dtl = dtl;
    }

    public KhachhangDTO(){}

    public int getDtl() {
        return dtl;
    }

    public void setDtl(int dtl) {
        this.dtl = dtl;
    }
}
