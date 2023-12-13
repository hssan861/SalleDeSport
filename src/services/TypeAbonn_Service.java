/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Type_abonn;
import util.MyConnection;
/**
 *
 * @author chayma2
 */
public class TypeAbonn_Service {
    
      //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    /*===================================================*/
      public void ajoutTypeAbonn(Type_abonn a) {
        String req = "INSERT INTO `type_abonn`(`type`, `description`, `nb_abonnement`) VALUES ( ?, ?,?)";
        
        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            
            R.setObject(1, a.getType().toString()); 
            R.setObject(2, a.getDescription().toString());
            R.setInt(3, a.getNb_abonnement());
            
            int rowsAffected = R.executeUpdate();
            
        if (rowsAffected > 0) {
            System.out.println("Abonnement ajoutée avec succès!");
        } else {
            System.out.println("Échec de l'abonnement.");
        }
        } catch (SQLException ex) {
                System.err.println("Erreur lors de l'abonnement : " + ex.getMessage());        }
    }
      
      
          /*===================================================*/
      
           //Fetch
    public List<Type_abonn> afficherTypeAbonn(){
        List<Type_abonn> t = new ArrayList<>();
         //1
         String req = "SELECT * FROM type_abonn";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Type_abonn o = new Type_abonn();
                o.setId(rs.getInt(1));
                o.setType(rs.getString(2));
                o.setDescription(rs.getString("description"));
                o.setNb_abonnement(rs.getInt(4));
                t.add(o);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TypeAbonn_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return t;
    }
          /*===================================================*/

        public Type_abonn getTypeAbonnById(int typeAbonId) {
            Type_abonn typeAbonn = null;
            String req = "SELECT * FROM type_abonn WHERE id = ?";

            try (PreparedStatement pst = cnx.prepareStatement(req)) {
                pst.setInt(1, typeAbonId);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    typeAbonn = new Type_abonn();
                    typeAbonn.setId(rs.getInt("id"));
                    typeAbonn.setType(rs.getString("type"));
                    typeAbonn.setDescription(rs.getString("description"));
                    typeAbonn.setNb_abonnement(rs.getInt("nb_abonnement"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TypeAbonn_Service.class.getName()).log(Level.SEVERE, null, ex);
            }

            return typeAbonn;
        }
                  /*===================================================*/

      
       
    
    
}
