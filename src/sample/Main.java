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
    private static final String DATABASE_NAME = "progress.db";
    private static final int DATABASE_VERSEIONS = 1;

    private static final String TABLE_NAME = "userProgress";
    private static final String ID = "id";
    private static final String BEFFECT = "bEffect";
    private static final String BPRICE = "bPrice";
    private static final String BNUMBER = "bNumber";
    private static final String PBEFFECT = "pbEffect";
    private static final String PBPRICE = "pbPrice";
    private static final String PBNUMBER = "pbNumber";
    private static final String CATCOUNT = "catCount";
    private static final String HPCOUNT = "hpCount";

    @Override
    public void start(Stage primaryStage) throws Exception{


        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + BEFFECT + " INTEGER, "
                        + BPRICE + " INTEGER, "
                        + BNUMBER + " INTEGER, "
                        + PBEFFECT + " REAL, "
                        + PBPRICE + " INTEGER, "
                        + PBNUMBER + " INTEGER, "
                        + CATCOUNT + " INTEGER, "
                        + HPCOUNT + " INTEGER);";

        System.out.println(query);


        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxms/sample.fxml"));
        primaryStage.setTitle("Lototron");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 848, 628));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
