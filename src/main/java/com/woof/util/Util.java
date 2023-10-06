package com.woof.util;

import java.sql.*;

public class Util {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String URL = "jdbc:mysql://localhost:3306/woof?serverTimezone=Asia/Taipei";

    public static final String USER = "root";

    public static final String PASSWORD = "password";

    static {
        try {
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException{
        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        return con;
    }

    public static void closeResources(Connection con , PreparedStatement ps , ResultSet rs){
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
