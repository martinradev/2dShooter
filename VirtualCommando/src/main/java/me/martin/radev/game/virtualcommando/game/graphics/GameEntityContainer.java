/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics;

import java.util.LinkedList;
import java.util.List;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.structures.BinarySpaceTree;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GameEntityContainer {

    private List<GraphicalObject> players;
    private List<GraphicalObject> mapObjects;
    private List<GraphicalObject> respawnPoints;
    private List<Bullet> bullets;
    private BinarySpaceTree binarySpaceTree;

    /**
     *
     */
    public GameEntityContainer() {
        players = new LinkedList<>();
        mapObjects = new LinkedList<>();
        respawnPoints = new LinkedList<>();
        bullets = new LinkedList<>();
        binarySpaceTree = new BinarySpaceTree(
                (int)Global.getGame().getMap().getWidth(),
                (int)Global.getGame().getMap().getHeight(),
                Global.getFrame().getWidth(),
                Global.getFrame().getHeight()
                );
    }

    /**
     * adds a player to the entity container. If a player is added, then the
     * player will be displayed on the screen.
     * @param p
     */
    public void addPlayer(GraphicalObject p) {
        players.add(p);
        ((Player)p).setRespawnTime(0d);
    }

    /**
     * Adds a list of players to the game screen. The players will be rendered.
     * @param players
     */
    public void addAllPlayers(List<GraphicalObject> players) {
        this.players.addAll(players);
    }

    /**
     * Adds objects to the map. Those objects are static are static.
     * @param go
     */
    public void addMapObject(GraphicalObject go) {
        mapObjects.add(go);
        binarySpaceTree.addElement(go);
    }

    /**
     * Adds a list of objects to the map. Those objects are static. 
     * @param mapObjects
     */
    public void addAllMapObjects(List<GraphicalObject> mapObjects) {
        this.mapObjects.addAll(mapObjects);
        for (GraphicalObject go : mapObjects) {
            binarySpaceTree.addElement(go);
        }
    }

    /**
     * Adds a respawn point. Those points can be used by a 
     * {@link me.martin.radev.game.virtualcommando.game.logic.respawn.Respawner}
     * as points where to respawn
     * @param go
     */
    public void addRespawnPoint(GraphicalObject go) {
        respawnPoints.add(go);
    }

    /**
     * Adds a list of respawn points.
     * @param mapObjects
     */
    public void addAllRespawnPoints(List<GraphicalObject> mapObjects) {
        this.respawnPoints.addAll(mapObjects);
    }

    /**
     * Adds a bullet to the map.
     * @param b
     */
    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    /**
     * Adds a list of bullets to the map.
     * @param bullets
     */
    public void addAllBullets(List<Bullet> bullets) {
        this.bullets.addAll(bullets);
    }

    /**
     * return the list of bullets on the map
     * @return
     */
    public List<Bullet> getBullets() {
        return bullets;
    }

    /**
     * returns the static objects on the map
     * @return
     */
    public List<GraphicalObject> getMapObjects() {
        return mapObjects;
    }

    /**
     * return the players on the map
     * @return
     */
    public List<GraphicalObject> getPlayers() {
        return players;
    }

    /**
     * returnts the respawn points on the map
     * @return
     */
    public List<GraphicalObject> getRespawnPoints() {
        return respawnPoints;
    }

    /**
     * returns a bounding box for all objects on the map.
     * Use with care: it will go over all objects and 
     * it is time consuming and not thread save.
     * @return
     */
    public Vector2D[] getBoundingBox() {
        Vector2D[] boundingBox = new Vector2D[2];
        double minX = MathUtil.POSITIVE_INFINITY;
        double minY = MathUtil.POSITIVE_INFINITY;
        double maxX = MathUtil.NEGATIVE_INFINITY;
        double maxY = MathUtil.NEGATIVE_INFINITY;
        for (GraphicalObject go : this.getMapObjects()) {

            Vector2D[] tmpBox = go.getBody().getBoundingBox();

            if (minX > tmpBox[0].getX()) {
                minX = tmpBox[0].getX();
            }
            if (minY > tmpBox[0].getY()) {
                minY = tmpBox[0].getY();
            }
            if (maxX < tmpBox[1].getX()) {
                maxX = tmpBox[1].getX();
            }
            if (maxY < tmpBox[1].getY()) {
                maxY = tmpBox[1].getY();
            }


        }
        boundingBox[0] = new Vector2D(minX, minY);
        boundingBox[1] = new Vector2D(maxX, maxY);
        return boundingBox;
    }

    public BinarySpaceTree getBinarySpaceTree() {
        return binarySpaceTree;
    }
    
    
}
