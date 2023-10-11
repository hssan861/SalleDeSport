/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Event;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ModifierEventFXMLController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfCoach;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfImage;
    @FXML
    private DatePicker tfDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


   

    @FXML
    private void loadImg(ActionEvent event) {
    }
    


    @FXML
    private void AfficherEv(ActionEvent event) throws IOException {
        /* aa.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();*/
    }

    @FXML
    private void clearEvents(ActionEvent event) {
    }

    @FXML
    private void ModifierEv(ActionEvent event) throws IOException {
        try {
    EventServices es = new EventServices();
    
    double prix = Double.parseDouble(tfPrix.getText());
    Date date = new Date(tfDate.getValue().getYear() - 1900, tfDate.getValue().getMonthValue(), tfDate.getValue().getDayOfMonth());
    
    // Assuming Event class constructor takes (int, String, String, String, Date, double, String)
    Event E = new Event(
        EvenementFXMain.ee.getIdEvent(),
        tfTitre.getText(),
        tfCoach.getText(),
        tfType.getText(),
        tfAdresse.getText(),
        date,
        prix,
        tfImage.getText()
    );
    
    // Assuming EvenementFXMain.event is the event you want to modify
    es.modifierEvent(new Event(
       // EvenementFXMain.ee.getIdEvent(),
        tfTitre.getText(),
        tfCoach.getText(),
        tfType.getText(),
        tfAdresse.getText(),
        date,
        prix,
        tfImage.getText()));
    
    
    tfTitre.getScene().getWindow().hide();
    
    Parent root = FXMLLoader.load(getClass().getResource("AfficherEventFXML.fxml"));
    Stage mainStage = new Stage();
    Scene scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
} catch (Exception e) {
   
    e.printStackTrace();
}
    }}    
        
    
    

