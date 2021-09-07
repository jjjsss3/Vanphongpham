package DTO;

public class SanphamDTO {
    private int masp;
    private String tensp;
    private int maloai;
    private int soluong;
    private long  dongia;
    private int mancc;
    private String anhsp;
    public SanphamDTO(){
    }

    public SanphamDTO(int masp, int soluong, long dongia) {
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public SanphamDTO(int masp, String tensp, int maloai, int soluong, long dongia, int mancc, String anhsp) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.soluong = soluong;
        this.dongia = dongia;
        this.mancc = mancc;
        this.anhsp = anhsp;
    }
    public SanphamDTO(int masp, String tensp, int maloai, int soluong, long dongia, String anhsp) {
        this.masp = masp;
        this.tensp = tensp;
        this.maloai = maloai;
        this.soluong = soluong;
        this.dongia = dongia;
        this.anhsp = anhsp;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }


    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public long getDongia() {
        return dongia;
    }

    public void setDongia(long dongia) {
        this.dongia = dongia;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
    
}
