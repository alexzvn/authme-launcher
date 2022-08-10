package dev.alexzvn.authentication;

import org.bukkit.plugin.java.JavaPlugin;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import dev.alexzvn.authentication.data.AuthCheck;
import dev.alexzvn.authentication.data.Authenticate;
import dev.alexzvn.authentication.data.RegisterData;

public class AuthmeLauncher extends JavaPlugin {

    private PlayerStateManager manager;

    private SocketIOServer server;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        manager = new PlayerStateManager();

        server = createSocket();

        server.addEventListener("authenticate", Authenticate.class, new DataListener<Authenticate>() {

            @Override
            public void onData(SocketIOClient client, Authenticate data, AckRequest ackSender) throws Exception {
                if (! data.isValid()) {
                    client.sendEvent("authenticate", false);
                    return;
                }

                boolean authenticated = manager.authenticate(data.getUsername(), data.getPassword(), data.getChallange());

                client.sendEvent("authenticate", authenticated);
            }
        });

        server.addEventListener("auth:check", AuthCheck.class, new DataListener<AuthCheck>() {

            @Override
            public void onData(SocketIOClient client, AuthCheck data, AckRequest ackSender) throws Exception {
                if (! data.isValid()) {
                    client.sendEvent("auth:check", false);
                    return;
                }

                boolean authenticated = manager.check(data.getUsername(), data.getPassword());

                client.sendEvent("auth:check", authenticated);
            }
        });

        server.addEventListener("auth:register", RegisterData.class, new DataListener<RegisterData>() {
            
            @Override
            public void onData(SocketIOClient client, RegisterData data, AckRequest ackSender) throws Exception {
                if (! data.isValid()) {
                    client.sendEvent("auth:register", false);
                    return;
                }

                boolean registered = manager.register(data.getUsername(), data.getPassword());

                client.sendEvent("auth:register", registered);
            }
        });

        server.start();

        getServer().getPluginManager().registerEvents(manager, this);
    }

    @Override
    public void onDisable() {
        if (server != null) server.stop();
    }

    public PlayerStateManager getPlayerStateManager() {
        return manager;
    }

    protected SocketIOServer createSocket() {
        Configuration config = new Configuration();

        config.setHostname(getConfig().getString("network.bind", "0.0.0.0"));
        config.setPort(getConfig().getInt("network.port", 8920));
        config.getSocketConfig().setReuseAddress(true);

        return new SocketIOServer(config);
    }
}
