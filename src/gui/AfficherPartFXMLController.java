/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Event;
import models.Participation;
import services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AfficherPartFXMLController implements Initializable {

    @FXML
    private ListView<Participation> LvPart;
    @FXML
    private TextField rechField;
    @FXML
    private Button Brech;

    /**
     * Initializes the controller class.
     */
            ParticipationServices ps = new ParticipationServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // ParticipationServices sp = new ParticipationServices();
             ObservableList<Participation> part = FXCollections.observableArrayList();
        part.addAll(ps.afficherParticipation());
        LvPart.setItems(part);
        
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
          rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ReserverFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
    }

    @FXML
    private void update(ActionEvent event) {
         try {
                  ParticipationFXMain.part = LvPart.getSelectionModel().getSelectedItem();
                  if (ParticipationFXMain.part != null) {
                 rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("ModifierPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
            }
                 
    
        } catch (Exception e) {
        }
    }

    @FXML
    private void delete(ActionEvent event) {
         try {
                 ParticipationFXMain.part = LvPart.getSelectionModel().getSelectedItem();
                  if (ParticipationFXMain.part != null) {
                 rechField.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("SuppPartFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); 
                  }
      
        } catch (Exception e) {
        }
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }
    
}
