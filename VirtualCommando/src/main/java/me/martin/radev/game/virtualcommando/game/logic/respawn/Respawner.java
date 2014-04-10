/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic.respawn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.ConnectedToServerGame;
import me.martin.radev.game.virtualcommando.game.logic.MultiPlayerGame;
import me.martin.radev.game.virtualcommando.game.logic.SinglePlayerGame;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.ServerPlayer;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public abstract class Respawner {

    /**
     *
     */
    protected List<GraphicalObject> respawnPlaces;
    private List<Player> playersToRespawn;
    private double timeTillRespawn = 2d;

    /**
     *
     * @param respawnPlaces
     */
    public Respawner(ArrayList<GraphicalObject> respawnPlaces) {
        this.respawnPlaces = respawnPlaces;
        playersToRespawn = new LinkedList<>();
    }

    /**
     * An abstract method for returning a position.
     *
     * @return
     */
    public abstract Vector2D getPosition();

    /**
     * Given a respawn queue we update the time left to respawn and accordingly
     * respawn the player
     */
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

    /**
     *
     * @param p
     */
    protected void respawn(Player p) {
        playersToRespawn.remove(p);

        Vector2D position = getPosition();


        p.getBody().translate(-p.getBody().getCenter().getX(),
                -p.getBody().getCenter().getY());
        p.getBody().translate(position.getX(), position.getY());
        p.regenerateFully();
        Global.getGame().getGameEntities().getPlayers().add(p);

        if (Global.getGame().getClass() == MultiPlayerGame.class) {
            String command = ((MultiPlayerGame) Global.getGame()).
                    getServer().getCommandBuilder().getRespawnPlayerCommand(p);
            ((MultiPlayerGame) Global.getGame()).
                    getServer().getServerSync().respawnPlayer(p);
        }

    }

    /**
     * adds a player to the respawn list
     *
     * @param p
     */
    public void addPlayer(Player p) {
        playersToRespawn.add(p);
        p.setRespawnTime(0d);
    }

    /**
     * removes a {@link Player} from the respawn list
     *
     * @param p
     */
    public void removePlayer(Player p) {
        playersToRespawn.remove(p);
    }

    /**
     * returns the pre-defined time till the respawn. Every player must wait
     * that time in order to respawn
     *
     * @return
     */
    public double getTimeTillRespawn() {
        return timeTillRespawn;
    }
}
