/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui;

import java.awt.Color;
import java.util.Random;
import me.martin.radev.game.virtualcommando.Global;

/**
 * A class containing helpful utility methods for gui
 * @author Marto
 */
public class GuiUtil {

  
    /**
     * returns a random color
     * @return
     */
    public static Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
}
