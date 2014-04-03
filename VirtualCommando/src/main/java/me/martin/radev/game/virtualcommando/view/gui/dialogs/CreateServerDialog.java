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
import me.martin.radev.game.virtualcommando.Settings;
import me.martin.radev.game.virtualcommando.view.gui.screens.MenuScreen;

/**
 *
 * @author Marto
 */
public class CreateServerDialog extends JDialog {

    private JLabel labelSocket;
    private JLabel labelPassword;
    private JTextField textSocket;
    private JTextField textPassword;
    private JButton createButton;
    private JFrame owner;

    /**
     *
     * @param owner
     */
    public CreateServerDialog(JFrame owner) {
        super(owner, "Create server", true);
        this.owner = owner;
        init();
    }

    private void init() {
        addCloseListener();

        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(boxLayout);
        FlowLayout flowLayout = new FlowLayout();

        labelSocket = new JLabel("Port:");
        labelPassword = new JLabel("Password:");
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

        firstRow.add(labelSocket);
        firstRow.add(textSocket);

        secondRow.add(labelPassword);
        secondRow.add(textPassword);

        thirdRow.add(createButton);

        this.getContentPane().add(firstRow);
        this.getContentPane().add(secondRow);
        this.getContentPane().add(thirdRow);

        setPreferredSize(new Dimension(400, 140));
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
                String socketNumber = textSocket.getText();
                String password = textPassword.getText();
                boolean error = false;
                try {
                    Integer.parseInt(socketNumber);
                } catch (NumberFormatException e) {
                    error = true;
                }
                if (!error) {
                    CreateServerDialog.this.dispose();
                }
            }
        });
    }
}
