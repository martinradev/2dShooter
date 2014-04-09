/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.util.LinkedList;
import java.util.List;
import me.martin.radev.game.virtualcommando.geometry.CollisionDetection;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 *
 * @author Marto
 */
public class BinarySpaceTree {

    private final int minimumWidth;
    private final int minimumHeight;
    private PartitionSpace root;

    public BinarySpaceTree(int totalWidth, int totalHeight, int minimumWidth, int minimumHeight) {
        this.minimumWidth = minimumWidth;
        this.minimumHeight = minimumHeight;
        root = new PartitionSpace(new Vector2D(0, 0), 
                new Vector2D(totalWidth, totalHeight));
    }

    public int getMinimumHeight() {
        return minimumHeight;
    }

    public int getMinimumWidth() {
        return minimumWidth;
    }

    public PartitionSpace getRoot() {
        return root;
    }
    
    public void addElement(GraphicalObject object) {
        addElementRecursively(root, object);
    }
    
    private void addElementRecursively(PartitionSpace tree, GraphicalObject object) {
        if (tree.leftSubspace == null) {
            tree.addObject(object);
        } else {
            if (CollisionDetection.doCollide(tree.leftSubspace.getBox(), object.getBody())) {
                addElementRecursively(tree.leftSubspace, object);
            }
            if (CollisionDetection.doCollide(tree.rightSubspace.getBox(), object.getBody())) {
                addElementRecursively(tree.rightSubspace, object);
            }
        }
    }
    
    public List<GraphicalObject> getObjectsInClosingArea(GraphicalObject object) {
        return getObjectsInClosingAreaRecursively(root, object);
    }
    
    private List<GraphicalObject> getObjectsInClosingAreaRecursively(PartitionSpace tree, GraphicalObject object) {
        if (tree.leftSubspace == null) {
            return new LinkedList<>(tree.getObjects());
        } else {
            List<GraphicalObject> A = null, B = null;
            if (CollisionDetection.doCollide(tree.leftSubspace.getBox(), object.getBody())) {
                A = getObjectsInClosingAreaRecursively(tree.leftSubspace, object);
            }
            if (CollisionDetection.doCollide(tree.rightSubspace.getBox(), object.getBody())) {
                B = getObjectsInClosingAreaRecursively(tree.rightSubspace, object);
            }
            if (A != null && B != null) {
                A.addAll(B);
                return A;
            } else if (A != null) {
                return A;
            } else if (B != null) {
                return B;
            } else {
                System.out.println("ERROR");
                System.out.println(object.getBody().getCenter());
            }
        }
        return null;
    }
    
    public class PartitionSpace {

        private Rectangle box;
        private PartitionSpace leftSubspace;
        private PartitionSpace rightSubspace;
        private List<GraphicalObject> objects;

        public PartitionSpace(Vector2D bottomLeft, Vector2D topRight) {
            box = new Rectangle(bottomLeft, topRight);
            this.leftSubspace = this.rightSubspace = null;
            partition();
            if (this.leftSubspace == null) objects = new LinkedList<>();
        }
        
        public int getWidth() {
            return (int)(box.getWidth());
        }
        
        public int getHeight() {
            return (int)(box.getHeight());
        }
        
        private void partition() {
            if (this.getWidth() <= BinarySpaceTree.this.getMinimumWidth()
                    && this.getHeight() <= BinarySpaceTree.this.getMinimumHeight()) {
                return;
            }
            if (this.getWidth() < this.getHeight()) {
                double minY = (box.getBottomLeftCorner().getY() + box.getTopRightCorner().getY())/2d;
                leftSubspace = 
                        new PartitionSpace(box.getBottomLeftCorner(), new Vector2D(box.getTopRightCorner().getX(), minY));
                rightSubspace = 
                        new PartitionSpace(new Vector2D(box.getBottomLeftCorner().getX(), minY), box.getTopRightCorner());
            } else {
                double minX = (box.getBottomLeftCorner().getX() + box.getTopRightCorner().getX())/2d;
                leftSubspace = 
                        new PartitionSpace(box.getBottomLeftCorner(), new Vector2D(minX, box.getTopRightCorner().getY()));
                rightSubspace = 
                        new PartitionSpace(new Vector2D(minX, box.getBottomLeftCorner().getY()), box.getTopRightCorner());
            }
            
           
            
        }

        public List<GraphicalObject> getObjects() {
            return objects;
        }
        
        public void addObject(GraphicalObject object) {
            objects.add(object);
        }

        public PartitionSpace getLeftSubspace() {
            return leftSubspace;
        }

        public PartitionSpace getRightSubspace() {
            return rightSubspace;
        }

        public Rectangle getBox() {
            return box;
        }

        
        
        
        
    }
    
}
