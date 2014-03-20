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
    
    NormalPlayer(100, 40, 40), EasyBot(75, 40, 40),
    NormalBot(100, 40, 40),
    HardBot(150, 50, 50),
    InsaneBot(200, 65, 65);
    
    private int maxHealth;
    private int width;
    private int height;
    
    private PlayerType(int health, int width, int height) {
        this.maxHealth = health;
        this.width = width;
        this.height = height;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
