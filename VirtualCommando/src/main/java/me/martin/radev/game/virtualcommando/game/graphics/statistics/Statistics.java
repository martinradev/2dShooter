/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics.statistics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;

/**
 * The {@link Statistics} class keeps information about the statistics in the game. It is a typical Name, Frags, Deaths score board.
 * @author Marto
 */
public final class Statistics {

    private Map<StatisticsRow, StatisticsRow> orderedEntries;
    private Map<String, StatisticsRow> entries;
    private final int rowHeight = 25;
    private final int xOffset;
    private final int yOffset;
    private final int leftPadding = 20;
    private final int topPadding = 10;
    private boolean isVisible;
    private final Color backgroundColor;
    private final int width;
    private final int height;
    
    // name frags deaths
    /**
     * Initializes the statistics
     */
    public Statistics() {
        width = (int) (Global.getWindowWidth() * 0.7);
        height = (int) (Global.getWindowHeight() * 0.7);
        xOffset = (int) (Global.getWindowWidth() * 0.15);
        yOffset = (int) (Global.getWindowHeight() * 0.15);
        isVisible = false;
        entries = new HashMap<>();
        orderedEntries = new TreeMap<>(new StatisticsComparator());
        addRow("Name", "Frags", "Deaths");
        backgroundColor = new Color(60f / 255f, 60f / 255f,
                60f / 255f, 0.8f);
    }

    /**
     * adds a row to the statistics board
     * @param name
     * @param kills
     * @param deaths
     */
    public void addRow(String name, String kills, String deaths) {
        StatisticsRow sr = new StatisticsRow(name, kills, deaths,
                (int) width - 2*leftPadding, rowHeight);
        entries.put(name, sr);
        orderedEntries.put(sr, sr);
    }

    /**
     * adds a frag to the score entity with the given name
     * @param name
     */
    public void addFrag(String name) {
        StatisticsRow sr = entries.get(name);
        if (sr != null) {
            orderedEntries.remove(sr);
            sr.addFrag();
            orderedEntries.put(sr,sr);
        } else {
            System.out.println("Error: " + name);
        }
    }

    /**
     * adds a death to the score entity with the given name
     * @param name
     */
    public void addDeath(String name) {
        StatisticsRow sr = entries.get(name);
        if (sr != null) {
            orderedEntries.remove(sr);
            sr.addDeath();
            orderedEntries.put(sr, sr);
        } else {
            System.out.println("Error: " + name);
        }
    }
    
    /**
     * renders the statistics board
     * @param g
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(backgroundColor);
        g2d.fillRect(xOffset, yOffset, width, height);
        int i = 1;
        for (StatisticsRow sr : orderedEntries.values()) {
            boolean important = false;
            if (Global.getGame().getMainPlayer().getName().equals(sr.getName())) {
                important = true;
            }
            sr.render(g2d, xOffset+leftPadding, topPadding+yOffset+i*rowHeight, important);
            ++i;
        }
    }
    
    /**
     * sets visibility to the statistics board
     * @param vis
     */
    public void setVisible(boolean vis) {
        this.isVisible = vis;
    }
    
    /**
     * return true if the statistics board is visible on the graphics
     * @return
     */
    public boolean isVisible() {
        return isVisible;
    }
    
}
