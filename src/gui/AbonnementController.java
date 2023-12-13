/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Type_abonn;
import services.TypeAbonn_Service;
import java.time.LocalDateTime;
import models.Abonnement;
import services.Abonnement_Service;

/**
 * FXML Controller class
 *
 * @author chayma2
 */
public class AbonnementController implements Initializable {
    

    @FXML
    private Button Abonner;
    @FXML
    private Button Retour;

    @FXML
    private TextField tf_idUser;
    @FXML
    private TableView<Type_abonn> tableView_type_abonn;
    @FXML
    private TextField tf_verificationCode;


    /**
     * Initializes the controller class.
     */
    

    @FXML
    private TableColumn<Type_abonn, String> C_type;
    @FXML
    private TableColumn<Type_abonn, String> C_Desc;
    @FXML
    private TableColumn<Type_abonn, Integer> C_NBabonn;
     
     private Type_abonn selectedtypeabonn;
     TypeAbonn_Service s = new TypeAbonn_Service();

    public Type_abonn getSelectedtypeabonn() {
        return selectedtypeabonn;
    }

    public void setSelectedtypeabonn(Type_abonn selectedtypeabonn) {
        this.selectedtypeabonn = selectedtypeabonn;
    }
     
                      

    @FXML
    private TextField serchTableView;
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Set up the listener for selection
        tableView_type_abonn.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedtypeabonn = newValue;
        });

        // Load data and populate the TableView
        List<Type_abonn> t = s.afficherTypeAbonn();
        ObservableList<Type_abonn> abonnList = FXCollections.observableArrayList(t);
        tableView_type_abonn.setItems(abonnList);
        
        C_type.setCellValueFactory(new PropertyValueFactory<Type_abonn, String>("type") );
        C_Desc.setCellValueFactory(new PropertyValueFactory<Type_abonn, String>("description") );
        C_NBabonn.setCellValueFactory(new PropertyValueFactory<Type_abonn, Integer>("nb_abonnement") );
        
        //filtrage
        FilteredList<Type_abonn> filteredData = new FilteredList<>(abonnList, b -> true);

             serchTableView.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(typeAbonn -> {
            if (newValue == null || newValue.isEmpty()) {
                return true; // Show all typeAbonn when the search field is empty
            }

            String lowerCaseFilter = newValue.toLowerCase();
            // Check if the filter matches any of the columns
            if (typeAbonn.getType().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (typeAbonn.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (String.valueOf(typeAbonn.getNb_abonnement()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } 
            
            

            return false;
        });
    });

    // Wrap the filtered list in a SortedList to enable sorting
    SortedList<Type_abonn> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tableView_type_abonn.comparatorProperty());

    // Bind the TableView to the sorted and filtered data
    tableView_type_abonn.setItems(sortedData);
         
    }    

    
    @FXML
    private void AbonnerAction(ActionEvent event) {
      
                 int verificationCode = Integer.parseInt(tf_verificationCode.getText());
                 int userId = Integer.parseInt(tf_idUser.getText());

                 LocalDateTime currentDateTime = LocalDateTime.now();

                 Type_abonn selectedtypeabonn = tableView_type_abonn.getSelectionModel().getSelectedItem();
                 System.out.println(selectedtypeabonn);

                        if (selectedtypeabonn == null) {
                        showAlert("No Offer Selected", "Please select an offer to make a reservation.");                           
                        } 
                                else {
                            

                            // Now, create the reservation
                            Abonnement abonnement = new Abonnement(currentDateTime,userId, verificationCode);
                            Abonnement_Service rs = new Abonnement_Service();
                            rs.Abonner(abonnement, selectedtypeabonn);
                            showAlert("Success", "Reservation added successfully!");
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
    private void GoToAceuilPage(ActionEvent event) {
    }
    
}
