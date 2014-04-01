/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.respawn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class Respawner {

    protected List<GraphicalObject> respawnPlaces;
    private List<Player> playersToRespawn;
    private double timeTillRespawn = 2d;

    public Respawner(List<GraphicalObject> respawnPlaces) {
        this.respawnPlaces = respawnPlaces;
        playersToRespawn = new LinkedList<>();
    }

    public abstract Vector2D getPosition();

    public void processRespawnQueue() {
        double deltaTime = 1d / Global.getFPS();
        LinkedList<Player> copy = new LinkedList<>(playersToRespawn);
        for (Player p : copy) {
            p.setRespawnTime(p.getRespawnTime() + deltaTime);
            if (p.getRespawnTime() >= timeTillRespawn) {
                this.respawn(p);
            }
        }
    }

    private void respawn(Player p) {
        playersToRespawn.remove(p);
        p.setRespawnTime(0d);
        Vector2D position = getPosition();


        p.getBody().translate(-p.getBody().getCenter().getX(),
                -p.getBody().getCenter().getY());
        p.getBody().translate(position.getX(), position.getY());

        p.regenerateFully();
        Global.getGame().getGameEntities().getPlayers().add(p);
    }

    public void addPlayer(Player p) {
        playersToRespawn.add(p);
    }
    
    public void removePlayer(Player p) {
        playersToRespawn.remove(p);
    }

    public double getTimeTillRespawn() {
        return timeTillRespawn;
    }
    
    
    
}
