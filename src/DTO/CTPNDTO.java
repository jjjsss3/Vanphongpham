package DTO;

public class CTPNDTO {
    int mapnh;
    int masp;
    int soluong;
    int dongianhap;
    int thanhtien;
    public CTPNDTO(){}
    public CTPNDTO(int mapnh) {
        this.mapnh = mapnh;
    }

    public CTPNDTO(int mapnh, int masp, int soluong, int dongianhap, int thanhtien) {
        this.mapnh = mapnh;
        this.masp = masp;
        this.soluong = soluong;
        this.dongianhap = dongianhap;
        this.thanhtien = thanhtien;
    }

    public int getMapnh() {
        return mapnh;
    }

    public void setMapnh(int mapnh) {
        this.mapnh = mapnh;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongianhap() {
        return dongianhap;
    }

    public void setDongianhap(int dongianhap) {
        this.dongianhap = dongianhap;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}
