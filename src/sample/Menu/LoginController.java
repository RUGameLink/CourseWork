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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private Label progLabel;

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Label infoLabel;

    @FXML
    private Button regBtn;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bridge.Connector();
        loginBtn.setOnAction(event -> {

            int res;
            res = checkUser();
            switch (res){
                case 1:
                    loginText.setText("");
                    passwordText.setText("");

                    Stage stage = (Stage) loginBtn.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/fxms/sample.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    stage = new Stage();
                    stage.setScene(new Scene(root,848, 628));
                    stage.setTitle("ShopMenu");
                    stage.setResizable(false);
                    stage.show();
                    break;
                case 2:
                    loginText.setText("");
                    passwordText.setText("");

                    stage = (Stage) loginBtn.getScene().getWindow();
                    stage.close();
                    loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/fxms/table.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    root = loader.getRoot();
                    stage = new Stage();
                    stage.setScene(new Scene(root,848, 628));
                    stage.setTitle("TableMenu");
                    stage.setResizable(false);
                    stage.show();
                    break;
                case -1:
                    infoLabel.setText("");
                    infoLabel.setStyle("-fx-text-fill: red;");
                    infoLabel.setText("Неверно введено имя пользователя\nи/или пароль");
                    break;

            }
        });
        regBtn.setOnAction(event -> {
            Stage stage = (Stage) regBtn.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/reg.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root,543, 379));
            stage.setTitle("Registration menu");
            stage.setResizable(false);
            stage.show();
        });

    }

    private int checkUser(){
        int res = 0;
        String[] temp = new String[2];
        String login = loginText.getText();
        String password = passwordText.getText();
        if(login.length() != 0 && password.length() != 0 ){
            String sql = "getUser '" + login +"', '" + password + "'";
        //    System.out.println(sql);
            temp = Bridge.checkUsers(sql);

            try {
                if(temp[0].equals("sa")  && temp[1].equals("123")){
                    res = 2;
                }
                else {
                    res = 1;
                }
            }
            catch (NullPointerException ex){
                res = -1;
            }

        }
        else {
            infoLabel.setText("");
            infoLabel.setText("Не все поля заполнены!");
        }

        return res;
    }
}
