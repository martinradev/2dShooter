package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.view.gui.GameView;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;

/**
 * Hello world!
 *
 */
public class App 
{
    
    private final int DEFALUT_WIDTH=800;
    private final int DEFAULT_HEIGHT=600;
    
    public App() {
        GameView gv = new GameView(DEFALUT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public static void main( String[] args )
    {
        new App();
    }
}
