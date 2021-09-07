package GUI.Components;


import GUI.QL_Thongke;
import org.jdatepicker.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import utils.FormaterDate;
import utils.NewColor;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DatePicker extends JFrame {
    public DatePicker(){
        initComponents();
        setVisible(false);
        setSize(390, 300);
        setResizable(false);
        setBackground(NewColor.background);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private void initComponents() {
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        add(datePanel, BorderLayout.CENTER);
        add(btnGet,BorderLayout.SOUTH);
        btnGet.addActionListener(e -> {
            Date d = (Date) datePanel.getModel().getValue();
            Date now = Date.from(lcd.atStartOfDay(ZoneId.systemDefault()).toInstant());
            date = new FormaterDate().formatter.format(d);
            if(QL_Thongke.z==0) {
                Date check = null;
                try {
                    if(!QL_Thongke.txtDate2.getText().equals(""))
                    check = new SimpleDateFormat("yyyy-MM-dd").parse(QL_Thongke.txtDate2.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                if (!QL_Thongke.txtDate2.getText().equals("") && d.after(check))
                    QL_Thongke.txtDate1.setText(QL_Thongke.txtDate2.getText());
                else {
                    if (d.after(now)) QL_Thongke.txtDate1.setText(new FormaterDate().formatter.format(now));
                    else QL_Thongke.txtDate1.setText(date);
                }
            }
            else if(QL_Thongke.z==1) {
                Date check = null;
                try {
                    if(!QL_Thongke.txtDate1.getText().equals(""))
                        check = new SimpleDateFormat("yyyy-MM-dd").parse(QL_Thongke.txtDate1.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                if (!QL_Thongke.txtDate1.getText().equals("") && check.after(d))
                    QL_Thongke.txtDate2.setText(QL_Thongke.txtDate1.getText());
                else {
                    if (d.after(now)) QL_Thongke.txtDate2.setText(new FormaterDate().formatter.format(now));
                    else QL_Thongke.txtDate2.setText(date);
                }
            }
            setVisible(false);
//            dispose();
        });
    }
    public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat formatshow=new SimpleDateFormat("dd-MM-yyyy");
    public String date;
    private JDatePickerImpl datePicker;
    private JDatePanelImpl datePanel;
    private UtilDateModel model;
    public static JButton btnGet=new JButton("Chọn");
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
    private LocalDate lcd=LocalDate.now();

}