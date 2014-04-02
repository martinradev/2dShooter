/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.graphics.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.entity.Polygon;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;

/**
 *
 * @author Marto
 */
public class GraphicalPolygon extends GraphicalObject {

    /**
     *
     * @param points
     * @param color
     */
    public GraphicalPolygon(List<Vector2D> points, Color color) {
        super(new Polygon(points), color);
    }

    /**
     *
     * @param points
     * @param color
     */
    public GraphicalPolygon(Vector2D[] points, Color color) {
        super(new Polygon(points), color);
    }

    /**
     *
     * @param g2d
     * @param xOffset
     * @param yOffset
     */
    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(super.getColor());
        List<Vector2D> points = ((Polygon) super.getBody()).getPoints();
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                points.size());
        polygon.moveTo(xOffset + points.get(0).getX(), yOffset + points.get(0).getY());
        for (int i = 1; i < points.size(); ++i) {
            polygon.lineTo(xOffset + points.get(i).getX(), yOffset + points.get(i).getY());
        }
        polygon.closePath();
        g2d.fill(polygon);
    }

    /**
     *
     * @return
     */
    @Override
    public Polygon getBody() {
        return (Polygon)super.getBody(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
