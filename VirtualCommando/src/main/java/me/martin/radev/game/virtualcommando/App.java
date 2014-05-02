package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.view.gui.GameView;

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
        // commented because some computers with ubuntu do not support opengl? lol?
        //System.setProperty("sun.java2d.opengl","True");
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
