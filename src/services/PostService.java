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
public class PostService {
    
        
    //var
    Connection cnx = MyConnection.getInstance().getCnx();
    
    
    //Add 
    public void ajouterPost(Post p){
        String req = "INSERT INTO 'post'('id_user', 'description', 'image', 'date') VALUES (?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdUser());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage());
        
            
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sdf.format(p.getDate());
             ps.setString(6,date);
            ps.executeUpdate();
            System.out.println("Post ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Fetch
     public List<Post> afficherPost(){
        List<Post> Posts = new ArrayList<>();
         //1
         String req = "SELECT * FROM post";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Post p = new Post();
                p.setIdPost(rs.getInt("IdPost"));//(rs.getInt("id"));
                p.setIdUser(rs.getInt("IdUser"));
                p. setDescription(rs.getString("Description"));
                p.setImage(rs.getString("Image"));
                p.setDate(rs.getDate("Date"));
                Posts.add(p);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return Posts;
    }
     //Modifier
     public void modifierPost(Post p) {
          try {
String sql = "UPDATE post SET  IdUser=?, Description=?, Image=?, Date=? WHERE idPost=?";
            PreparedStatement ps = cnx.prepareStatement(sql);
         
            ps.setInt(1, p.getIdUser());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getImage());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sdf.format(p.getDate());
             ps.setString(4,date);
             ps.setInt(5,p.getIdPost());
             
            ps.executeUpdate();
            System.out.println("Post Modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex);
        
        }
     }
     //supprimer 
     public void supprimerPost(int idPost) {
            System.out.println(idPost);
                try {
            String req = "DELETE FROM Post WHERE 'idPost'="+idPost;
            Statement stm = cnx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Post supprimer avec succés");
        } catch (SQLException ex) {
            System.out.println(ex);
            }
        }

    
}
    

