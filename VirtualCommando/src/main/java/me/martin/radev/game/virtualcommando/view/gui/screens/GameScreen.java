/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.game.unit.Player;
/**
 *
 * @author Marto
 */
public class GameScreen extends Screen {

    private GameEntityContainer gameEntities;
    private int offsetX;
    private int offsetY;

    public GameScreen(GameEntityContainer gameEntities, int width, int height) {
        super();
        this.setSize(width, height);
        this.gameEntities = gameEntities;
        this.offsetX = 0;
        this.offsetY = 0;
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        for (GraphicalObject go : gameEntities.getMapObjects()) {
            go.render(g2d, offsetX, offsetX);
        }
        
        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = ((Player)players.get(i));
            p.render(g2d, 0, 0, p.getAngleOffset());
        }
    }
}
