/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;

/**
 *
 * @author Marto
 */
public class Vector2DTest extends TestCase {
    
    /**
     *
     * @param testName
     */
    public Vector2DTest(String testName) {
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
     * Test of translate method, of class Vector2D.
     */
    public void testTranslate() {
        System.out.println("translate");
        double dx = 20.0;
        double dy = -3.0;
        Vector2D instance = new Vector2D(1d, 2d);
        instance.translate(dx, dy);
        assertTrue(instance.getX() == (1d + dx) && instance.getY() == (2d+dy));
    }

    /**
     * Test of rotate method, of class Vector2D.
     */
    public void testRotate() {
        System.out.println("rotate");
        double angle = Math.toRadians(45f);
        Vector2D instance = new Vector2D(1f, 1f);
        instance.rotate(angle);
        assertTrue(MathUtil.relativelyEqual(instance.getX(),0d) 
                && MathUtil.relativelyEqual(instance.getY(), Math.sqrt(2f)));
        
        angle = Math.toRadians(90f);
        instance.rotate(-angle);
        assertTrue(MathUtil.relativelyEqual(instance.getX(),Math.sqrt(2f)) 
                && MathUtil.relativelyEqual(instance.getY(), 0d));
    }

    /**
     * Test of contains method, of class Vector2D.
     */
    public void testContains() {
        System.out.println("contains");
        Vector2D v2d = new Vector2D(5d, 5d);
        Vector2D instance = v2d;
        boolean expResult = true;
        boolean result = instance.contains(v2d);
        assertEquals(expResult, result);
        
        // not contains
        expResult = false;
        instance = new Vector2D(5.5d, 2d);
        result = instance.contains(v2d);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Vector2D.
     */
    public void testEquals() {
        System.out.println("equals");
        Object o = new Vector2D(4,5);
        Vector2D instance = new Vector2D(4, 5);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Vector2D.
     */
    public void testToString() {
        System.out.println("toString");
        Vector2D instance = new Vector2D(4d, 6d);
        String expResult = "[ 4.0 : 6.0 ]";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUnitVector method, of class Vector2D.
     */
    public void testGetUnitVector() {
        System.out.println("getUnitVector");
        Vector2D instance = new Vector2D(3d, 4d);
        Vector2D expResult = new Vector2D(0.6d, 0.8d);
        Vector2D result = instance.getUnitVector();
        assertEquals(expResult, result);
    }

    /**
     * Test of scale method, of class Vector2D.
     */
    public void testScale() {
        System.out.println("scale");
        double factor = 4.0;
        Vector2D instance = new Vector2D(1d, -3d);
        instance.scale(factor);
        Vector2D expResult = new Vector2D(4d, -12d);
        assertEquals(instance, expResult);
    }
}
