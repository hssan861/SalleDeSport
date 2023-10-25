/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Post;
import services.PostService;



/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjouterPostController implements Initializable {

    @FXML
    private TextField tfdescription;
   
    @FXML
    private ImageView tfimage;

    @FXML
    private Button dd;
    @FXML
    private Button oo;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private AnchorPane root1;
    
    private File file = null ;
    private Image image = null ;
    private String url_image = null ;
    
    @FXML
    private Button uu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Notif notif = new Notif();
       
    }

    @FXML
    private void Annulerp(ActionEvent event) throws IOException {
        oo.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void Ajouterp(ActionEvent event) throws IOException {
         PostService sp = new PostService();
         String imagePath = "C:\\Users\\lenovo\\Documents\\NetBeansProjects\\SalleDeSport\\src\\Image\\s2.jpg";
    Image image = new Image(new File(imagePath).toURI().toString());
    
    Alert alert ;
         //Validation du champ de description
         
    String description = tfdescription.getText();
    if (description.isEmpty()) {
        errorMessageLabel.setText("Le champ de description est vide. Veuillez entrer une description.");
        return;
    }
 
// Check if a file is selected
if (file != null) {
    imagePath = file.getAbsolutePath();
} else {
    // No image is selected
    errorMessageLabel.setText("Veuillez spécifier une image.");
    return;
}

 
    errorMessageLabel.setText("");
       
         Post p = new Post (tfdescription.getText(),imagePath);
    sp.ajouterPost(p);
    
    
    alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                
                ////////////Notification   
               Notif notif = new Notif();
                notif.notifme("New post added");

                
               
    uu.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
              
        
   
    }

    @FXML
    private void Afficherp(ActionEvent event) throws IOException {
        dd.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
    }

    @FXML
    private void load(ActionEvent event) {
        FileChooser open = new FileChooser();
        file = open.showOpenDialog(root1.getScene().getWindow());

        if (file != null) {
            String imagePath = "C:\\Users\\lenovo\\Documents\\NetBeansProjects\\SalleDeSport\\src\\Image\\s2.jpg";
    Image image = new Image(new File(imagePath).toURI().toString());
            image = new Image(file.toURI().toString(), 200, 119, false, true) {};
            url_image= file.getName();
            tfimage.setImage(image);
        }
        
    }
    
   
    
}
