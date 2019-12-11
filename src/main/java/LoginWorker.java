import lombok.Builder;
import lombok.Data;


public class LoginWorker {
    String login;
    String password;
    int iduser;

    public LoginWorker(String login, String password, int iduser) {
        this.login = login;
        this.password = password;
        this.iduser = iduser;
    }

    public LoginWorker(String login, String password) {
        this.login = login;
        this.password = password;
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

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
