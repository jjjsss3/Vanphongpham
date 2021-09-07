package utils;

import DTO.DangnhapDTO;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.String.valueOf;
import static java.util.Calendar.*;

public class CheckInput {
    public boolean checkString(String data) {
        return data.matches("\\D+$");
    }


    public boolean checkMaID(String data) {
        return data.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{3,}$");
    }

    public boolean checkDate(String data) {
        LocalDate localDate = LocalDate.now();
        if (data.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            String[] mang = data.split("-");
            int day = Integer.parseInt(mang[2]);
            int mon = Integer.parseInt(mang[1]);
            int year = Integer.parseInt(mang[0]);
            int t2 = 28;
            if (year < 1950 || year > localDate.getYear() || mon > 12 || mon < 1 || day > 31 || day < 1) return false;
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) t2 = 29;
            if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
                if (day > 30) return false;
            }
            if (mon == 2) {
                if (day > t2) return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkMatkhau(String data) {
        return data.matches("^[0-9a-zA-Z]{4,}$");
    }


    public boolean checkPhoneNumber(String data) {
        return data.matches("^(0|84)[3,7,8,9]\\d{8}$");
    }

    public boolean checkInt(String data) {
        return data.matches("^\\d+$");
    }

    public String refresh(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        return s;
    }

    public boolean checkTen1(JLabel lb, JTextField txt) {
        lb.setFont(new Font("Segoe UI", 2, 13));
        lb.setForeground(Color.RED);
        if (txt.getText().equals("")) {
            lb.setText("Không được để trống");
            return false;
        } else {
            String s = refreshName(txt.getText());
            txt.setText(s);
            lb.setText(" ");
            return true;
        }
    }

    public boolean checkSo(JLabel lb, JTextField txt) {
        lb.setFont(new Font("Segoe UI", 2, 13));
        lb.setForeground(Color.RED);
        if (txt.getText() == "") {
            lb.setText("Không được để trống");
        } else {
            if (checkInt(txt.getText())) {
                lb.setText("");
                return true;
            } else {
                lb.setText("Dữ liệu phải là số");
            }
        }
        return false;
    }

    public String refreshName(String str) {
        str = refresh(str);
        str = str.toLowerCase();
        String temp[] = str.split(" ");
        str = ""; // ? ^-^
        for (int i = 0; i < temp.length; i++) {
            str += valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) // ? ^-^
                str += " ";
        }
        return str;
    }


    public long checkMoney(JTextField txt) {
        String a = txt.getText();
        long tien = 0;
        if (a.matches("\\d{1,3}(?:(\\,\\d{3})+(?!\\d))$") || a.matches("[0-9]+$")) {
            a = a.replaceAll("\\s+", "");
            a = a.replaceAll(",", "");
            return Long.parseLong(a);
        } else return 0;
    }

    public boolean checkMoney(JLabel lb, JTextField txt) {
        lb.setFont(new Font("Segoe UI", 2, 13));
        lb.setForeground(Color.RED);
        if (txt.getText().equals("")) {
            lb.setText("Không được để trống");
            return false;
        } else {
            if (checkMoney(txt) == 0) {
                lb.setText("Đơn giá có dạng 9x9999 hoặc 99,999,999");
                return false;
            } else {
                lb.setText("");
                return true;
            }
        }
    }

    public int searchChucvu(ArrayList<DangnhapDTO> list, int check) {
        int i = 0;
        for (DangnhapDTO dn : list
        ) {
            if (check == dn.getMachucvu())
                return i;
            i++;
        }
        return -1;
    }

    public String changeDate(String date) throws ParseException {
        String[] mang = date.split("-");
        Date mon = new SimpleDateFormat("MMM").parse(mang[1]);
        Calendar cal = getInstance();
        cal.setTime(mon);
        mang[1] = valueOf(cal.get(MONTH) + 1);
        return mang[2] + "-" + mang[1] + "-" + mang[0];
    }
}