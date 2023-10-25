/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.SalleDeSport_Offer_Reservation.CUI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.CodePromo;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Entities.Reservation_Offer;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Offer_Service;
import tn.esprit.SalleDeSport_Offer_Reservation.Services.Reservation_Offer_Service;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class List_Offres_FXMLController implements Initializable {
    Offer_Service sp = new Offer_Service();
    @FXML
    private Button ReserverOffer;
    @FXML
    private Button AfficheOffer;
    @FXML
    private ListView<Offer> list_view_affiche;
    @FXML
    private Button retour;
    @FXML
    private TextField Tf_CodePromo;
    @FXML
    private TextField Tf_idUser;
    @FXML
    private HBox carLayout;
    
    private List<Offer> Recently_Added;
    @FXML
    private TextField tx_Recherche;
    @FXML
    private Label err_CodePromo;
    @FXML
    private ImageView imgqrcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                        err_CodePromo.setVisible(false);

        //displayOffers();
        Recently_Added = new Offer_Service().orderOffersByReservationCount(); //new ArrayList<>(ListDesOffers());
        try{
            for(int i=0;i<Recently_Added.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(Recently_Added.get(i));
                carLayout.getChildren().add(cardBox);
                
            }
        }catch (IOException e){
                    e.printStackTrace();
                    }
        
      
    }    

    private void displayOffers() {
        List<Offer> offers = sp.afficherOffer();

        for (Offer offer : offers) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardListViewOffer.fxml"));
                Node cardNode = loader.load();
                CardController cardController = loader.getController();
                cardController.setData(offer);

                carLayout.getChildren().add(cardNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

private List<Offer> ListDesOffers(){
       List<Offer> ls =new ArrayList<>();
    Offer o = new Offer();
    o.setTitleOffer("Offer_Special");
    o.setDescriptionOffer("BLACK_FRIDAY");
    o.setImg("/Users/chayma2/Desktop/SalleDeSport_Offer_Reservation/src/tn/esprit/SalleDeSport_Offer_Reservation/img/Black_Friday.jpeg");
    ls.add(o);
    
    o = new Offer();
    o.setTitleOffer("Offer_BLACK");
    o.setDescriptionOffer("BLACK DAYS");
    o.setImg("/Users/chayma2/Desktop/SalleDeSport_Offer_Reservation/src/tn/esprit/SalleDeSport_Offer_Reservation/img/BlacDays.jpeg");
    ls.add(o);
    
    o = new Offer();
    o.setTitleOffer("Offer_Love");
    o.setDescriptionOffer("SAINT_VALENTINT");
    o.setImg("/Users/chayma2/Desktop/SalleDeSport_Offer_Reservation/src/tn/esprit/SalleDeSport_Offer_Reservation/img/saintValentin.jpeg");
    ls.add(o);
    
    o = new Offer();
    o.setTitleOffer("Offer_COUPEL");
    o.setDescriptionOffer("PINK_PACK");
    o.setImg("/Users/chayma2/Desktop/SalleDeSport_Offer_Reservation/src/tn/esprit/SalleDeSport_Offer_Reservation/img/couple.jpeg");
    ls.add(o);
   
      return ls;
    }



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
        if (s.contains("0") == true)
        return 1;
        
        return 0;
    }
    @FXML
    private void GoToAjouterReservation(ActionEvent event) {
   
                int tt = 0;

             if (Tf_CodePromo.getText().length()== 0) {
                 err_CodePromo.setVisible(true);
                 return;
             } 
             else {
                 String codePromoString = Tf_CodePromo.getText();
                 CodePromo codePromo = CodePromo.valueOf(codePromoString);
                 int userId = Integer.parseInt(Tf_idUser.getText());

                 LocalDateTime currentDateTime = LocalDateTime.now();

                 Offer selectedOffer = list_view_affiche.getSelectionModel().getSelectedItem();
                 System.out.println(selectedOffer);

                        if (selectedOffer == null) {
                        showAlert("No Offer Selected", "Please select an offer to make a reservation.");                           
                        } 
                                else {
                             // Generate QR code for the selected offer
                            generateQRCodeForSelectedOffer();

                            // Now, create the reservation
                            Reservation_Offer reservation = new Reservation_Offer(userId, currentDateTime, codePromo);
                            Reservation_Offer_Service rs = new Reservation_Offer_Service();
                            rs.ajouterReservation(reservation, selectedOffer);
                            showAlert("Success", "Reservation added successfully!");
                                }
             }
    }

    @FXML
private void generateQRCodeForSelectedOffer() {
    Offer selectedOffer = list_view_affiche.getSelectionModel().getSelectedItem();
    if (selectedOffer != null) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String offerInfo = selectedOffer.toString(); // You can customize this as per your offer data structure.
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(offerInfo, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            imgqrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            System.out.println("QR Code generated for selected offer.");
        } catch (WriterException ex) {
            Logger.getLogger(List_Offres_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    @FXML
    private void GoToAcueil(ActionEvent event) {
    }

    @FXML
    private void ActualiseOffer(ActionEvent event) {
    List<Offer> offers = sp.afficherOffer();
    
    // Create an ObservableList to store the offers
    ObservableList<Offer> offerList = FXCollections.observableArrayList(offers);
    
    // Assuming you have a ListView in your FXML with an ID "offerListView"
    list_view_affiche.setItems(offerList);
    list_view_affiche.setCellFactory(new OfferCellFactory());
    }
    
    private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }

    @FXML
    private void Recherche(ActionEvent event) {
          String idText = tx_Recherche.getText();
    if (!idText.isEmpty()) {
        try {
            int id = Integer.parseInt(idText);
            Offer r = sp.fetchOfferById(id);

            if (r != null) {
                ObservableList<Offer> ReservationList = FXCollections.observableArrayList(r);

                list_view_affiche.setItems(ReservationList);

                list_view_affiche.getSelectionModel().select(r);
            } else {
                showAlert("Offer Not Found", "Offer with ID " + id + " does not exist.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid ID", "Please enter a valid numeric ID.");
        }
    } else {
        showAlert("Empty ID", "Please enter an ID to search for an offer.");
    }
    }
    

    

    
    
}
