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
import java.time.LocalDate;
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
    private Connection cnx = MyConnection.getInstance().getCnx();
    
    
    //Add 
    public void ajouterPost(Post p){
        String req = "INSERT INTO post(description, image) VALUES (?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, p.getDescription());
            ps.setString(2, p.getImage());
        
            
          
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
               
                p. setDescription(rs.getString("Description"));
                p.setImage(rs.getString("Image"));
               
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
String sql = "UPDATE post SET   Description=?, Image=?  WHERE idPost=?";
            PreparedStatement ps = cnx.prepareStatement(sql);
         
           
            ps.setString(1, p.getDescription());
            ps.setString(2, p.getImage());
            
           
             ps.setInt(3,p.getIdPost());
             
            ps.executeUpdate();
            System.out.println("Post Modifié avec succées");
        } catch (SQLException ex) {
            System.out.println(ex);
        
        }
     }
     //supprimer 
     public void supprimerPost(Post post) {
            String req = "DELETE FROM `post` WHERE `idPost`=?";
                try {
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setInt(1,post.getIdPost());
                   
            
            
            ps.executeUpdate();
            System.out.println("Post supprimer avec succés");
            
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
     
     public Post getPostById(int idPost) {
        Post post = null ;
        String req = "SELECT * FROM post WHERE idPost = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, idPost);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                post = new Post();
                post.setIdPost(resultSet.getInt("idPost"));
               
                post.setDescription(resultSet.getString("Description"));
                post.setImage(resultSet.getString("Image"));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

public List<Post> getAll() {
    List<Post> list = new ArrayList<>();
    try {
     
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM post");

        while (rs.next()) {
            Post u = new Post(
                    rs.getInt("idPost"),
                    rs.getString("Description"),
               
                rs.getString("Image")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

    
}
    

