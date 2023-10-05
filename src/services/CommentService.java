/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Comment;
import models.Post;
import util.MyConnection;

/**
 *
 * @author lenovo
 */
public class CommentService {
     MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();;
    
    
    //Add 
    public void ajouterComment(Comment C){
        String req = "INSERT INTO 'comment'( 'idPost', 'idUser', 'content', 'date) VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1, C.getIdPost());
            ps.setInt(2, C.getIdUser());
            ps.setString(3, C.getContent());
        
            
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sdf.format(C.getDate());
             ps.setString(6,date);
            ps.executeUpdate();
            System.out.println("Comment ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Fetch 
    public List<Comment> afficherComment(){
        List<Comment> Comments = new ArrayList<>();
         //1
         String req = "SELECT * FROM comment";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Comment C = new Comment();
                C.setIdC(rs.getInt("IdC"));//(rs.getInt("id"));
                C.setIdUser(rs.getInt("IdUser"));
                C.setIdPost(rs.getInt("IdPost"));
                C.setContent(rs.getString("Content"));
                
                C.setDate(rs.getDate("Date"));
                Comments.add(C);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Comments;
   
    }
    
    //Modifier
    
    public void modifierComment(Comment C) {
          try {
            String sql ="UPDATE  comment SET  IdPost =?, IdUser=? , Content=? , Date=? WHERE idC=?";
            PreparedStatement ps = cnx.prepareStatement( sql);
         
            ps.setInt(1, C.getIdPost());
            ps.setInt(2, C.getIdUser());
            ps.setString(3, C.getContent());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sdf.format(C.getDate());
             ps.setString(4,date);
             ps.setInt(5,C.getIdC());
             
            ps.executeUpdate();
            System.out.println("Comment Modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex);
        
       
        
        }
     }
    //supp
     public void supprimerComment(int idC) {
            System.out.println(idC);
                try {
            String req = "DELETE FROM Comment WHERE 'idPost'="+idC;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Comment supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        }
}
