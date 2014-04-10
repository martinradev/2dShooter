/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import me.martin.radev.game.virtualcommando.game.graphics.GameEntityContainer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.Updatable;
import me.martin.radev.game.virtualcommando.game.logic.respawn.Respawner;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;

/**
 * A game class. When created it also initialize helpful classes like
 * game flow classes, etc. The class is abstract and it can be wrapped
 * by {@link SinglePlayerGame}, {@link MultiPlayerGame}, etc
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
    private Class respawnerType;
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
     * @param respawnerType  
     */
    public Game(String mapName, Class respawnerType) {
        this.mapName = mapName;
        this.respawnerType = respawnerType;
        Global.setGame(this);
        init();
    }

    private void init() {
        players = new HashMap<>();
        map = (TiledMap) Global.getAssetManager().load(AssetType.Map, mapName);
        gameEntities = new GameEntityContainer();
        gameEntities.addAllMapObjects(map.getObjects());
        gameEntities.addAllRespawnPoints(map.getRespawnPoints());
        initRespawner();
        gameFlow = new GameFlow(gameEntities, respawner);
        Global.setGameFlow(gameFlow);
        
        toUpdate = new LinkedList<>();

        screen = new GameScreen(gameEntities, Global.getWindowWidth(), Global.getWindowHeight());
        Global.getFrame().setScreen(screen);
        
    }
    
    private void initRespawner() {
        Constructor<?> respawnConstructor = null;
        try {
            respawnConstructor = respawnerType.getConstructor(map.getRespawnPoints().getClass());
        } catch (NoSuchMethodException | SecurityException ex) {
            // todo show error and back to menu screen
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            respawner = (Respawner) respawnConstructor.newInstance(map.getRespawnPoints());
        } catch (InstantiationException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Starts the game. If the game is not started, the game is not rendered.
     */
    public void startGame() {
        timer = new Timer();
        loop = new GameLoop();
        timer.schedule(loop, 0, 1000 / Global.getFPS());
    }
    
    /**
     * Adds a player to the game. The player is processed accordingly:
     * if player needs to be respawned, then he is added to the respawner
     * if not, then the player is added to the game entity container to be rendered
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
     * binds {@link Updatable} classes. Those classes will be updated by 
     * calling the method {@link Updatable#update()}.
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
     * Returns the current map the game is rendering
     * @return
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * returns the {@link GameEntityContainer} class which contains
     * all game entities which are rendered.
     * @return
     */
    public GameEntityContainer getGameEntities() {
        return gameEntities;
    }

    /**
     * returns the current used respawner in the game
     * @return
     */
    public Respawner getRespawner() {
        return respawner;
    }

    /**
     * returns the {@link MyPlayer}. {@link MyPlayer} is the main player in the game
     * by your perspective meaning that this is the player you control by your input device.
     * @return
     */
    public MyPlayer getMainPlayer() {
        return mainPlayer;
    }

    /**
     * return a {@link Map} of all the players in the game.
     * @return
     */
    public Map<String, Player> getPlayers() {
        return players;
    }
    
    /**
     * Removes a player from the game. 
     * @param p
     */
    public void removePlayer(Player p) {
        players.remove(p.getName());
        gameEntities.getPlayers().remove(p);
        respawner.removePlayer(p);
    }

    /**
     * returns the name of the map
     * @return
     */
    public String getMapName() {
        return mapName;
    }
    
    
    
}
