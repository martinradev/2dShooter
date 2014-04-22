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
 *
 * @author Marto
 */
public final class Statistics {

    private Map<StatisticsRow, StatisticsRow> orderedEntries;
    private Map<String, StatisticsRow> entries;
    private final int rowHeight = 25;
    private final int xOffset;
    private final int yOffset;
    private boolean isVisible;
    private final Color backgroundColor;
    private final int width;
    private final int height;
    
    // name frags deaths
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

    public void addRow(String name, String kills, String deaths) {
        StatisticsRow sr = new StatisticsRow(name, kills, deaths,
                (int) width, rowHeight);
        entries.put(name, sr);
        orderedEntries.put(sr, sr);
    }

    public void addFrag(String name) {
        StatisticsRow sr = entries.get(name);
        if (sr != null) {
            orderedEntries.remove(sr);
            sr.addFrag();
            orderedEntries.put(sr,sr);
        }
    }

    public void addDeath(String name) {
        StatisticsRow sr = entries.get(name);
        if (sr != null) {
            orderedEntries.remove(sr);
            sr.addDeath();
            orderedEntries.put(sr, sr);
        }
    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(backgroundColor);
        g2d.fillRect(xOffset, yOffset, width, height);
        int i = 1;
        for (StatisticsRow sr : orderedEntries.values()) {
            sr.render(g2d, xOffset, yOffset+i*rowHeight);
            ++i;
        }
    }
    
    public void setVisible(boolean vis) {
        this.isVisible = vis;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
}
