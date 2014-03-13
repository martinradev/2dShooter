package me.martin.radev.game.virtualcommando;

import me.martin.radev.game.virtualcommando.map.SimpleObjectMap;
import me.martin.radev.game.virtualcommando.map.loader.ObjectLoader;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalObject;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
import me.martin.radev.game.virtualcommando.view.gui.test.TestScreen;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ObjectLoader ol = new ObjectLoader(null);
        
        SimpleObjectMap som = (SimpleObjectMap)ol.load("D:\\map.tmx");
        
        new TestScreen(som);
        
        
    }
}
