/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
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
    
    public static void setAssetManager(AssetManager manager) {
        Global.assetManager = manager;
    }
    
    public static AssetManager getAssetManager() {
        return Global.assetManager;
    }

    public static void setExceptionHandler(ExceptionHandler exceptionHandler) {
        Global.exceptionHandler = exceptionHandler;
    }

    public static ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static void setWindowHeight(int windowHeight) {
        Global.windowHeight = windowHeight;
    }

    public static void setWindowWidth(int windowWidth) {
        Global.windowWidth = windowWidth;
    }

    public static void setDefaultWindowHeight(int defaultWindowHeight) {
        Global.defaultWindowHeight = defaultWindowHeight;
    }

    public static void setDefaultWindowWidth(int defaultWindowWidth) {
        Global.defaultWindowWidth = defaultWindowWidth;
    }

    public static int getDefaultWindowHeight() {
        return defaultWindowHeight;
    }

    public static int getDefaultWindowWidth() {
        return defaultWindowWidth;
    }

    public static void setScalingFactor(double scalingFactor) {
        Global.scalingFactor = scalingFactor;
    }

    public static double getScalingFactor() {
        return scalingFactor;
    }
    
}
