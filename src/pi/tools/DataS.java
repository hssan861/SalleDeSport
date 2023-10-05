/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hama
 */
public class DataS {
     private Connection cnx;
    private static DataS instance;
    
    private String url = "jdbc:mysql://localhost:3306/salledesport";
    private String user = "root";
    private String password = "";
    
    private DataS(){
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to DB !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DataS getInstance(){
        if(instance == null){
            instance = new DataS();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return this.cnx;
    }
}