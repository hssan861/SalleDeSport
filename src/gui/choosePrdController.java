/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author hama
 */
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Produit;
import services.serviceProduit;

public class choosePrdController implements Initializable {
        private serviceProduit sp;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_view1.setEditable(true);
        //choose.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        //choose.setCellFactory(CheckBoxTableCell.forTableColumn(choose));
        
        this.sp = new serviceProduit();
      }
    @FXML
    private Button btnafficher;

    
    private List<Produit> products;

    @FXML
    private Button ajouterproduit;
    
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
    private ListView<Produit> table_view1;

    @FXML
    void aff(ActionEvent event) {
 // Obtenez les produits depuis la base de données en utilisant le service de produit
    this.products = (ArrayList) this.sp.afficher();
    if(products!= null){
    if (products.isEmpty()) {
        
        // Si la liste des produits est vide, affichez un message d'alerte
        showInfoMessage("Aucun produit existant.");

    } else {
        // Créez une ObservableList à partir de votre liste de produits
        ObservableList<Produit> data = FXCollections.observableArrayList(products);

        // Configurez le ListView pour afficher les produits
        table_view1.setItems(data);

        // Définissez un cell factory personnalisé si vous souhaitez personnaliser l'affichage des éléments dans le ListView.
        table_view1.setCellFactory(param -> new ListCell<Produit>() {
            @Override
            protected void updateItem(Produit item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {

                    CheckBox checkBox = new CheckBox();
                    // Liaison entre le CheckBox et la propriété "selected" du produit
                    checkBox.selectedProperty().bindBidirectional(item.selectedProperty());

                    // Créez un ImageView pour afficher l'image du produit
                    ImageView imageView = item.getImageAsImageView();
                    imageView.setFitHeight(50); // Ajustez la hauteur de l'image selon vos besoins
                    imageView.setFitWidth(50);  // Ajustez la largeur de l'image selon vos besoins

                    // Vous pouvez également ajouter d'autres informations du produit
                    String productInfo = "Titre: " + item.getTitre() + "\n" + "Catégorie: " + item.getCategorie() + "\n" +
                            "Description: " + item.getDescription() + "\n" +
                            "Date: " + item.getDate();

                    // Créez un VBox pour afficher le CheckBox, l'ImageView et les informations du produit
                    VBox vbox = new VBox(checkBox, imageView, new Label(productInfo));
                    vbox.setSpacing(5);

                    // Affichez le VBox dans la cellule
                    setGraphic(vbox);

                }
            }
        });
    }}
   }
    
    
    @FXML
    void validerp(ActionEvent event) throws IOException {
        List<Integer> l =  new ArrayList<>();
        for(Produit p: products){
            if(p.isSelected()){
                l.add(p.getId());
            }

        }
        FXMLLoader fxl=new FXMLLoader();
        fxl.setLocation(getClass().getResource("AjoutCommande.fxml"));
        Parent root=fxl.load();
        AjoutCommandeController c=fxl.getController();
        c.setproduits(l);
        table_view1.getScene().setRoot(root);
    }
    
    
    private void showInfoMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    public void ajouterproduit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
        Parent root=loader.load();
        table_view1.getScene().setRoot(root);
    }

    public void Options(ActionEvent actionEvent) throws IOException {
        Produit selectedItem = table_view1.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Créez une alerte (popup) avec des boutons Supprimer et Modifier
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sélection d'élément");
            alert.setHeaderText("Que voulez-vous faire avec cet élément ?");
            alert.setContentText("Sélectionnez une option :");

            ButtonType buttonTypeDelete = new ButtonType("Supprimer");
            ButtonType buttonTypeEdit = new ButtonType("Modifier");

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeEdit, ButtonType.CANCEL);

            // Affichez l'alerte et attendez la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();

            // Traitez la réponse de l'utilisateur
            if (result.isPresent()) {
                if (result.get() == buttonTypeDelete) {
                    this.sp.supprimer(selectedItem);
                    table_view1.getItems().remove(selectedItem);
                    showInfoMessage("Produit supprimé avec succès !");
                    // Vous pouvez ajouter ici la logique pour supprimer l'élément de votre modèle de données
                } else if (result.get() == buttonTypeEdit) {
                    // Vous pouvez ajouter ici la logique pour ouvrir l'élément en mode édition
                    FXMLLoader fxl=new FXMLLoader();
                    fxl.setLocation(getClass().getResource("ModifierProduit.fxml"));
                    Parent root=fxl.load();
                    ModifierProduitController c=fxl.getController();
                    c.setdata(selectedItem);
                    table_view1.getScene().setRoot(root);
                }
            }
        }
    }

    public void commande(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfficheC.fxml"));
        Parent root=loader.load();
        table_view1.getScene().setRoot(root);
    }

    public void Statistique(ActionEvent actionEvent) {
        Map<String, Double> categoryPercentage = sp.calculatePercentageByCategory();

        // Créer un graphique en secteurs (PieChart) pour afficher les pourcentages
        PieChart pieChart = new PieChart();

        for (Map.Entry<String, Double> entry : categoryPercentage.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        // Créer une fenêtre contextuelle (popup) pour afficher le graphique
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Votre statistique ");
        alert.setTitle("Pourcentage des Commandes par Catégorie");
        alert.getDialogPane().setContent(pieChart);
        alert.showAndWait();
    }
}
