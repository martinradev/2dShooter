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
        Global.setDefaultWindowWidth(DEFALUT_WIDTH);
        Global.setDefaultWindowHeight(DEFAULT_HEIGHT);
        GameView gv = new GameView(DEFALUT_WIDTH, DEFAULT_HEIGHT);
        Global.setFrame(gv);
    }
    
    public static void main( String[] args )
    {
        new App();
    }
}
