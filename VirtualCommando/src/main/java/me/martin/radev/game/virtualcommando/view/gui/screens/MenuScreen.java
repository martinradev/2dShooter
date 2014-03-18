/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.screens;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.GuiUtil;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;
import me.martin.radev.game.virtualcommando.view.gui.entity.buttons.HomeScreenButton;
import me.martin.radev.game.virtualcommando.view.gui.entity.buttons.HomeScreenButtonTypes;
import me.martin.radev.game.virtualcommando.view.gui.entity.panels.BackgroundPanel;
import me.martin.radev.game.virtualcommando.view.gui.listener.homescreen.HomeScreenButtonListener;

/**
 *
 * @author Marto
 */
public class MenuScreen extends Screen {
    
    private JPanel menuGroup;
    private HomeScreenButton singlePlayerButton;
    private HomeScreenButton multiPlayerButton;
    private HomeScreenButton exitButton;
    private JPanel logo;
    private final int bottomItemMargin = 20;
    private int width;
    private int height;
    
    public MenuScreen(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        initialize(width, height);
        this.repaint();
    }
    
    private void initialize(int width, int height) {
        menuGroup = new JPanel();
        menuGroup.setLayout(new BoxLayout(menuGroup, BoxLayout.Y_AXIS));
        
        menuGroup.setBackground(Color.black);
        Sprite logoSprite = (Sprite)
                Global.getAssetManager().load(AssetType.Sprite, "logo.png");
        logo = new BackgroundPanel(logoSprite);
        logo.setPreferredSize(new Dimension(logoSprite.getWidth(), logoSprite.getHeight()));
        logo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuGroup.add(logo);
        this.addRigidArea(menuGroup, bottomItemMargin);
        
        HomeScreenButtonListener buttonListener = new HomeScreenButtonListener();
        
        singlePlayerButton = new HomeScreenButton("Single player");
        singlePlayerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        singlePlayerButton.setName(HomeScreenButtonTypes.Singleplayer.toString());
        singlePlayerButton.addActionListener(buttonListener);
        menuGroup.add(singlePlayerButton);
        this.addRigidArea(menuGroup, bottomItemMargin);
        
        multiPlayerButton = new HomeScreenButton("Multi player");
        multiPlayerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        multiPlayerButton.setName(HomeScreenButtonTypes.Multiplayer.toString());
        multiPlayerButton.addActionListener(buttonListener);
        menuGroup.add(multiPlayerButton);
        this.addRigidArea(menuGroup, bottomItemMargin);
        
        exitButton = new HomeScreenButton("Exit");
        exitButton.setName(HomeScreenButtonTypes.Exit.toString());
        exitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        exitButton.addActionListener(buttonListener);
        menuGroup.add(exitButton);
        
        menuGroup.setPreferredSize(menuGroup.getPreferredSize());
        menuGroup.setVisible(true);
        
        this.add(menuGroup);
        
        //this.setSize(width, height);
        this.setBackground(Color.BLACK);
        this.setSize(width, height);
        this.setVisible(true);
        
    }
    
    private void addRigidArea(Container cont, int margin) {
        cont.add(Box.createVerticalStrut(margin));
    }

    @Override
    public void validate() {
        this.setSize(GuiUtil.rescale(width), GuiUtil.rescale(height));
        super.validate();;
    }
    
    
    
}
