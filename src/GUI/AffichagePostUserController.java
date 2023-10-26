/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Post;
import services.CommentService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AffichagePostUserController implements Initializable {

    @FXML
    private ListView<Post> lvaffichercommentUser;
    @FXML
    private TextField cherchercUser;

    /**
     * Initializes the controller class.
     */PostService sp = new PostService();
      ObservableList<Post> items = FXCollections.observableArrayList();
    @FXML
    private Button qq;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CommentService cs= new CommentService();      
PostService es = new PostService();
       
        
         lvaffichercommentUser.setCellFactory(param -> new ListCell<Post>() {
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

      // PostService es = new PostService();
        //ObservableList<Post> items = FXCollections.observableArrayList();
               items.addAll(es.afficherPost());
        lvaffichercommentUser.setItems(items);
        
        
       loadPost();
        cherchercUser.textProperty().addListener((observable, oldValue, newValue) -> {
            searchPost(newValue.toLowerCase());
        });}
        
    private void loadPost() {
        items.setAll(sp.getAll());
        lvaffichercommentUser.setItems(items);
    }
    

    @FXML
    private void commentUser(ActionEvent event) throws IOException {
        qq.getScene().getWindow().hide();
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

        lvaffichercommentUser.setItems(filteredUsers);
    }
    
}
