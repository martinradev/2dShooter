/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.game.graphics.Renderable;

/**
 *
 * @author Marto
 */
public abstract class GraphicalObject implements Renderable, Cloneable {
    
    private GeometricObject body;
    private Color color;
    
    /**
     *
     * @param body
     * @param color
     */
    public GraphicalObject(GeometricObject body, Color color) {
        this.body = body;
        this.color = color;
    }
    
    /**
     *
     * @return
     */
    public GeometricObject getBody() {
        return this.body;
    }

    /**
     *
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     *
     * @param body
     */
    public void setBody(GeometricObject body) {
        this.body = body;
    }

    /**
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
