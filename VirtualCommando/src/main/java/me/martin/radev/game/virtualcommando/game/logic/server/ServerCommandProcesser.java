/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.server;

import me.martin.radev.game.virtualcommando.game.logic.server.protocols.ServerPlayerProtocol;

/**
 *
 * @author Marto
 */
public class ServerCommandProcesser {
    
    private ServerPlayerProtocol protocol;
    
    public ServerCommandProcesser(ServerPlayerProtocol spp) {
        this.protocol = spp;
    }
    
    public void process() {
        
    }
    
}
