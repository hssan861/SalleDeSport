/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class CardListViewOfferController implements Initializable {

    @FXML
    private Label description;

    public VBox getVbox() {
        return Vbox;
    }
    

    @FXML
    private Label name;
    @FXML
    private ImageView imgOffer;
    @FXML
    private Label label_Prix;
    @FXML
    private Label label_DateD;
    @FXML
    private Label label_DateF;
    
    private String []colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"};
    @FXML
    private VBox Vbox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
        
        public void setData(Offer o){
          Image image = new Image(new File(o.getImg()).toURI().toString());
       imgOffer.setImage(image);
       name.setText(o.getTitleOffer());
       description.setText(o.getDescriptionOffer());
       label_Prix.setText(Float.toString(o.getPrix()));
        Date dateDeb = (Date) o.getDateDebOffer();
        Date dateFin = (Date) o.getDateFinOffer();

        // Convert java.sql.Date to LocalDate
        LocalDate localDateDeb = dateDeb.toLocalDate();
        LocalDate localDateFin = dateFin.toLocalDate();

        // Format the dates as strings and set them in the labels.
        label_DateD.setText( localDateDeb.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        label_DateF.setText( localDateFin.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));

     
        Vbox.setStyle("-fx-background-color: #"+ colors[(int)(Math.random()*colors.length)]+";" + " -fx-background-radius: 15;" + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1),10,0 ,0 ,10);");
    }
      

}
