/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.Updatable;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;

/**
 *
 * @author Marto
 */
public abstract class Game {

    // TODO add Variable timestep
    private TiledMap map;
    private GameEntityContainer gameEntities;
    private GameScreen screen;
    private Timer timer;
    private GameLoop loop;
    private List<Updatable> toUpdate;
    private String level;
    private GameFlow gameFlow;

    public Game(String level) {
        this.level = level;
    }

    public void init() {
        gameEntities = new GameEntityContainer();
        gameFlow = new GameFlow(gameEntities);
        Global.setGameFlow(gameFlow);
        map = (TiledMap) Global.getAssetManager().load(AssetType.Map, level);
        gameEntities.addAllMapObjects(map.getObjects());
        toUpdate = new LinkedList<>();

        screen = new GameScreen(gameEntities, Global.getWindowWidth(), Global.getWindowHeight());
        Global.getFrame().setScreen(screen);

        MyPlayer mp = new MyPlayer();
        this.addPlayer(mp);
        Global.setPlayerOffset(new Vector2D(mp.getBody().getCenter()));

        double offsetX = screen.getWidth() / 2 - mp.getBody().getCenter().getX();
        double offsetY = screen.getHeight() / 2 - mp.getBody().getCenter().getY();
        mp.getBody().translate(offsetX, offsetY);
        gameFlow.relativeTranslateAccordingToPlayer(new Vector2D(offsetX, offsetY));
        

        timer = new Timer();
        loop = new GameLoop();
        timer.schedule(loop, 0, 1000 / Global.getFPS());
    }

    public void addPlayer(Player p) {
        gameEntities.addPlayer(p);
    }

    private class GameLoop extends TimerTask {

        @Override
        public void run() {
            gameFlow.processGameFlow();
            update();
            render();
        }
    }

    private void render() {
        screen.repaint();
    }

    public void bind(Updatable upd) {
        this.toUpdate.add(upd);
    }

    private void update() {
        for (Updatable upd : toUpdate) {
            upd.update();
        }
    }

    public GameScreen getScreen() {
        return screen;
    }

    public TiledMap getMap() {
        return map;
    }

    public GameEntityContainer getGameEntities() {
        return gameEntities;
    }
    
     
    
}
