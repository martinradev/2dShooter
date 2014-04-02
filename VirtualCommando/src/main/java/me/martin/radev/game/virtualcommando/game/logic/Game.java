/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.util.HashMap;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.Updatable;
import me.martin.radev.game.virtualcommando.game.logic.respawn.RandomRespawner;
import me.martin.radev.game.virtualcommando.game.logic.respawn.Respawner;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;

/**
 *
 * @author Marto
 */
public abstract class Game {

    private TiledMap map;
    private GameEntityContainer gameEntities;
    private GameScreen screen;
    private Timer timer;
    private GameLoop loop;
    private List<Updatable> toUpdate;
    private String mapName;
    private GameFlow gameFlow;
    /**
     *
     */
    protected Respawner respawner;
    /**
     *
     */
    protected MyPlayer mainPlayer;
    /**
     *
     */
    protected Map<String, Player> players;

    /**
     *
     * @param mapName
     */
    public Game(String mapName) {
        this.mapName = mapName;
        Global.setGame(this);
        init();
    }

    private void init() {
        players = new HashMap<>();
        gameEntities = new GameEntityContainer();
        map = (TiledMap) Global.getAssetManager().load(AssetType.Map, mapName);
        gameEntities.addAllMapObjects(map.getObjects());
        gameEntities.addAllRespawnPoints(map.getRespawnPoints());
        respawner = new RandomRespawner(map.getRespawnPoints());
        gameFlow = new GameFlow(gameEntities, respawner);
        Global.setGameFlow(gameFlow);
        
        toUpdate = new LinkedList<>();

        screen = new GameScreen(gameEntities, Global.getWindowWidth(), Global.getWindowHeight());
        Global.getFrame().setScreen(screen);
        
    }
    
    /**
     *
     */
    public void startGame() {
        timer = new Timer();
        loop = new GameLoop();
        timer.schedule(loop, 0, 1000 / Global.getFPS());
    }
    
    /**
     *
     * @param p
     */
    public void addPlayer(Player p) {
        if (p.getRespawnTime() >= respawner.getTimeTillRespawn()) {
            gameEntities.addPlayer(p);
        } else {
            respawner.addPlayer(p);
        }
        players.put(p.getName(), p);
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

    /**
     *
     * @param upd
     */
    public void bind(Updatable upd) {
        this.toUpdate.add(upd);
    }

    private void update() {
        for (Updatable upd : toUpdate) {
            upd.update();
        }
    }

    /**
     *
     * @return
     */
    public GameScreen getScreen() {
        return screen;
    }

    /**
     *
     * @return
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     *
     * @return
     */
    public GameEntityContainer getGameEntities() {
        return gameEntities;
    }

    /**
     *
     * @return
     */
    public Respawner getRespawner() {
        return respawner;
    }

    /**
     *
     * @return
     */
    public MyPlayer getMainPlayer() {
        return mainPlayer;
    }

    /**
     *
     * @return
     */
    public Map<String, Player> getPlayers() {
        return players;
    }
    
    /**
     *
     * @param p
     */
    public void removePlayer(Player p) {
        players.remove(p.getName());
        gameEntities.getPlayers().remove(p);
        respawner.removePlayer(p);
    }

    /**
     *
     * @return
     */
    public String getMapName() {
        return mapName;
    }
    
    
    
}
