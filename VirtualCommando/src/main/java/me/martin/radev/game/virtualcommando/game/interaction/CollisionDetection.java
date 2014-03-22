/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.interaction;

import me.martin.radev.game.virtualcommando.geometry.entity.Ellipse;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Polygon;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class CollisionDetection {
    
    public static boolean doCollide(GeometricObject goA, GeometricObject goB) {
        if (goA.getClass() == Polygon.class) {
            if (goB.getClass() == Polygon.class) {
                return CollisionDetection.doCollide(
                        (Polygon)goA, (Polygon)goB);
            } else if (goB.getClass() == Ellipse.class) {
                return CollisionDetection.doCollide(
                        (Polygon)goA, (Ellipse)goB);
            } else if (goB.getClass() == Rectangle.class) {
                return CollisionDetection.doCollide(
                        (Polygon)goA, (Rectangle)goB);
            }
        } else if (goA.getClass() == Ellipse.class) {
            if (goB.getClass() == Polygon.class) {
                
                return CollisionDetection.doCollide(
                        (Ellipse)goA, (Polygon)goB);
            } else if (goB.getClass() == Ellipse.class) {
                return CollisionDetection.doCollide(
                        (Ellipse)goA, (Ellipse)goB);
            } else if (goB.getClass() == Rectangle.class) {
                throw new IllegalStateException();
            }
        } else {
            System.out.println("bug");
        }
        return false;
    }
    
    public static boolean doCollide(Vector2D a, Vector2D b) {
        return a.contains(b);
    }
    
    public static boolean doCollide(Polygon polA, Polygon polB) {
        for (Vector2D v2d : polA.getPoints()) {
            if (polB.contains(v2d)) return true;
        }
        for (Vector2D v2d : polB.getPoints()) {
            if (polA.contains(v2d)) return true;
        }
        return false;
    } 
    
    public static boolean doCollide(Polygon polA, Rectangle polB) {
        for (Vector2D v2d : polA.getPoints()) {
            if (polB.contains(v2d)) return true;
        }
        for (Vector2D v2d : polB.getPoints()) {
            if (polA.contains(v2d)) return true;
        }
        return false;
    }
    
    public static boolean doCollide(Polygon pol, Ellipse ell) {
        for (Vector2D v2d : pol.getPoints()) {
            if (ell.contains(v2d)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean doCollide (Ellipse ellA, Ellipse ellB) {
        throw new IllegalStateException();
        //return false;
        
    }
    
    
    
}
