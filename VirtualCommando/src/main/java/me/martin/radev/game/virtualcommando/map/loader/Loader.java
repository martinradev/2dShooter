/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.loader;

import java.io.File;
import me.martin.radev.game.virtualcommando.map.MapInterface;



/**
 *
 * @author Marto
 */
public interface Loader {
    
    public MapInterface load(File file);
    
}
