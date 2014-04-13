/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.weapon;

import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.Game;
import me.martin.radev.game.virtualcommando.game.logic.SinglePlayerGame;
import me.martin.radev.game.virtualcommando.game.unit.MyPlayer;
import me.martin.radev.game.virtualcommando.game.unit.Player;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.gui.GameView;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marto
 */
public class NormalWeaponTest {
    
    public NormalWeaponTest() {
        Global.setFrame(new GameView(500,500));
        Game game = new SinglePlayerGame(0); 
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of produceBullet method, of class NormalWeapon.
     */
    @Test
    public void testProduceBullet() {
        System.out.println("produceBullet");
        Vector2D direction = new Vector2D(1d,1d);
        Vector2D position = new Vector2D(10d,10d);;
        
        NormalWeapon instance = new NormalWeapon();
        Bullet result = instance.produceBullet(direction, position, Global.getGame().getMainPlayer());
        assertTrue(result!=null);
        instance.setCurrentAmmuCount(0);
        result = instance.produceBullet(direction, position, Global.getGame().getMainPlayer());
        assertTrue(result==null);
    }
    
    @Test
    public void weaponAmmoCount() {
        System.out.println("Weapon ammo count decreases test");
        Vector2D direction = new Vector2D(1d,1d);
        Vector2D position = new Vector2D(10d,10d);
        Player player = Global.getGame().getMainPlayer();
        NormalWeapon instance = new NormalWeapon();
        int count = instance.getCurrentAmmuCount();
        Bullet result = instance.produceBullet(direction, position, player);
        assertTrue(count-1 == instance.getCurrentAmmuCount());
    }
    
    @Test
    public void weaponAmmoCountNotNegative() {
        System.out.println("Weapon ammo is not negative");
        Vector2D direction = new Vector2D(1d,1d);
        Vector2D position = new Vector2D(10d,10d);
        Player player = Global.getGame().getMainPlayer();
        NormalWeapon instance = new NormalWeapon();
        instance.setCurrentAmmuCount(1);
        instance.produceBullet(direction, position, player);
        instance.produceBullet(direction, position, player);
        instance.produceBullet(direction, position, player);
        assertTrue(instance.getCurrentAmmuCount()==0);
        if (instance.produceBullet(direction, position, player) != null) {
            fail("Bullet must be null if it cannot be produced");
        }
    }
    
}