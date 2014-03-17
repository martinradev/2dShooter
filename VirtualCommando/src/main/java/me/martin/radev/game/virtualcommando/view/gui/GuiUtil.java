/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui;

import me.martin.radev.game.virtualcommando.Global;

/**
 *
 * @author Marto
 */
public class GuiUtil {
    
    public static int rescale(int length) {
        return (int) (Global.getScalingFactor() * length);
    }
    
}
