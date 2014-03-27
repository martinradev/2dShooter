/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens.gamescreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Marto
 */
public class AmmoBar extends JPanel {
    
    private int totalAmmo;
    private int currentAmmo;
    private final int FONT_SIZE = 14;
    private final int startingHeight = 70;
    private final int startingWidth = 200;
    private final int leftOffset = 175;
    private final int bottomOffset = 15;
    private int parentWidth;
    private int parentHeight;
    
    public AmmoBar(int totalAmmo, int currentAmmo, int parentWidth, int parentHeight) {
        this.totalAmmo = totalAmmo;
        this.currentAmmo = currentAmmo;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.setBounds(leftOffset, 
                (parentHeight-bottomOffset)-startingHeight
                , startingWidth, startingHeight);
        this.setForeground(Color.red);
        this.setBackground(new Color(Color.red.getRed(), Color.red.getGreen(), Color.red.getBlue(), 0));
        this.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
    }


    public void setTotalAmmo(int totalAmmo) {
        this.totalAmmo = totalAmmo;
    }

    public void setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawString("12", leftOffset, (parentHeight-bottomOffset)-startingHeight);
        String str = Integer.toString(currentAmmo) + " / " + Integer.toString(totalAmmo);
        g.drawString(str, 0, startingHeight);
    }
    
    
    
}
