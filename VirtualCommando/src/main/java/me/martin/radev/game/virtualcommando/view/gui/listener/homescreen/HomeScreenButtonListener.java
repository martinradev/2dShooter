/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.listener.homescreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.ConnectedToServerGame;
import me.martin.radev.game.virtualcommando.game.logic.Game;
import me.martin.radev.game.virtualcommando.game.logic.MultiPlayerGame;
import me.martin.radev.game.virtualcommando.game.logic.SinglePlayerGame;
import me.martin.radev.game.virtualcommando.view.gui.dialogs.ConnectToServerDialog;
import me.martin.radev.game.virtualcommando.view.gui.dialogs.CreateServerDialog;
import me.martin.radev.game.virtualcommando.view.gui.dialogs.UsernameDialog;
import me.martin.radev.game.virtualcommando.view.gui.entity.buttons.HomeScreenButtonTypes;

/**
 *
 * @author Marto
 */
public class HomeScreenButtonListener implements ActionListener {
    
    /**
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton button = (JButton)ae.getSource();
        if (button.getName().equals(HomeScreenButtonTypes.Singleplayer.toString())) {
            Game game = new SinglePlayerGame(5);
            Global.setGame(game);
        } else if (button.getName().equals(HomeScreenButtonTypes.Multiplayer.toString())) {
            CreateServerDialog csDialog = 
                    new CreateServerDialog(Global.getFrame());
            String port = csDialog.getTextSocket().getText();
            String password = csDialog.getTextPassword().getText();
            Game game = new MultiPlayerGame(port, password);
        } else if (button.getName().equals(HomeScreenButtonTypes.Connect.toString())) {
            ConnectToServerDialog ctsDialog = 
                    new ConnectToServerDialog(Global.getFrame());
            String ip = ctsDialog.getTextIp().getText();
            String port = ctsDialog.getTextSocket().getText();
            String password = ctsDialog.getTextPassword().getText();
            Game game = new ConnectedToServerGame(ip, port, password);
        } else if (button.getName().equals(HomeScreenButtonTypes.Exit.toString())) {
            Global.getFrame().dispose();
        }
    }
    
}
