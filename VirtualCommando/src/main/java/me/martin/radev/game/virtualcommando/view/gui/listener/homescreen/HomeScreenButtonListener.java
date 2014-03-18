/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.listener.homescreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.view.gui.entity.buttons.HomeScreenButtonTypes;

/**
 *
 * @author Marto
 */
public class HomeScreenButtonListener implements ActionListener {
    
    public void actionPerformed(ActionEvent ae) {
        JButton button = (JButton)ae.getSource();
        if (button.getName().equals(HomeScreenButtonTypes.Singleplayer.toString())) {
            // TODO
            System.out.println("Single player");
        } else if (button.getName().equals(HomeScreenButtonTypes.Multiplayer.toString())) {
            // TODO
            System.out.println("Multi player");
        } else if (button.getName().equals(HomeScreenButtonTypes.Exit.toString())) {
            Global.getFrame().dispose();
        }
    }
    
}
