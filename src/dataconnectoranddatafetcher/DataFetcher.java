/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataconnectoranddatafetcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gubotdev
 */
public class DataFetcher {
    
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    
    public DataFetcher(){
        
    }
    
    public ArrayList<String[]> getResults(String query){
        ArrayList<String[]> al = new ArrayList();
        ResultSet rs;
        ResultSetMetaData rsmd;
        try {
            Connection conn = DBConnector.getConn();
            rs = populateResultSet(query, conn);
            rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] colNames = getColNames(colCount);
            al.add(colNames);
            while(rs.next()){
                String[] currRow = new String[colCount];
                for(int i = 0; i < colCount; i++){
                    currRow[i] = rs.getString(i+1);
                }
                al.add(currRow);
            }
            DBConnector.closeConn();
        } catch (SQLException ex) {
            Logger.getLogger(DataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    private ResultSet populateResultSet(String query, Connection conn) throws SQLException {
        
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        return rs;
    }

    private String[] getColNames(int colCt) throws SQLException {
        String[] colNames = new String[colCt];
        for(int i = 0; i < colCt; i++){
            colNames[i] = rsmd.getColumnName(i+1);
        }
        return colNames;
    }
    
    public void insertData(String sql){
        try {
            Connection conn = DBConnector.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
