/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.interaction.CollisionDetection;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GameFlow {

    private GameEntityContainer gameEntities;
    

    public GameFlow(GameEntityContainer gameEntities) {
        this.gameEntities = gameEntities;
        
    }

    public void processGameFlow() {
        this.processObjectMovement();
    }

    private void processObjectMovement() {
        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = (Player) players.get(i);
            p.processMovement();
            p.processRotation();
        }
        List<Bullet> bullets = gameEntities.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = bullets.get(i);
            bullet.move();
        }
    }
    
    private void processRespawning() {
        
    }

    public boolean isPlayerColliding(Player p) {
        return this.isColliding(gameEntities.getMapObjects(), p);
    }

    public boolean isBulletCollidingWithMap(Bullet b) {
        return this.isColliding(gameEntities.getMapObjects(), b.getObject());
    }

    public Player isBulletCollidingWithPlayer(Bullet b) {
        GeometricObject objBody = b.getObject().getBody();
        for (GraphicalObject go : gameEntities.getPlayers()) {
            if (b.getOwner() != go) {
                if (CollisionDetection.doCollide(go.getBody(), objBody)) {
                    System.out.println("bullet-player collision");
                    return (Player)go;
                }
            }
        }
        return null;
    }

    public boolean isColliding(List<GraphicalObject> objectList, GraphicalObject obj) {
        GeometricObject objBody = obj.getBody();
        for (GraphicalObject go : objectList) {
            if (CollisionDetection.doCollide(go.getBody(), objBody)) {
                return true;
            }
        }
        return false;
    }

    public void relativeTranslateAccordingToPlayer(Vector2D direction) {
        //Global.getGame().getScreen().relativeTranslate(direction);
        for (GraphicalObject go : gameEntities.getMapObjects()) {
            go.getBody().translate(direction.getX(), direction.getY());
        }
        for (Bullet b : gameEntities.getBullets()) {
            b.getObject().getBody().translate(direction.getX(), direction.getY());
        }
    }
    
    
    
}
