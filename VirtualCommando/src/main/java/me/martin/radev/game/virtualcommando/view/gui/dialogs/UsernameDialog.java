/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import me.martin.radev.game.virtualcommando.Global;

/**
 *
 * @author Marto
 */
public class UsernameDialog extends JDialog {
    
    private JLabel labelUsername;
    private JTextField textUsername;
    private JButton saveButton;
    private JFrame owner;
    
    public UsernameDialog(JFrame owner) {
        super(owner, "Enter username in game", true);
        this.owner = owner;
        init();
    }
    
    private void init() {
        addCloseListener();
        setLayout(new FlowLayout());
        labelUsername = new JLabel("Username: ");
        textUsername = new JTextField();
        textUsername.setColumns(10);
        saveButton = new JButton("Save");
        add(labelUsername);
        add(textUsername);
        add(saveButton);
        setPreferredSize(new Dimension(300, 80));
        pack();
        setVisible(true);
        
    }
    
    private void addCloseListener() {
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we) {
                UsernameDialog.this.dispose();
                Global.getFrame().setVisible(false);
                Global.getFrame().dispose();
            }

            @Override
            public void windowClosed(WindowEvent we) {
               
            }

            @Override
            public void windowIconified(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            
        });
    }
    
    
    
}
