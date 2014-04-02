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
    
    /**
     * Checks for a collision between a graphical object A and graphical object B.
     * doCollide will call a separate method accordingly for both objects.
     * @param goA
     * @param goB
     * @return
     */
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
                return CollisionDetection.doCollide(
                        (Polygon)goB, (Ellipse)goA );
            }
        } else if (goA.getClass() == Rectangle.class) {
            if (goB.getClass() == Polygon.class) {
                return CollisionDetection.doCollide(
                        (Polygon)goB, (Rectangle)goA);
            } else if (goB.getClass() == Ellipse.class) {
                throw new IllegalStateException();
            } else if (goB.getClass() == Rectangle.class) {
                return CollisionDetection.doCollide(
                        (Polygon)goB, (Rectangle)goA);
            }
        } else {
            System.out.println("bug");
        }
        return false;
    }
    
    /**
     * Checks for a collision between vectors A and B. The collide if they are equal.
     * @param a
     * @param b
     * @return
     */
    public static boolean doCollide(Vector2D a, Vector2D b) {
        return a.contains(b);
    }
    
    /**
     * Checks for collision between polygons A and B.
     * They collide if either one of the vertices of A is in B,
     * or one of the vertices of B is in A
     * @param polA
     * @param polB
     * @return
     */
    public static boolean doCollide(Polygon polA, Polygon polB) {
        for (Vector2D v2d : polA.getPoints()) {
            if (polB.contains(v2d)) return true;
        }
        for (Vector2D v2d : polB.getPoints()) {
            if (polA.contains(v2d)) return true;
        }
        return false;
    } 
    
    /**
     * Checks for a collision between a Rectangle and Polygon.
     * @param polA
     * @param polB
     * @return
     */
    public static boolean doCollide(Polygon polA, Rectangle polB) {
        for (Vector2D v2d : polA.getPoints()) {
            if (polB.contains(v2d)) return true;
        }
        for (Vector2D v2d : polB.getPoints()) {
            if (polA.contains(v2d)) return true;
        }
        return false;
    }
    
    /**
     * Checks for a collision between a polygon and an ellipse.
     * It checks only for the case when one of the vertices of the polygon
     * are in the Ellipse. Not the other way around.
     * @param pol
     * @param ell
     * @return
     */
    public static boolean doCollide(Polygon pol, Ellipse ell) {
        for (Vector2D v2d : pol.getPoints()) {
            if (ell.contains(v2d)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Not supported at this point.
     * @param ellA
     * @param ellB
     * @return
     */
    public static boolean doCollide (Ellipse ellA, Ellipse ellB) {
        throw new IllegalStateException();
        //return false;
        
    }
    
    
    
}
