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
import me.martin.radev.game.virtualcommando.game.logic.server.ServerCommandBuilder;
import me.martin.radev.game.virtualcommando.game.logic.server.protocols.GameConcurrencyProtocol;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;

/**
 *
 * @author Marto
 */
public class ConnectedToServerGame extends Game  {
    
    private Socket socket;
    private GameConcurrencyProtocol concurrencyProtocol;
    private ServerCommandBuilder commandBuilder;
    
    /**
     *
     * @param ip
     * @param port
     * @param password
     */
    public ConnectedToServerGame(String ip, String port, String password) {
        super("Desert");
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
        addPlayer(mainPlayer);
        startGame();
    }

    /**
     *
     * @return
     */
    public GameConcurrencyProtocol getConcurrencyProtocol() {
        return concurrencyProtocol;
    }
    
}
