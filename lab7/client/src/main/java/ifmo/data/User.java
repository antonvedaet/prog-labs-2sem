package ifmo.data;

import java.io.Serializable;

public class User implements Serializable{
    String login;
    String password;

    public User(String login, String pwd){
        this.login = login;
        this.password = pwd;
    }
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
