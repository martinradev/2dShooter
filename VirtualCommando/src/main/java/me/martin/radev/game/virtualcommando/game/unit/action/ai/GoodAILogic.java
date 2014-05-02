/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.unit.action.ai.heuristics.ClosestPlayerHeuristic;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.structures.Graph;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class GoodAILogic implements AILogic {

    private Player player;
    private Graph graph;
    private GraphicalObject currentNode;
    private GraphicalObject nextNode;
    private Random rand;
    private ClosestPlayerHeuristic playerHeuristic;
    private Player closestPlayer;

    /**
     *
     * @param player
     * @param graph
     */
    public GoodAILogic(Player player, Graph graph) {
        this.player = player;
        this.graph = graph;
        currentNode = null;
        nextNode = null;
        rand = new Random();
        playerHeuristic = new ClosestPlayerHeuristic();
    }

    @Override
    public Vector2D getDirection() {
        if (currentNode == null) {
            Object obj = graph.getNodeCopy(player);
            if (obj == null) {
                return null;
            }
            currentNode = (GraphicalObject) obj;
            nextNode = currentNode;

        }

        if (nextNode == null || nodesEqual()) {
            GraphicalObject next = getNextNode();
            if (nextNode != null) {
                currentNode = nextNode;
            }
            nextNode = next;
        }
        Vector2D direction = new Vector2D(nextNode.getBody().getCenter());
        direction.translate(-player.getBody().getCenter().getX(),
                -player.getBody().getCenter().getY());
        return direction.getUnitVector();
    }

    private boolean nodesEqual() {
        return MathUtil.distance(
                player.getBody().getCenter(), nextNode.getBody().getCenter()) <= 7d;
    }

    private GraphicalObject getNextNode() {
        ArrayList<GraphicalObject> nbList = graph.getNeighbours(nextNode);
        int index = rand.nextInt(nbList.size());
        return nbList.get(index);
    }

    @Override
    public float getRotationAngle() {
        if (currentNode == null || nextNode == null) {
            return 0;
        }
        if (closestPlayer != null) {
            return (float) (MathUtil.getAngleBetweenPoints(player.getBody().getCenter(),
                    closestPlayer.getBody().getCenter()) - Math.PI / 2d);
        }
        return (float) (MathUtil.getAngleBetweenPoints(player.getBody().getCenter(),
                nextNode.getBody().getCenter()) - Math.PI / 2d);
    }

    @Override
    public boolean shouldShoot() {
        Player p = playerHeuristic.getClosestPlayer(player);
        double distance = MathUtil.distance(p.getBody().getCenter(),
                player.getBody().getCenter());
        float num = rand.nextFloat();
        if (distance <= 200d && num > 0.98999) {
            closestPlayer = p;
            return true;
        }
        return false;
    }

    @Override
    public Vector2D directionOfShooting(Player player) {
        Vector2D direction = new Vector2D(player.getBody().getCenter());
        direction.translate(-closestPlayer.getBody().getCenter().getX(), -closestPlayer.getBody().getCenter().getY());
        direction.scale(-1d);
        return direction.getUnitVector();
    }

    /**
     *
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    public void render(Graphics2D g2d, int xOffset, int yOffset) {

        g2d.setStroke(new BasicStroke(2));
        if (nextNode != null) {
            g2d.setColor(Color.blue);
            g2d.drawLine((int) player.getBody().getCenter().getX(),
                    (int) player.getBody().getCenter().getY(),
                    (int) nextNode.getBody().getCenter().getX(),
                    (int) nextNode.getBody().getCenter().getY());
        }
        if (currentNode != null) {
            g2d.setColor(Color.black);
            g2d.drawLine(
                    (int) currentNode.getBody().getCenter().getX(),
                    (int) currentNode.getBody().getCenter().getY(),
                    (int) player.getBody().getCenter().getX(),
                    (int) player.getBody().getCenter().getY());
        }
        g2d.setStroke(new BasicStroke(1));
    }

    /**
     *
     * @param currentNode
     */
    public void setCurrentNode(GraphicalObject currentNode) {
        this.currentNode = currentNode;
    }
}
