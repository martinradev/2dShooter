/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.entity.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public class HomeScreenButton extends ScreenButton implements MouseListener {

    private static final int FONT_SIZE = 30;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 60;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color HOVER_COLOR = Color.YELLOW;
    private static final String FONT_FILE = "VIDEOPHREAK.ttf";
    private Font font;
    
    public HomeScreenButton(String text) {
        super(text, HomeScreenButton.WIDTH, HomeScreenButton.HEIGHT, HomeScreenButton.FONT_SIZE);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        try {
            Font newFont = Font.createFont(Font.TRUETYPE_FONT,
                    (File) Global.getAssetManager().load(AssetType.Font, FONT_FILE));
            this.font = newFont;
            this.setFont(newFont.deriveFont(Font.BOLD, HomeScreenButton.FONT_SIZE));

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
        this.setForeground(Color.WHITE);
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void mouseEntered(MouseEvent me) {
        this.setForeground(HomeScreenButton.HOVER_COLOR);
    }

    public void mouseExited(MouseEvent me) {
        this.setForeground(HomeScreenButton.DEFAULT_COLOR);
    }
}
