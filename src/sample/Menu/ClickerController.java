package sample.Menu;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.FileStreamer.FileStreamer;
import sample.Logic.Clicker;
import sample.Logic.ClickerBridge;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;



public class ClickerController implements Initializable {

    @FXML
    private Button tableWindow;

    @FXML
    private Button shopWindow;


    @FXML
    private Button upBtn;

    @FXML
    private Label infoLabel;

    @FXML
    private Button upgBtn;

    @FXML
    private ImageView stone;

    @FXML
    private Label progLabel;

    @FXML
    private Label totalOre;

    @FXML
    private Button oreBtn;


    Clicker clicker;

    Image image = new Image("/sample/Assets/null.png");

    private Random r = new Random();

    private int oreCHeck;
    private int temp;
    private int progress = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clicker = new Clicker(0, 10, 0, 0, 20, 0, 100,
                0, 1, 6);

        read();
        readOre();

        temp ++;

        upBtn.setText("Заточить кирку (" + clicker.getBustNumber() + ")");
        upgBtn.setText("Улучшить кирку (" + clicker.getPassivBustNumber() + ")");
        infoLabel.setText("Количество монет: " + clicker.getClickerCount());
        totalOre.setText("Разбито руды: " +  clicker.getOreCount());
        progLabel.setText("Залежа добыта на:  " + temp + "%");

        tableWindow.setOnAction((event -> {
            Stage stage = (Stage) tableWindow.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/table.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("TableMenu");
            stage.setResizable(false);
            stage.show();

        }));

        shopWindow.setOnAction((event -> {
            Stage stage = (Stage) tableWindow.getScene().getWindow();
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
            stage.setScene(new Scene(root));
            stage.setTitle("TableMenu");
            stage.setResizable(false);
            stage.show();

        }));

        oreBtn.setOnAction(actionEvent -> {
            clickOre();
            write();
            temp =  (100 * progress) / (int) clicker.getHpCount();
            progLabel.setText("Залежа добыта на:  " + temp + "%");
        });

    }

    private void clickOre(){
        progress = progress + 1 + clicker.getBustEffect();
        if(progress >= clicker.getHpCount()){
            scale();
            int temp = clicker.getOreCount() + 1;
            clicker.setOreCount(temp);
            progress = 0;
            temp = clicker.getPower();
            clicker.setClickerCount(temp);
            totalOre.setText("Разбито руды: " +  clicker.getOreCount());
            infoLabel.setText("Количество монет: " + clicker.getClickerCount());
            changeOre();
        }
    }

    private void changeOre(){
        oreCHeck = r.nextInt(4 - 1 + 1) + 1;
        switch(oreCHeck)
        {
            case 1:
                image = new Image("/sample/Assets/ore/ore1.png");
                break;
            case 2:
                image = new Image("/sample/Assets/ore/ore2.png");
                break;
            case 3:
                image = new Image("/sample/Assets/ore/ore3.png");
                break;
            case 4:
                image = new Image("/sample/Assets/ore/ore4.png");
                break;
        }
        stone.setImage(image);
        write();
    }

    private void readOre(){
        switch(oreCHeck)
        {
            case 1:
                image = new Image("/sample/Assets/ore/ore1.png");
                break;
            case 2:
                image = new Image("/sample/Assets/ore/ore2.png");
                break;
            case 3:
                image = new Image("/sample/Assets/ore/ore3.png");
                break;
            case 4:
                image = new Image("/sample/Assets/ore/ore4.png");
                break;
        }
        stone.setImage(image);
    }

    private void scale(){
        int temp;
        if (clicker.getBustEffect() > 20000){
            temp = (int) clicker.getHpCount() + 50000;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 15000){
            temp = (int) clicker.getHpCount() + 18000;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 10000){
            temp = (int) clicker.getHpCount() + 12000;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 3000){
            temp = (int) clicker.getHpCount() + 5000;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 1000){
            temp = (int) clicker.getHpCount() + 2000;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 400){
            temp = (int) clicker.getHpCount() + 600;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 300){
            temp = (int) clicker.getHpCount() + 400;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 200){
            temp = (int) clicker.getHpCount() + 300;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 100){
            temp = (int) clicker.getHpCount() + 200;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 70){
            temp = (int) clicker.getHpCount() + 80;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 50){
            temp = (int) clicker.getHpCount() + 60;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 20){
            temp = (int) clicker.getHpCount() + 30;
            clicker.setHpCount(temp);
        }
        else if (clicker.getBustEffect() > 10){
            temp = (int) clicker.getHpCount() + 20;
            clicker.setHpCount(temp);
        }
    }


    private void write(){
        String myText = clicker.getBustEffect() + " " + clicker.getBustPrice() + " " + clicker.getBustNumber() + " "
                + clicker.getPassivBustPrice() + " " + clicker.getPassivBustNumber() + " " + clicker.getClickerCount() + " " + clicker.getHpCount() + " " + clicker.getOreCount()
                + " " + clicker.getMin() + " " + clicker.getMax() + " " + oreCHeck + " " + temp + " " + progress;
        try {
            FileStreamer.OutputStream(myText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(){
        String res = "";

        try {
            res = FileStreamer.InputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(res == null){
            clicker = new Clicker(0, 10, 0, 0, 20, 0, 100, 0, 1, 6);
            write();
        }
        else {
            String word[] = split(res);
            clicker.setBustEffect(Integer.parseInt(word[0]));
            clicker.setBustPrice(Integer.parseInt(word[1]));
            clicker.setBustNumber(Integer.parseInt(word[2]));

            clicker.setPassivBustPrice(Integer.parseInt(word[3]));
            clicker.setPassivBustNumber(Integer.parseInt(word[4]));
            clicker.setClickerCount(Integer.parseInt(word[5]));
            clicker.setHpCount(Double.parseDouble(word[6]));
            clicker.setOreCount(Integer.parseInt(word[7]));
            clicker.setMin(Integer.parseInt(word[8]));
            clicker.setMax(Integer.parseInt(word[9]));
            oreCHeck = Integer.parseInt(word[10]);
            temp = Integer.parseInt(word[11]);
            progress = Integer.parseInt(word[12]);
        }
    }

    private static String[] split(String temp){

        String[] words = temp.split(" ");
        return words;
    }

}