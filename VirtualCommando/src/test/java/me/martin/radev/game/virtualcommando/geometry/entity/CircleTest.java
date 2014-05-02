/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author Marto
 */
public class CircleTest extends TestCase {
    
    /**
     *
     * @param testName
     */
    public CircleTest(String testName) {
        super(testName);
    }
    
    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of contains method, of class Circle.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        
        // test whether inside
        Vector2D v2d = new Vector2D(1d,1d);
        double radius = 5.0;
        Circle instance = new Circle(new Vector2D(3.0,3.0), radius);
        boolean expResult = true;
        boolean result = instance.contains(v2d);
        assertEquals(expResult, result);
        
        
        // test whether outside
        v2d = new Vector2D(-15d, -15d);
        expResult = false;
        result = instance.contains(v2d);
        assertEquals(expResult, result);
    }

    /**
     * Test of rotate method, of class Circle.
     */
    @Test
    public void testRotate() {
        System.out.println("rotate");
        // shouldn't rotate
        double angle = 0.0;
        Vector2D center = new Vector2D(5d, 5d);
        Circle instance = new Circle(center, 3d);
        instance.rotate(angle);
        assertEquals(center, instance.getCenter());
    }

    
}
