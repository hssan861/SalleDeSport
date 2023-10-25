package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import models.Commande;
import models.Produit;
import services.serviceCommande;
import services.serviceProduit;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

public class AfficheOneCommande implements Initializable {
    private Commande c;

    public void setdatacommande(Commande c0) {
        c = c0;
    }

    private serviceProduit sp;
    private serviceCommande sc;
    @FXML
    private Label Adresselabel;
    @FXML
    private Label Datelabel;
    @FXML
    private Label typelabel;
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
    private ListView<Produit> table_view1;

    private void showInfoMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void Retour(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheC.fxml"));
        Parent root = loader.load();
        table_view1.getScene().setRoot(root);
    }

    @FXML
    public void supprimer(ActionEvent actionEvent) {
        int t = 0;
        Produit selectedItem = table_view1.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            List<Produit> p;
            p = sp.afficherproduit(c.getId());
            for (Produit p0 : p) {
                if (selectedItem.getId() == p0.getId()) {
                    t = 1;
                    sc.DeleteCommandeproduit(c.getId(), p0.getId());
                    table_view1.getItems().remove(selectedItem);
                    showInfoMessage("produit annuler");
                }
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sp = new serviceProduit();
        this.sc = new serviceCommande();
        table_view1.setEditable(true);


    }

    public void Afficher(ActionEvent actionEvent) {
        Adresselabel.setText(c.getAdresse());
        Datelabel.setText(c.getDate().toString());
        typelabel.setText(c.getType());
        List<Produit> p;
        p = sp.afficherproduit(c.getId());
        if(p!=null){
        if (p.isEmpty()) {
            showInfoMessage("Aucun produit existant.");
        } else {
            // Créez une ObservableList à partir de votre liste de produits
            ObservableList<Produit> data = FXCollections.observableArrayList(p);

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
                        // Créez un affichage personnalisé pour la cellule
                        setText("Titre: " + item.getTitre() + "\n" +
                                "Catégorie: " + item.getCategorie() + "\n" +
                                "Description: " + item.getDescription() + "\n" +
                                "Date: " + item.getDate());

                        // Vous pouvez également ajouter l'image si vous le souhaitez
                        setGraphic(item.getImageAsImageView());
                    }
                }
            });
        }}

    }

    @FXML
    public void modifier(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxl = new FXMLLoader();
        fxl.setLocation(getClass().getResource("ModifierC.fxml"));
        Parent root = fxl.load();
        ModifierCController cont = fxl.getController();
        cont.setcommodif(c);
        table_view1.getScene().setRoot(root);
    }

    public void genererFacture(ActionEvent actionEvent) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Ajouter un titre
            String title = "Votre Commande";
            float titleWidth = new PDType1Font(HELVETICA_BOLD).getStringWidth(title) / 1000 * 32;
            contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);

            contentStream.beginText();
            contentStream.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, 700);
            contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 18);
            contentStream.showText(title);
            contentStream.endText();

            // Ajouter une image (vous devez spécifier le chemin d'accès à votre image)
            String imagePath = AfficheOneCommande.class.getResource("380428727_1040086700454796_151680136043784588_n.png").getPath();
            File imageFile = new File(imagePath);
            PDImageXObject badgeImage = PDImageXObject.createFromFileByContent(imageFile, document);
            contentStream.drawImage(badgeImage, 400, 600, 200, 200);

            String imagePath2 = AfficheOneCommande.class.getResource("signature.png").getPath();
            File imageFile2 = new File(imagePath2);
            PDImageXObject badgeImage2 = PDImageXObject.createFromFileByContent(imageFile2, document);
            contentStream.drawImage(badgeImage2, 450, 20, 80, 80);

            // Ajouter des informations de commande
            contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 550);
            contentStream.showText("Adresse: " + c.getAdresse());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Type: " + c.getType());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date: " + c.getDate());
            contentStream.endText();


// Créez un tableau pour stocker les titres des colonnes
            String[] columnTitles = {"Titre", "Description", "Date", "Catégorie"};
            float tableX = 50;
            float tableY = 350; // Définissez tableY à la position où vous voulez les titres des colonnes
            float cellWidth = 500;
            float cellHeight = 60; // Augmentez la hauteur de la cellule pour inclure l'image
            float columnWidth = cellWidth / 4; // Divisez la cellule en 4 colonnes

// Dessinez les cellules du tableau avec les titres des colonnes
            for (int i = 0; i < columnTitles.length; i++) {
                contentStream.setLineWidth(1f);
                contentStream.addRect(tableX + i * columnWidth, tableY, columnWidth, cellHeight);
                contentStream.stroke();

                // Ajoutez le titre de la colonne
                contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(tableX + i * columnWidth + 10, tableY + cellHeight - 20); // Notez le changement ici
                contentStream.showText(columnTitles[i]);
                contentStream.endText();
            }

            List<Produit> produits;
            produits = sp.afficherproduit(c.getId());

            for (int i = 0; i < produits.size(); i++) {
                Produit produit = produits.get(i);

                // Pour chaque produit, dessinez une ligne de tableau
                for (int j = 0; j < columnTitles.length; j++) {
                    contentStream.setLineWidth(1f);
                    contentStream.addRect(tableX + j * columnWidth, tableY - (i + 1) * cellHeight, columnWidth, cellHeight);
                    contentStream.stroke();

                    // Remarque : vous pouvez personnaliser la position des éléments dans chaque cellule ici
                    contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 10);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(tableX + j * columnWidth + 10, tableY - (i + 1) * cellHeight + cellHeight - 20); // Notez le changement ici

                    // En fonction de la colonne, ajoutez les informations appropriées du produit
                    switch (j) {
                        case 0:
                            contentStream.showText(produit.getTitre());
                            break;
                        case 1:
                            contentStream.showText(produit.getDescription());
                            break;
                        case 2:
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            String formattedDate = dateFormat.format(produit.getDate());
                            contentStream.showText(formattedDate);
                            break;
                        case 3:
                            contentStream.showText(produit.getCategorie());
                            break;
                    }

                    contentStream.endText();
                }
            }

            // Enregistrez le PDF
            contentStream.close();
            document.save("facture.pdf");
            document.close();

            // Ouvrez le fichier PDF avec le lecteur de PDF par défaut
            File file = new File("facture.pdf");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
