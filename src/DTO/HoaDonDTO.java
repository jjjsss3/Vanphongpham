package DTO;

import java.util.Date;

public class HoaDonDTO {
    private int mahd;
    private String manv;
    private String makh;
    private Date ngay;
    private long tongtien;
    private long tongkm;
    private int giamgiakh;

    public HoaDonDTO() {
        this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.ngay = ngay;
        this.tongtien = tongtien;
        this.tongkm = tongkm;
        this.giamgiakh = giamgiakh;
    }

    public HoaDonDTO(String manv, String makh, Date ngay, long tongtien, long tongkm, int giamgiakh) {
        this.manv = manv;
        this.makh = makh;
        this.ngay = ngay;
        this.tongtien = tongtien;
        this.tongkm = tongkm;
        this.giamgiakh = giamgiakh;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    public long getTongkm() {
        return tongkm;
    }

    public void setTongkm(long tongkm) {
        this.tongkm = tongkm;
    }

    public int getGiamgiakh() {
        return giamgiakh;
    }

    public void setGiamgiakh(int giamgiakh) {
        this.giamgiakh = giamgiakh;
    }

    @Override
    public String toString() {
        return "HoaDonDTO{" +
                "mahd=" + mahd +
                ", manv='" + manv + '\'' +
                ", makh=" + makh +
                ", ngay=" + ngay +
                ", tongtien=" + tongtien +
                ", tongkm=" + tongkm +
                ", giamgiakh=" + giamgiakh +
                '}';
    }
}
