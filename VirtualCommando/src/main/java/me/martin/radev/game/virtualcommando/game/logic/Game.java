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
import me.martin.radev.game.virtualcommando.game.logic.respawn.RandomRespawner;
import me.martin.radev.game.virtualcommando.game.logic.respawn.Respawner;
import me.martin.radev.game.virtualcommando.game.unit.Bot;
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
    protected Respawner respawner;
    protected MyPlayer mainPlayer;

    public Game(String level) {
        this.level = level;
        Global.setGame(this);
        init();
    }

    private void init() {
        gameEntities = new GameEntityContainer();
        map = (TiledMap) Global.getAssetManager().load(AssetType.Map, level);
        gameEntities.addAllMapObjects(map.getObjects());
        gameEntities.addAllRespawnPoints(map.getRespawnPoints());
        respawner = new RandomRespawner(map.getRespawnPoints());
        gameFlow = new GameFlow(gameEntities, respawner);
        Global.setGameFlow(gameFlow);
        
        toUpdate = new LinkedList<>();

        screen = new GameScreen(gameEntities, Global.getWindowWidth(), Global.getWindowHeight());
        Global.getFrame().setScreen(screen);
        
        mainPlayer = new MyPlayer();
        respawner.addPlayer(mainPlayer);
        
    }
    
    public void startGame() {
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

    public Respawner getRespawner() {
        return respawner;
    }

    public MyPlayer getMainPlayer() {
        return mainPlayer;
    }
    
     
    
}
