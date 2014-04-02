/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

/**
 *
 * @author Marto
 */
public enum PlayerType {
    
    /**
     *
     */
    NormalPlayer(100, 30, 30),
    /**
     *
     */
    EasyBot(75, 30, 30),
    /**
     *
     */
    NormalBot(100, 30, 30),
    /**
     *
     */
    HardBot(150, 30, 30),
    /**
     *
     */
    InsaneBot(200, 35, 35);
    
    private int maxHealth;
    private int width;
    private int height;
    
    private PlayerType(int health, int width, int height) {
        this.maxHealth = health;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }
    
    
    
}
