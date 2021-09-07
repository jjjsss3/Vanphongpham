package DTO;

public class DangnhapDTO {
    private String ma;
    private String matkhau;
    private int machucvu;
    private String tenchucvu;
    public DangnhapDTO(){}

    public DangnhapDTO(String manv, String matkhau, int machucvu, String tenchucvu) {
        this.ma = manv;
        this.matkhau = matkhau;
        this.machucvu = machucvu;
        this.tenchucvu = tenchucvu;
    }


    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }

    public DangnhapDTO(String manv, String matkhau) {
        this.ma = manv;
        this.matkhau = matkhau;
    }

    public DangnhapDTO(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }

    public DangnhapDTO(int machucvu, String tenchucvu) {
        this.machucvu = machucvu;
        this.tenchucvu = tenchucvu;
    }

    public DangnhapDTO(String manv, String matkhau, int machucvu) {
        this.ma = manv;
        this.matkhau = matkhau;
        this.machucvu = machucvu;
    }


    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getMachucvu() {
        return machucvu;
    }

    public void setMachucvu(int machucvu) {
        this.machucvu = machucvu;
    }

    @Override
    public String toString() {
        return "DangnhapDTO{" +
                "manv='" + ma + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", machucvu=" + machucvu +
                ", tenchucvu='" + tenchucvu + '\'' +
                '}';
    }
}
