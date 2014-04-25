/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.graphics.statistics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.entity.buttons.HomeScreenButton;

/**
 *
 * @author Marto
 */
public class StatisticsRow {

    private String name;
    private String frags;
    private String deaths;
    private int width;
    private int height;
    private final String FONT_FILE = "VIDEOPHREAK.ttf";
    private final int FONT_SIZE = 14;
    private Font font;
    private final Color backgroundColor;
    private final int rowLeftPadding = 8;

    public StatisticsRow(String name, String frags, String deaths, int width, int height) {
        backgroundColor = new Color(Color.gray.getRed() / 255f, Color.gray.getGreen() / 255f,
                Color.gray.getBlue() / 255f, 0.2f);
        Border leftPadding = BorderFactory.createEmptyBorder(0, 20, 0, 0);
        this.width = width;
        this.height = height;


        this.name = name;
        this.frags = frags;
        this.deaths = deaths;
        if (this.name == null) name="";
        loadFont();
    }
    
    private void loadFont() {
        try {
            Font newFont = Font.createFont(Font.TRUETYPE_FONT,
                    (File) Global.getAssetManager().load(AssetType.Font, "fonts/"
                    + FONT_FILE));
            int fontSize = FONT_SIZE;
            if (this.name.equals("Name")) {
                fontSize += 5;
            }
            this.font = newFont.deriveFont(Font.BOLD, fontSize);

        } catch (IllegalArgumentException ex) {
            Global.getExceptionHandler().notificate(ExceptionHelper.IllegalArgumentException.getTitle(),
                    ExceptionHelper.IllegalArgumentException.getMessage());
        } catch (FontFormatException ex) {
            Global.getExceptionHandler().notificate(ExceptionHelper.FontFormatException.getTitle(),
                    ExceptionHelper.FontFormatException.getMessage());
        } catch (IOException ex) {
            Global.getExceptionHandler().notificate(ExceptionHelper.IOException.getTitle(),
                    ExceptionHelper.IOException.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != String.class) {
            if (o.getClass() == StatisticsRow.class) {
                StatisticsRow sr = (StatisticsRow) o;
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
        if (name == null) {
            return -1d;
        }
        if (this.name.equals("Name")) {
            return 99999999d;
        }
        return Double.parseDouble(frags + 1) / Double.parseDouble(deaths + 1);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void render(Graphics2D g2d, int xOffset, int yOffset, boolean important) {
        if (important) {
            g2d.setColor(backgroundColor);
            g2d.fillRect(xOffset, yOffset - (height-FONT_SIZE/2), width, height);
        }
        if (this.name.equals("Name")) {
            g2d.setColor(Color.red);
            g2d.drawLine(xOffset, yOffset+5, xOffset+width, yOffset+5);
        }
        g2d.setColor(Color.yellow);
        g2d.setFont(font);
        g2d.drawString(name, xOffset + rowLeftPadding, yOffset);
        g2d.drawString(frags, xOffset + width/3, yOffset);
        g2d.drawString(deaths, xOffset + 2*width/3, yOffset);
    }

    public String getName() {
        return name;
    }

    public String getFrags() {
        return frags;
    }

    public String getDeaths() {
        return deaths;
    }
    
    
}
