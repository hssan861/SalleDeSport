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
        PostService ps=new PostService();
        System.out.println(ps.getAll());
        //Ajouter post 
       /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2022-09-30");
        Post p = new Post (22,5, "ranim", "asma", date);
        PostService sp = new PostService();
        sp.ajouterPost(p);
        System.out.println(sp.afficherPost());
        
        //ajouter comment 
       SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date dateC = dateFormat1.parse("2023-09-30");
        Post p=new Post(19,5,"bonjour","ccc",dateC);
        Comment C = new Comment ( "asma","zakraoui",p, 18, "salah",dateC);
        PostService ps = new PostService();
       CommentService sp = new CommentService ();
       sp.ajouterComment(C);
        System.out.println(sp.afficherComment());
        
        
      
        
        
         
        //System.out.println(ps.afficherPost());
        CommentService c = new CommentService();
        System.out.println(c.afficherComment());
        // TODO code application logic here
        
       
    // Create an instance of the post class with the data you want to update
   /* Post postToUpdate = new Post();
   
         
   postToUpdate.setIdPost(15); // Replace 1 with the actual event ID you want to update
    postToUpdate.setIdUser(1);
    postToUpdate.setDescription("hola");
    postToUpdate.setImage("ccc");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datePost = sdf.parse("2023-06-18");
            postToUpdate.setDate(datePost); 
    // Call the modifierEvent method to update the event
   ps.modifierPost(postToUpdate); 
    System.out.println(ps.afficherPost());*/
   
 /*
   Comment commentToUpdate = new Comment(); 
   
    commentToUpdate.setIdC(28);
   commentToUpdate.setPost(p); 
    commentToUpdate.setIdUser(1);
    commentToUpdate.setContent("asmaaaa");
    
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
           Date dateComment = sf.parse("2023-06-22");
            commentToUpdate.setDate(dateComment); 
  
     //Call the modifiercomment method to update the comment
     
     c.modifierComment(commentToUpdate); 
    System.out.println(c.afficherComment());
     
  
    //deletee
   /* 
    PostService Ps = new PostService();
   Post posttoDelete = new Post();
     posttoDelete.setIdPost(7); 
     Ps.supprimerPost(posttoDelete);
     System.out.println(Ps.afficherPost());

   
   CommentService C = new CommentService();
   Comment commenttoDelete = new Comment();
     commenttoDelete.setIdC(3); 
     C.supprimerComment(commenttoDelete);
     System.out.println(C.afficherComment());*/
   
   
   
   
    }
   

    
    
}




