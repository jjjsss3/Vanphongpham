package DTO;

public class ChitietGiamgiaDTO {
    private String makm;
    private int phantramkm;
    private int masp;

    public ChitietGiamgiaDTO(String makm, int phantramkm, int masp) {
        this.makm = makm;
        this.phantramkm = phantramkm;
        this.masp = masp;
    }

    public ChitietGiamgiaDTO() {

    }

    @Override
    public String toString() {
        return "ChitietKMDTO{" +
                "makm='" + makm + '\'' +
                ", phantramkm=" + phantramkm +
                ", masp=" + masp +
                '}';
    }

    public String getMakm() {
        return makm;
    }

    public void setMakm(String makm) {
        this.makm = makm;
    }

    public int getPhantramkm() {
        return phantramkm;
    }

    public void setPhantramkm(int phantramkm) {
        this.phantramkm = phantramkm;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }
}
