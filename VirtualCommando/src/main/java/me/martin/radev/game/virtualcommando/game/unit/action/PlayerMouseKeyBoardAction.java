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
import java.util.Set;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.structures.ConcurrentHashSet;

/**
 *
 * @author Marto
 */
public class PlayerMouseKeyBoardAction {

    private MyPlayer player;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    private KeyListener keyListener;
    private ConcurrentHashSet<Integer> keysToProcess;
    private Point currentPoint;

    /**
     *
     * @param player
     */
    public PlayerMouseKeyBoardAction(MyPlayer player) {
        this.player = player;
        currentPoint = new Point(0, 0);
        this.mouseListener = new PlayerMouseListener();
        this.mouseMotionListener = new PlayerMouseMotionListener();
        this.keyListener = new PlayerKeyListener();
        Global.getFrame().addKeyListener(keyListener);
        Global.getFrame().requestFocus();
    }

    /**
     *
     * @return
     */
    public Set<Integer> getKeysToProcess() {
        return keysToProcess.getHashSet();
    }

    /**
     *
     * @return
     */
    public KeyListener getKeyListener() {
        return keyListener;
    }

    /**
     *
     * @return
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }

    /**
     *
     * @return
     */
    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }

    /**
     *
     * @return
     */
    public Point getCurrentPoint() {
        return currentPoint;
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
            Vector2D offset = Global.getGame().getScreen().getGameScreenMap().getScreenOffset();
            mousePosition.translate(offset.getX(), offset.getY());
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

    /**
     *
     */
    public class PlayerKeyListener implements KeyListener {

        /**
         *
         */
        public PlayerKeyListener() {
            keysToProcess = new ConcurrentHashSet<Integer>();
        }

        /**
         *
         * @param ke
         */
        @Override
        public void keyTyped(KeyEvent ke) {
        }

        /**
         *
         * @param ke
         */
        @Override
        public void keyPressed(KeyEvent ke) {
            keysToProcess.add(ke.getKeyCode());
        }

        /**
         *
         * @param ke
         */
        @Override
        public void keyReleased(KeyEvent ke) {
            keysToProcess.remove(ke.getKeyCode());
        }
    }
    
    
}
