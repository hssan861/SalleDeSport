package gui;

import services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import util.MyConnection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import javafx.scene.control.CheckBox;
import models.Login;
import models.Role;

public class LoginController implements Initializable {
        private EspaceClientController espaceClientController;

    private AddReclamationController addReclamationController;
    private User currentUser;
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection cnx = MyConnection.getInstance().getCnx();
    @FXML
    private TextField username;
    @FXML
    private Button reset;
    @FXML
    private Button Logiiin;
    @FXML
    private Button CreateAccount;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox rememberCheckbox; // Replace the RadioButton with a CheckBox

    private final String path = "/data/LoginData.ini";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService userService = new UserService();
        loadEmailAndPassword(); // Load email and password if saved
    }
    
    
    
    public void setEspaceClientController(EspaceClientController controller) {
        this.espaceClientController = controller;
    }

    public void setAddReclamationController(AddReclamationController controller) {
        this.addReclamationController = controller;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        Login Log_in = Login.getInstance();
        UserService su = new UserService();

        String userEmail = username.getText();
        String userPassword = password.getText();

        if (userEmail.isEmpty()) {
            showAlert("Email ne doit pas être vide!");
        } else if (!isValidEmail(userEmail)) {
            showAlert("Email invalide!");
        } else if (userPassword.isEmpty()) {
            showAlert("Mot de passe ne doit not be empty!");
        } else if (!isValidPassword(userPassword)) {
            showAlert("Invalid password!");
        } else {
            String role = su.getRoleByEmail(userEmail);
           // User loggedInUser = su.getuserbyemailandpass(userEmail, userPassword);

            if ("Admin".equals(role)) {
                User u = su.getuserbyemailandpass(userEmail, userPassword);
                if (u != null) {
                    // Only users with the "Admin" role can access the UserInterface
                    Log_in.setId(u.getId());
                    Log_in.setNom(u.getNom());
                    Log_in.setPrenom(u.getPrenom());
                    Log_in.setUsername(u.getEmail());
                    Log_in.setPassword(u.getMdp());
                    Log_in.setRole(u.getRole());
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Authentifié avec Succès!", ButtonType.OK);
                    a.showAndWait();

                    // Create a new stage for the UserInterface
                    Stage userInterfaceStage = new Stage();
                    Parent userInterfaceRoot = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
                    Scene userInterfaceScene = new Scene(userInterfaceRoot);
                    userInterfaceStage.setScene(userInterfaceScene);

                    // Get the current login stage and close it
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    // Show the UserInterface stage
                    userInterfaceStage.show();

                    if (rememberCheckbox.isSelected()) {
                        saveEmailAndPassword();
                    }
                } else {
                    showAlert("Les données sont invalides!");
                }
            } else if ("Utilisateur".equals(role)) {
                User u = su.getuserbyemailandpass(userEmail, userPassword);
                if (u != null) {
                    // User with the "Utilisateur" role has logged in
                    /*this.currentUser = u;
                    espaceClientController.setCurrentUser(currentUser);
                    espaceClientController.setCurrentUser(this.currentUser);*/
                   // addReclamationController.setCurrentUser(this.currentUser);
                    Log_in.setId(u.getId());
                    Log_in.setNom(u.getNom());
                    Log_in.setPrenom(u.getPrenom());
                    Log_in.setUsername(u.getEmail());
                    Log_in.setPassword(u.getMdp());
                    Log_in.setRole(u.getRole());
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Authentifié avec Succès!", ButtonType.OK);
                    a.showAndWait();

                    // Close the current login stage
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();

                    // Open the EspaceClient stage
                    Stage espaceClientStage = new Stage();
                    Parent espaceClientRoot = FXMLLoader.load(getClass().getResource("EspaceClient.fxml"));
                    Scene espaceClientScene = new Scene(espaceClientRoot);
                    espaceClientStage.setScene(espaceClientScene);
                    espaceClientStage.show();
                    

                    if (rememberCheckbox.isSelected()) {
                        saveEmailAndPassword();
                    }
                } else {
                    showAlert("Les données sont invalides!");
                }
            } else {
                showAlert("Accès refusé. Vous n'avez pas la permission d'accéder à cette interface.");
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'authentification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email);
        return m.find() && m.group().equals(email);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private void saveEmailAndPassword() {
        String email = username.getText();
        String pass = password.getText();
        Properties properties = new Properties();
        properties.setProperty("email", email);
        properties.setProperty("password", pass);

        try (OutputStream output = new FileOutputStream(path)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadEmailAndPassword() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);
            username.setText(properties.getProperty("email", ""));
            password.setText(properties.getProperty("password", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        password.clear();
        username.clear();
    }

    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        // Close the Login interface
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginStage.close();

        // Open the CreateAccount interface
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
        Parent createAccountRoot = loader.load();
        Scene createAccountScene = new Scene(createAccountRoot);

        Stage createAccountStage = new Stage();
        createAccountStage.setScene(createAccountScene);
        createAccountStage.show();
    }
}
