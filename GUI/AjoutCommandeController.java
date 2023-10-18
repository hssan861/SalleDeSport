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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pi.entities.Commande;
import pi.entities.Produit;
import pi.services.serviceCommande;
import pi.services.serviceProduit;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AjoutCommandeController implements Initializable {
private serviceProduit sp;
    /**
     * Initializes the controller class.
     */
     public AjoutCommandeController(){
        this.sc = new serviceCommande();
        
    }
    private serviceCommande sc; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_view1.setEditable(true);
        choose.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        choose.setCellFactory(CheckBoxTableCell.forTableColumn(choose));
        
        this.sp = new serviceProduit();
    }    
    List<Produit> produits;
    @FXML
    private Button btnafficher;
    private List<Produit> products;

   
    
    @FXML
    private TableColumn<Produit, Boolean> choose;
    @FXML
    private TableColumn<Produit, Boolean> select;

    @FXML
    private TableColumn<Produit, String> col_Image1;

    @FXML
    private TableColumn<Produit, String> col_catégorie1;

    @FXML
    private TableColumn<Produit, Date> col_date1;

    @FXML
    private TableColumn<Produit, String> col_description1;

    @FXML
    private TableColumn<Produit, String> col_titre1;

    @FXML
    private Button supprBtn;

    @FXML
    private TableView<Produit> table_view1;

        @FXML
    private Button btnchoose;

    @FXML
    private TextField adresseField;

    @FXML
    private DatePicker tfdate;

  

    @FXML
    private TextField typeField;

    @FXML
    void afficherCommandes(ActionEvent event) {

    }

    @FXML
    void ajouterCommande(ActionEvent event) {
// Récupérez les données de la commande
    Date date = new Date(tfdate.getValue().getYear() - 1900, tfdate.getValue().getMonthValue(), tfdate.getValue().getDayOfMonth());
    String adresse = adresseField.getText();
    String type = typeField.getText();
    
    // Validez les données de la commande
    
    
 
    
    Commande c = new Commande( date, adresse, type); // Assurez-vous d'avoir l'ID de l'utilisateur actuel
    /*
        ArrayList<Produit> selectedProducts;
        for(Produit p: produits){
            if(p.getSelected() == true) selectedProducts.add(p);
        }
        c.setProduits(selectedProducts);
    */
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);        
    int createdCommandeId = this.sc.add(c);
    
    if (createdCommandeId == -1) {
        alert.setContentText("Échec de l'ajout de la commande");
    } else {
        alert.setContentText("Commande ajoutée avec succès (ID : " + createdCommandeId + ")");
    }
    
    alert.showAndWait();
}
    
    
    
    
     @FXML
    void selectPrd(ActionEvent event) {
        try {
            // Chargez le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("choosePrd.fxml"));
            Parent root = loader.load();

            // Créez une nouvelle scène
            Scene scene = new Scene(root);

            // Configurez la scène dans une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Choisir produit");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void aff(ActionEvent event) {
 // Obtenez les produits depuis la base de données en utilisant le service de produit
    this.products = (ArrayList) this.sp.afficher();
    if (products.isEmpty()) {
        
        // Si la liste des produits est vide, affichez un message d'alerte
        showInfoMessage("Aucun produit existant.");
    } else {
        System.out.println(products.size());
        // Configurez les colonnes de la table et affichez les produits
        col_titre1.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_catégorie1.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_description1.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_date1.setCellValueFactory(new PropertyValueFactory<>("date"));
        //choose.setCellValueFactory(new PropertyValueFactory<>("selected")); // Assurez-vous que "selected" est une propriété Boolean dans votre modèle de données
        //choose.setCellFactory(CheckBoxTableCell.forTableColumn(choose)); //
        //choose.setEditable(true);
        ObservableList<Produit> data = FXCollections.observableArrayList(products);
        this.table_view1.setItems(data);
    }
   }
    
    
    @FXML
    void validerp(ActionEvent event) {
        for(Produit p: products){
            System.out.println(p.isSelected());
        }
    }
    
    
    private void showInfoMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}

