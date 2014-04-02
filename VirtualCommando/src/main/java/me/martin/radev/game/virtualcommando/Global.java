/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.game.logic.Game;
import me.martin.radev.game.virtualcommando.game.logic.GameFlow;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
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
    private static double scalingFactor;
    private static GameView frame;
    private static Game game;
    private static int fps = 100;
    private static GameFlow gameFlow;
    
    /**
     *
     * @param manager
     */
    public static void setAssetManager(AssetManager manager) {
        Global.assetManager = manager;
    }
    
    /**
     *
     * @return
     */
    public static AssetManager getAssetManager() {
        return Global.assetManager;
    }

    /**
     *
     * @param exceptionHandler
     */
    public static void setExceptionHandler(ExceptionHandler exceptionHandler) {
        Global.exceptionHandler = exceptionHandler;
    }

    /**
     *
     * @return
     */
    public static ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    /**
     *
     * @return
     */
    public static int getWindowHeight() {
        return windowHeight;
    }

    /**
     *
     * @return
     */
    public static int getWindowWidth() {
        return windowWidth;
    }

    /**
     *
     * @param windowHeight
     */
    public static void setWindowHeight(int windowHeight) {
        Global.windowHeight = windowHeight;
    }

    /**
     *
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
     * @param scalingFactor
     */
    public static void setScalingFactor(double scalingFactor) {
        Global.scalingFactor = scalingFactor;
    }

    /**
     *
     * @return
     */
    public static double getScalingFactor() {
        return scalingFactor;
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
