/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Comment {
    private int idC;
    private int idPost;
    private int idUser;
    private String content;
    private Date date;

    public Comment() {
        
    }

    public Comment(int idPost, int idUser, String content, Date date) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.content = content;
        this.date = date;
    }
    

    

    public Comment(int idC, int idPost, int idUser, String content, Date date) {
        this.idC = idC;
        this.idPost = idPost;
        this.idUser = idUser;
        this.content = content;
        this.date = date;
    }

    public int getIdC() {
        return idC;
    }

    public int getIdPost() {
        return idPost;
    }

  

    public int getIdUser() {
        return idUser;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

   

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" + "idC=" + idC + ", idPost=" + idPost + ", idUser=" + idUser + ", content=" + content + ", date=" + date + '}';
    }
    
    
    
}
