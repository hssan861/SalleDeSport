/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Comment {

    private int idC;
    private Post post;
    private String content;
    

    public Comment() {
        
    }

    public Comment(int idC, Post post, String content) {
        this.idC = idC;
        this.post = post;
        this.content = content;
    }

   

    public Comment(Post post, String content) {
        this.post = post;
        this.content = content;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
    
    
    
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

  
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" + "post=" + post + ", content=" + content + '}';
    }

    
   
     
}
