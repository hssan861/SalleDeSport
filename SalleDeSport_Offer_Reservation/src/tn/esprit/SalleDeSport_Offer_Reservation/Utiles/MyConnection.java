/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chayma2
 */
public class MyConnection {
    
    //DB Credentials
    final String URL = "jdbc:mysql://localhost:3306/SalleDeSport";
    final String USR = "root";
    final String PWD = "";
    
    //var
    Connection cnx;
    static MyConnection instance;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion etablie avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("Failed to connect to the database: " + ex.getMessage());

        }
        
    }

    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
}
