/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.game.graphics.Renderable;

/**
 * an abstract class which defines most of the common methods and variables for a
 * graphical object
 * @author Marto
 */
public abstract class GraphicalObject implements Renderable {
    
    private GeometricObject body;
    private Color color;
    protected Sprite sprite;
    
    /**
     * creates a graphical object from a body and a color
     * @param body
     * @param color
     */
    public GraphicalObject(GeometricObject body, Color color) {
        this.body = body;
        this.color = color;
    }
    
    /**
     * returns the body of the graphical object
     * @return
     */
    public GeometricObject getBody() {
        return this.body;
    }

    /**
     * returns the color of the graphical object
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * sets a new body for the graphical object
     * @param body
     */
    public void setBody(GeometricObject body) {
        this.body = body;
    }

    /** 
     * sets a new color
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    
    
}
