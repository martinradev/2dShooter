/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit.action.ai;

import me.martin.radev.game.virtualcommando.geometry.MathUtil;
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
public class RandomAILogicTest {
    
    public RandomAILogicTest() {
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
     * Test of getDirection method, of class RandomAILogic.
     */
    @Test
    public void testGetDirection() {
        RandomAILogic instance = new RandomAILogic();
        Vector2D prev = null;
        for (int i = 0; i < 20; ++i) {
            Vector2D result = instance.getDirection();
            double dist = MathUtil.distance(result, new Vector2D(0d,0d));
            if (!MathUtil.relativelyEqual(dist, 1d)) {
                fail("Should be a unit vector."); 
            }
            if (prev != null) {
                if (prev.equals(result)) {
                    fail("Vectors should be random."); 
                }
            }
            prev = result;
        }
        
        
        
        
    }

    /**
     * Test of getRotationAngle method, of class RandomAILogic.
     */
    @Test
    public void testGetRotationAngle() {
        System.out.println("getRotationAngle");
        RandomAILogic instance = new RandomAILogic();
        float result = instance.getRotationAngle();
        if (result < 0f || result > 2f*Math.PI) {
            fail("Angle of rotation should be between 0 AND 2*PI");
        }
        
    }
}