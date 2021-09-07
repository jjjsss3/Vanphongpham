package DTO;

import java.util.Date;

public class PhieunhaphangDTO {
    private int mapnh;
    private String manv;
    private Date ngay;
    private int mancc;
    private long tongtien;

    public PhieunhaphangDTO(){}
    public PhieunhaphangDTO(int mapnh, String manv, Date ngay, int mancc, long tongtien) {
        this.mapnh = mapnh;
        this.manv = manv;
        this.ngay = ngay;
        this.mancc = mancc;
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "PhieunhaphangDTO{" +
                "mapnh=" + mapnh +
                ", manv='" + manv + '\'' +
                ", ngay=" + ngay +
                ", mancc=" + mancc +
                ", tongtien=" + tongtien +
                '}';
    }

    public int getMapnh() {
        return mapnh;
    }

    public void setMapnh(int mapnh) {
        this.mapnh = mapnh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }
}
