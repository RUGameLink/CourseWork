package sample.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Logic.DBConnector;
import sample.Items.Heroes;
import sample.Logic.Clicker;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class TableController {

    private ObservableList<Heroes> heroesData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button retBtn;


    @FXML
    private TableView<Heroes> table;

    @FXML
    private TableColumn<Heroes, Heroes.Names> name;

    @FXML
    private TableColumn<Heroes, Integer> rarity;

    @FXML
    private TableColumn<Heroes, Heroes.damageAmplification> dam;

    @FXML
    private TableColumn<Heroes, Heroes.healing> heal;

    @FXML
    private TableColumn<Heroes, String> element;

    @FXML
    private TableColumn<Heroes, String> skill;

    @FXML
    private TableColumn<Heroes, Integer> cost;

    @FXML
    private Pane salePane;

    @FXML
    private ListView<Heroes> itemsList;

    @FXML
    private Button saleItem;

    @FXML
    private Button workWindow;

    @FXML
    private Button closeBtn;

    @FXML
    private Button saleBtn;

    @FXML
    private void initialize() {
        heroesData.clear();
        heroesData = DBConnector.getItems(); //Получение значений пользовательского инвентаря
        System.out.println(heroesData);
        salePane.setVisible(false);

        name.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.Names>("name"));
        rarity.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("rarity"));
        dam.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.damageAmplification>("dam"));
        heal.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.healing>("heal"));
        element.setCellValueFactory(new PropertyValueFactory<Heroes, String>("element"));
        skill.setCellValueFactory(new PropertyValueFactory<Heroes, String>("skill"));
        cost.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("price"));
        table.setItems(heroesData);

        itemsList.setItems(heroesData); //Заполнение таблицы из базы данных

        workWindow.setOnAction(event -> { //Обработка нажатия на кнопку перехода в окно Кликера
            Stage stage = (Stage) workWindow.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxmls/clicker.fxml"));

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

        retBtn.setOnAction((event -> { //Обработчик нажатия кнопки перехода в окно магазина
            Stage stage = (Stage) retBtn.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/fxmls/sample.fxml"));

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

        saleItem.setOnAction(actionEvent -> { //Отображение панели продажи предметов
            salePane.setVisible(true);
        });

        closeBtn.setOnAction(actionEvent -> {//Скрытие панели продажи предметов
            salePane.setVisible(false);
        });

        saleBtn.setOnAction(actionEvent -> { //Обработчик нажатия кнопки продажи предмета

            try {
                Heroes heroes = itemsList.getSelectionModel().getSelectedItem();
                int cost = heroes.getPrice() + Clicker.getClickerCount();
                Clicker.setClickerCount(cost);
                ClickerController clickerController = new ClickerController();
                clickerController.write(); //Перезаписть информации о количестве валюты
                int selectedIdx = itemsList.getSelectionModel().getSelectedIndex();
                itemsList.getItems().remove(selectedIdx);
                DBConnector.delItem(heroes);
                heroesData.clear();
                heroesData = DBConnector.getItems();
                table.setItems(heroesData);
            }
            catch (NullPointerException ex){
                System.out.println(ex);
            }
        });
    }
}
