/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.structures;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import me.martin.radev.game.virtualcommando.geometry.CollisionDetection;
import me.martin.radev.game.virtualcommando.geometry.entity.Rectangle;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;

/**
 * A BinarySpaceTree (BST) uses the technique called binary space partitioning
 * to partition the space into subspaces. In the lowest subspace (leaf node in the tree), there is a set of static objects on the map. This drastically reduces 
 * the number of objects on the map to be checked for collisions. It is important
 * as well how big should be the smallest subspace and also the proper way of partitioning.
 * @author Marto
 */
public class BinarySpaceTree {

    private final int minimumWidth;
    private final int minimumHeight;
    private PartitionSpace root;

    /**
     * Creates a BST with a totalWidth and totalHeight.
     * minimumWidth and minimumHeight represent the width and height border for
     * smallest leaf node.
     * @param totalWidth
     * @param totalHeight
     * @param minimumWidth
     * @param minimumHeight
     */
    public BinarySpaceTree(int totalWidth, int totalHeight, int minimumWidth, int minimumHeight) {
        this.minimumWidth = minimumWidth;
        this.minimumHeight = minimumHeight;
        root = new PartitionSpace(new Vector2D(0, 0), 
                new Vector2D(totalWidth, totalHeight));
    }

    /**
     * returns the minimum height
     * @return
     */
    public int getMinimumHeight() {
        return minimumHeight;
    }

    /**
     * returns the minimum width
     * @return
     */
    public int getMinimumWidth() {
        return minimumWidth;
    }

    /**
     * returns the root of the tree. The tree is the biggest {@link PartitionSpace}
     * @return
     */
    public PartitionSpace getRoot() {
        return root;
    }
    
    /**
     * adds an object to the binary space tree
     * @param object
     */
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
    
    /**
     * returns a {@link Collection} of objects which are possible to 
     * collide with the object.
     * @param object
     * @return
     */
    public Collection<GraphicalObject> getObjectsInClosingArea(GraphicalObject object) {
        return getObjectsInClosingAreaRecursively(root, object);
    }
    
    private Set<GraphicalObject> getObjectsInClosingAreaRecursively(PartitionSpace tree, GraphicalObject object) {
        if (tree.leftSubspace == null) {
            return new HashSet<>(tree.getObjects());
        } else {
            Set<GraphicalObject> A = null, B = null;
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
    
    /**
     *
     */
    public class PartitionSpace {

        private Rectangle box;
        private PartitionSpace leftSubspace;
        private PartitionSpace rightSubspace;
        private List<GraphicalObject> objects;

        /**
         * Creates a {@link PartitionSpace} with a bottom left corner and a
         * top right corner.
         * @param bottomLeft
         * @param topRight
         */
        public PartitionSpace(Vector2D bottomLeft, Vector2D topRight) {
            box = new Rectangle(bottomLeft, topRight);
            this.leftSubspace = this.rightSubspace = null;
            partition();
            if (this.leftSubspace == null) objects = new LinkedList<>();
        }
        
        /**
         * returns the width of the space
         * @return
         */
        public int getWidth() {
            return (int)(box.getWidth());
        }
        
        /**
         * returns the height of the space
         * @return
         */
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

        /**
         * returns the objects in the space
         * @return
         */
        public List<GraphicalObject> getObjects() {
            return objects;
        }
        
        /**
         * adds an object to the list of objects
         * @param object
         */
        public void addObject(GraphicalObject object) {
            objects.add(object);
        }

        /**
         * returns left subspace
         * @return
         */
        public PartitionSpace getLeftSubspace() {
            return leftSubspace;
        }

        /**
         * returns right subspace
         * @return
         */
        public PartitionSpace getRightSubspace() {
            return rightSubspace;
        }

        /**
         * returns the space as a {@link Rectangle} object
         * @return
         */
        public Rectangle getBox() {
            return box;
        }

        
        
        
        
    }
    
}
