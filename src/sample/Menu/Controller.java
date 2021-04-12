package sample.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Bridges.Bridge;
import sample.Bridges.ClickerBridge;
import sample.Items.Heroes;


public class Controller implements Initializable {
    private ObservableList<Heroes> heroesData = FXCollections.observableArrayList();

    Image image = new Image("/sample/Assets/null.png");

    @FXML
    private Button getBtn;


    @FXML
    private TextArea textField;

    @FXML
    private Button tableWindow;

    @FXML
    private ImageView RarityImage;

    @FXML
    private ImageView bgImg;

    @FXML
    private ImageView healImage;

    @FXML
    private ImageView damageImage;

    @FXML
    private ImageView characterImage;

    @FXML
    private ImageView skill;

    @FXML
    private Button workWindow;

    @FXML
    private Label moneyCountIn;

    private static int moneyCount;
    private int boxPrice;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (moneyCount == 0){

            try {
                ClickerBridge.Input();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Bridge.Connector();

        moneyCount = ClickerBridge.getClickerCount();

        boxPrice = ClickerBridge.getBoxPrice();

        moneyCountIn.setText("Количество монет: " + moneyCount);

        getBtn.setOnAction(event -> {
            try {
                btnGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        workWindow.setOnAction(event -> {
            Stage stage = (Stage) workWindow.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxms/clicker.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ClickerMenu");
            stage.setResizable(false);
            stage.show();
        });

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

        getBtn.setText("Взять (" + boxPrice + ")");
    }

    private void btnGet() throws IOException {

        if (moneyCount >= boxPrice) {

            //this.heroesList = Bridge.getHeroesArrayList();
            this.heroesData = Bridge.getHeroesDataList();

            if (this.heroesData.size() == 0) {
                textField.setText("Пусто Q_Q");

                image = new Image("/sample/Assets/null.png");
                RarityImage.setImage(image);
                bgImg.setImage(image);
                healImage.setImage(image);
                damageImage.setImage(image);
                characterImage.setImage(image);
                skill.setImage(image);
                return;
            }

            moneyCount -= boxPrice;
            moneyCountIn.setText("Количество монет: " + moneyCount);
            var heroes = this.heroesData.get(0);
            System.out.println(heroes);
            HeroesVis(heroes);

            this.heroesData.remove(0);
            Bridge.cleanerOne();

            textField.setText(heroes.toString());
            boxPrice = boxPrice + boxPrice;
            getBtn.setText("Взять (" + boxPrice + ")");

            ClickerBridge.setBoxPrice(boxPrice);
            ClickerBridge.setClickerCount(moneyCount);
            ClickerBridge.Output();
        }
        else {
            image = new Image("/sample/Assets/null.png");
            RarityImage.setImage(image);
            bgImg.setImage(image);
            healImage.setImage(image);
            damageImage.setImage(image);
            characterImage.setImage(image);
            skill.setImage(image);
            textField.setText("Нет денег:(");
        }
    }

    public static int getMoney(){
        return moneyCount;
    }

    private void HeroesVis(Heroes hero){
        switch (hero.rarity){
            case 1:
                image = new Image("/sample/Assets/rarity/rarity1.png");
                break;
            case 2:
                image = new Image("/sample/Assets/rarity/rarity2.png");
                break;
            case 3:
                image = new Image("/sample/Assets/rarity/rarity3.png");
                break;
            case 4:
                image = new Image("/sample/Assets/rarity/rarity4.png");
                break;
            case 5:
                image = new Image("/sample/Assets/rarity/rarity5.png");
                break;
        }
        RarityImage.setImage(image);

        switch (hero.element){
            case "Огонь":
                image = new Image("/sample/Assets/bg/fireBG.png");
                break;
            case "Вода":
                image = new Image("/sample/Assets/bg/waterBG.png");
                break;
            case "Нет":
                image = new Image("/sample/Assets/bg/notBG.png");
                break;
        }

        switch (hero.element){
            case "Огонь":
                image = new Image("/sample/Assets/bg/fireBG.png");
                break;
            case "Вода":
                image = new Image("/sample/Assets/bg/waterBG.png");
                break;
            case "Нет":
                image = new Image("/sample/Assets/bg/notBG.png");
                break;
        }
        bgImg.setImage(image);

        switch (hero.heal){
            case TRUE:
                image = new Image("/sample/Assets/ability/heal.png");
                break;
            case FALSE:
                image = new Image("/sample/Assets/ability/heal1.png");
                break;
        }
        healImage.setImage(image);

        switch (hero.damAmpl){
            case TRUE:
                image = new Image("/sample/Assets/ability/damage.png");
                break;
            case FALSE:
                image = new Image("/sample/Assets/ability/damage1.png");
                break;
        }
        damageImage.setImage(image);

        switch (hero.name){
            case ABEL:
                image = new Image("/sample/Assets/characters/m1.png");
                break;
            case ZELDA:
                image = new Image("/sample/Assets/characters/f1.png");
                break;
            case NINO:
                image = new Image("/sample/Assets/characters/f2.png");
                break;
            case LUCIAN:
                image = new Image("/sample/Assets/characters/m2.png");
                break;
            case HILDA:
                image = new Image("/sample/Assets/characters/f3.png");
                break;
        }
        characterImage.setImage(image);

        switch (hero.skill){
            case "Огненный шар":
                image = new Image("/sample/Assets/ability/typeAbility/fire_ball.png");
                break;
            case "Огненный вихрь":
                image = new Image("/sample/Assets/ability/typeAbility/fire_whirlwind.png");
                break;
            case "Пожар":
                image = new Image("/sample/Assets/ability/typeAbility/fire.png");
                break;
            case "Свеча":
                image = new Image("/sample/Assets/ability/typeAbility/candle.png");
                break;
            case "Стена огня":
                image = new Image("/sample/Assets/ability/typeAbility/fire_wall.png");
                break;
            case "Заморозка":
                image = new Image("/sample/Assets/ability/typeAbility/ice.png");
                break;
            case "Водяной элементаль":
                image = new Image("/sample/Assets/ability/typeAbility/water_elemental.png");
                break;
            case "Поток":
                image = new Image("/sample/Assets/ability/typeAbility/flow.png");
                break;
            case "Пузырь":
                image = new Image("/sample/Assets/ability/typeAbility/bubble.png");
                break;
            case "Отражение":
                image = new Image("/sample/Assets/ability/typeAbility/reflection.png");
                break;
            case "Разящий удар":
                image = new Image("/sample/Assets/ability/typeAbility/hit.png");
                break;
            case "Парирование":
                image = new Image("/sample/Assets/ability/typeAbility/parry.png");
                break;
            case "Купол":
                image = new Image("/sample/Assets/ability/typeAbility/dome.png");
                break;
            case "Отсутствует":
                image = new Image("/sample/Assets/ability/typeAbility/none.png");
                break;
        }
        skill.setImage(image);
    }
}

