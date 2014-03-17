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
import me.martin.radev.game.virtualcommando.exception.ExceptionHandler;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;

/**
 *
 * @author Marto
 */
public class AssetManager {
    
    private Map<String, HashMap<String, Object>> assets;
    private ExceptionHandler exceptionHandler;
    private String assetDirectory = "assets";
    
    public AssetManager(ExceptionHandler exceptionHandler) {
        assets = new HashMap<String, HashMap<String, Object>>();
        this.exceptionHandler = exceptionHandler;
    }
    
    public Object load(AssetType type, String file) {
        if (this.contains(type, file)) {
            return this.get(type, file);
        }
        File f = new File(this.buildPath(type, file));
        Object found = null;
        if (type == AssetType.Sprite) {
            found = this.loadSprite(f);
        } else if (type == AssetType.Font) {
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
        System.out.println("added");
        objMap.put(name, obj);
    }
    
    private String buildPath(AssetType type, String fileName) {
        return this.assetDirectory + "/" + type.getFolder() + "/" + fileName;
    }
    
}
