package BLL;

import DAL.GiamGiaDAL;

public class GiamGiaBLL {
    GiamGiaDAL giamGiaDAL=new GiamGiaDAL();
    public boolean getListGG() {
        if(giamGiaDAL.getListGG()) return true;
        return false;
    }
    public boolean getListCTGG() {
        if(giamGiaDAL.getListCTGG()) return true;
        return false;
    }

}
