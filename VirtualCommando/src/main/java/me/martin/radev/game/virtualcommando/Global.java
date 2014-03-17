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
    
    
    
}
