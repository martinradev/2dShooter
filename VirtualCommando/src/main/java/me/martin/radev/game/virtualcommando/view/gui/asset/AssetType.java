/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.view.gui.asset;

/**
 *
 * @author Marto
 */
public enum AssetType {
    
    Sprite(),Sound(),Map(), Font();
    
    private String folder;
    
    private AssetType() {
        this.folder = this.toString().toLowerCase() + "s";
    }

    public String getFolder() {
        return folder;
    }
    
    

}
