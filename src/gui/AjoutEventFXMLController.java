/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Event;
import services.EventServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AjoutEventFXMLController implements Initializable {

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
    private void AjouterEv(ActionEvent event) {
          double prix = Double.parseDouble(tfPrix.getText());
    EventServices sp = new EventServices();
    Date date =new Date (tfDate.getValue().getYear()-1900,tfDate.getValue().getMonthValue(),tfDate.getValue().getDayOfMonth());
        sp.ajouterEvent(new Event(tfTitre.getText(), tfCoach.getText(),tfType.getText(),tfAdresse.getText(),date ,prix,tfImage.getText()));
  
    }

    @FXML
    private void AffichageEv(ActionEvent event) {
    }

    @FXML
    private void clearEvent(ActionEvent event) {
    }

    @FXML
    private void loadImg(ActionEvent event) {
    }
    
}
