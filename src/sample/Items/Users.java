package sample.Items;

public class Users {
    public String login;
    public String password;

    public Users(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String  getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }
}
