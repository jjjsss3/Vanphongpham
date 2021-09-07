package utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import DTO.NhanvienDTO;
import GUI.QL_Nhanvien;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRW {
    private CheckInput ck=new CheckInput();
    private FileInputStream file=null;
    private Workbook workbook=null;
    private Sheet sheet=null;
    private Row row=null;
    private Cell cell=null;
    private Iterator<Cell> cellIterator=null;
    private Iterator<Row> rowIterator=null;
    public ExcelRW(){}
    public void getListNVfromExcel(String directoryFile){
        QL_Nhanvien.listNV=new ArrayList<>();
        try {
            file = new FileInputStream(new File(directoryFile));
            //Create Workbook instance holding reference to .xlsx file
            if(directoryFile.endsWith("xlsx")) workbook = new XSSFWorkbook(file);
            if(directoryFile.endsWith("xls")) workbook=new HSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            sheet = workbook.getSheetAt(0);
            //Iterate through each rows one by one
            rowIterator = sheet.iterator();
            int count=0;
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                if(count!=0) {
                    NhanvienDTO nv = new NhanvienDTO();
                    nv = assignEmployee(row);
                    QL_Nhanvien.listNV.add(nv);
//                    System.out.println(ck.changeDate(row.getCell(5).toString()));
                }
                count++;
//                //For each row, iterate through all the columns
//                row = rowIterator.next();
//                cellIterator = row.cellIterator();
//
//                while (cellIterator.hasNext())
//                {
//                    cell = cellIterator.next();
//                    //Check the cell type and format accordingly
//                    switch (cell.getCellType())
//                    {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                    }
//                }
//                System.out.println("");
            }
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    private NhanvienDTO assignEmployee(Row row) throws ParseException {
        NhanvienDTO nv=new NhanvienDTO();
        nv.setMa(row.getCell(1).toString());
        nv.setHo(row.getCell(2).toString());
        nv.setTen(row.getCell(3).toString());
        nv.setGioitinh(row.getCell(4).toString());
        nv.setDob(formatter.parse(ck.changeDate(row.getCell(5).toString())));
        nv.setDiachi(row.getCell(6).toString());
        nv.setNgayvaolam(formatter.parse(ck.changeDate(row.getCell(7).toString())));
        nv.setSdt(row.getCell(8).toString());
        nv.setMachucvu((int) Double.parseDouble(row.getCell(9).toString()));
        if(row.getCell(10).getCellType()==0) nv.setMatkhau(String.valueOf((int)Double.parseDouble(row.getCell(10).toString())));
        else  nv.setMatkhau(row.getCell(10).toString());
        return nv;
    }

}
