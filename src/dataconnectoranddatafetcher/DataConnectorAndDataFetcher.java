/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataconnectoranddatafetcher;

import java.util.ArrayList;

/**
 *
 * @author gubotdev
 */
public class DataConnectorAndDataFetcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DataFetcher fetcher = new DataFetcher();
        
        //fetcher.insertData("INSERT INTO blair012.test_tbl VALUES (null,'Parth')");
        
        ArrayList<String[]> results = fetcher.getResults("SELECT * FROM blair012.test_tbl");
        
        for(String[] row : results){
            for(String field : row){
                System.out.print(field + " | ");
            }
            System.out.print("\n");
        }
        
    }
    
}
