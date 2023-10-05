/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import models.Categorie;

import util.MyConnection;
/**
 *
 * @author Lenovo
 */
public class CategorieService {
    
     //var
    MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();
    
    
    public void ajouterCategorie (Categorie C){
        String req = "INSERT INTO `categorie`( `NomCategorie`, `DescriptionCategorie`) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, C.getNomCategorie());
            ps.setString(2, C.getDescriptionCategorie());
            ps.executeUpdate();
            System.out.println("categorie ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //Fetch
    public List<Categorie> afficherCategorie(){
        List<Categorie> categorie = new ArrayList<>();
         //1
         String req = "SELECT * FROM `categorie` ";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Categorie CA = new Categorie();
                CA.setIdCategorie(rs.getInt(1));//(rs.getInt("id"));
                CA.setNomCategorie(rs.getString("NomCategorie"));
                CA.setDescriptionCategorie(rs.getString(3));
               
                categorie.add(CA);
            }
                  
            
        }
        catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return categorie;
        }
 
    public void supprimer(Categorie c) {
                try {
            String req = "DELETE FROM `categorie` WHERE ID=?";
             PreparedStatement st = cnx.prepareStatement(req);
              st.setInt(1,c.getIdCategorie());
            st.executeUpdate();
            System.out.println("categorie supprimer avec succés");
        }
                catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void modifier( Categorie c)
    {
          
      try {
            String req ="UPDATE `categorie` SET `NomCategorie`=?,`DescriptionCategorie`=? WHERE ID=?";
            PreparedStatement st = cnx.prepareStatement(req);
            
            st.setString(1, c.getNomCategorie());
            st.setString(2, c.getDescriptionCategorie());
            st.setInt(3,c.getIdCategorie());                      
            st.executeUpdate();
            System.out.println("Categorie Modifié avec succés");
        }
         catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void supprimer(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
   
    
    
    
   
    
    
    
    
    
    
 
       
    
    
    