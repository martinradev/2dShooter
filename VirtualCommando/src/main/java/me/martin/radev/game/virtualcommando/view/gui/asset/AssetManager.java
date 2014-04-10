/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.asset;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.map.TiledMap;
import me.martin.radev.game.virtualcommando.map.loader.Loader;
import me.martin.radev.game.virtualcommando.map.loader.ObjectLoader;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 * An AssetManager loads and store the needed assets for the game.
 * @author Marto
 */
public class AssetManager {
    
    private Map<String, HashMap<String, Object>> assets;
    private ExceptionHandler exceptionHandler;
    private String assetDirectory = "assets";
    
    /**
     * Creates an asset manager
     * @param exceptionHandler
     */
    public AssetManager(ExceptionHandler exceptionHandler) {
        assets = new HashMap<String, HashMap<String, Object>>();
        this.exceptionHandler = exceptionHandler;
    }
    
    /**
     * loads an {@link Object} with a given {@link AssetType} and a file name
     * @param type
     * @param file
     * @return
     */
    public Object load(AssetType type, String file) {
        if (this.contains(type, file)) {
            return this.get(type, file);
        }
        File f = new File(this.buildPath(type, file));
        Object found = null;
        if (type == AssetType.Sprite) {
            found = this.loadSprite(f);
        } else if (type == AssetType.Map) {
            found = this.loadMap(f);
        } else if (type == AssetType.Font) {
            found = f;
        } else if (type == AssetType.Sound) {
            found = f;
        }
        if (found != null) {
            add(type, file, found);
        }
        return found;
    }
    
    private Sprite loadSprite(File f) {
        try {
            Image im = ImageIO.read(f);
            return new Sprite(im);
        } catch (IOException ex) {
            this.exceptionHandler.notificate(ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
        return null;
    }
    
    private TiledMap loadMap(File f) {
        Loader loader = new ObjectLoader(Global.getExceptionHandler());
        return loader.load(f);
    }
    
    private Object get(AssetType type, String file) {
        return this.assets.get(type.toString()).get(file);
    }
    
    private boolean contains(AssetType type, String file) {
        Map<String, Object> container = this.assets.get(type.toString());
        if (container == null) return false;
        return container.containsKey(file);
    }
    
    private void add(AssetType type, String name,  Object obj) {
        HashMap<String, Object> objMap = this.assets.get(type.toString());
        if (objMap == null) {
            objMap = new HashMap<String, Object>();
            this.assets.put(type.toString(), objMap);
        }
        objMap.put(name, obj);
    }
    
    private String buildPath(AssetType type, String fileName) {
        if (type == AssetType.Map) {
            return this.assetDirectory + "/" + type.getFolder() + "/" + fileName + "/map.tmx";
        }
        return this.assetDirectory + "/" + type.getFolder() + "/" + fileName;
    }
    
}
