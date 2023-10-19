/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_salle_de_sport.GUI;

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
import pi_salle_de_sport.Services.ServiceActivities;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SupprimerActiviteFXMLController implements Initializable {

   /**
     * Initializes the controller class.
     */
    ServiceActivities sa = new ServiceActivities();
    @FXML
    private Button aa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void confirmersupp(ActionEvent event) throws IOException {
        sa.supprimer(pi_salle_de_sport_fxml .activite);
         
        aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherActiviteFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void annulersupp(ActionEvent event) throws IOException {
        aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherActiviteFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
    
}
