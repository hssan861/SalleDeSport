/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */

public class SupprimerEventFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    EventServices es =new EventServices();
    @FXML
    private Button aa;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmerSuppEvent(ActionEvent event) throws IOException {
         es.deleteEvent(EvenementFXMain.event);
         
        aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void annulerSuppEvent(ActionEvent event) throws IOException {
        aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    }
    

