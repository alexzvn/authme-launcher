package dev.alexzvn.authentication.data;

public class AuthCheck {

    private String username;
    private String password;

    public AuthCheck() {}

    public AuthCheck(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return username != null && password != null && username.length() > 0 && password.length() > 0;
    }
}
