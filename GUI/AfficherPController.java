/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pi.entities.Produit;
import pi.services.serviceProduit;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AfficherPController implements Initializable {

    private serviceProduit sp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.sp = new serviceProduit();
    }

    @FXML
    private Button btnafficher;

    @FXML
    private Button btnajouter;

    @FXML
    private TextField categorieField;

    @FXML
    private TableColumn<Produit, String> col_Image;

    @FXML
    private TableColumn<Produit, String> col_catégorie;

    @FXML
    private TableColumn<Produit, Date> col_date;

    @FXML
    private TableColumn<Produit, String> col_description;

    @FXML
    private TableColumn<Produit, String> col_titre;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TextField descriptionField;

    @FXML
    private ImageView image_view;

    @FXML
    private Button insert_image;

    @FXML
    private TableView<Produit> table_view;

    @FXML
    private TextField titreField;

    @FXML
    void aff(ActionEvent event) {
           // Obtenez les produits depuis la base de données en utilisant le service de produit
    ArrayList<Produit> products = (ArrayList) this.sp.afficher();

    if (products.isEmpty()) {
        
        // Si la liste des produits est vide, affichez un message d'alerte
        showInfoMessage("Aucun produit existant.");
    } else {
        // Configurez les colonnes de la table et affichez les produits
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_catégorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<Produit> data = FXCollections.observableArrayList(products);
        this.table_view.setItems(data);
    }
}

private void showInfoMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    private void showAlert(AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}
    
    @FXML
    private void deleteSelectedProduct() {
        Produit selectedProduct = table_view.getSelectionModel().getSelectedItem();
        if(selectedProduct == null){
            showAlert(AlertType.WARNING, "No Selection", "Please select a product to delete.");
            return;
        }
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Delete");
        confirmationAlert.setHeaderText("Are you sure you want to delete this record?");
        confirmationAlert.setContentText("This action cannot be undone.");

        

        // Show the confirmation alert and wait for the user's response
        ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            System.out.println("product will be deleted");
            this.sp.supprimer(selectedProduct);
            ObservableList<TablePosition> selectedCells = table_view.getSelectionModel().getSelectedCells();

            // Loop through the selected rows and remove them
            for (TablePosition tablePosition : selectedCells) {
                int rowIndex = tablePosition.getRow();
                table_view.getItems().remove(rowIndex);
            }
        } else {
            // User canceled the operation
            System.out.println("product will not be deleted");
        }
    }

}
