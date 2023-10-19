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
   private MyConnection myConnection = MyConnection.getInstance();
    private Connection connection = myConnection.getCnx();
    
    
     public boolean CategorieExists(int CategorieId) {
    String req = "SELECT COUNT(*) FROM Categorie WHERE IdCategorie = ?";
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, CategorieId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count >0;
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    
    public void ajouterCategorie (Categorie categorie){
        String req = "INSERT INTO `categorie`( `NomCategorie`, `DescriptionCategorie`) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            
          preparedStatement.setString(1, categorie.getNomCategorie());
           preparedStatement.setString(2, categorie.getDescriptionCategorie());
            preparedStatement.executeUpdate();
            System.out.println("categorie ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //Fetch
    public List<Categorie> afficherCategorie(){
        List<Categorie> categories = new ArrayList<>();
         //1
         String req = "SELECT * FROM `categorie` ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(resultSet.getInt(1));//(rs.getInt("id"));
               categorie.setNomCategorie(resultSet.getString("NomCategorie"));
             categorie.setDescriptionCategorie(resultSet.getString(3));
               
                categories.add(categorie);
            }
                  
            
        }
        catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
             return categories;
        }
 
    
    
       public List<Categorie> getCategoryListFromService() {
        // Call the afficherCategorie method to retrieve categories
        return afficherCategorie();
    }
       
       
    public void supprimerCategorie(Categorie categorie) {
               
            String req = "DELETE FROM `categorie` WHERE IdCategorie=?";
               try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
             preparedStatement.setInt(1,categorie.getIdCategorie());
          
             int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("categorie supprimer avec succée!");
        } else {
            System.out.println(" Aucune categorie avec cette IDc categorie supprimer avec succée ");
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
                
    
    public void modifierCategorie(Categorie categorie) {
    
          
     
            String req ="UPDATE `categorie` SET `NomCategorie`=?,`DescriptionCategorie`=? WHERE IdCategorie=?";
             try {
                 PreparedStatement preparedStatement = connection.prepareStatement(req);
             
            
            preparedStatement.setString(1, categorie.getNomCategorie());
            preparedStatement.setString(2, categorie.getDescriptionCategorie());
            preparedStatement.setInt(3,categorie.getIdCategorie());                      
            
           
            int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Categorie modifier avec succée!");
        } else {
            System.out.println("Aucune categorie avec cette ID");
        }
        }
         catch (SQLException ex) {
              Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
        public Categorie getCategorieById(int IdCategorie) {
        Categorie categorie = null;
        String req = "SELECT * FROM `categorie` WHERE `IdCategorie` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, IdCategorie);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                categorie = new Categorie();
                categorie.setIdCategorie(resultSet.getInt("IdCategorie"));
                categorie.setNomCategorie(resultSet.getString("NomCategorie"));
                categorie.setDescriptionCategorie(resultSet.getString("DescriptionCategorie"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorie;
    }
    
}
   
    
    
    
   
    
    
    
    
    
    
 
       
    
    
    