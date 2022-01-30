package de.sollder1.jhttp;

import de.sollder1.jhttp.config.ConfigManager;
import org.tinylog.Logger;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        TcpConnector server = null;

        try {
            ConfigManager.init();
            server = new TcpConnector(ConfigManager.get().getPort());
            server.startup();
        } catch (IOException e) {
            Logger.error(e, "Server startup failed...");
        } finally {
            server.shutdown();
        }

    }


}
