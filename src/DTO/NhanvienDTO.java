package DTO;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NhanvienDTO extends DangnhapDTO {

    private String ho;
    private String ten;
    private String gioitinh;
    private String diachi;
    private Date ngayvaolam;
    private String sdt;
    private Date dob;

    public NhanvienDTO(){}

    public NhanvienDTO(String manv, String matkhau, int machucvu, String honv, String tennv, String gioitinh, String diachi, Date ngayvaolam, String sdt, Date dob) {
        super(manv, matkhau, machucvu);
        this.ho = honv;
        this.ten = tennv;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.ngayvaolam = ngayvaolam;
        this.sdt = sdt;
        this.dob = dob;
    }
    public NhanvienDTO(String manv,String honv, String tennv, String diachi, String sdt){
        super(manv);
        this.ho = honv;
        this.ten = tennv;
        this.diachi = diachi;
        this.sdt = sdt;
    }
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(Date ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "NhanvienDTO{" +
                "manv='" + getMa() + '\'' +
                ", honv='" + ho + '\'' +
                ", tennv='" + ten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", ngaysing=" + dob +
                ", diachi='" + diachi + '\'' +
                ", ngayvaolam=" + ngayvaolam +
                ", sdt='" + sdt + '\'' +
                ", machucvu='" + getMachucvu() + '\'' +
                ", tenchucvu='" + getTenchucvu() + '\'' +
                ", matkhau='" + getMatkhau() + '\'' +
                '}';
    }
}
