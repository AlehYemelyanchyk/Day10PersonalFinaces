package by.javatr.finances.dao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleh Yemelyanchyk on 12/27/2019.
 */
public class User implements Serializable {

    private static final long SerialVersionUID = 1L;

    private String login;
    private String password;
    private String email;
    private List<String> accounts = new ArrayList<>();

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;

        return getLogin() != null ? getLogin().equals(user.getLogin()) : user.getLogin() == null;
    }

    @Override
    public int hashCode() {
        return getLogin() != null ? getLogin().hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@'
                + "login: " + login
                + ", password: " + password
                + ", email: " + email;
    }
}
