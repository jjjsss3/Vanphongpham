package DTO;

public class ChitietHoadonDTO extends SanphamDTO{
    private int mahd;
    private long giamgia;

    public ChitietHoadonDTO(int mahd, long giamgia) {
        this.mahd = mahd;
        this.giamgia = giamgia;
    }

    public ChitietHoadonDTO(int masp, String tensp, int maloai, int soluong, long dongia, int mancc, String anhsp, int mahd, long giamgia) {
        super(masp, tensp, maloai, soluong, dongia, mancc, anhsp);
        this.mahd = mahd;
        this.giamgia = giamgia;
    }
    public ChitietHoadonDTO(int mahd,int masp, int soluong, long dongia, long giamgia) {
        super(masp, soluong, dongia);
        this.mahd = mahd;
        this.giamgia = giamgia;
    }

    public ChitietHoadonDTO() {

    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    @Override
    public String toString() {
        return "ChitietHoadonDTO{" +
                "mahd=" + mahd +
                ", giamgia=" + giamgia +
                '}';
    }

    public long getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(long giamgia) {
        this.giamgia = giamgia;
    }
}
