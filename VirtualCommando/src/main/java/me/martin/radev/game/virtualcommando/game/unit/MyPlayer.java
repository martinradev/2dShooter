/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import javax.swing.KeyStroke;
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
                new Vector2D(700,400), PlayerType.NormalPlayer.getWidth(),
                PlayerType.NormalPlayer.getHeight(),
                new Color(1f,0f,0f,.0f ));
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
    
}
