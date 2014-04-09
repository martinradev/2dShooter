package me.martin.radev.game.virtualcommando.structures;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.martin.radev.game.virtualcommando.structures.BinarySpaceTree.PartitionSpace;

/**
 *
 * @author Marto
 */
public class BinarySpaceTreeTest {
    
    private static BinarySpaceTree bst;
    private static PaintingScreen drawScreen;
    
    public static void main( String[] args )
    {
        final int width = 800;
        final int height = 600;
        bst = new BinarySpaceTree(width, height, 40, 40);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
        drawScreen = new PaintingScreen(width, height);
        frame.add(drawScreen);
        drawScreen.repaint();
        frame.revalidate();
    }
    
    private static class PaintingScreen extends JPanel {
        
        public PaintingScreen(int width, int height) {
            super();
            super.setPreferredSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            Stack<PartitionSpace> tree = new Stack<>();
            tree.add(bst.getRoot());
            while (!tree.isEmpty()) {
                PartitionSpace node = tree.pop();
                g.drawRect((int)node.getBox().getBottomLeftCorner().getX(), (int)node.getBox().getBottomLeftCorner().getY(),
                        (int)node.getBox().getTopRightCorner().getX(), (int)node.getBox().getTopRightCorner().getY());
                if (node.getLeftSubspace() != null) {
                    tree.push(node.getLeftSubspace());
                    tree.push(node.getRightSubspace());
                }
            }
        }
        
        
        
    }
    
}