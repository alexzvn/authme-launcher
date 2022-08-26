package dev.alexzvn.authentication.data;

// Class support for login in-game
public class Authenticate {

    private String username;
    private String password;
    private String challenge;

    public Authenticate() {}

    public Authenticate(String username, String password, String challenge) {
        super();
        this.username = username;
        this.password = password;
        this.challenge = challenge;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public boolean isValid() {

        return username != null
            && password != null
            && challenge != null
            && username.length() > 0
            && password.length() > 0
            && challenge.length() > 0;
    }
}
