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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
;
import java.time.ZoneId;
import java.util.ArrayList;
import util.MyConnection;
import models.Abonnement;
import models.Type_abonn;
import java.time.ZoneId;
import util.MyConnection;
import models.Abonnement;
import models.Type_abonn;
/**
 *
 * @author chayma2
 */
public class Abonnement_Service {
       //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    TypeAbonn_Service TS=new TypeAbonn_Service();
    /*===================================================*/
      /*public void Abonner(Abonnement a, Type_abonn t) {
        String req = "INSERT INTO `abonnement`( `dateAbonnement`, `typeAbon`, `idUser`, `verification_code`) VALUES ( ?, ?,?,?)";
        String updateQuery = "UPDATE `type_abonn` SET `nb_abonnement` = ? WHERE `id` = ?";

        try (Connection cnx = MyConnection.getInstance().getCnx();
             PreparedStatement R = cnx.prepareStatement(req))
        {
            
            R.setObject(1, a.getDateAbonnement()); 
            System.out.println("Type_abonn ID: " + t.getId());
            R.setInt(2, t.getId()); // Utiliser l'ID du type_abonn
            R.setInt(3, a.getIdUser());
            R.setInt(4, a.getVerification_code());

            
            
            int rowsAffected = R.executeUpdate();
        if (rowsAffected > 0) {
            
            t.getId();
            t.setNb_abonnement(t.getNb_abonnement() + 1);
            System.out.println("Abonner ajoutée avec succès!");
        } else {
            System.out.println("Échec de l'abonnement.");
        }
        } catch (SQLException ex) {
                System.err.println("Erreur lors de l'abonnement : " + ex.getMessage());        }
    }*/
    
    public void Abonner(Abonnement a, Type_abonn t) {
    String insertQuery = "INSERT INTO `abonnement` (`dateAbonnement`, `typeAbon`, `idUser`, `verification_code`) VALUES (?, ?, ?, ?)";
    String updateQuery = "UPDATE `type_abonn` SET `nb_abonnement` = ? WHERE `id` = ?";

    try (Connection cnx = MyConnection.getInstance().getCnx();
         PreparedStatement insertStatement = cnx.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement updateStatement = cnx.prepareStatement(updateQuery)) {

        // Insert Abonnement
        insertStatement.setObject(1, a.getDateAbonnement());
        insertStatement.setInt(2, t.getId());
        insertStatement.setInt(3, a.getIdUser());
        insertStatement.setInt(4, a.getVerification_code());

        int rowsAffected = insertStatement.executeUpdate();

        if (rowsAffected > 0) {
            // Get the generated ID of the inserted Abonnement
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);

                // Update nb_abonnement in Type_abonn
                t.setNb_abonnement(t.getNb_abonnement() + 1);

                // Update the nb_abonnement in the database
                updateStatement.setInt(1, t.getNb_abonnement());
                updateStatement.setInt(2, t.getId());
                updateStatement.executeUpdate();

                System.out.println("Abonnement ajouté avec succès! (ID: " + generatedId + ")");
            }
        } else {
            System.out.println("Échec de l'abonnement.");
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de l'abonnement : " + ex.getMessage());
    }
}

      

      
      
          /*===================================================*/
       
       
          //Fetch
    public List<Abonnement> afficherAbonnement(){
        List<Abonnement> t = new ArrayList<>();
         //1
         String req = "SELECT * FROM abonnement";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Abonnement o = new Abonnement();
                o.setIdabonement(rs.getInt(1));
                // Convert Date to LocalDateTime
                java.sql.Date  dateAbonnement = rs.getDate("dateAbonnement");
                java.util.Date utilDate = new java.util.Date(dateAbonnement.getTime());
                    LocalDateTime localDateTime = utilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                o.setDateAbonnement(localDateTime);            
                //o.setTypeAbon((Type_abonn) rs.getObject(3));
                // Retrieve the ID of the Type_abonn (assuming typeAbon is the ID)
                int typeAbonId = rs.getInt(3);
            
                // Fetch the Type_abonn using the ID (implement this method)
                Type_abonn typeAbonn = TS.getTypeAbonnById(typeAbonId);
                //Type_abonn typeAbonn = getTypeAbonnById(typeAbonId);
                o.setTypeAbon(typeAbonn);
            
                o.setIdUser(rs.getInt(4));
                o.setVerification_code(rs.getInt(4));
                t.add(o);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TypeAbonn_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return t;
    }
       
          /*===================================================*/

      
      
}
