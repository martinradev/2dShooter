/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.util.Objects;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.game.graphics.Renderable;
import me.martin.radev.game.virtualcommando.view.graphics.animation.Animation;

/**
 * an abstract class which defines most of the common methods and variables for a
 * graphical object
 * @author Marto
 */
public abstract class GraphicalObject implements Renderable {
    
    private GeometricObject body;
    private Color color;
    protected String name;
    protected Animation animation;
    
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

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof GraphicalObject)) return false;
        GraphicalObject go = (GraphicalObject)o;
        if (go.getBody().getCenter().equals(this.getBody().getCenter())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.body);
        return hash;
    }

    
    
    
}
