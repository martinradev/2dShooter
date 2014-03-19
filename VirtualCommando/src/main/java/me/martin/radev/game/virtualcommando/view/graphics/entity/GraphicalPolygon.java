/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.entity.GeometricObject;
import me.martin.radev.game.virtualcommando.geometry.entity.Polygon;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GraphicalPolygon implements GraphicalObject {
    
    private Polygon body;
    private Color color;
    
    public GraphicalPolygon(List<Vector2D> points, Color color) {
        this.body = new Polygon(points);
        this.color = color;
    }
    
    public GraphicalPolygon(Vector2D [] points, Color color) {
        this.body = new Polygon(points);
        this.color = color;
    }

    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(this.color);
        List<Vector2D> points = this.body.getPoints();
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 
                points.size());
        polygon.moveTo(xOffset + points.get(0).getX(), yOffset + points.get(0).getY());
        for (int i = 1; i < points.size(); ++i) {
            polygon.lineTo(xOffset + points.get(i).getX(), yOffset + points.get(i).getY());
        }
        polygon.closePath();
        g2d.fill(polygon);
    }
    
    public GeometricObject getBody() {
        return body;
    }
    
    
    
    
}
