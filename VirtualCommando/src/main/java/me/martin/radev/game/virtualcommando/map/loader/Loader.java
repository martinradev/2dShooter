/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.map.loader;

import me.martin.radev.game.virtualcommando.map.Map;



/**
 *
 * @author Marto
 */
public interface Loader {
    
    public Map load(String file);
    
}
