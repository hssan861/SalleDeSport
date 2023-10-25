/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import static GUI.NewFXMain1.post;
import static GUI.NewFXMain2.comment;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Comment;
import models.Post;
import services.CommentService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierCommentController implements Initializable {

    @FXML
    private TextArea tfmodifcommenter;
    @FXML
    private ComboBox<Post> modpostcomm;
   
    @FXML
    private Button ww;
    @FXML
    private Button xx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         PostService es = new PostService();
    List<Post> postList = es.afficherPost();
    ObservableList<Post> items = FXCollections.observableArrayList(postList);

    // Clear the ComboBox and set its items to the 'items' list
    modpostcomm.getItems().clear();
   modpostcomm.setItems(items);

    // Optionally, you can set a default selection
    modpostcomm.getSelectionModel().selectFirst();
    
        tfmodifcommenter.setText(NewFXMain2.comment.getContent());
       
       
       
    }    

    @FXML
    private void ModifierComm(ActionEvent event) throws IOException {
      
    
    Post ev = modpostcomm.getValue();
    String commentaire =  tfmodifcommenter.getText();
    Comment c = new Comment();
    CommentService ps = new CommentService();
    c.setContent(commentaire);
  
    c.setPost(ev);
  
   ps.modifierComment(c);
   //Stage stage = (Stage) tfmodifcommenter.getScene().getWindow();
    //    stage.close();
       
    tfmodifcommenter.getScene().getWindow().hide();
    Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
    Stage mainStage = new Stage();
    Scene scene = new Scene(root);
    mainStage.setScene(scene);
    mainStage.show();
        
    }

    @FXML
    private void annulerModifcomm(ActionEvent event) throws IOException {
         ww.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("AfficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void AfficherModifComm(ActionEvent event) throws IOException {
        tfmodifcommenter.getScene().getWindow().hide();
              Parent root = FXMLLoader.load(getClass().getResource("afficherComment.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
}
