/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.entity.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Transparency;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.exception.ExceptionHelper;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public class HomeScreenButton extends ScreenButton {
    
    private static final int fontSize = 30;
    private static final int width = 300;
    private static final int height = 60;
    private static final String fontFile = "VIDEOPHREAK.ttf";
    
    public HomeScreenButton(String text) {
        super(text, HomeScreenButton.width, HomeScreenButton.height, HomeScreenButton.fontSize);
        this.setBackground(new Color(0,0,0,0));
        this.setBorder(null);
        try {
            Font newFont = Font.createFont(Font.TRUETYPE_FONT,
                    (File)Global.getAssetManager().load(AssetType.Font, fontFile));
            this.setFont(newFont.deriveFont(Font.BOLD, HomeScreenButton.fontSize));
     
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
    }
    
    
    
}
