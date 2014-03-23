/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.action.PlayerMouseKeyBoardAction;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MyPlayer extends Player {

    private PlayerMouseKeyBoardAction actionListener;

    public MyPlayer() {
        super(PlayerType.NormalPlayer.getMaxHealth(),
                new Vector2D(1200, 1000), PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f, 0f, 0f, .0f));
        init();
    }

    private void init() {
        actionListener = new PlayerMouseKeyBoardAction(this);
        Global.getFrame().getScreen().addMouseListener(actionListener.getMouseListener());
        Global.getFrame().getScreen().addKeyListener(actionListener.getKeyListener());
        Global.getFrame().getScreen().addMouseMotionListener(actionListener.getMouseMotionListener());
    }

    @Override
    public void processMovement() {
        actionListener.processMovement();
    }

    @Override
    public void processRotation() {
        actionListener.processRotation(actionListener.getCurrentPoint());
    }

    @Override
    public void move(Vector2D direction) {
        Vector2D xDirection = new Vector2D(direction.getX(), 0);
        Vector2D yDirection = new Vector2D(0, direction.getY());
        xDirection.scale(-velocity);
        yDirection.scale(-velocity);
        
        Vector2D playerOffset = Global.getPlayerOffset();
        
        
        double mapWidth = Global.getGame().getMap().getWidth();
        double mapHeight = Global.getGame().getMap().getHeight();
        double mapDiapazoneX =  mapWidth - playerOffset.getX();
        double mapDiapazoneY = mapHeight - playerOffset.getY();
        double screenWidth = Global.getGame().getScreen().getWidth();
        double screenHeight = Global.getGame().getScreen().getHeight();

        if (mapDiapazoneX <= screenWidth / 2 || 
                 mapWidth - mapDiapazoneX <= screenWidth / 2) {
            this.getBody().translate(-xDirection.getX(), 0);
            if (Global.getGameFlow().isPlayerColliding(this)) {
                this.getBody().translate(xDirection.getX(), 0);
            } else {
                playerOffset.translate(-xDirection.getX(), 0);
                super.setSprite(walkAnimation.getCurrent());
            }
        } else {
            Global.getGameFlow().relativeTranslateAccordingToPlayer(xDirection);
            if (Global.getGameFlow().isPlayerColliding(this)) {
                xDirection.scale(-1d);
                Global.getGameFlow().relativeTranslateAccordingToPlayer(xDirection);
            } else {
                playerOffset.translate(-xDirection.getX(), 0);
                super.setSprite(walkAnimation.getCurrent());
            }
        }
        
        if (mapDiapazoneY <= screenHeight / 2 || 
               mapHeight - mapDiapazoneY  <= screenHeight / 2 ) {
            this.getBody().translate(0, -yDirection.getY());
            if (Global.getGameFlow().isPlayerColliding(this)) {
                this.getBody().translate(0, yDirection.getY());
            } else {
                playerOffset.translate(0, -yDirection.getY());
                super.setSprite(walkAnimation.getCurrent());
            }
        } else {
            Global.getGameFlow().relativeTranslateAccordingToPlayer(yDirection);
            if (Global.getGameFlow().isPlayerColliding(this)) {
                yDirection.scale(-1d);
                Global.getGameFlow().relativeTranslateAccordingToPlayer(yDirection);
            } else {
                playerOffset.translate(0, -yDirection.getY());
                super.setSprite(walkAnimation.getCurrent());
            }
        }
        
        System.out.println(playerOffset);
    }

}
