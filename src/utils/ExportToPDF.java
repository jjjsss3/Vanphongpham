package utils;

import DAL.HoaDonDAL;
import DTO.*;
import GUI.Dangnhap;
import GUI.QL_Nhanvien;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ExportToPDF {
    HoaDonDAL hoaDonDAL=new HoaDonDAL();
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    String ngay="Ngày "+new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
    String thang=" tháng "+new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
    String nam=" năm "+new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
    String gio=", "+new SimpleDateFormat("HH").format(Calendar.getInstance().getTime())+"h";
    String phut=new SimpleDateFormat("mm").format(Calendar.getInstance().getTime())+"ph";
    String giay=new SimpleDateFormat("ss").format(Calendar.getInstance().getTime())+"s";
    String time=ngay+thang+nam+gio+phut+giay;
    URL img_path = Thread.currentThread().getContextClassLoader().getResource("GUI/images/logo_pdf.png");
    Image img = Image.getInstance(img_path.toString().substring(6, img_path.toString().length()));
    URL font_path = Thread.currentThread().getContextClassLoader().getResource("Fonts/Arial/ArialUnicodeMS.ttf");
    BaseFont baseFont = BaseFont.createFont(font_path.toString().substring(6, font_path.toString().length()), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    private Locale localeVN = new Locale("vi", "VN");
    private NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public ExportToPDF() throws IOException, DocumentException {}
    public void exportToPDFNhanvien(ArrayList<NhanvienDTO> list){
        try {
            //close if running
            new ActionPDF().closeFile();
            //intialize
            String fileName = "D:\\Code\\Java_CoTrang\\Vanphongpham\\src\\File\\Nhanvien.pdf";
            Document doc = new Document();
            doc.setPageSize(PageSize.A4.rotate());
            FileOutputStream file = new FileOutputStream(new String(fileName.getBytes("UTF-8"),"UTF-8"));
            PdfWriter.getInstance(doc,file);
            doc.open();

            //font
            FontFactory.register(font_path.toString().substring(6, font_path.toString().length()));
            Font fontText  = new Font(baseFont,10);
            Font fontHead  = new Font(baseFont,11);

            //table head
            String[] head=new String[]{
                    "STT", "Mã nhân viên", "Họ và tên",
                    "Ngày sinh", "Địa chỉ", "Ngày vào làm", "Số điện thoại", "Chức vụ"
            };
            //align table
            int algin[]=new int[]{1,0,0,1,0,1,1,0};

            //PDF head
            img.scaleToFit(150,100);
            doc.add (new Chunk(img, 0, -30, true));
            Chunk glue = new Chunk(new VerticalPositionMark());
            doc.add(new Chunk("DANH SÁCH NHÂN VIÊN",new Font(baseFont,18,1)));
            doc.add(glue);
            doc.add(new Chunk(time ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Người xuất: "+Dangnhap.taikhoan.getHo()+" "+Dangnhap.taikhoan.getTen(),new Font(baseFont,10,Font.ITALIC)));

            doc.add(new Paragraph("\n\n\n"));

            //table

            PdfPTable table=new PdfPTable(head.length);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4,12,23,8,20,8,10,15});
            int count=0;
            for (String s:head) {
                table.addCell(setCell(s,fontHead,algin[count++]));
            }
            table.setHeaderRows(1);
            int stt=1;

            for (NhanvienDTO n: list) {
                count=0;
                table.addCell(setCell(String.valueOf(stt++),fontText,algin[count++]));
                table.addCell(setCell(n.getMa(),fontText,algin[count++]));
                table.addCell(setCell(n.getHo()+" "+n.getTen(),fontText,algin[count++]));
                table.addCell(setCell(formatshow.format(n.getDob()),fontText,algin[count++]));
                table.addCell(setCell(n.getDiachi(),fontText,algin[count++]));
                table.addCell(setCell(formatshow.format(n.getNgayvaolam()),fontText,algin[count++]));
                table.addCell(setCell(n.getSdt(),fontText,algin[count++]));
                table.addCell(setCell(n.getTenchucvu(),fontText,algin[count++]));
            }
            doc.add(table);

            //footer
            doc.add(new Paragraph("\n"));
            //close and open
            doc.close();
            new ActionPDF().openFile(fileName);
        }catch (Exception e){
        }

    }
    public void exportToPDFThongKe(DefaultTableModel model, String timestart, String timeend, String order, String kind,String sumM, String sumA){
        try {
            //close if running
            new ActionPDF().closeFile();
            //intialize
            File f=new File("");
            String fileName = f.getAbsolutePath()+"\\src\\File\\Thongkesanpham.pdf";
            Document doc = new Document();
            doc.setPageSize(PageSize.A4.rotate());
            FileOutputStream file = new FileOutputStream(new String(fileName.getBytes("UTF-8"),"UTF-8"));
            PdfWriter.getInstance(doc,file);
            doc.open();

            //font

            FontFactory.register(font_path.toString().substring(6, font_path.toString().length()));
            Font fontText  = new Font(baseFont,10);
            Font fontHead  = new Font(baseFont,11);

            //table head
            String[] head=new String[]{
                    "STT", "Mã sản phẩm", "Tên sản phẩm",
                    "Số lượng bán được", "Số tiền bán được"
            };
            //align table
            int algin[]=new int[]{1,0,0,0,0};

            //PDF head

            img.scaleToFit(150,100);
            doc.add (new Chunk(img, 0, -30, true));
            Chunk glue = new Chunk(new VerticalPositionMark());
            doc.add(new Chunk("THỐNG KÊ DOANH THU",new Font(baseFont,18,1)));
            doc.add(glue);
            doc.add(new Chunk(time ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Người xuất: "+Dangnhap.taikhoan.getHo()+" "+Dangnhap.taikhoan.getTen(),new Font(baseFont,10,Font.ITALIC)));
            doc.add(new Paragraph("\n\n\n"));
            if(timestart.equals("")) time="ban đầu";
            else {
                time=timestart.substring(8,10)+"/"+timestart.substring(5,7)+"/"+timestart.substring(0,4);
            }
            doc.add(new Chunk("Thời gian từ: "+time ,fontText));
            if(timeend.equals("")) time=ngay.substring(5,7)+"/"+thang.substring(7,9)+"/"+nam.substring(5,nam.length());
            else {
                time=timeend.substring(8,10)+"/"+timeend.substring(5,7)+"/"+timeend.substring(0,4);
            }
            doc.add(new Chunk(" đến "+time ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Loại hàng: "+kind+", theo "+order.toLowerCase() ,fontText));
            //table

            PdfPTable table=new PdfPTable(head.length);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4,12,39,20,25});
            int count=0;
            for (String s:head) {
                table.addCell(setCell(s,fontHead,algin[count++]));
            }
            table.setHeaderRows(1);
            int dong=model.getRowCount();
            int cot=model.getColumnCount();
            for (int i = 0; i < dong; i++) {
                String value="";
                for (int j = 0; j < cot; j++) {
                    value=model.getValueAt(i,j).toString();
                    table.addCell(setCell(value,fontText,algin[j]));
                }
            }
            doc.add(table);
            //footer
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            Font fontz  = new Font(baseFont,12,2);
            doc.add(new Chunk("Tổng tiền sản phẩm bán được: "+sumM ,fontz));
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            doc.add(new Chunk("Tổng lượng sản phẩm bán được: "+sumA ,fontz));
            //close and open
            doc.close();
            new ActionPDF().openFile(fileName);
        }catch (Exception e){
        }

    }
    public void exportToPDFHoaDon(DefaultTableModel model, HoaDonDTO hd, KhachhangDTO kh){
        try {
            //close if running
//            new ActionPDF().closeFile();
            String ngay=new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
            String thang=new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
            String nam=new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
            String timefolder=nam+"-"+thang+"-"+ngay;
            //intialize
            File f = new File("");
            File dir = new File(f.getAbsolutePath()+ "\\src\\File\\Hoadon\\"+timefolder);
            dir.mkdirs();
            String nameHD="HD"+String.valueOf(hoaDonDAL.getHDAdded())+".pdf";
            String fileName = dir.getPath()+"\\"+nameHD;
            Document doc = new Document();
            doc.setPageSize(PageSize.A4.rotate());
            FileOutputStream file = new FileOutputStream(new String(fileName.getBytes("UTF-8"),"UTF-8"));
            PdfWriter.getInstance(doc,file);
            doc.open();

            //font

            FontFactory.register(font_path.toString().substring(6, font_path.toString().length()));
            Font fontText  = new Font(baseFont,10);
            Font fontHead  = new Font(baseFont,11);

            //table head
            String[] head=new String[]{
                    "STT", "Tên sản phẩm",
                    "Số lượng", "Đơn giá", "Giảm giá"
            };
            //align table
            int algin[]=new int[]{1,0,0,0,0};

            //PDF head

            img.scaleToFit(150,100);
            doc.add (new Chunk(img, 0, -30, true));
            Chunk glue = new Chunk(new VerticalPositionMark());
            doc.add(new Chunk("HÓA ĐƠN BÁN HÀNG",new Font(baseFont,18,1)));
            doc.add(glue);
            doc.add(new Chunk(time ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Người xuất: "+Dangnhap.taikhoan.getHo()+" "+Dangnhap.taikhoan.getTen(),new Font(baseFont,10,Font.ITALIC)));
            doc.add(new Paragraph("\n\n\n"));
            doc.add(new Chunk("Mã hóa đơn: "+hd.getMahd(),fontText));
            doc.add(new Paragraph("\n"));
            doc.add(new Chunk("Họ và tên khách hàng: "+kh.getHo()+" "+ kh.getTen() ,fontText));
            doc.add(glue);
            doc.add(new Chunk("SDT: "+kh.getSdt()+", địa chỉ "+kh.getDiachi() ,fontText));
//            //table
//
            PdfPTable table=new PdfPTable(head.length);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{4,40,10,23,23});
            int count=0;
            for (String s:head) {
                table.addCell(setCell(s,fontHead,algin[count++]));
            }
            table.setHeaderRows(1);
            int dong=model.getRowCount();
            int cot=model.getColumnCount();
            for (int i = 0; i < dong; i++) {
                String value="";
                for (int j = 0; j < cot; j++) {
                    value=model.getValueAt(i,j).toString();
                    if(j==3||j==4){
                        table.addCell(setCell(currencyVN.format(Long.parseLong(value)),fontText,algin[j]));
                    }
                    else
                        table.addCell(setCell(value,fontText,algin[j]));
                }
            }
//            for (ChitietHoadonDTO n: list) {
//                count=0;
//                table.addCell(setCell(String.valueOf(count++),fontText,algin[count++]));
//                table.addCell(setCell(n.getMa(),fontText,algin[count++]));
//                table.addCell(setCell(n.getHo()+" "+n.getTen(),fontText,algin[count++]));
//                table.addCell(setCell(formatshow.format(n.getDob()),fontText,algin[count++]));
//                table.addCell(setCell(n.getDiachi(),fontText,algin[count++]));
//                table.addCell(setCell(formatshow.format(n.getNgayvaolam()),fontText,algin[count++]));
//                table.addCell(setCell(n.getSdt(),fontText,algin[count++]));
//                table.addCell(setCell(n.getTenchucvu(),fontText,algin[count++]));
//            }
            doc.add(table);
            //footer
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            Font fontz  = new Font(baseFont,12,2);
            doc.add(new Chunk("Tổng tiền sản phẩm : "+currencyVN.format(hd.getTongtien()) ,fontz));
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            doc.add(new Chunk("Giảm giá sản phẩm: "+currencyVN.format(hd.getTongkm()) ,fontz));
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            doc.add(new Chunk("Giảm giá khách hàng: "+currencyVN.format(hd.getGiamgiakh()) ,fontz));
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            doc.add(new Chunk("Thanh toán: "+currencyVN.format(hd.getTongtien()-hd.getTongkm()) ,fontz));
//            close and open
            doc.close();
            new ActionPDF().openFile(fileName);
        }catch (Exception e){
        }

    }
    public void exportToPDFPhieunhaphang(DefaultTableModel model,ArrayList<CTPhieunhaphangDTO> list, PhieunhaphangDTO p, NhacungcapDTO ncc){
        try {
            //close if running
//            new ActionPDF().closeFile();
            String ngay=new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
            String thang=new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
            String nam=new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
            String timefolder=nam+"-"+thang+"-"+ngay;
            //intialize
            File f = new File("");
            File dir = new File(f.getAbsolutePath()+ "\\src\\File\\Phieunhaphang\\"+timefolder);
            dir.mkdirs();
            String nameHD="PNH"+String.valueOf(p.getMapnh())+".pdf";
            String fileName = dir.getPath()+"\\"+nameHD;
            Document doc = new Document();
            doc.setPageSize(PageSize.A4.rotate());
            FileOutputStream file = new FileOutputStream(new String(fileName.getBytes("UTF-8"),"UTF-8"));
            PdfWriter.getInstance(doc,file);
            doc.open();

            //font

            FontFactory.register(font_path.toString().substring(6, font_path.toString().length()));
            Font fontText  = new Font(baseFont,10);
            Font fontHead  = new Font(baseFont,11);

            //table head
            String[] head=new String[]{
                    "STT","Mã sản phẩm" ,"Tên sản phẩm","Mã danh mục",
                    "Số lượng nhập", "Đơn giá nhập", "Tổng tiền"
            };
            //align table
            int algin[]=new int[]{1,1,0,1,1,0,0};

            //PDF head

            img.scaleToFit(150,100);
            doc.add (new Chunk(img, 0, -30, true));
            Chunk glue = new Chunk(new VerticalPositionMark());
            doc.add(new Chunk("HÓA ĐƠN PHIẾU NHẬP HÀNG",new Font(baseFont,18,1)));
            doc.add(glue);
            doc.add(new Chunk(time ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Người xuất: "+Dangnhap.taikhoan.getHo()+" "+Dangnhap.taikhoan.getTen(),new Font(baseFont,10,Font.ITALIC)));
            doc.add(new Paragraph("\n\n\n"));
            doc.add(new Chunk("Mã  phiếu nhập: "+p.getMapnh(),fontText));
            doc.add(new Paragraph("\n"));
            doc.add(new Chunk("Tên nhà cung cấp: "+ncc.getTenncc() ,fontText));
            doc.add(glue);
            doc.add(new Chunk("SDT: "+ncc.getSdt() ,fontText));
            doc.add(glue);
            doc.add(new Chunk("Địa chỉ: "+ncc.getDiachi(),fontText));
//            //table
//
            PdfPTable table=new PdfPTable(head.length);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{5,10,38,10,11,12,14});
            int count=0;
            for (String s:head) {
                table.addCell(setCell(s,fontHead,algin[count++]));
            }
            table.setHeaderRows(1);
            int dong=model.getRowCount();
            for (int i = 0; i < dong; i++) {
                table.addCell(setCell(model.getValueAt(i,0).toString(),fontText,algin[0]));
                table.addCell(setCell(String.valueOf(list.get(i).getMasp()),fontText,algin[1]));
                table.addCell(setCell(model.getValueAt(i,1).toString(),fontText,algin[2]));
                table.addCell(setCell(String.valueOf(list.get(i).getMaloai()),fontText,algin[3]));
                table.addCell(setCell(model.getValueAt(i,2).toString(),fontText,algin[4]));
                table.addCell(setCell(model.getValueAt(i,3).toString(),fontText,algin[5]));
                table.addCell(setCell(model.getValueAt(i,4).toString(),fontText,algin[6]));
            }
//            for (CTPhieunhaphangDTO c:list) {
//                count=0;
//                table.addCell(setCell(String.valueOf(count++),fontText,algin[count++]));
//                table.addCell(setCell(String.valueOf(c.getMasp()),fontText,algin[count++]));
//                table.addCell(setCell(c.getTensp(),fontText,algin[count++]));
//                table.addCell(setCell(String.valueOf(c.getMaloai()),fontText,algin[count++]));
//                table.addCell(setCell(String.valueOf(c.getSoluong()),fontText,algin[count++]));
//                table.addCell(setCell(currencyVN.format(c.getDongianhap()),fontText,algin[count++]));
//                table.addCell(setCell(currencyVN.format(c.getSoluong()*c.getDongianhap()),fontText,algin[count++]));
//
//            }
//            for (ChitietHoadonDTO n: list) {
//                count=0;
//                table.addCell(setCell(String.valueOf(count++),fontText,algin[count++]));
//                table.addCell(setCell(n.getMa(),fontText,algin[count++]));
//                table.addCell(setCell(n.getHo()+" "+n.getTen(),fontText,algin[count++]));
//                table.addCell(setCell(formatshow.format(n.getDob()),fontText,algin[count++]));
//                table.addCell(setCell(n.getDiachi(),fontText,algin[count++]));
//                table.addCell(setCell(formatshow.format(n.getNgayvaolam()),fontText,algin[count++]));
//                table.addCell(setCell(n.getSdt(),fontText,algin[count++]));
//                table.addCell(setCell(n.getTenchucvu(),fontText,algin[count++]));
//            }
            doc.add(table);
            //footer
            doc.add(new Paragraph("\n"));
            doc.add(glue);
            Font fontz  = new Font(baseFont,12,2);
            doc.add(new Chunk("Tổng tiền sản phẩm : "+currencyVN.format(p.getTongtien()) ,fontz));
//            close and open
            doc.close();
            new ActionPDF().openFile(fileName);
        }catch (Exception e){
        }
    }
    private PdfPCell setCell(String text,Font font,int align){
        PdfPCell cell=new PdfPCell();
        cell=new PdfPCell(new Phrase(text,font));
        cell.setFixedHeight(25);
        cell.setPaddingLeft(3);
        cell.setPaddingTop(6);
        cell.setHorizontalAlignment(align);
        return cell;
    }
}
