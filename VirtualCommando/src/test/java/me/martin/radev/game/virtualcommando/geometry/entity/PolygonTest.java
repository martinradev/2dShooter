/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.geometry.entity;

import java.util.List;
import static junit.framework.Assert.fail;
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
    /*
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
     * */
    /**
     * Test of rotate method, of class Polygon.
     */
    public void testRotate() {
        System.out.println("rotate");
        double angle = Math.toRadians(90f);
        Polygon A = new Polygon(new Vector2D[]{
            new Vector2D(-1, -1),
            new Vector2D(1, -1),
            new Vector2D(1, 1),
            new Vector2D(-1, 1)
        });
        A.rotate(angle);
        Polygon B = new Polygon(new Vector2D[]{
            new Vector2D(-1, 1),
            new Vector2D(-1, -1),
            new Vector2D(1, -1),
            new Vector2D(1, 1)
        });
        List<Vector2D> pointsA = A.getPoints();
        List<Vector2D> pointsB = B.getPoints();
        for (int i = 0; i < pointsA.size(); ++i) {
            if (!pointsA.get(i).equals(pointsB.get(i))) {
                fail("Not rotated properly.");
            }
        }
    }

    /**
     * Test of rotate method, of class Polygon.
     */
    public void testRelativeRotate() {
        System.out.println("relativeRotate");
        double angle = Math.toRadians(90f);
        Polygon A = new Polygon(new Vector2D[]{
            new Vector2D(-4, -4),
            new Vector2D(4, -4),
            new Vector2D(4, 4),
            new Vector2D(-4, 4)
        });
        A.relativeRotate(A.getCenter(), angle);
        Polygon B = new Polygon(new Vector2D[]{
            new Vector2D(-4, 4),
            new Vector2D(-4, -4),
            new Vector2D(4, -4),
            new Vector2D(4, 4)
        });
        List<Vector2D> pointsA = A.getPoints();
        List<Vector2D> pointsB = B.getPoints();
        for (int i = 0; i < pointsA.size(); ++i) {
            if (!pointsA.get(i).equals(pointsB.get(i))) {
                fail("Not rotated properly.");
            }
        }
    }
}
