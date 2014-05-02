/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
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
public class BinarySpaceTreeTest {
    
    /**
     *
     */
    public BinarySpaceTreeTest() {
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
     * Test of addElement method, of class BinarySpaceTree.
     */
    @Test
    public void testAddElement() {
        System.out.println("addElement");
        GraphicalObject object = new GraphicalRectangle(new Vector2D(100d,100d),
                30d,30d, Color.black);
        BinarySpaceTree instance = new BinarySpaceTree(2000, 2000, 100,100);
        instance.addElement(object);
        Collection<GraphicalObject> objects = instance.getObjectsInClosingArea(object);
        System.out.println(objects.size());
        if (objects.size() != 1) {
            fail("Element not added");
        }
        instance.addElement(new GraphicalRectangle(new Vector2D(22100d,100d),
                30d,30d, Color.black));
        if (objects.size() != 1) {
            fail("Second element added although out of bounds");
        }
    }

    /**
     * Test of getObjectsInClosingArea method, of class BinarySpaceTree.
     */
    @Test
    public void testGetObjectsInClosingArea() {
        System.out.println("getObjectsInClosingArea");
        GraphicalObject object = new GraphicalRectangle(new Vector2D(100d,100d),
                30d,30d, Color.black);
        GraphicalObject object2 = new GraphicalRectangle(new Vector2D(1000d,1000d),
                300d,300d, Color.black);
        BinarySpaceTree instance = new BinarySpaceTree(2000, 2000, 100,100);
        instance.addElement(object);
        instance.addElement(object2);
        Collection expResult = new HashSet<>();
        expResult.add(object);
        Collection result = instance.getObjectsInClosingArea(object);
        assertEquals(expResult, result);
        GraphicalRectangle object3 = new GraphicalRectangle(new Vector2D(120d,130d),
                30,30d, Color.black);
        expResult.add(object3);
        instance.addElement(object3);
        result = instance.getObjectsInClosingArea(object);
        assertEquals(expResult, result);
    }
}