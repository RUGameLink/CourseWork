package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{ //Загрузка окна авторизации

        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxmls/login.fxml"));
        primaryStage.setTitle("Authorization menu");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 543, 379));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
