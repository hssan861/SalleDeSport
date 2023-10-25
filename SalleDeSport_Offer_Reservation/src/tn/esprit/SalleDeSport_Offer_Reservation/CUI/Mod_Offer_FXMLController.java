/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Offer_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class Mod_Offer_FXMLController implements Initializable {
    
                Offer_Service sp = new Offer_Service();
                public static  Offer selectedOffer;
                
                /*public Mod_Offer_FXMLController(Offer offer) {
        selectedOffer = offer;
    }*/
    public Offer offer = new Offer();
    @FXML
    private Label hint;
    @FXML
    private ImageView imageV;
    @FXML
    private Label ErrFiche;
    @FXML
    private TextField FicheArtc;

    //setters and getters

    
    public Label getHint() {
        return hint;
    }
    public TextField getTfTitle() {
        return tf_TitleOffer_Mod;
    }
    public TextField getPrix() {
        return tfPrix_Mod;
    }
    public TextArea getDescription() {
        return tf_Description_Mod;
    }
    public void setStartDate(java.sql.Date date) {
        tf_DateDebuOffer_Mod.setValue(date.toLocalDate());
    }
    public void setEndDate(java.sql.Date date) {
         tf_DateFinOffer_Mod.setValue(date.toLocalDate());;
    }

    public void setHint(Label hint) {
        this.hint = hint;
    }
     public TextField getImg() {
        return FicheArtc;
    }
  
    

    public void setOffer(Offer o) {
        this.offer = o;
    }
                

    @FXML
    private TextField tf_TitleOffer_Mod;
    @FXML
    private TextArea tf_Description_Mod;
    @FXML
    private TextField tfPrix_Mod;
    @FXML
    private DatePicker tf_DateDebuOffer_Mod;
    @FXML
    private DatePicker tf_DateFinOffer_Mod;
    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonModifierOffer;
    @FXML
    private Label err_titre_offer;
    @FXML
    private Label err_dateD_offer;
    @FXML
    private Label err_prix_offer;
    @FXML
    private Label err_desc_offer;
    @FXML
    private Label err_dateF_offer;
    @FXML
    private Label champnegatif;
    @FXML
    private Label champNonValid;

    String imageeget = "pas d'image";
    /**
     * Initializes the controller class.
     */
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       //System.out.println("FRO: DSTINQTION"+offer);
       //..
        err_titre_offer.setVisible(false);
        err_dateD_offer.setVisible(false);
        err_dateF_offer.setVisible(false); 
        err_prix_offer.setVisible(false);
        err_desc_offer.setVisible(false); 
        champnegatif.setVisible(false); 
        champNonValid.setVisible(false); 
        
        tf_TitleOffer_Mod.setText(offer.getTitleOffer());
        tf_Description_Mod.setText(offer.getDescriptionOffer());
        tfPrix_Mod.setText(String.valueOf(offer.getPrix()));
        FicheArtc.setText(offer.getImg());
        
    }
    

    
    @FXML
    private void goToAficheOffer(ActionEvent event) {
           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affiche_Offer_FXML.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.close();
        } catch (IOException e) {
        e.printStackTrace(); 
    }
    
    }

    @FXML
    private void addAction(ActionEvent event) {
          if (tf_TitleOffer_Mod.getText().isEmpty()|| tfPrix_Mod.getText().isEmpty() ||
                 tf_DateDebuOffer_Mod.getValue().equals(0) || tf_DateFinOffer_Mod.getValue().equals(0) || tf_Description_Mod.getText().isEmpty()){
          
            err_titre_offer.setVisible(true);
            err_dateD_offer.setVisible(true);
            err_dateF_offer.setVisible(true); 
            err_prix_offer.setVisible(true);
            err_desc_offer.setVisible(true); 
            champnegatif.setVisible(true); 
            champNonValid.setVisible(true); 
            return;
    }
          else{
        //if (selectedOffer != null) {
        // Update the selectedOffer with the modified details
        offer.setTitleOffer(tf_TitleOffer_Mod.getText());
        offer.setDescriptionOffer(tf_Description_Mod.getText());
        offer.setPrix(Float.parseFloat(tfPrix_Mod.getText()));
        Date DateD = Date.valueOf(tf_DateDebuOffer_Mod.getValue().toString());
        Date DateF = Date.valueOf(tf_DateFinOffer_Mod.getValue().toString());
        offer.setDateDebOffer(DateD);
        offer.setDateDebOffer(DateF);
        offer.setImg(FicheArtc.getText());
        
        //Offer offer = new Offer( tf_TitleOffer_Mod.getText(),tf_Description_Mod.getText(), Float.parseFloat(tfPrix_Mod.getText()), DateD,DateF,FicheArtc.getText());
        sp.ModOffer(offer);

        //Offer o = new Offer(tf_TitleOffer_Mod.getText(),tf_Description_Mod.getText(),tfPrix_Mod.getText().toString(),DateD,DateF,FicheArtc.getText());
        //sp.ModOffer(o);
        showAlert("Success", "Offer Modified successfully!");
          }
        
    //}
        
    }
    
    
    

    
    private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }

    @FXML
    private void file(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        //fileChooser.setTitle("Pick a banner file!");
        //fileChooser.setInitialDirectory(new File("\\"));
        //Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
        new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),   
        new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        //File file = fileChooser.showOpenDialog(stage);
            File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    if (file != null) {
        try {
        BufferedImage bufferedImage = ImageIO.read(file);
        Image img = SwingFXUtils.toFXImage(bufferedImage, null);
            System.out.println(file.getAbsolutePath());
            imageeget = file.toURI().toURL().toString();
                        System.out.println(imageeget);

            imageV.setImage(img);
            FicheArtc.setText( file.getAbsolutePath().toString());
             } catch (IOException ex) {
                System.out.println("could not get the image");
         
    }
    }
    }
    
    
}
