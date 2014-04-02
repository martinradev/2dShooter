/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.gui.screens.Screen;

/**
 *
 * @author Marto
 */
public class GameScreenMap extends JPanel {

    private GameEntityContainer gameEntities;
    private int width;
    private int height;

    /**
     *
     * @param gameEntities
     * @param width
     * @param height
     */
    public GameScreenMap(GameEntityContainer gameEntities, int width, int height) {
        super();
        this.setSize(width, height);
        this.gameEntities = gameEntities;
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     *
     * @return
     */
    public Vector2D getScreenOffset() {
        Vector2D offset = new Vector2D(0, 0);
        if (Global.getGame().getMainPlayer() != null) {
            Vector2D mainPlayerPosition = Global.getGame().getMainPlayer().getBody().getCenter();
            Screen scr = Global.getGame().getScreen();
            TiledMap map = Global.getGame().getMap();


            if (mainPlayerPosition.getX() > scr.getWidth() / 2) {
                offset.setX(mainPlayerPosition.getX() - scr.getWidth() / 2);
            }
            if (mainPlayerPosition.getY() > scr.getHeight() / 2) {
                offset.setY(mainPlayerPosition.getY() - scr.getHeight() / 2);
            }
            if (mainPlayerPosition.getX() > map.getWidth() - scr.getWidth() / 2) {
                offset.translate(-mainPlayerPosition.getX()
                        + (map.getWidth() - scr.getWidth() / 2), 0);
            }
            if (mainPlayerPosition.getY() > map.getHeight() - scr.getHeight() / 2) {
                offset.translate(0, -mainPlayerPosition.getY()
                        + (map.getHeight() - scr.getHeight() / 2));
            }
        }

        return offset;
    }

    /**
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        Vector2D offset = this.getScreenOffset();

        g2d.translate(-offset.getX(), -offset.getY());
        for (GraphicalObject go : gameEntities.getMapObjects()) {
            go.render(g2d, 0, 0);
        }

        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = ((Player) players.get(i));
            p.render(g2d, 0, 0, p.getAngleOffset());
        }

        List<Bullet> bullets = gameEntities.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = ((Bullet) bullets.get(i));
            bullet.render(g2d, 0, 0);
        }
        g2d.translate(offset.getX(), offset.getY());
    }
}
