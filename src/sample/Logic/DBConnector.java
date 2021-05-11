package sample.Logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Items.*;

import java.sql.*;

public class DBConnector {
    private static Connection connection;
    private static String userName;

    private static ObservableList<Heroes> heroesData = FXCollections.observableArrayList();

    private static ObservableList<Users> usersData = FXCollections.observableArrayList();

    private static ObservableList<Heroes> usersItemsData = FXCollections.observableArrayList();

    private static ObservableList<Heroes> ItemsData = FXCollections.observableArrayList();
    
    public static void Connector(){
        String url = "jdbc:sqlserver://RUGAMELINK\\SQLEXPRESS;databaseName=items";
        String username = "sa";
        String password = "123";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connect 2 SQL");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    } // Конструктор по умолчанию

    public static String getUserName(){
        return userName;
    }

    public static void setUserName(String username){
        userName = username;
    }

    public static void setHeroesDataList(Heroes heroes){ // Метод записи объекта Heroes в базу данных
        write(heroes);
    }

    public static ObservableList getHeroesDataList(){ // Метод вызова метода чтения из базы данных и возврат списка объектов Heroes
        read();
        return heroesData;
    }

    public static ObservableList getUsersDataList(){ // Метод вызова метода чтения из базы данных и возврат списка объектов Users
        readUsers();
        return usersData;
    }

    public static ObservableList getUsersInvDataList(){ // Метод вызова метода чтения из базы данных и возврат списка объектов Heroes
        readInv();
        return ItemsData;
    }

    public static void read(){ // Метод чтения данных из базы данных
        heroesData = FXCollections.observableArrayList();
        String sql = "GetBoxTable";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int rarity = resultSet.getInt("rarity");
                Heroes.Names[] names = Heroes.Names.values();
                var damageBust = Heroes.damageAmplification.TRUE;
                var healing = Heroes.healing.TRUE;
                var element = resultSet.getString("element");
                var skill = resultSet.getString("skill");
                var name = Heroes.Names.ABEL;

                for(var title : names){
                    if (resultSet.getString("name").equals(title.toString())){
                        name = title;
                        break;
                    }
                }

                Heroes.damageAmplification[] damageBustes = Heroes.damageAmplification.values();
                for(var title : damageBustes){
                    if (resultSet.getString("damageBust").equals(title.toString())){
                        damageBust = title;
                        break;
                    }
                }

                Heroes.healing[] healings = Heroes.healing.values();
                for(var title : healings){
                    if (resultSet.getString("healing").equals(title.toString())){
                        healing = title;
                        break;
                    }
                }
                var cost = resultSet.getInt("cost");
                switch (resultSet.getString("element")){
                    case "Нет":
                        heroesData.add(new Knights(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Вода":
                        heroesData.add(new WaterHeroes(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Огонь": ;
                        heroesData.add(new FireHeroes(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                }

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getUserItems(){ // Метод чтения данных пользовательского инвентаря из базы данных и возврат
        usersItemsData.clear();
        String sql = "GetUserImems " + "'" + getUserName() + "'";
        System.out.println("\n"+sql+"\n");
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int rarity = resultSet.getInt("rarity");
                Heroes.Names[] names = Heroes.Names.values();
                var damageBust = Heroes.damageAmplification.TRUE;
                var healing = Heroes.healing.TRUE;
                var element = resultSet.getString("element");
                var skill = resultSet.getString("skill");
                var name = Heroes.Names.ABEL;

                for(var title : names){
                    if (resultSet.getString("name").equals(title.toString())){
                        name = title;
                        break;
                    }
                }

                Heroes.damageAmplification[] damageBustes = Heroes.damageAmplification.values();
                for(var title : damageBustes){
                    if (resultSet.getString("damageBust").equals(title.toString())){
                        damageBust = title;
                        break;
                    }
                }

                Heroes.healing[] healings = Heroes.healing.values();
                for(var title : healings){
                    if (resultSet.getString("healing").equals(title.toString())){
                        healing = title;
                        break;
                    }
                }
                int cost = resultSet.getInt("cost");
                switch (resultSet.getString("element")){
                    case "Нет":
                        usersItemsData.add(new Knights(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Вода":
                        usersItemsData.add(new WaterHeroes(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Огонь": ;
                        usersItemsData.add(new FireHeroes(rarity, damageBust, healing, name, element, skill, cost));
                        break;
                }

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList getItems(){
        getUserItems();
        return usersItemsData;
    }

    public static void buyItem(Heroes heroes){ // Метод покупки пользователем предмета
        String sql = "AddUserItems'"+getUserName() +"', '" +heroes.getName()+"', " + heroes.getRarity() + ", "
                + "'" +heroes.getDam() + "', " + "'" +heroes.getHeal() + "', "
                + "'" +heroes.getElement() + "', " + "'" +heroes.getSkill() + "', " +heroes.getPrice();
        System.out.println("\n"+sql+"\n");
        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows > 0){
                System.out.println("Rows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void delItem(Heroes heroes){ // Метод удаления купленного предмета из магазина
        String sql = "DelItem '" + heroes.getName() + "', " + heroes.getRarity() + ", " + heroes.getPrice();
        System.out.println("\n"+sql+"\n");
        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows > 0){
                System.out.println("Rows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void readUsers(){ // Метод удаления купленного предмета из магазина
        String sql = "GetUsers";

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                
                usersData.add(new Users(login, password));
            }

        } catch (Exception throwables) {
        throwables.printStackTrace();

        }
    }


    public static void readInv(){ // Метод чтения из базы данных инвентарей пользователей
        heroesData = FXCollections.observableArrayList();
        ItemsData.clear();
        String sql = "GetUserItems";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int rarity = resultSet.getInt("rarity");
                Heroes.Names[] names = Heroes.Names.values();
                var owner = resultSet.getString("userName");
                var damageBust = Heroes.damageAmplification.TRUE;
                var healing = Heroes.healing.TRUE;
                var element = resultSet.getString("element");
                var skill = resultSet.getString("skill");
                var name = Heroes.Names.ABEL;

                for(var title : names){
                    if (resultSet.getString("name").equals(title.toString())){
                        name = title;
                        break;
                    }
                }

                Heroes.damageAmplification[] damageBustes = Heroes.damageAmplification.values();
                for(var title : damageBustes){
                    if (resultSet.getString("damageBust").equals(title.toString())){
                        damageBust = title;
                        break;
                    }
                }

                Heroes.healing[] healings = Heroes.healing.values();
                for(var title : healings){
                    if (resultSet.getString("healing").equals(title.toString())){
                        healing = title;
                        break;
                    }
                }
                var cost = resultSet.getInt("cost");
                switch (resultSet.getString("element")){
                    case "Нет":
                        ItemsData.add(new Knights(owner, rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Вода":
                        ItemsData.add(new WaterHeroes(owner, rarity, damageBust, healing, name, element, skill, cost));
                        break;
                    case "Огонь": ;
                        ItemsData.add(new FireHeroes(owner, rarity, damageBust, healing, name, element, skill, cost));
                        break;
                }

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public static void write(Heroes heroes){ // Метод записи объектов Heroes в хранилище магазина
        String sql = "AddBoxTable'"+heroes.getName()+"', " + heroes.getRarity() + ", "
                + "'" +heroes.getDam() + "', " + "'" +heroes.getHeal() + "', "
                + "'" +heroes.getElement() + "', " + "'" +heroes.getSkill() + "', " +heroes.getPrice();
        System.out.println(sql);
        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows > 0){
                System.out.println("Rows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int writeUser(String sql){ // Запись нового пользователя в базу данных
        System.out.println(sql);
        int rows = 0;

        try {
            Statement statement = connection.createStatement();
            rows = statement.executeUpdate(sql);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    public static String[] checkUsers(String sql){ // Метод проверки пользователя в базе данных
        System.out.println(sql);
        String[] res = new String[2];
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                res[0] = resultSet.getString("login");
                res[1] = resultSet.getString("password");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;

    }

    public static void cleaner(){ // Метод очистки хранилища магазина в базе данных
        String sql = "DELETE boxTable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void cleanerOne(){ // Метод удаления записи из таблицы хранилища магазина в базе данных
        String sql = "DelBoxTable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
