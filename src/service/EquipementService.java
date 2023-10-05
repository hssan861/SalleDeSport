/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Equipement;
import util.MyConnection;
import java.util.Date;
/**
 *
 * @author Lenovo
 */
public class EquipementService {
    
    //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    
    public void ajouterEquipement (Equipement E){
        String req = "INSERT INTO `equipement`( `Nom`, `Quantite`, `DateAchat`, `PrixAchat`, `IdCategorie`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
           
            ps.setString(1, E.getNom());
            ps.setInt(2, E.getQuantite());
           // ps.setDate(3, (java.sql.Date) E.getDateAchat());
            ps.setString(3, E.getDateAchat());
            ps.setFloat(4,  E.getPrixAchat());
            ps.setInt(5, E.getIdCategorie());
            ps.executeUpdate();
            System.out.println("Equipement ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(EquipementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fetch
    public List<Equipement> afficherEquipement(){
        List<Equipement> equipement  = new ArrayList<>();
         //1
         String req = "SELECT * FROM `equipement` ";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs1 = st.executeQuery(req);
            while (rs1.next()) {
                Equipement E1 = new Equipement();
                E1.setID(rs1.getInt(1));//(rs.getInt("id"));
                E1.setNom(rs1.getString("Nom Equipement"));
                E1.setQuantite(rs1.getInt(3));
                E1.setDateAchat(rs1.getString(4));
                E1.setPrixAchat(rs1.getFloat(5));
                E1.setIdCategorie(rs1.getInt(6));
               
                equipement .add(E1);
            }
                  
            
        }
        catch (SQLException ex) {
            Logger.getLogger(EquipementService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return equipement;
        }
  
     public void supprimer(Equipement e) {
                try {
            String req = "DELETE FROM `equipement` WHERE ID=+id";
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Equipement supprimer avec succés");
             } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        
        
        public void modifierEquipement(Equipement e) {
          
      try {
            String req="UPDATE `equipement` SET `ID`='[value-1]',`Nom`='[value-2]',`Quantite`='[value-3]',`DateAchat`='[value-4]',`PrixAchat`='[value-5]',`IdCategorie`='[value-6]' WHERE 1";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, e.getID());
            st.setString(2, e.getNom());
            st.setInt(3, e.getQuantite());
            st.setString(4,  e.getDateAchat());
            st.setFloat(4, e.getPrixAchat());
            st.setInt(5, e.getIdCategorie());
         
            st.executeUpdate();
            System.out.println("Equipement Modifié avec succés");
        }
         catch (SQLException ex) {
            System.out.println(ex);
        }
    
    
    
    
    
    
}
}