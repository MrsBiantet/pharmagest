package PharmaGest;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.Window;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class loginControlleur implements Initializable {
        @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button inscris;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField enterPasswordField;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void cancelButtonAction (ActionEvent event){
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }

            //bouton pour redirection
            public void inscrip(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../JavaFX/inscription.fxml"));
                root = loader.load();

                inscriControlleur inscriControlleur = loader.getController();
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

    }

    @Override
            public void initialize (URL url, ResourceBundle resourceBundle){

            }

            public void getData (ActionEvent event) throws SQLException   {
                Window owner = loginButton.getScene().getWindow();
                System.out.println(usernameTextfield.getText());
                System.out.println(enterPasswordField.getText());
                if(usernameTextfield.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Please enter your email id");
                    return;    }
                if(enterPasswordField.getText().isEmpty()) {showAlert(Alert.AlertType.ERROR, owner, "Form Error!","Please enter a password");
                    return;    }
                String nom = usernameTextfield.getText();
                String password = enterPasswordField.getText();
                databaseConnection databaseConnection = new databaseConnection();
                boolean flag = databaseConnection.writeToDatabase(nom, password);
                if(!flag) {infoBox("Please enter correct Nom and Password", null, "Failed");}
                else {  infoBox("Login Successful!", null, "Succès");   }
        }

            public static void infoBox(String infoMessage, String headerText, String title){
            Alert alert = new Alert(AlertType.CONFIRMATION);alert.setContentText(infoMessage);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.showAndWait();}

            private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.initOwner(owner);
            alert.show();}
}