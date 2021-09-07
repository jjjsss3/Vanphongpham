package DTO;

public class ChiTietCTKMDTO {
        public String MaCTKM;
        public int masp;
        public int PhanTramKM;
        public int TinhTrang;
        public ChiTietCTKMDTO(String MaCTKM,int masp,int PhanTramKM,int TinhTrang){
                this.MaCTKM=MaCTKM;
                this.masp=masp;
                this.PhanTramKM=PhanTramKM;
                this.TinhTrang=TinhTrang;
        }
        public  ChiTietCTKMDTO(){

        }
        public int getTinhTrang(){
                return TinhTrang;
        }
        public void setTinhTrang(int TinhTrang){
                this.TinhTrang=TinhTrang;
        }
        public String getMaCTKM(){
                return MaCTKM;
        }
        public void setMaCTKM(String MaCTKM){
                this.MaCTKM=MaCTKM;
        }
        public int getMasp(){
                return masp;
        }
        public void setMasp(int masp){
                this.masp=masp;

        }
        public int getPhanTramKM(){
                return PhanTramKM;
        }
        public void setPhanTramKM(int PhanTramKM){
                this.PhanTramKM=PhanTramKM;
        }
}
