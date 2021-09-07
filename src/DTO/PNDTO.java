package DTO;

public class PNDTO {
    int mapnh;
    int manv;
    String ngay;
    int mancc;
    int tongtien;

    public PNDTO() {
    }

    public PNDTO(int mapnh) {
        this.mapnh = mapnh;
    }

    public PNDTO(int mapnh, int manv, String ngay, int mancc, int tongtien) {
        this.mapnh = mapnh;
        this.manv = manv;
        this.ngay = ngay;
        this.mancc = mancc;
        this.tongtien = tongtien;
    }

    public int getMapnh() {
        return mapnh;
    }

    public void setMapnh(int mapnh) {
        this.mapnh = mapnh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
