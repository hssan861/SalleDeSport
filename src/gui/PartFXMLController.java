/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Event;
import services.EventServices;
import services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class PartFXMLController implements Initializable {

    private Label prix;
    @FXML
    private ImageView image;
    private Label nom;
private List<Event> eventlist = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setproduit(Event p) throws IOException, SQLException {
        EventServices sp = new EventServices();
  //  prodduitlist = sp.afficherListeP();
     //produit = p;
  
       // nom.setText(p.getTitreEvent());
        //prix.setText(p.getPrixEvent() + "TND");
String imagePath = p.getImgEvent();
        try {
            Image img = new Image(new File(imagePath).toURI().toString());
            image.setImage(img);
        } catch (Exception e) {
            System.out.println("Failed to load image: " + imagePath);
        }
    }
}
