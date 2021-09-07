package DTO;

public class QuyenDTO {
    private int maquyen;
    private String tenquyen;
    private int trangthai;
    private String chitiet;

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public QuyenDTO(int maquyen, String tenquyen, int trangthai, String chitiet) {
        this.maquyen = maquyen;
        this.tenquyen = tenquyen;
        this.trangthai = trangthai;
        this.chitiet = chitiet;
    }

    public int getMaquyen() {
        return maquyen;
    }
    public QuyenDTO(){}
    public void setMaquyen(int maquyen) {
        this.maquyen = maquyen;
    }

    public String getTenquyen() {
        return tenquyen;
    }

    public void setTenquyen(String tenquyen) {
        this.tenquyen = tenquyen;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public QuyenDTO(int maquyen, String tenquyen, int trangthai) {
        this.maquyen = maquyen;
        this.tenquyen = tenquyen;
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "QuyenDTO{" +
                "maquyen=" + maquyen +
                ", tenquyen='" + tenquyen + '\'' +
                ", trangthai=" + trangthai +
                ", chitiet='" + chitiet + '\'' +
                '}';
    }
}
