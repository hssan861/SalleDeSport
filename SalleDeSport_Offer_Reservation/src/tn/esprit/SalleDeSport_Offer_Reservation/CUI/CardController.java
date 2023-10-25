/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class CardController implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private ImageView imagSrc;
    @FXML
    private Label name;
    private String []colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"};
    @FXML
    private Label name1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setData(Offer o){
        
       //Image image= new Image( o.getImg()) ;
         //         imagSrc.setImage(image);
           //       name.setText(o.getTitleOffer());
             Image image = new Image(new File(o.getImg()).toURI().toString());
    imagSrc.setImage(image);
    name.setText(o.getTitleOffer());
    name1.setText(o.getDescriptionOffer());
        box.setStyle("-fx-background-color: #"+ colors[(int)(Math.random()*colors.length)]+";" + " -fx-background-radius: 15;" + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1),10,0 ,0 ,10);");
    }
    
}
