/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.game.logic.server.protocols.ServerPlayerProtocol;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class ServerPlayer extends Player {
    
    private ServerPlayerProtocol protocol;
    
    /**
     * A player which is added on the server side. This player contains the server protocol.
     * @param name
     * @param position
     * @param protocol
     */
    public ServerPlayer(String name, Vector2D position,
            ServerPlayerProtocol protocol) {
        super(name, PlayerType.NormalPlayer.getMaxHealth(),
                position, PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        this.protocol = protocol;
    }

    /**
     *
     */
    @Override
    public void processMovement() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void processRotation() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void processShooting() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * returns the protocol
     * @return
     */
    public ServerPlayerProtocol getProtocol() {
        return protocol;
    }
    
}
