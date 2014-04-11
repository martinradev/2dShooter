/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.util.Collection;
import java.util.List;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.interaction.CollisionDetection;
import me.martin.radev.game.virtualcommando.game.logic.respawn.Respawner;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GameFlow {

    private GameEntityContainer gameEntities;
    private Respawner respawnerLogic;
    
    /**
     * Creates a GameFlow object. The GameFlow objects is responsible for
     * the flow of the game, meaning that it is the object which is responsible
     * for calling the movement of objects and calling other flow methods.
     * @param gameEntities
     * @param respawner
     */
    public GameFlow(GameEntityContainer gameEntities, Respawner respawner) {
        this.gameEntities = gameEntities;
        this.respawnerLogic = respawner;
    }

    /**
     * Processes the game flow like moving of objects, respawning, etc
     */
    public void processGameFlow() {
        this.processObjectMovement();
        respawnerLogic.processRespawnQueue();
    }

    private void processObjectMovement() {
        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = (Player) players.get(i);
            p.processMovement();
            p.processRotation();
            p.processShooting();
        }
        List<Bullet> bullets = gameEntities.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = bullets.get(i);
            bullet.move();
        }
    }
    
    /**
     * Checks whether player is colliding
     * @param p
     * @return
     */
    public boolean isPlayerColliding(Player p) {
        return this.isColliding(
                gameEntities.getBinarySpaceTree().getObjectsInClosingArea(p), p);
    }

    /**
     * Checks whether bullet is colliding with the static objects on the map
     * @param b
     * @return
     */
    public boolean isBulletCollidingWithMap(Bullet b) {
        return this.isColliding(
                gameEntities.getBinarySpaceTree().getObjectsInClosingArea(b.getObject()), b.getObject());
    }

    /**
     * Checks whether a bullet is colliding with a player.
     * @param b
     * @return
     */
    public Player isBulletCollidingWithPlayer(Bullet b) {
        GeometricObject objBody = b.getObject().getBody();
        for (GraphicalObject go : gameEntities.getPlayers()) {
            if (!((Player)go).isPlayerActive()) continue;
            if (b.getOwner() != go) {
                if (CollisionDetection.doCollide(go.getBody(), objBody)) {
                    return (Player)go;
                }
            }
        }
        return null;
    }

    /**
     * Checks whether a graphical object is colliding with a list of graphical objects
     * @param objectList
     * @param obj
     * @return
     */
    public boolean isColliding(Collection<GraphicalObject> objectList, GraphicalObject obj) {
        GeometricObject objBody = obj.getBody();
        for (GraphicalObject go : objectList) {
            if (CollisionDetection.doCollide(go.getBody(), objBody)) {
                return true;
            }
        }
        return false;
    }
    
    
}
