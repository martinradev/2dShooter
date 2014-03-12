/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry;

import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MathUtil {
    
    public static double crossProduct(Vector2D a, Vector2D b) {
        return a.getX()*b.getY() - a.getY()*b.getX();
    }
    
    public static double dotProduct(Vector2D a, Vector2D b) {
        return a.getX()*b.getX() + a.getY()*b.getY();
    }
   
    public static double distance(Vector2D a, Vector2D b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
    
}
