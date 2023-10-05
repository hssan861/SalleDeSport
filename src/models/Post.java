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
public class Post {
     private int idPost;
    private int id_User;
    private String description;
    private String image;
    private Date date;
    
    //constructeur 

    public Post() {
    }

    public Post(int idUser, String description, String image, Date date) {
        this.id_User = idUser;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Post(int idPost, int idUser, String description, String image, Date date) {
        this.idPost = idPost;
        this.id_User = idUser;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Post(int i, String ahlem, String ahlem0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdPost() {
        return idPost;
    }

    public int getIdUser() {
        return id_User;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public void setIdUser(int idUser) {
        this.id_User = idUser;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" + "idPost=" + idPost + ", idUser=" + id_User + ", description=" + description + ", image=" + image + ", date=" + date + '}';
    }
    
    
}
