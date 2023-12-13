/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.Offer_Service;
import services.ExcelService;
import models.Offer;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class Affiche_Offer_FXMLController implements Initializable {
    Offer_Service sp = new Offer_Service();
    public Offer selectedOffer; 
    public void setSelectedOffer(Offer o) {
                            selectedOffer = o;
                        }
                  public Offer getSelectedOffer() {
                    return selectedOffer;
                                            }

    @FXML
    private TableView<Offer> tableViewOffer;
    @FXML
    private TableColumn<Offer, String> C_titleOffer;
    @FXML
    private TableColumn<Offer, String> C_Descp;
    @FXML
    private TableColumn<Offer, Float> C_Prix;
    @FXML
    private TableColumn<Offer, Date> C_DateD;
    @FXML
    private TableColumn<Offer, Date> C_DateF;
    @FXML
    private TableColumn<Offer, Integer> C_NBreservation;
    @FXML
    private TableColumn<Offer, Integer> C_NBmax;
    @FXML
    private TextField serchTableView;
    @FXML
    private Button Retour;
    @FXML
    private Button AjouetrOffer;
    @FXML
    private Button ModifierOffer;
    @FXML
    private Button SupprimerOffer;
    @FXML
    private Button Excel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewOffer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOffer = newValue;
        });
        
        List<Offer> offerList = sp.afficherOffer(); 
        ObservableList<Offer> observableList = FXCollections.observableArrayList(offerList);
        ObservableList<Offer> list = observableList;



        C_titleOffer.setCellValueFactory(new PropertyValueFactory<Offer, String>("titleOffer") );
        C_Descp.setCellValueFactory(new PropertyValueFactory<Offer, String>("descriptionOffer") );
        C_Prix.setCellValueFactory(new PropertyValueFactory<Offer, Float>("prix") );
        C_DateD.setCellValueFactory(new PropertyValueFactory<Offer, Date>("dateDebOffer") );
        C_DateF.setCellValueFactory(new PropertyValueFactory<Offer, Date>("dateFinOffer") );
        C_NBreservation.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("nb_reservation") );
        C_NBmax.setCellValueFactory(new PropertyValueFactory<Offer, Integer>("nb_max_reservation") );


        
        System.out.println(list);
        tableViewOffer.setItems(list);
        
         FilteredList<Offer> filteredData = new FilteredList<>(list, b -> true);

             serchTableView.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(offer -> {
            if (newValue == null || newValue.isEmpty()) {
                return true; // Show all offers when the search field is empty
            }

            String lowerCaseFilter = newValue.toLowerCase();
            // Check if the filter matches any of the columns
            if (offer.getTitleOffer().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (offer.getDescriptionOffer().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (offer.getDateDebOffer().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (offer.getDateFinOffer().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            
            

            return false;
        });
    });

    // Wrap the filtered list in a SortedList to enable sorting
    SortedList<Offer> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tableViewOffer.comparatorProperty());

    // Bind the TableView to the sorted and filtered data
    tableViewOffer.setItems(sortedData);

    }    

    @FXML
    private void goToAceuilAdmin(ActionEvent event) {
    }

    @FXML
    private void goToAjouterOffer(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouter_Offer_FXML.fxml"));
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
    private void goToModifierOffer(ActionEvent event) {
         if (selectedOffer != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Mod_Offer_FXML.fxml"));
            Parent root = loader.load();

            // Get the controller for Mod_Offer_FXML.fxml
            Mod_Offer_FXMLController modController = loader.getController();

            // Pass the selected offer to the controller
            //modController.getHint().setText(selectedOffer.toString());
            modController.getTfTitle().setText(selectedOffer.getTitleOffer());
            modController.getDescription().setText(selectedOffer.getDescriptionOffer());
            modController.getPrix().setText(String.valueOf(selectedOffer.getPrix()));
            modController.setStartDate((java.sql.Date) selectedOffer.getDateDebOffer());
            modController.setEndDate((java.sql.Date) selectedOffer.getDateFinOffer());
            modController.getImg().setText(selectedOffer.getImg());
            modController.getTfNBmax_Mod().setText(String.valueOf(selectedOffer.getNb_max_reservation()));

            modController.setOffer(selectedOffer);
            System.out.println(selectedOffer);


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current stage
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Show an error message if no offer is selected
        showAlert("No Offer Selected", "Please select an offer to modify.");
    }
    }
    private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }
    public void handleUpdateEvent() {
    // Refresh the TableView with the updated data
    List<Offer> updatedList = sp.afficherOffer();
    ObservableList<Offer> observableList = FXCollections.observableArrayList(updatedList);
    tableViewOffer.setItems(observableList);
}

    @FXML
    private void goToSuppOffer(ActionEvent event) {
        if (selectedOffer != null) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Supp_Offer.fxml"));
            Parent root = loader.load();

            // Pass the selected offer to the Supp_OfferController
            Supp_OfferController suppOfferController = loader.getController();
            suppOfferController.setSelectedOffer(selectedOffer);
            suppOfferController.setAfficheOfferController(this); // Set a reference to this controller

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
      }
      else {
        // Show an error message if no offer is selected
        showAlert("No Offer Selected", "Please select an offer to deleted.");
    }
    }

    @FXML
    private void ExcelAction(ActionEvent event) {
         ExcelService x = new ExcelService();  
           x.ExcelCreator();
            showAlert("Success", "Excel file created successfully.");
    }
    
}
