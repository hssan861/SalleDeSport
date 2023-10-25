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
import services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SuppPartFXMLController implements Initializable {

    @FXML
    private Button cc;

    /**
     * Initializes the controller class.
     */
      ParticipationServices ps = new ParticipationServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmerSuppPart(ActionEvent event) throws IOException {
        ps.deletePartcipant(ParticipationFXMain.part);
         
        cc.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void annulerSuppPart(ActionEvent event) {
    }
    
}
