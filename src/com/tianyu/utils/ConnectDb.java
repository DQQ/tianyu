package com.tianyu.utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by dongqingqing on 17/9/7.
 */
public class ConnectDb {
    private static String driveClassName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://10.20.216.195:3306/cm_tester?useUnicode=true&characterEncoding=utf8";

    private static String user = "test";
    private static String password = "test";

    public static Connection Connect(){
        Connection conn = null;

        //load driver
        try {
            Class.forName(driveClassName);
        } catch (ClassNotFoundException  e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        //connect db
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }

        return conn;
    }


}
