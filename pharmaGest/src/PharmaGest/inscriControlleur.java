package PharmaGest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class inscriControlleur {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField tel;
    @FXML
    private PasswordField password;
    @FXML
    private Button inscription;

    public void take(ActionEvent actionEvent) throws SQLException {

        System.out.println(nom.getText());
        System.out.println(prenom.getText());
        System.out.println(email.getText());
        System.out.println(adresse.getText());
        System.out.println(tel.getText());
        System.out.println(password.getText());
        databaseConnection.inscriptionToDatabase(nom.getText(), prenom.getText(),email.getText(),adresse.getText(),tel.getText(),password.getText());
    }


}
