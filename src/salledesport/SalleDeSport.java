/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salledesport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Comment;
import models.Post;
import services.CommentService;
import services.PostService;

/**
 *
 * @author lenovo
 */
public class SalleDeSport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
      
          //3
        
        PostService ps = new PostService();
         System.out.println(ps.afficherPost());
        CommentService c = new CommentService();
        System.out.println(c.afficherComment());
        // TODO code application logic here
        
       
    // Create an instance of the post class with the data you want to update
    Post postToUpdate = new Post();
   
         
   // postToUpdate.setIdPost(1); // Replace 1 with the actual event ID you want to update
    postToUpdate.setIdUser(10);
    postToUpdate.setDescription("dfgh");
    postToUpdate.setImage("dfgh");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datePost = sdf.parse("2023-06-18");
            postToUpdate.setDate(datePost); 
  
    // Call the modifierEvent method to update the event
   ps.modifierPost(postToUpdate); 
    System.out.println(ps.afficherPost());
   
   //
   Comment commentToUpdate = new Comment();
   
         
   // postToUpdate.setIdPost(1); // Replace 1 with the actual event ID you want to update
    commentToUpdate.setIdPost(10);
    commentToUpdate.setIdUser(11);
    commentToUpdate.setContent("changement");
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
           Date dateComment = sf.parse("2023-06-19");
            commentToUpdate.setDate(dateComment); 
  
     //Call the modifierEvent method to update the event
     
     c.modifierComment(commentToUpdate); 
    System.out.println(c.afficherComment());
      
      PostService Ps = new PostService();
   
   Ps.supprimerPost(1);
   
   CommentService C = new CommentService();
   
   C.supprimerComment(1);
   
   
   
    }
   

    
    
}




