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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marto
 */
public class CollisionDetectionTest {
    
    public CollisionDetectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of doCollide method, of class CollisionDetection.
     */
    @Test
    public void testDoCollide_Vector2D_Vector2D() {
        System.out.println("doCollide");
        Vector2D a = new Vector2D(0d, 0d);
        Vector2D b = new Vector2D(0d, 0d);;
        boolean expResult = true;
        boolean result = CollisionDetection.doCollide(a, b);
        assertEquals(expResult, result);
        
        b = new Vector2D(1d, 2d);
        expResult = false;
        result = CollisionDetection.doCollide(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of doCollide method, of class CollisionDetection.
     */
    @Test
    public void testDoCollide_Polygon_Polygon() {
        System.out.println("doCollide");
        Polygon polA = new Polygon(new Vector2D[] {
            new Vector2D(0d, 0d),
            new Vector2D(0d, 3d),
            new Vector2D(3d, 3d),
            new Vector2D(3d, 0d),
        });
        Polygon polB = new Polygon(new Vector2D[] {
            new Vector2D(100d, 100d),
            new Vector2D(120d, 6004d),
            
            new Vector2D(150d, 150d),
            new Vector2D(150d, 100d),
        });
        boolean expResult = false;
        boolean result = CollisionDetection.doCollide(polA, polB);
        assertEquals(expResult, result);
        polA = new Polygon(new Vector2D[] {
            new Vector2D(0d, 0d),
            new Vector2D(0d, 3d),
            
            new Vector2D(3d, 3d),
            new Vector2D(3d, 0d),
        });
         polB = new Polygon(new Vector2D[] {
            new Vector2D(2d, 2d),
            new Vector2D(2d, 6d),
            
            new Vector2D(6d, 6d),
            new Vector2D(6d, 2d),
            
        });
        expResult = true;
        result = CollisionDetection.doCollide(polA, polB);
        assertEquals(expResult, result);
    }

    
}