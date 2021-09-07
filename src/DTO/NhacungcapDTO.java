package DTO;

public class NhacungcapDTO {
    private int mancc;
    private String tenncc;
    private String sdt;
    private String diachi;

    public NhacungcapDTO(){}
    public NhacungcapDTO(int mancc, String tenncc, String sdt, String diachi) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getMancc() {

        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
