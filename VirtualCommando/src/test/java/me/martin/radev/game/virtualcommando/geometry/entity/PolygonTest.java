/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import junit.framework.TestCase;

/**
 *
 * @author Marto
 */
public class PolygonTest extends TestCase {
    
    public PolygonTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of contains method, of class Polygon.
     */
    public void testContains() {
        System.out.println("contains");
        Vector2D point = null;
        Polygon instance = null;
        boolean expResult = false;
        boolean result = instance.contains(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotate method, of class Polygon.
     */
    public void testRotate() {
        System.out.println("rotate");
        double angle = 0.0;
        Polygon instance = null;
        instance.rotate(angle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
