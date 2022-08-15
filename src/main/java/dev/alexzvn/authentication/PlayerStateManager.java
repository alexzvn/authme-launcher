package dev.alexzvn.authentication;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.xephi.authme.api.v3.AuthMeApi;

public class PlayerStateManager implements Listener {
    private HashMap<String, PlayerState> states = new HashMap<>();

    public PlayerStateManager() {
        Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            states.put(player.getName(), PlayerState.from(player));
        });
    }

    @EventHandler
    public void addStateOnJoin(PlayerJoinEvent e) {
        PlayerState state = PlayerState.from(e.getPlayer());

        states.put(e.getPlayer().getName(), state);

        e.getPlayer().sendMessage("Automatic login challange: __(" + state.challange + ")");
    }

    @EventHandler
    public void removeStateOnQuit(PlayerQuitEvent e) {
        states.remove(e.getPlayer().getName());
    }

    public boolean isAuthenticated(String username) {
        if (! states.containsKey(username)) {
            return false;
        }

        return states.get(username).isAuthenticated;
    }

    public boolean authenticate(String username, String password, String challange) {
        return authenticate(Bukkit.getServer().getPlayer(username), password, challange);
    }

    public boolean isRegistered(String username) {
        return AuthMeApi.getInstance().isRegistered(username);
    }

    public boolean register(String username, String password) {
        if (isRegistered(username)) {
            return false;
        }

        return AuthMeApi.getInstance().registerPlayer(username, password);
    }

    public boolean authenticate(Player player, String password, String challange) {
        AuthMeApi api = AuthMeApi.getInstance();
        String username = player.getName();

        if (! states.containsKey(username)) {
            return false;
        }

        PlayerState state = states.get(username);

        if (state.isAuthenticated) {
            return true;
        }

        if (! state.challange.equals(challange)) {
            return false;
        }

        if (! api.isRegistered(username)) {
            return false;
        }

        if (! api.checkPassword(username, password)) {
            return false;
        }

        api.forceLogin(player);

        return state.isAuthenticated = true;
    }

    /**
     * If player doesn't has account in AuthMe return false, true otherwise!
     */
    public boolean check(String username, String password) {
        return isRegistered(username) || AuthMeApi.getInstance().checkPassword(username, password);
    }
}
