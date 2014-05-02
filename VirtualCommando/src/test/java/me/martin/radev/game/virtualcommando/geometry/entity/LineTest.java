/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import me.martin.radev.game.virtualcommando.geometry.MathUtil;
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
public class LineTest {
    
    /**
     *
     */
    public LineTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getCenter method, of class Line.
     */
    @Test
    public void testGetCenter() {
        System.out.println("getCenter");
        Line instance = new Line(new Vector2D(0d, 0d),
                new Vector2D(110d, 110d));
        Vector2D expResult = new Vector2D(55d, 55d);
        Vector2D result = instance.getCenter();
        assertTrue(MathUtil.relativelyEqual(expResult.getX(),
                result.getX())
                && MathUtil.relativelyEqual(expResult.getY(),
                result.getY()));
    }

    /**
     * Test of translate method, of class Line.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        double dx = 10.0;
        double dy = -15.0;
        Line instance = new Line(new Vector2D(33, 33),
                new Vector2D(-15, -100));
        instance.translate(dx, dy);
        assertEquals(instance.getTail().getX(), 43d, 0.00001d);
        assertEquals(instance.getTail().getY(), 18d, 0.00001d);
        assertEquals(instance.getHead().getX(), -5, 0.00001d);
        assertEquals(instance.getHead().getY(), -115, 0.00001d);
    }

    /**
     * Test of rotate method, of class Line.
     */
    @Test
    public void testRotate() {
        System.out.println("rotate");
        double angle = Math.toRadians(90);
        Line instance = new Line(new Vector2D(0d, 0d),
                new Vector2D(2d,2d));
        instance.rotate(angle);
        assertTrue(instance.getHead().equals(new Vector2D(0d,0d))
                && instance.getTail().equals(new Vector2D(-2d, 2d)));
    }

    /**
     * Test of contains method, of class Line.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Vector2D v2d = new Vector2D(50d, 50d);
        Line instance = new Line(new Vector2D(2d,2d), new Vector2D(100d, 100d));
        boolean result = instance.contains(v2d);
        assertEquals(true, result);
        
        v2d = new Vector2D(-50d, -50d);
        result = instance.contains(v2d);
        assertEquals(false, result);
        v2d = new Vector2D(0, -0);
        result = instance.contains(v2d);
        assertEquals(false, result);
        
        v2d = new Vector2D(2d, 2d);
        result = instance.contains(v2d);
        assertEquals(true, result);
    }

    /**
     * Test of relativeRotate method, of class Line.
     */
    @Test
    public void testRelativeRotate() {
        /*
        System.out.println("relativeRotate");
        Vector2D center = new Vector2D(0,2d);
        double angle = Math.toRadians(90d);
        Line instance = new Line(new Vector2D(0d, 1d),
                new Vector2D(0d,3d));
        instance.relativeRotate(center, angle);
        System.out.println(instance.getTail() + " " + instance.getHead());
        assertTrue(instance.getTail().equals(new Vector2D(-1d,2d))
                && 
                instance.getHead().equals(new Vector2D(1d, 2d)));
                */
    }

    /**
     * Test of getBoundingBox method, of class Line.
     */
    @Test
    public void testGetBoundingBox() {
        System.out.println("getBoundingBox");
        Line instance = new Line(new Vector2D(1d, 1d), new Vector2D(3d, 3d));
        Vector2D[] expResult = new Vector2D[] {instance.getHead(), instance.getTail()};
        Vector2D[] result = instance.getBoundingBox();
        assertArrayEquals(expResult, result);
    }

    

    /**
     * Test of generateVector method, of class Line.
     */
    @Test
    public void testGenerateVector() {
        System.out.println("generateVector");
        Line instance = new Line(new Vector2D(15d,15d),
                new Vector2D(-15d, 100d));
        Vector2D expResult = new Vector2D(-30d, 85d);
        Vector2D result = instance.generateVector();
        assertEquals(expResult, result);
    }

    /**
     * Test of isParallel method, of class Line.
     */
    @Test
    public void testIsParallel() {
        System.out.println("isParallel");
        Line l = new Line(new Vector2D(0d,0d), new Vector2D(1d,1d));
        Line instance = new Line(new Vector2D(3d, 3d), new Vector2D(6d,6d));
        boolean expResult = true;
        boolean result = instance.isParallel(l);
        assertEquals(expResult, result);
        l = new Line(new Vector2D(-4d,-4d), new Vector2D(-1d,-1d));
        result = instance.isParallel(l);
        assertEquals(true, result);
        
        l = new Line(new Vector2D(-14d,-4d), new Vector2D(-1d,-1d));
        result = instance.isParallel(l);
        assertEquals(false, result);
    }

    /**
     * Test of distanceToPoint method, of class Line.
     */
    @Test
    public void testDistanceToPoint() {
        System.out.println("distanceToPoint");
        Vector2D point = new Vector2D(5d,5d);
        Line instance = new Line(new Vector2D(0d,0d), new Vector2D(100d,100d));
        double expResult = 0.0;
        double result = instance.distanceToPoint(point);
        assertTrue(MathUtil.relativelyEqual(result, expResult));
    }

    /**
     * Test of liesOnLine method, of class Line.
     */
    @Test
    public void testLiesOnLine() {
        System.out.println("liesOnLine");
        Vector2D point = new Vector2D(10d,10d);
        Line instance = new Line(new Vector2D(-10,-10), new Vector2D(11,11));
        boolean expResult = true;
        boolean result = instance.liesOnLine(point);
        assertEquals(expResult, result);
    }

    /**
     * Test of onSameSideOfLine method, of class Line.
     */
    @Test
    public void testOnSameSideOfLine() {
        System.out.println("onSameSideOfLine");
        Vector2D pointA = new Vector2D(-5, 100);
        Vector2D pointB = new Vector2D(5, 100);
        Line instance = new Line(new Vector2D(0d,0d), new Vector2D(0d,10000));
        boolean expResult = false;
        boolean result = instance.onSameSideOfLine(pointA, pointB);
        assertEquals(expResult, result);
        
        pointB = new Vector2D(-5555, 100);
        expResult = true;
        result = instance.onSameSideOfLine(pointA, pointB);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSlope method, of class Line.
     */
    @Test
    public void testGetSlope() {
        System.out.println("getSlope");
        Line instance = new Line(new Vector2D(-1,-1), new Vector2D(1,1));
        double expResult = 1d;
        double result = instance.getSlope();
        assertEquals(expResult, result, 0.0000001d);
    }
}