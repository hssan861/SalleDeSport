/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Post;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierPostController implements Initializable {

    
    @FXML
    private Button imagemodifierbutton;
    
    @FXML
    private TextField tfdescription;
    @FXML
    private Button pp;
    @FXML
    private ImageView tfimagee;
    
    
    private File file = null ;
    private Image image = null ;
    private String url_image = null ;
    @FXML
    private AnchorPane root3;
    @FXML
    private Label errorMessageLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      String imagePath = NewFXMain1.post.getImage(); 
   // System.out.println("Image Path: " + imagePath);
    Image image = new Image(new File(imagePath).toURI().toString());       
       tfimagee.setImage(image);
       tfdescription.setText(NewFXMain1.post.getDescription());
        
 

       
    }    

    @FXML
    private void Annulermodifierp(ActionEvent event) throws IOException {
       pp .getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void modifierp(ActionEvent event) {
    
    try {
    PostService es = new PostService();
    
   
        NewFXMain1.post.setDescription(tfdescription.getText());
       String imagePath = NewFXMain1.post.getImage(); 
   // System.out.println("Image Path: " + imagePath);
    Image image = new Image(new File(imagePath).toURI().toString());       
      // tfimagee.setImage(image);
    
             //Validation du champ de description
       String description = tfdescription.getText();
    if (description.isEmpty()) {
        errorMessageLabel.setText("Le champ de description est vide. Veuillez entrer une description.");
        return;
    }
    
       if (file != null) {
    imagePath = file.getAbsolutePath();
} else {
    // No image is selected
    errorMessageLabel.setText("Veuillez spécifier une image.");
    return;
}
    errorMessageLabel.setText("");
    
    Post p = new Post (NewFXMain1.post.getIdPost(),tfdescription.getText(),imagePath);
       
        es.modifierPost(p);

        Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
        } catch (Exception e) {
        e.printStackTrace();
    }
  
    
    
     
    

    
    }
    
    
    //////////////////////////////////

    @FXML
    private void loadmod(ActionEvent event) {
        FileChooser open = new FileChooser();
        file = open.showOpenDialog(root3.getScene().getWindow());

        if (file != null) {
            String imagePath = "C:\\Users\\lenovo\\Documents\\NetBeansProjects\\SalleDeSport\\src\\GUI\\Img.jpg";
    Image image = new Image(new File(imagePath).toURI().toString());
            image = new Image(file.toURI().toString(), 200, 119, false, true) {};
            url_image= file.getName();
            tfimagee.setImage(image);
        
    }
}}

