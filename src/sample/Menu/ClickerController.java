package sample.Menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Bridges.ClickerBridge;
import sample.FileStreamer.FileStreamer;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;



public class ClickerController implements Initializable {


    @FXML
    private Label passiveBustCount;

    @FXML
    private Label moneyCount;

    @FXML
    private Button workBtn;

    @FXML
    private Button bustBtn;

    @FXML
    private Button passivBustBtn;

    public static int clickerCount;

    @FXML
    private Button retBtn;

    @FXML
    private TextArea infoPanel;

    private int timerSpeed;
    private double perSecond;
    private boolean timerOn;

    private int passivBustPrice;
    private int passivBustNumber;

    private int bustPrice;
    private int bustNumber;
    private int bustEffect;
    private int userEffect;

    private Timeline timeline;

//    private FileStreamer fileStreamer = new FileStreamer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //    perSecond = 0;
    //    timerOn = false;
        userEffect = 1;

        try {
            ClickerBridge.Input();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clickerCount = Controller.getMoney();

        passivBustPrice = ClickerBridge.getBustPrice();
        passivBustNumber = ClickerBridge.getPassivBustNumber();

        bustPrice = ClickerBridge.getBustPrice();
        bustNumber = ClickerBridge.getBustNumber();
        bustEffect = ClickerBridge.getBustEffect();
        perSecond = ClickerBridge.getPerSecond();
        passivBustPrice = ClickerBridge.getPassivBustPrice();
        timerOn = ClickerBridge.getTimerOne();

        moneyCount.setText("Количество монет: " + clickerCount);
        bustBtn.setText("Усилить (" + bustNumber + ")");
        passivBustBtn.setText("Пассивный доход (" + passivBustNumber + ")");
        passivBustBtn.setText("Пассивный доход (" + passivBustNumber + ")");

        String s = String.format("%.1f", perSecond);
        passiveBustCount.setText("В секунду " + s);


        retBtn.setOnAction((event -> {
            Stage stage = (Stage) retBtn.getScene().getWindow();
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
            stage.setTitle("Lototron");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }));

        passivBustBtn.setOnMouseEntered(event -> {
            infoPanel.setVisible(true);
            infoPanel.setText("Уровень: " + passivBustNumber
                    + "\n" + "Стоимость улучшения: " + passivBustPrice);
        } );

        passivBustBtn.setOnMouseExited(event -> {
            infoPanel.setVisible(false);
            infoPanel.setText("");
        } );

        bustBtn.setOnMouseEntered(event -> {
            infoPanel.setVisible(true);
            infoPanel.setText("Уровень: " + bustNumber
                    + "\n" + "Стоимость улучшения: " + bustPrice
                    + "\n" + "Эффкт буста: " + bustEffect);
        } );

        bustBtn.setOnMouseExited(event -> {
            infoPanel.setVisible(false);
            infoPanel.setText("");
        } );

        workBtn.setOnMouseEntered(event -> {
            infoPanel.setVisible(true);
            int effect = userEffect + bustEffect;
            infoPanel.setText("Добыча за клик: " + effect);
        } );

        workBtn.setOnMouseExited(event -> {
            infoPanel.setVisible(false);
            infoPanel.setText("");
        } );

        timerUpdate();
    }

    public static int getMoney(){
        return clickerCount;
    }

    private void onTimerTick(ActionEvent actionEvent){
        clickerCount ++;
        moneyCount.setText("Количество монет: " + clickerCount);

        ClickerBridge.setClickerCount(clickerCount);
        try {
            ClickerBridge.Output();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timerUpdate(){

        if (timerOn == false){
            timerOn = true;
        }
    //    else if (timerOn == true){
    //        timeline.stop();
    //    }


        double speed = 1 / perSecond * 1000;
        timerSpeed = (int)Math.round(speed);

        timeline = new Timeline(new KeyFrame(
                Duration.millis(timerSpeed), this::onTimerTick));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        String s = String.format("%.1f", perSecond);
        passiveBustCount.setText("В секунду " + s);

        ClickerBridge.setTimerOn(timerOn);
    }

    public void actionPerfomed(ActionEvent actionEvent) throws IOException {
        Random random = new Random();
        var btn = (Button)actionEvent.getTarget();
        switch (btn.getId()){
            case "workBtn":{
                clickerCount = clickerCount + userEffect + bustEffect;
                moneyCount.setText("Количество монет: " + clickerCount);

                ClickerBridge.setClickerCount(clickerCount);
                ClickerBridge.Output();
            }
            break;
            case "bustBtn":{
                if (clickerCount >= bustPrice) {
                    clickerCount = clickerCount - bustPrice;
                    bustPrice += bustPrice * 0.5;
                        if(bustEffect == 0){
                        bustEffect ++;
                    }
                    else {
                        bustEffect += bustEffect + (int) (Math.random() * +3) + 1;
                    }
                    bustNumber ++;

                    bustBtn.setText("Усилить (" + bustNumber + ")");
                    moneyCount.setText("Количество монет: " + clickerCount);

                    infoPanel.setText("Уровень: " + bustNumber
                            + "\n" + "Стоимость улучшения: " + bustPrice
                            + "\n" + "Эффкт буста: " + bustEffect);

                    ClickerBridge.setBustNumber(bustNumber);
                    ClickerBridge.setClickerCount(clickerCount);
                    ClickerBridge.setBustPrice(bustPrice);
                    ClickerBridge.setBustEffect(bustEffect);

                    ClickerBridge.Output();
                }
            }
            break;
            case "passivBustBtn":{

                if (clickerCount >= passivBustPrice) {
                    clickerCount = clickerCount - passivBustPrice;
                    passivBustPrice += passivBustPrice * 0.5;
                    passivBustNumber ++;

                    passivBustBtn.setText("Пассивный доход (" + passivBustNumber + ")");
                    moneyCount.setText("Количество монет: " + clickerCount);
                    perSecond = perSecond + 0.1;

                    infoPanel.setText("Уровень: " + passivBustNumber
                            + "\n" + "Стоимость улучшения: " + passivBustPrice);

                    timerUpdate();

                    ClickerBridge.setPassivBustNumber(passivBustNumber);
                    ClickerBridge.setClickerCount(clickerCount);
                    ClickerBridge.setPassivBustPrice(passivBustPrice);
                    ClickerBridge.setPerSecond(perSecond);

                    ClickerBridge.Output();
                }
            }
            break;
        }
    }

/*    public void Output() throws IOException {

        String res;
        res = clickerCount + " " + bustEffect + " " + bustNumber + " " + bustPrice + " "
                  + passivBustNumber + " " + passivBustPrice + " " + perSecond;
        fileStreamer.OutputStream(res);
    }

    public void Input() throws IOException {

         String temp = fileStreamer.InputStream();

         if (temp.length() == 0){
             passivBustPrice = 10;
             passivBustNumber = 0;

             bustPrice = 25;
             bustNumber = 0;
             bustEffect = 0;
             clickerCount = 0;
             perSecond = 0;
         }
         else {
             String[] words = split(temp);
             clickerCount = Integer.parseInt(words[0]);
             bustEffect = Integer.parseInt(words[1]);
             bustNumber = Integer.parseInt(words[2]);
             bustPrice = Integer.parseInt(words[3]);
             passivBustNumber = Integer.parseInt(words[4]);
             passivBustPrice = Integer.parseInt(words[5]);
             perSecond = Double.parseDouble(words[6]);


         }
    }
    private String[] split(String temp){

        String[] words = temp.split(" ");
        return words;
    }*/
}