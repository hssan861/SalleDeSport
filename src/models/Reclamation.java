package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Reclamation {
    private int id;
    private String description;
    private User user;

    public Reclamation() {
    }

    public Reclamation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Reclamation(int id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Reclamation(String description, User user) {
        this.description = description;
        this.user = user;
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", user=" + user + '}';
        
    }
    public ImageView getImageAsImageView() {
        // Créez un ImageView à partir du nom de fichier d'image
        ImageView imageView = new ImageView(new Image( this.user.getImg()));
        // Configurez la taille de l'image si nécessaire
        imageView.setFitWidth(100); // Largeur souhaitée
        imageView.setFitHeight(100); // Hauteur souhaitée
        return imageView;
    }
    
    
}