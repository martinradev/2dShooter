package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.view.gui.GameView;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;

/**
 * Hello world!
 *
 */
public class App 
{
    
    private final int DEFALUT_WIDTH=1000;
    private final int DEFAULT_HEIGHT=700;
    
    /**
     *
     */
    public App() {
        Global.setDefaultWindowWidth(DEFALUT_WIDTH);
        Global.setDefaultWindowHeight(DEFAULT_HEIGHT);
        GameView gv = new GameView(DEFALUT_WIDTH, DEFAULT_HEIGHT);
        gv.openUsernameDialog();
    }
    
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        new App();
    }
}
