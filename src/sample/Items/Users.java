package sample.Items;

public class Users {
    private String login;
    private String password;

    public Users(String login, String password){ //Пользовательский конструктор
        this.login = login;
        this.password = password;
    }

    public String  getLogin(){
        return this.login;
    } //Возврат поля логина

    public String getPassword(){
        return this.password;
    } //Возврат поля пароля
}
