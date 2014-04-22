/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics.statistics;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author Marto
 */
public class StatisticsRow implements Comparable<StatisticsRow> {

    private String name;
    private String frags;
    private String deaths;
    private int width;
    private int height;

    public StatisticsRow(String name, String frags, String deaths, int width, int height) {
        Color noColor = new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue(), 0.0f);
        Border leftPadding = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        this.width = width;
        this.height = height;


        this.name = name;
        this.frags = frags;
        this.deaths = deaths;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != String.class) {
            if (o.getClass() == StatisticsRow.class) {
                StatisticsRow sr = (StatisticsRow)o;
                return sr.name.equals(name);
            }
            return false;
        }
        String s = o.toString();
        return this.name.equals(s);
    }

    public void addDeath() {
        int val = Integer.parseInt(deaths);
        ++val;
        deaths = Integer.toString(val);
    }
    
    public void addFrag() {
        int val = Integer.parseInt(frags);
        ++val;
        frags = Integer.toString(val);
    }
    
    public double getScore() {
        if (name == null) return -1d;
        if (this.name.equals("Name")) return 99999999d;
        return Double.parseDouble(frags+1) / Double.parseDouble(deaths+1);
    }

    @Override
    public int compareTo(StatisticsRow t) {
        if (t == null) return -1;
        double myScore = this.getScore();
        double otherScore = t.getScore();
        if (myScore > otherScore) {
            return 1;
        } else if (myScore < otherScore) {
            return -1;
        } else {
            return this.name.compareTo(t.name);
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    
    
    public void render(Graphics2D g2d, int xOffset, int yOffset) {
        g2d.setColor(Color.yellow);
        g2d.drawString(name, xOffset, yOffset);
        g2d.drawString(frags, xOffset+100, yOffset);
        g2d.drawString(deaths, xOffset+200, yOffset);
    }

    public String getName() {
        return name;
    }

    
    
}
