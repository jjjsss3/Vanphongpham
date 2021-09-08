package DTO;

import java.util.Date;

public class GiamGiaDTO {
    private String makm;
    private String tenkm;
    private Date ngaybd;
    private Date ngaykt;

    public GiamGiaDTO() {
        this.makm = makm;
        this.tenkm = tenkm;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
    }

    public GiamGiaDTO(String makm, String tenkm, Date ngaybd, Date ngaykt) {
        this.makm = makm;
        this.tenkm = tenkm;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
    }

    @Override
    public String toString() {
        return "KhuyenMaiDTO{" +
                "makm='" + makm + '\'' +
                ", tenkm='" + tenkm + '\'' +
                ", ngaybd=" + ngaybd +
                ", ngaykt=" + ngaykt +
                '}';
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public Date getNgaybd() {
        return ngaybd;
    }

    public void setNgaybd(Date ngaybd) {
        this.ngaybd = ngaybd;
    }

    public Date getNgaykt() {
        return ngaykt;
    }

    public void setNgaykt(Date ngaykt) {
        this.ngaykt = ngaykt;
    }
}
