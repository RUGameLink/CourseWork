package sample.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Items.*;
import sample.Logic.Bridge;

import java.net.URL;

import java.util.ResourceBundle;


public class TableSAController implements Initializable {

    private ObservableList<Heroes> heroesData = FXCollections.observableArrayList();
    private ObservableList<Users> usersData = FXCollections.observableArrayList();
    private ObservableList<Heroes> usetItemsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Heroes> table;

    @FXML
    private TableView<Users> usersTable;

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
    private TableColumn<Users, String> login;

    @FXML
    private TableColumn<Users, String> password;

    @FXML
    private Button refBtn;

    @FXML
    private Label infoLabel;

    @FXML
    private TableView<Heroes> inv;

    @FXML
    private TableColumn<Heroes, String> owner;

    @FXML
    private TableColumn<Heroes, Heroes.Names> name1;

    @FXML
    private TableColumn<Heroes, Integer> rarity1;

    @FXML
    private TableColumn<Heroes, Heroes.damageAmplification> dam1;

    @FXML
    private TableColumn<Heroes, Heroes.healing> heal1;

    @FXML
    private TableColumn<Heroes, String> element1;

    @FXML
    private TableColumn<Heroes, String> skill1;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        heroesData = Bridge.getHeroesDataList();
        usersData = Bridge.getUsersDataList();
        usetItemsData = Bridge.getUsersInvDataList();
        System.out.println(heroesData);

        ShowInfo();
        name.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.Names>("name"));
        rarity.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("rarity"));
        dam.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.damageAmplification>("dam"));
        heal.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.healing>("heal"));
        element.setCellValueFactory(new PropertyValueFactory<Heroes, String>("element"));
        skill.setCellValueFactory(new PropertyValueFactory<Heroes, String>("skill"));
        table.setItems(heroesData);

        login.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
        password.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        usersTable.setItems(usersData);

        owner.setCellValueFactory(new PropertyValueFactory<Heroes, String>("owner"));
        name1.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.Names>("name"));
        rarity1.setCellValueFactory(new PropertyValueFactory<Heroes, Integer>("rarity"));
        dam1.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.damageAmplification>("dam"));
        heal1.setCellValueFactory(new PropertyValueFactory<Heroes, Heroes.healing>("heal"));
        element1.setCellValueFactory(new PropertyValueFactory<Heroes, String>("element"));
        skill1.setCellValueFactory(new PropertyValueFactory<Heroes, String>("skill"));
        inv.setItems(usetItemsData);

        refBtn.setOnAction((event -> {
            Ref();
        }));
    }

    private void ShowInfo() {
        int FireHeroesCount = 0;
        int WaterHeroesCount = 0;
        int KnightsCount = 0;

        for (var heroes : this.heroesData) {
            if (heroes instanceof WaterHeroes) {
                WaterHeroesCount += 1;
            } else if (heroes instanceof FireHeroes) {
                FireHeroesCount += 1;
            } else if (heroes instanceof Knights) {
                KnightsCount += 1;
            }
        }
    }

    private void Ref(){
        Bridge.cleaner();
        for(var i=0;i<15;++i)
        {
            switch(0 + (int)(Math.random() * ((2 - 0) + 1)))
            {
                case 0:
                    WaterHeroes whero = WaterHeroes.Generate();
                    Bridge.setHeroesDataList(whero);
                    break;
                case 1:
                    FireHeroes fhero = FireHeroes.Generate();
                    Bridge.setHeroesDataList(fhero);
                    break;
                case 2:
                    Knights khero = Knights.Generate();
                    Bridge.setHeroesDataList(khero);

                    break;
            }
        }

        heroesData = Bridge.getHeroesDataList();
        table.setItems(heroesData);
        ShowInfo();
    }
}
