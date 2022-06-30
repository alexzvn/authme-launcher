package dev.alexzvn.authentication.data;

// Class support for login in-game
public class Authenticate {

    private String username;
    private String password;
    private String challange;

    public Authenticate() {}

    public Authenticate(String username, String password, String challange) {
        super();
        this.username = username;
        this.password = password;
        this.challange = challange;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getChallange() {
        return challange;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChallange(String challange) {
        this.challange = challange;
    }

    public boolean isValid() {

        return username != null
            && password != null
            && challange != null
            && username.length() > 0
            && password.length() > 0
            && challange.length() > 0;
    }
}
