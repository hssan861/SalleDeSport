/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class SupprimerPostController implements Initializable {
PostService ps = new PostService() ;
    @FXML
    private Button aaa;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Confirmersuppost(ActionEvent event) throws IOException {
            ps.supprimerPost(NewFXMain1.post);
         
        aaa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void Annulersuppost(ActionEvent event) throws IOException {
        aaa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherPost.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
}
