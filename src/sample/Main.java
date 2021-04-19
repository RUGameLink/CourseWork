package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxms/login.fxml"));
        primaryStage.setTitle("Authorization menu");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 543, 379));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
