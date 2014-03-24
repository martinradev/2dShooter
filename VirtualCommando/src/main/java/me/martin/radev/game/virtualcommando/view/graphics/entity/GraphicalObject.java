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
    
    public GraphicalObject(GeometricObject body, Color color) {
        this.body = body;
        this.color = color;
    }
    
    public GeometricObject getBody() {
        return this.body;
    }

    public Color getColor() {
        return color;
    }

    public void setBody(GeometricObject body) {
        this.body = body;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
