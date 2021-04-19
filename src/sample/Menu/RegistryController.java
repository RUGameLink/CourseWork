package sample.Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Logic.Bridge;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistryController implements Initializable {

    @FXML
    private Label infoLabel;

    @FXML
    private Button regBtn;

    @FXML
    private Label progLabel;

    @FXML
    private TextField loginText;

    @FXML
    private Button retBtn;

    @FXML
    private PasswordField passwordText;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retBtn.setOnAction(event -> {
            Stage stage = (Stage) retBtn.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/login.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root,543, 379));
            stage.setTitle("Authorization menu");
            stage.setResizable(false);
            stage.show();
        });

        regBtn.setOnAction(event -> {
            addUser();
        });
    }

    private void addUser(){
        String login = loginText.getText();
        String password = passwordText.getText();
        if(login.length() != 0 && password.length() != 0 ){
            String sql = "AddUser '" + login +"', '" + password + "'";
            int res = Bridge.writeUser(sql);
            if(res == 1){
                infoLabel.setText("");
                infoLabel.setStyle("-fx-text-fill: green;");
                infoLabel.setText("Пользователь зарегистрирован\nуспешно!");
            }
            else {
                infoLabel.setText("");
                infoLabel.setStyle("-fx-text-fill: red;");
                infoLabel.setText("Пользователь уже\nзарегистрирован!");
            }
        }
        else {
            infoLabel.setText("");
            infoLabel.setText("Не все поля заполнены!");
        }
    }
}
