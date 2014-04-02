/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
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
    private final double DEFAULT_VISION_RANGE = 285d;
    private int mask = 0;
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
        for (int i = 1; i<= 32; ++i) {
            if (i%2==0) {
                mask |= (1<<i);
            }
        }
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
        int width = this.getParent().getWidth();
        int height = this.getParent().getHeight();
        BufferedImage image = 
                new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setBackground(Color.white);
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        Vector2D offset = new Vector2D(this.getScreenOffset());

        imageGraphics.translate(-offset.getX(), -offset.getY());
        for (GraphicalObject go : gameEntities.getMapObjects()) {
            go.render(imageGraphics, 0, 0);
        }

        Player mainPlayer = Global.getGame().getMainPlayer();
        List<GraphicalObject> players = gameEntities.getPlayers();
        for (int i = 0; i < players.size(); ++i) {
            Player p = ((Player) players.get(i));
            if (this.canSee(mainPlayer, p)) {
                p.render(imageGraphics, 0, 0, p.getAngleOffset());
            }
        }

        List<Bullet> bullets = gameEntities.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            Bullet bullet = ((Bullet) bullets.get(i));
            if (this.canSee(mainPlayer, bullet.getObject())) {
                bullet.render(imageGraphics, 0, 0);
            }
        }
        imageGraphics.translate(offset.getX(), offset.getY());
        imageGraphics.dispose();
        offset.setX(mainPlayer.getBody().getCenter().getX() - offset.getX());
        offset.setY(mainPlayer.getBody().getCenter().getY() - offset.getY());
        drawFogOfWar(image, offset);
        g2d.drawImage(image, null, this);
    }
    
    private void drawFogOfWar(BufferedImage img, Vector2D playerPosition) {
        Vector2D tmp = new Vector2D(0,0);
        for (int i = 0; i < img.getWidth(); ++i) {
            for (int j = 0; j < img.getHeight(); ++j) {
                tmp.setX(i);
                tmp.setY(j);
                if (MathUtil.distance(tmp, playerPosition) > DEFAULT_VISION_RANGE) {
                    img.setRGB(i, j, img.getRGB(i, j)&mask);
                }
            }
        }
    }

    private boolean canSee(GraphicalObject a, GraphicalObject b) {
        double distance = MathUtil.distance(a.getBody().getCenter(), b.getBody().getCenter());
        return distance <= DEFAULT_VISION_RANGE;
    }
    
}
