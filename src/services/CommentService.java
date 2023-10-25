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
public class CommentService {
     MyConnection Mycnx = MyConnection.getInstance();
    Connection cnx = Mycnx.getCnx();;
    
    
    //Add 
    public void ajouterComment(Comment C){


            int idPost = C.getPost().getIdPost();
             
PostService es = new PostService();
        String req = "INSERT INTO comment (  idPost  ,  content  ) VALUES (?,?)";
        try {
             PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, idPost);
           
            ps.setString(2, C.getContent());
         
             int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Commentaire ajoutée avec succès!");
            } else {
                System.out.println("Failed to add Comment.");
            }
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
                int idPost = rs.getInt("IdPost");
                PostService es = new PostService();
               
                Post post = es.getPostById(idPost);
                C.setPost(post);
               // C.setNom(rs.getString("Nom"));
                //C.setPrenom(rs.getString("Prenom"));
                
             
                C.setContent(rs.getString("Content"));
                
               // C.setDateC(rs.getDate("DateC").toLocalDate());
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
            String sql ="UPDATE  comment SET  Content=?  WHERE idC=?";
            PreparedStatement ps = cnx.prepareStatement( sql);  
            ps.setString(1, C.getContent());
             ps.setInt(2,C.getIdC());
             
             int rowsUpdated = ps.executeUpdate();         
        if (rowsUpdated > 0) {
            System.out.println("Comment Modifié avec succès");
        } else {
            System.out.println("Aucune Comment n'a été modifié.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }   
    }
    //supp
      public void supprimerComment(Comment comment) {
            String req = "DELETE FROM `comment` WHERE `idC`=?";
                try {
                    PreparedStatement ps = cnx.prepareStatement(req);
                    ps.setInt(1,comment.getIdC());
            ps.executeUpdate();
            System.out.println("Comment supprimer avec succés");
            
        } catch (SQLException ex) {
            Logger.getLogger(PostService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public List<Comment> getAll() {
    List<Comment> list = new ArrayList<>();
    try {
     
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM comment");

        while (rs.next()) {
             int postId = rs.getInt("idpost");
             PostService postService = new PostService();
            Post post = postService.getPostById(postId);
            Comment u = new Comment(
                    rs.getInt("idc"),
                    post,
               
                rs.getString("content")
            );
            list.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
}
