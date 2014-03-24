/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class PlayerMouseKeyBoardAction {

    private MyPlayer player;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private KeyListener keyListener;
    private HashSet<Integer> keysToProcess;
    private Point currentPoint;

    public PlayerMouseKeyBoardAction(MyPlayer player) {
        this.player = player;
        currentPoint = new Point(0, 0);
        this.mouseListener = new PlayerMouseListener();
        this.mouseMotionListener = new PlayerMouseMotionListener();
        this.keyListener = new PlayerKeyListener();
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    public void processMovement() {
        Vector2D direction = new Vector2D(0d, 0d);
        for (Integer key : keysToProcess) {
            if (key == KeyEvent.VK_LEFT) {
                direction.translate(-1d, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                direction.translate(1d, 0);
            }
            if (key == KeyEvent.VK_UP) {
                direction.translate(0, -1d);
            }
            if (key == KeyEvent.VK_DOWN) {
                direction.translate(0, 1d);
            }
        }
        direction = direction.getUnitVector();
        if (direction.getX() != 0 || direction.getY() != 0) {
            player.move(direction);
            processRotation(MouseInfo.getPointerInfo().getLocation());
        } else {
            player.stopMovement();
        }

    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void processRotation(Point p) {
        Vector2D mousePosition = new Vector2D(p);
        Vector2D playerPosition = new Vector2D(player.getBody().getCenter());
        double angle = MathUtil.getAngleBetweenPoints(mousePosition, playerPosition) + Math.PI / 2d;
        player.rotate(angle);
    }

    private class PlayerMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            // fix
        }

        @Override
        public void mousePressed(MouseEvent me) {
            Vector2D mousePosition = new Vector2D(me.getPoint());
            Vector2D playerPosition = new Vector2D(player.getBody().getCenter());
            double angle = MathUtil.getAngleBetweenPoints(mousePosition, playerPosition);
            Vector2D direction = new Vector2D(Math.cos(angle), Math.sin(angle));
            player.shoot(direction);
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            // not implemented
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            // not implemented
        }

        @Override
        public void mouseExited(MouseEvent me) {
            // not implemented
        }
    }

    private class PlayerMouseMotionListener implements MouseMotionListener {

        public PlayerMouseMotionListener() {
        }

        @Override
        public void mouseDragged(MouseEvent me) {
        }

        @Override
        public void mouseMoved(MouseEvent me) {

            currentPoint = me.getPoint();

        }
    }

    public class PlayerKeyListener implements KeyListener {

        public PlayerKeyListener() {
            keysToProcess = new HashSet<>();
        }

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            keysToProcess.add(ke.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            keysToProcess.remove(ke.getKeyCode());
        }
    }
}
