/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;

/**
 *
 * @author Marto
 */
public class ServerConnectionListener implements Runnable {

    private String port;
    private String password;
    private ServerSocket server;

    public ServerConnectionListener(String port, String password) {
        this.port = port;
        this.password = password;
        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
    }

    @Override
    public void run() {
        if (server == null) {
            return;
        }
        while (true) {
            try {
                Socket socket = server.accept();
                processConnection(socket);
            } catch (IOException ex) {
                Global.getExceptionHandler().notificate(
                        ExceptionHelper.IOException.getTitle(),
                        ExceptionHelper.IOException.getMessage());
            }
        }
    }

    private void processConnection(Socket sock) {
        ServerPlayerProtocol spp = new ServerPlayerProtocol(sock);
        ServerPlayer player = new ServerPlayer(spp);
        new Thread(spp).start();
        Global.getGame().getRespawner().addPlayer(player);
    }
}
