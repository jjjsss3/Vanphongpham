package DTO;

public class CTPhieunhaphangDTO extends SanphamDTO{
    private int mapnh;
    private long dongianhap;
    private long thanhtien;
    private int check;
    public CTPhieunhaphangDTO(){}
    public CTPhieunhaphangDTO(int mapnh, long dongianhap ,long thanhtien, int check) {
        this.mapnh = mapnh;
        this.thanhtien = thanhtien;
        this.dongianhap=dongianhap;
        this.check=check;
    }

    public CTPhieunhaphangDTO(int mapnh, int masp, int soluong, long dongianhap, long dongia , long thanhtien, int check) {
        super(masp, soluong, dongia);
        this.mapnh = mapnh;
        this.thanhtien = thanhtien;
        this.check=check;
        this.dongianhap=dongianhap;
    }

    public CTPhieunhaphangDTO(int masp, String tensp, int maloai, int soluong, long dongia, long dongianhap, int mancc, String anhsp, int mapnh, long thanhtien, int check) {
        super(masp, tensp, maloai, soluong, dongia, mancc, anhsp);
        this.mapnh = mapnh;
        this.dongianhap=dongianhap;
        this.thanhtien = thanhtien;
        this.check=check;
    }

    public long getDongianhap() {
        return dongianhap;
    }

    public void setDongianhap(long dongianhap) {
        this.dongianhap = dongianhap;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "CTPhieunhaphangDTO{" +
                "mapnh=" + mapnh +
                ", thanhtien=" + thanhtien +
                ", ten sp="+ getTensp()+
                ", so luong="+ getSoluong()+
                ", check="+getCheck()+
                '}';
    }

    public int getMapnh() {
        return mapnh;
    }

    public void setMapnh(int mapnh) {
        this.mapnh = mapnh;
    }

    public long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(long thanhtien) {
        this.thanhtien = thanhtien;
    }
}
