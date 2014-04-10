/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.game.logic.respawn.DummyRespawner;
import me.martin.radev.game.virtualcommando.game.logic.server.ServerCommandBuilder;
import me.martin.radev.game.virtualcommando.game.logic.server.protocols.GameConcurrencyProtocol;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;

/**
 * A connected to server game. This means that when the game is started,
 * it is connected to a remote server.
 * @author Marto
 */
public class ConnectedToServerGame extends Game  {
    
    private Socket socket;
    private GameConcurrencyProtocol concurrencyProtocol;
    private ServerCommandBuilder commandBuilder;
    
    /**
     * Constructs the objects. Accordingly proper ip, port and password for the
     * server must be given. If the connection is declined by the server,
     * it a dialog with information will pop up.
     * @param ip
     * @param port
     * @param password
     */
    public ConnectedToServerGame(String ip, String port, String password) {
        super("Desert", DummyRespawner.class);
        mainPlayer = new MyPlayer();
        commandBuilder = new ServerCommandBuilder();
        try {
            socket = new Socket(ip, Integer.parseInt(port));
        } catch (UnknownHostException ex) {
            // TODO
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.UnknownHostException.getTitle(),
                    ExceptionHelper.UnknownHostException.getMessage());
            Global.getFrame().setScreen(
                    new MenuScreen(Global.getFrame().getWidth(),
                    Global.getFrame().getHeight()));
        } catch (IOException ex) {
            // TODO
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.UnknownHostException.getTitle(),
                    ExceptionHelper.UnknownHostException.getMessage());
            Global.getFrame().setScreen(
                    new MenuScreen(Global.getFrame().getWidth(),
                    Global.getFrame().getHeight()));
        }
        
        concurrencyProtocol = new GameConcurrencyProtocol(socket, commandBuilder);
        new Thread(concurrencyProtocol).start();
        concurrencyProtocol.updatePlayer(mainPlayer);
        addPlayer(mainPlayer);
        startGame();
    }

    /**
     * returns the game concurrency protocol.
     * @return
     */
    public GameConcurrencyProtocol getConcurrencyProtocol() {
        return concurrencyProtocol;
    }
    
}
