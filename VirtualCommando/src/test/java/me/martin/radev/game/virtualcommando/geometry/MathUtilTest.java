/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class MathUtilTest extends TestCase {
    
    public MathUtilTest(String testName) {
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
     * Test of crossProduct method, of class MathUtil.
     */
    public void testCrossProduct() {
        System.out.println("crossProduct");
        Vector2D a = new Vector2D(3, 2);
        Vector2D b = new Vector2D(4, 6);
        double expResult = 10.0;
        double result = MathUtil.crossProduct(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of dotProduct method, of class MathUtil.
     */
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector2D a = new Vector2D(5, 0);
        Vector2D b = new Vector2D(0, 6);
        double expResult = 0.0;
        double result = MathUtil.dotProduct(a, b);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of distance method, of class MathUtil.
     */
    public void testDistance() {
        System.out.println("distance");
        Vector2D a = new Vector2D(3, 4);
        Vector2D b = new Vector2D(-1, 7);
        double expResult = 5.0;
        double result = MathUtil.distance(a, b);
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of crossProductFromNewOrigin method, of class MathUtil.
     */
   public void testCrossProductFromNewOrigin() {
       System.out.println("crossProductFromNewOrigin");
        Vector2D a = new Vector2D(1, 2);
        Vector2D b = new Vector2D(8, 4);
        Vector2D o = new Vector2D(1.5, 6);
        double expResult = -27.0;
        double result = MathUtil.crossProductFromNewOrigin(o, b, a);
        assertEquals(expResult, result, 0.0);
        
        result = MathUtil.crossProductFromNewOrigin(b, o, a);
        assertEquals(expResult, -result, 0.0);
   }
   
   /**
     * Test of relativelyEqual method, of class MathUtil.
     */
   public void testRelativelyEqual() {
       System.out.println("relativelyEqual");
       double a = 60.0000000d;
       double b = 60.000000001d;
       assertTrue(MathUtil.relativelyEqual(a, b));
       
       a = 1.5f;
       b = 1.6f;
       assertTrue(!MathUtil.relativelyEqual(a, b));
   }
   
    
}
