package DAL;

import java.sql.*;

public class MySQLConnect {
    private Statement stmt=null;
    private Connection conn=null;
    private String url="jdbc:mysql://localhost:3306/vanphongpham";
    private String user="root";
    private String password="";
    public MySQLConnect(){
    }
    public Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void CloseConnect(){
        if(conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MySQLConnect mc=new MySQLConnect();
        System.out.println(mc.getConn());
    }
}
