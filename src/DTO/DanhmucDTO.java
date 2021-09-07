package DTO;

public class DanhmucDTO {
    private int maloai;
    private String tenloai;
    private String chitiet;

    public DanhmucDTO(){}
    public DanhmucDTO(int maloai, String tenloai, String chitiet) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.chitiet = chitiet;
    }

    public DanhmucDTO(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public DanhmucDTO(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }
}
