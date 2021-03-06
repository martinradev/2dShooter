/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server.protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;

/**
 *
 * @author Marto
 */
public abstract class ServerProtocol implements Runnable {
    
    private Socket connection;
    private PrintWriter output;
    private BufferedReader input;
    private List<String> commands;
    private boolean connected;
    
    /**
     * Creates a server protocol with a given connection socket.
     * @param connection
     */
    public ServerProtocol(Socket connection) {
        this.connection = connection;
        try {
            output = new PrintWriter(connection.getOutputStream(), true);
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
        try {
            input = new BufferedReader(
                    new InputStreamReader(
                    connection.getInputStream()));
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
        commands = new LinkedList<>();
        connected = true;
    }
    
    /**
     * sends a command to the socket
     * @param command
     */
    public void sendCommand(String command) {
        output.println(command);
        System.out.println(command);
    }

    /**
     * reads a command from the socket
     * @return
     */
    public String readCommand() {
        try {
            String line = input.readLine();
            System.out.println(line);
            return line;
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.UnknownHostException.getTitle(),
                    ExceptionHelper.UnknownHostException.getMessage());
            this.connected = false;
            output.close();
            System.out.println("closed error");
        }
        return null;
    }

    /**
     *
     */
    @Override
    public void run() {
        String inputLine = null;
        while ((inputLine = this.readCommand()) != null) {
            if (inputLine.equals("disconnect")) {
                break;
            }
            processCommand(inputLine);
        }
        this.connected = false;
        disconnect();
    }
    
    /**
     *
     * @param command
     */
    protected abstract void processCommand(String command);
    
    /**
     * checks whether the socket is connected.
     * @return
     */
    public boolean isConnected() {
        return connected && connection.isConnected();
    }
    
    /**
     * disconnects the socket
     */
    public void disconnect() {
        try {
            connection.close();
            output.close();
            input.close();
        } catch (IOException ex) {
            
        }
    }
    
}
