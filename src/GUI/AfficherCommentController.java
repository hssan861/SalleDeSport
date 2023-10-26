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
import models.Comment;
import models.Post;
import services.CommentService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AfficherCommentController implements Initializable {

    @FXML
    private ListView<Comment> lvaffichercomment;
    
    @FXML
    private Button aa;
    @FXML
    private Button mm;
CommentService es = new CommentService();
        ObservableList<Comment> items = FXCollections.observableArrayList();
    @FXML
    private Button xxx;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
          
        lvaffichercomment.setCellFactory(param -> new ListCell<Comment>() {
    private final ImageView imageView = new ImageView();
    
    protected void updateItem(Comment C, boolean empty) {
        super.updateItem(C, empty);

        if (empty || C == null) {
            setGraphic(null);
        } else {
           
            try {
                File file = new File(C.getPost().getImage());
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Adjust the width as needed
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                                     String CommentInfo = "Commentaire: " + C.getContent() + "\n" 
              
                           ;
                    
                           VBox vbox = new VBox( imageView, new Label(CommentInfo));
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
       CommentService cs = new CommentService();
        ObservableList<Comment> items = FXCollections.observableArrayList();
               items.addAll(cs.getAll());
        lvaffichercomment.setItems(items);
      
       
        
        // Remplissez la liste observable "items" avec les données de votre service EventServices
      //  items.addAll(es.afficherComment());

        // Utilisez la ListView définie dans FXML pour afficher les données
        //lvaffichercomment.setItems(items);
        loadComment();

      
        
        // TODO
    }    

    @FXML
    private void supprimercomment(ActionEvent event) {
        try {
                 NewFXMain2.comment= lvaffichercomment.getSelectionModel().getSelectedItem();
                  if (NewFXMain2.comment != null) {
                
              Parent root = FXMLLoader.load(getClass().getResource("SupprimerComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
}
      
        } catch (Exception e) {
        
        }
    }

    @FXML
    private void modifiercomment(ActionEvent event) {
         try {
                  NewFXMain2.comment = (Comment) lvaffichercomment.getSelectionModel().getSelectedItem();
                  if (NewFXMain1.post != null) {
                 
              Parent root = FXMLLoader.load(getClass().getResource("ModifierComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                 
    
        } catch (Exception e) {
        }
    }


    @FXML
    private void ajoutercomment(ActionEvent event) throws IOException {
       
        
        
        try {
                  NewFXMain2.comment = (Comment) lvaffichercomment.getSelectionModel().getSelectedItem();
                  if (NewFXMain1.post != null) {
                 
               Parent root = FXMLLoader.load(getClass().getResource("AjouterComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                   } catch (Exception e) {
        
        
    }
      aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AjouterComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();}

    private void loadComment() {
       items.setAll(es.getAll());
        lvaffichercomment.setItems(items);
    }

    @FXML
    private void Posts(ActionEvent event) throws IOException {
         xxx.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();}
    }
    

