/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.game.logic.Game;
import me.martin.radev.game.virtualcommando.game.logic.GameFlow;
import me.martin.radev.game.virtualcommando.view.gui.GameView;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;

/**
 *
 * @author Marto
 */
public class Global {
    
    private static AssetManager assetManager;
    private static ExceptionHandler exceptionHandler;
    private static int defaultWindowWidth;
    private static int defaultWindowHeight;
    private static int windowWidth;
    private static int windowHeight;
    
    private static GameView frame;
    private static Game game;
    private static int fps = 120;
    private static GameFlow gameFlow;
    
    /**
     * sets a global asset manager
     * @param manager
     */
    public static void setAssetManager(AssetManager manager) {
        Global.assetManager = manager;
    }
    
    /**
     * returns the current asset manager
     * @return
     */
    public static AssetManager getAssetManager() {
        return Global.assetManager;
    }

    /**
     * sets the exception handler
     * @param exceptionHandler
     */
    public static void setExceptionHandler(ExceptionHandler exceptionHandler) {
        Global.exceptionHandler = exceptionHandler;
    }

    /**
     * gets the current exception handler
     * @return
     */
    public static ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    /**
     * return the height of the jframe
     * @return
     */
    public static int getWindowHeight() {
        return windowHeight;
    }

    /**
     * return the width of the jframe
     * @return
     */
    public static int getWindowWidth() {
        return windowWidth;
    }

    /**
     * sets the height
     * @param windowHeight
     */
    public static void setWindowHeight(int windowHeight) {
        Global.windowHeight = windowHeight;
    }

    /**
     * sets the width
     * @param windowWidth
     */
    public static void setWindowWidth(int windowWidth) {
        Global.windowWidth = windowWidth;
    }

    /**
     *
     * @param defaultWindowHeight
     */
    public static void setDefaultWindowHeight(int defaultWindowHeight) {
        Global.defaultWindowHeight = defaultWindowHeight;
    }

    /**
     *
     * @param defaultWindowWidth
     */
    public static void setDefaultWindowWidth(int defaultWindowWidth) {
        Global.defaultWindowWidth = defaultWindowWidth;
    }

    /**
     *
     * @return
     */
    public static int getDefaultWindowHeight() {
        return defaultWindowHeight;
    }

    /**
     *
     * @return
     */
    public static int getDefaultWindowWidth() {
        return defaultWindowWidth;
    }

   

    /**
     *
     * @return
     */
    public static GameView getFrame() {
        return frame;
    }

    /**
     *
     * @param gv
     */
    public static void setFrame(GameView gv) {
        Global.frame = gv;
    }

    /**
     *
     * @return
     */
    public static Game getGame() {
        return game;
    }

    /**
     *
     * @param game
     */
    public static void setGame(Game game) {
        Global.game = game;
    }

    /**
     *
     * @return
     */
    public static int getFPS() {
        return fps;
    }

    /**
     *
     * @param gameFlow
     */
    public static void setGameFlow(GameFlow gameFlow) {
        Global.gameFlow = gameFlow;
    }

    /**
     *
     * @return
     */
    public static GameFlow getGameFlow() {
        return gameFlow;
    }


    
    
    
    
}
