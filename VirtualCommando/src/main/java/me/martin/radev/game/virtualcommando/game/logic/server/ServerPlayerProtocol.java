/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class ServerPlayerProtocol implements Runnable {

    private Socket connection;
    private PrintWriter output;
    private BufferedReader input;
    private List<String> commands;
    private Queue<Vector2D> shootings;
    private Queue<Vector2D> movement;
    private Queue<Double> rotations;

    public ServerPlayerProtocol(Socket connection) {
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
        shootings = new LinkedList<>();
        movement = new LinkedList<>();
        rotations = new LinkedList<>();
    }

    public void sendCommand(String command) {
        output.println(command);
    }

    public String readCommand() {
        try {
            return input.readLine();
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(
                    ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
        return null;
    }

    @Override
    public void run() {
        String inputLine = null;
        while ((inputLine = this.readCommand()) != null) {
            if (inputLine.equals("disconnect")) {
                break;
            }
            processCommand(inputLine);
        }
    }
    
    private void processCommand(String command) {
        String [] tokens = command.split(" ");
        if (tokens[0].equals("S")) {
            Vector2D direction = new Vector2D(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]));
            shootings.add(direction);
        } else if (tokens[0].equals("M")) {
            Vector2D direction = new Vector2D(
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]));
            movement.add(direction);
        } else if (tokens[0].equals("R")) {
            double angle = Double.parseDouble(tokens[1]);
            rotations.add(angle);
        }
    }

    public Queue<Vector2D> getMovement() {
        return movement;
    }

    public Queue<Vector2D> getShootings() {
        return shootings;
    }

    public Queue<Double> getRotations() {
        return rotations;
    }
    
    
    
}
