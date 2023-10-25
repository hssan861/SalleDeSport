/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Date;

public class Post {
    
    private int idPost;
    private String description;
    private String image;
  
    
    //constructeur 

    public Post() {
    }

    public Post( String description, String image) {
        
        this.description = description;
        this.image = image;
        
    }

    public Post( int idPost, String description, String image) {
        this.idPost = idPost;
        
        this.description = description;
        this.image = image;
       
    }

    

    public int getIdPost() {
        return idPost;
    }


    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }


    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

  
    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return   description + "           " + image;
    }


    
}
