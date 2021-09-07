package DTO;

public class SPDTO {
    int masp;
    String tensp;
    int maloai;
    String anh;
    int dongia;
    int soluong;

    public SPDTO() {
    }

    public SPDTO(int masp) {
        this.masp = masp;
    }

    public SPDTO(int masp, String tensp, int maloai, String anh, int dongia, int soluong) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.anh = anh;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
