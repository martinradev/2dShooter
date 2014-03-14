package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.map.SimpleObjectMap;
import me.martin.radev.game.virtualcommando.map.loader.ObjectLoader;
import me.martin.radev.game.virtualcommando.view.gui.GameView;
import me.martin.radev.game.virtualcommando.view.gui.screens.GameScreen;
import me.martin.radev.game.virtualcommando.view.gui.screens.Screen;

/**
 * Hello world!
 *
 */
public class App 
{
    
    private final int DEFALUT_WIDTH=800;
    private final int DEFAULT_HEIGHT=600;
    
    public App() {
        // TODO test
        ObjectLoader ol = new ObjectLoader(null);
        
        SimpleObjectMap som = (SimpleObjectMap)ol.load("D:\\map.tmx");
        
        GameView gv = new GameView(DEFALUT_WIDTH, DEFAULT_HEIGHT);
        
        Screen scr = new GameScreen(som, DEFALUT_WIDTH, DEFAULT_HEIGHT);
        
        gv.setGameScreen(scr);
    }
    
    public static void main( String[] args )
    {
        new App();
    }
}
