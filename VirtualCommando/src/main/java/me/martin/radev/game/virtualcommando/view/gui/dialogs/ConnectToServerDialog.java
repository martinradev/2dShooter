/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.dialogs;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;

/**
 *
 * @author Marto
 */
public class ConnectToServerDialog extends JDialog {
    private JLabel labelSocket;
    private JLabel labelPassword;
    private JLabel labelIp;
    private JTextField textSocket;
    private JTextField textIp;
    private JTextField textPassword;
    private JButton createButton;
    private JFrame owner;
    
    /**
     *
     * @param owner
     */
    public ConnectToServerDialog(JFrame owner) {
        super(owner, "Connect to server", true);
        this.owner = owner;
        init();
    }
    
    private void init() {
        addCloseListener();
        
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(boxLayout);
        FlowLayout flowLayout = new FlowLayout();
        
        labelIp = new JLabel("IP:");
        labelSocket = new JLabel("Port:");
        labelPassword = new JLabel("Password:");
        
        textIp = new JTextField("");
        textIp.setColumns(10);
        
        textSocket = new JTextField("");
        textSocket.setColumns(5);
        
        textPassword = new JTextField("");
        textPassword.setColumns(12);
        
        createButton = new JButton("Create");
        addButtonActionListener();
        
        JPanel firstRow = new JPanel();
        firstRow.setLayout(flowLayout);
        
        JPanel secondRow = new JPanel();
        secondRow.setLayout(flowLayout);
        
        JPanel thirdRow = new JPanel();
        thirdRow.setLayout(flowLayout);
        
        JPanel fourthRow = new JPanel();
        fourthRow.setLayout(flowLayout);
        
        firstRow.add(labelIp);
        firstRow.add(textIp);
        
        secondRow.add(labelSocket);
        secondRow.add(textSocket);
        
        thirdRow.add(labelPassword);
        thirdRow.add(textPassword);
        
        fourthRow.add(createButton);
        
        this.getContentPane().add(firstRow);
        this.getContentPane().add(secondRow);
        this.getContentPane().add(thirdRow);
        this.getContentPane().add(fourthRow);
        
        setPreferredSize(new Dimension(400, 160));
        pack();
        setVisible(true);
        
    }

    /**
     *
     * @return
     */
    public JTextField getTextSocket() {
        return textSocket;
    }

    /**
     *
     * @return
     */
    public JTextField getTextPassword() {
        return textPassword;
    }

    /**
     *
     * @return
     */
    public JTextField getTextIp() {
        return textIp;
    }

    private void addCloseListener() {
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing(WindowEvent we) {
                Global.getFrame().setScreen(
                        new MenuScreen(Global.getWindowWidth(), Global.getWindowHeight()));
                Global.getFrame().getScreen().revalidate();
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
    
    private void addButtonActionListener() {
        createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String ipNumber = textIp.getText();
                String socketNumber = textSocket.getText();
                String password = textPassword.getText();
                
                // TODO check if ip is valid
                
                boolean error = false;
                try {
                    Integer.parseInt(socketNumber);
                } catch(NumberFormatException e) {
                    error = true;
                }
                if (!error) {
                    ConnectToServerDialog.this.dispose();
                }
            }
            
        });
    }
}
