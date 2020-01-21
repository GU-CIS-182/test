/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataconnectoranddatafetcher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gubotdev
 */
public class DBConnector {
    private static Connection conn = null;
    private static String url = "jdbc:mysql://fa19gcis516.cnsnapspj50q.us-west-2.rds.amazonaws.com"; //don't forget to add jdbc:mysql://
    private static String uname = "admin";
    private static String pword = "Gannon123";
    
    private static void connectToDB(){
        try {
            //Establish Database Connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, uname, pword);
            System.out.println("Database connection established!!!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConn(){
        if(conn==null){
            connectToDB();
        }
        return conn;
    }
    
    public static void closeConn(){
        if(conn!=null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
