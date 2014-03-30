/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import java.util.List;
import java.util.Queue;
import me.martin.radev.game.virtualcommando.game.logic.server.ServerPlayerProtocol;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class ServerPlayer extends Player {

    private ServerPlayerProtocol protocol;
    
    public ServerPlayer(ServerPlayerProtocol protocol) {
        super(PlayerType.NormalPlayer.getMaxHealth(),
                new Vector2D(100d, 100d), PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        this.protocol = protocol;
    }
    
    @Override
    public void processMovement() {
        Queue<Vector2D> directions = protocol.getMovement();
        if (directions.isEmpty()) stopMovement();
        while (!directions.isEmpty()) {
            Vector2D direction = directions.poll();
            move(direction);
        }
    }

    @Override
    public void processRotation() {
        Queue<Double> angles = protocol.getRotations();
        while (!angles.isEmpty()) {
            double angle = angles.poll();
            rotate(angle);
        }
    }

    @Override
    public void processShooting() {
        Queue<Vector2D> directions = protocol.getShootings();
        while (!directions.isEmpty()) {
            Vector2D direction = directions.poll();
            shoot(direction);
        }
    }
    
}
