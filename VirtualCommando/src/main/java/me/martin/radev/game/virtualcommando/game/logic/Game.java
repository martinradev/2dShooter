/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.logic;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.map.MapInterface;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;

/**
 *
 * @author Marto
 */
public abstract class Game  {
    
    // TODO add Variable timestep
    
    private List<Player> players;
    private MapInterface map;
    private GameScreen screen;
    private Timer timer;
    
    public Game(String level) {
        players = new ArrayList<Player>();
        map = (MapInterface)Global.getAssetManager().load(AssetType.Map, level);
        screen = new GameScreen(map,Global.getWindowWidth(), Global.getWindowHeight());
        Global.getFrame().setScreen(screen);
        this.addPlayer(new MyPlayer());
        timer = new Timer();
        timer.schedule(new GameLoop(), 0, 1000 / Global.getFPS());
        
    }
    
    public void addPlayer(Player p) {
        players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    private class GameLoop extends TimerTask {

        @Override
        public void run() {
            render();
        }
        
    }
    
    private void render() {
        screen.repaint();
        
    }
    
    
}
