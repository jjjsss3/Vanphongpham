package DTO;

public class ChuongTrinhKMDTO {
    public String MaCTKM;
    public String NgayBD;
    public String NgayKT;
    public String TenCTKM;
    public int TinhTrang;

    public ChuongTrinhKMDTO(String MaCTKM, String TenCTKM, String NgayBD, String NgayKT, int TinhTrang){
        this.MaCTKM= MaCTKM;
        this.TenCTKM=TenCTKM;
        this.NgayBD=NgayBD;
        this.NgayKT=NgayKT;
        this.TinhTrang=TinhTrang;

    }
    public ChuongTrinhKMDTO(){
        this.MaCTKM=null;
        this.TenCTKM=null;
        this.NgayBD=null;
        this.NgayKT=null;
        this.TinhTrang=0;
    }
    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }


    public String getMaCTKM() {
        return MaCTKM;
    }

    public void setMaCTKM(String MaCTKM) {
        this.MaCTKM = MaCTKM;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String NgayBD) {
        this.NgayBD = NgayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String NgayKT) {
        this.NgayKT = NgayKT;
    }


    public String getTenCTKM() {
        return TenCTKM;
    }

    public void setTenCTKM(String TenCTKM) {
        this.TenCTKM = TenCTKM;
    }

}
