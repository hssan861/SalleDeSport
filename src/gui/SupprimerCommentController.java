/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.stage.Stage;
import models.Post;
import services.CommentService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SupprimerCommentController implements Initializable {
    CommentService ps = new CommentService() ;

    @FXML
    private Button ccc;
     @FXML
    private Button supp;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void Confirmersupcomment(ActionEvent event) throws IOException {
        ps.supprimerComment(NewFXMain2.comment);
         
        ccc.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

  @FXML
    private void Annulersupcomment(ActionEvent event) throws IOException {
         ccc.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    }
}
