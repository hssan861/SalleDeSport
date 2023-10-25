/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Comment;
import models.Post;
import services.PostService;
import GUI.CommentController;
import java.io.File;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import services.CommentService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherPostController implements Initializable {

    @FXML
    private ListView<Post> lvafficherpost;
    @FXML
    private TextField chercherp;
    @FXML
    private Button mmm;
    @FXML
    private Button ww;

    /**
     * Initializes the controller class.
     */
 CommentService cs= new CommentService();      
PostService es = new PostService();
        ObservableList<Post> items = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lvafficherpost.setCellFactory(param -> new ListCell<Post>() {
    private final ImageView imageView = new ImageView();
    
    protected void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);

        if (empty || post == null) {
            setGraphic(null);
        } else {
           
            try {
                File file = new File(post.getImage());
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Adjust the width as needed
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                                     String EventInfo = "Description: " + post.getDescription() + "\n" 
              
                           ;
                    
                           VBox vbox = new VBox( imageView, new Label(EventInfo));
                    vbox.setSpacing(5);

                    // Affichez le VBox dans la cellule
                    setGraphic(vbox);
                } else {
                    setGraphic(null); // Image file not found
                }
            } catch (Exception e) {
                e.printStackTrace();
                setGraphic(null); // Handle any exceptions gracefully
            }
        }
    }
});

              PostService sp = new PostService();

       PostService es = new PostService();
        ObservableList<Post> items = FXCollections.observableArrayList();
               items.addAll(es.afficherPost());
        lvafficherpost.setItems(items);
      
    
       
       loadPost();
        chercherp.textProperty().addListener((observable, oldValue, newValue) -> {
            searchPost(newValue.toLowerCase());
        });
        
    }
    private void loadPost() {
        items.setAll(es.getAll());
        lvafficherpost.setItems(items);
    }

    @FXML
    private void Modifierposte(ActionEvent event) {
        try {
                  NewFXMain1.post = lvafficherpost.getSelectionModel().getSelectedItem();
                  if (NewFXMain1.post != null) {
                 chercherp.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ModifierPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                 
    
        } catch (Exception e) {
        }
    }

    @FXML
    private void supprimerposte(ActionEvent event) {
        try {
                 NewFXMain1.post= lvafficherpost.getSelectionModel().getSelectedItem();
                  if (NewFXMain1.post != null) {
                 chercherp.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("SupprimerPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
}
      
        } catch (Exception e) {
        
        }}

    @FXML
    private void ajouterposte(ActionEvent event) throws IOException {
         mmm.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AjouterPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
        
        
    }


    @FXML
    private void commenterPost(ActionEvent event) throws IOException {
         
        
        
         ww.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AjouterComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
       
    }

        
    
    private void searchPost(String searchValue) {
        ObservableList<Post> filteredUsers = FXCollections.observableArrayList();

        for (Post user : items) {
            if (user.getDescription().toLowerCase().contains(searchValue) 
                
             )     {
                filteredUsers.add(user);
            }
        }

        lvafficherpost.setItems(filteredUsers);
    }
    
}
