package sample.Logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Items.*;

import java.sql.*;

public class Bridge {
    private static Connection connection;
    private static String userName;
    
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
    }

    public static String getUserName(){
        return userName;
    }

    public static void setUserName(String username){
        userName = username;
    }

    private static ObservableList<Heroes> heroesData = FXCollections.observableArrayList();

    private static ObservableList<Users> usersData = FXCollections.observableArrayList();

    private static ObservableList<Heroes> usersItemsData = FXCollections.observableArrayList();

    private static ObservableList<Heroes> ItemsData = FXCollections.observableArrayList();

    public static void setHeroesDataList(Heroes heroes){
        write(heroes);
    }

    public static ObservableList getHeroesDataList(){
        read();
        return heroesData;
    }

    public static ObservableList getUsersDataList(){
        readUsers();
        return usersData;
    }

    public static ObservableList getUsersInvDataList(){
        readInv();
        return ItemsData;
    }

    public static void read(){
        heroesData = FXCollections.observableArrayList();
        String sql = "select * from boxTable";
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

                switch (resultSet.getString("element")){
                    case "Нет":
                        heroesData.add(new Knights(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Вода":
                        heroesData.add(new WaterHeroes(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Огонь": ;
                        heroesData.add(new FireHeroes(rarity, damageBust, healing, name, element, skill));
                        break;
                }

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static void getUserItems(){
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

                switch (resultSet.getString("element")){
                    case "Нет":
                        usersItemsData.add(new Knights(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Вода":
                        usersItemsData.add(new WaterHeroes(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Огонь": ;
                        usersItemsData.add(new FireHeroes(rarity, damageBust, healing, name, element, skill));
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

    public static void buyItem(Heroes heroes){
        String sql = "insert UserItems values('"+getUserName() +"', '" +heroes.getName()+"', " + heroes.getRarity() + ", "
                + "'" +heroes.getDam() + "', " + "'" +heroes.getHeal() + "', "
                + "'" +heroes.getElement() + "', " + "'" +heroes.getSkill() + "'" + ")";
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

    public static void readUsers(){
        String sql = "select * from Users";

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


    public static void readInv(){
        heroesData = FXCollections.observableArrayList();
        String sql = "select * from UserItems";
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

                switch (resultSet.getString("element")){
                    case "Нет":
                        ItemsData.add(new Knights(owner, rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Вода":
                        ItemsData.add(new WaterHeroes(owner, rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Огонь": ;
                        ItemsData.add(new FireHeroes(owner, rarity, damageBust, healing, name, element, skill));
                        break;
                }

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    public static void write(Heroes heroes){
        String sql = "insert boxTable values('"+heroes.getName()+"', " + heroes.getRarity() + ", "
                + "'" +heroes.getDam() + "', " + "'" +heroes.getHeal() + "', "
                + "'" +heroes.getElement() + "', " + "'" +heroes.getSkill() + "'" + ")";
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

    public static int writeUser(String sql){
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

    public static String[] checkUsers(String sql){
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

    public static void cleaner(){
        String sql = "DELETE boxTable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void cleanerOne(){
        String sql = "delete top (1) from boxTable";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList sort(){
        heroesData = FXCollections.observableArrayList();
        String sql = "select * from boxTable\n order by rarity desc";
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

                switch (resultSet.getString("element")){
                    case "Нет":
                        heroesData.add(new Knights(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Вода":
                        heroesData.add(new WaterHeroes(rarity, damageBust, healing, name, element, skill));
                        break;
                    case "Огонь": ;
                        heroesData.add(new FireHeroes(rarity, damageBust, healing, name, element, skill));
                        break;
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return heroesData;
    }
}
