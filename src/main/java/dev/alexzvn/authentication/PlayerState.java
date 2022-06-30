package dev.alexzvn.authentication;

import org.bukkit.entity.Player;

import fr.xephi.authme.api.v3.AuthMeApi;

public class PlayerState {
    public String challange;
    public Boolean isAuthenticated = false;

    public PlayerState(String challange) {
        this.challange = challange;
    }

    public PlayerState() {
        challange = random(10);
    }

    public void regenerateChallange() {
        this.challange = random(10);
    }

    private String random(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    public static PlayerState from(Player player) {
        final PlayerState state = new PlayerState();
        AuthMeApi api = AuthMeApi.getInstance();

        state.isAuthenticated = api.isAuthenticated(player);

        return state;
    }
}
