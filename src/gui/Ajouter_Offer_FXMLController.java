/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import services.Offer_Service;
import models.Offer;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class Ajouter_Offer_FXMLController implements Initializable {
              Offer_Service sp = new Offer_Service();


    @FXML
    private TextField tf_TitleOffer;
    @FXML
    private TextArea tf_Description;
    @FXML
    private TextField tfPrix;
    @FXML
    private DatePicker tf_DateDebuOffer;
    @FXML
    private DatePicker tf_DateFinOffer;
    @FXML
    private Button buttonRetour;
    @FXML
    private Button buttonAjouterOffer;
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
    @FXML
    private Label ErrFiche;
    @FXML
    private TextField FicheArtc;
    @FXML
    private ImageView imageV;
    @FXML
    private Label err_image;
    @FXML
    private TextField tfNBmax;
    @FXML
    private Label champnegatif1;
    
    String imageeget = "pas d'image";

    /**
     * Initializes the controller class.
     */
    
    public int testNum(String s){
        if (s.contains("1") == true)
        return 1;
        if (s.contains("2") == true)
        return 1;
        if (s.contains("3") == true)
        return 1;
        if (s.contains("4") == true)
        return 1;
        if (s.contains("5") == true)
        return 1;
        if (s.contains("6") == true)
        return 1;
        if (s.contains("7") == true)
        return 1;
        if (s.contains("8") == true)
        return 1;
        if (s.contains("9") == true)
        return 1;
        if (s.contains("0") == true)
        return 1;
        
        return 0;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                champNonValid.setVisible(false);
                champnegatif.setVisible(false);
                champnegatif1.setVisible(false);
                err_titre_offer.setVisible(false);
                err_prix_offer.setVisible(false);
                err_dateD_offer.setVisible(false);
                err_dateF_offer.setVisible(false);
                err_desc_offer.setVisible(false);
                err_image.setVisible(false);
    }    

    @FXML
    private void addAction(ActionEvent event) {
                champNonValid.setVisible(false);
                champnegatif.setVisible(false);
                champnegatif1.setVisible(false);
                err_titre_offer.setVisible(false);
                err_prix_offer.setVisible(false);
                err_dateD_offer.setVisible(false);
                err_dateF_offer.setVisible(false);
                err_desc_offer.setVisible(false);
                err_image.setVisible(false);
                
             int tt = 0;
        try {
            
       
        if(testNum(tf_TitleOffer.getText()) ==1 )
        {
          champNonValid.setVisible(true);
          tt =1;
          
        }
        if (FicheArtc.getText().equals("")){
        ErrFiche.setVisible(true);
        ErrFiche.setText("Champ Obligatoire");
        tt=1;
        }
        if(Float.parseFloat(tfPrix.getText()) < 0 )
            {
          champnegatif.setVisible(true);
          tt =1;
          
        }
     } catch (NumberFormatException e) {
             showAlert("Error", "Invalid price value. Please enter a valid number.");
             return;

        }
               
        if (tf_TitleOffer.getText().isEmpty()|| tfPrix.getText().isEmpty() ||
                 tf_DateDebuOffer.getValue().equals(0) || 
                tf_DateFinOffer.getValue().equals(0) || 
                tf_Description.getText().isEmpty()||
                FicheArtc.getText().isEmpty()||
                tfNBmax.getText().isEmpty())
        {
                err_titre_offer.setVisible(true);
                err_prix_offer.setVisible(true);
                err_dateD_offer.setVisible(true);
                err_dateF_offer.setVisible(true);
                err_desc_offer.setVisible(true);
                err_image.setVisible(true);
                champnegatif1.setVisible(true);

                return;
        }
        
        else {
            Date DateDebut = Date.valueOf(tf_DateDebuOffer.getValue().toString());
            Date DateFin = Date.valueOf(tf_DateFinOffer.getValue().toString());
            sp.ajouterOffer(new Offer(tf_TitleOffer.getText()
                ,tf_Description.getText()
                , Float.parseFloat(tfPrix.getText())
                , DateDebut, DateFin
                , FicheArtc.getText()
                , Integer.parseInt(tfNBmax.getText())));
            showAlert("Success", "Offer added successfully!");
        }
    }
    
    
     private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
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
