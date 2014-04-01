/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import me.martin.radev.game.virtualcommando.Global;
import me.martin.radev.game.virtualcommando.game.logic.ConnectedToServerGame;
import me.martin.radev.game.virtualcommando.game.logic.MultiPlayerGame;
import me.martin.radev.game.virtualcommando.game.logic.server.GameServer;
import me.martin.radev.game.virtualcommando.game.weapon.NormalWeapon;
import me.martin.radev.game.virtualcommando.game.weapon.Weapon;
import me.martin.radev.game.virtualcommando.game.weapon.bullet.Bullet;
import me.martin.radev.game.virtualcommando.geometry.MathUtil;
import me.martin.radev.game.virtualcommando.geometry.entity.Vector2D;
import me.martin.radev.game.virtualcommando.view.graphics.animation.LinearAnimation;
import me.martin.radev.game.virtualcommando.view.graphics.entity.GraphicalRectangle;
import me.martin.radev.game.virtualcommando.view.graphics.entity.Sprite;
import me.martin.radev.game.virtualcommando.view.gui.asset.AssetType;

/**
 *
 * @author Marto
 */
public abstract class Player extends GraphicalRectangle {

    protected double angleOffset = 0d;
    protected double currentAngleOfRotation = 0d;
    protected int maxHealth;
    protected int currentHealth;
    protected double velocity = 2.2d;
    protected LinearAnimation walkAnimation;
    protected Sprite staticSprite;
    protected Vector2D lastMovement;
    protected Weapon weapon;
    private double respawnTime = 0d;
    private String name;

    public Player(String name, int maxHealth, Vector2D startingPosition,
            int gObjectWidth, int gObjectHeight, Color color) {
        super(startingPosition,
                (double) gObjectWidth, (double) gObjectHeight, color);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        staticSprite = (Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/solid.png");
        super.setSprite(staticSprite);
        walkAnimation = new LinearAnimation();
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/frame1.png"));
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "soldier/frame2.png"));
        Global.getGame().bind(walkAnimation);
        lastMovement = new Vector2D(0d, 0d);
        currentAngleOfRotation = 0d;
        this.name = name;
        initWeapon();

    }

    private void initWeapon() {
        weapon = new NormalWeapon();
    }

    public void move(Vector2D direction) {
        Vector2D xDirection = new Vector2D(direction.getX(), 0);
        Vector2D yDirection = new Vector2D(0, direction.getY());
        xDirection.scale(-velocity);
        yDirection.scale(-velocity);

        this.getBody().translate(-xDirection.getX(), 0);
        if (Global.getGameFlow().isPlayerColliding(this)) {
            this.getBody().translate(xDirection.getX(), 0);
        } else {
            super.setSprite(walkAnimation.getCurrent());
        }

        this.getBody().translate(0, -yDirection.getY());
        if (Global.getGameFlow().isPlayerColliding(this)) {
            this.getBody().translate(0, yDirection.getY());
        } else {
            super.setSprite(walkAnimation.getCurrent());
        }
        // TODO
        updatePlayerStatus();
    }
    
    private void updatePlayerStatus() {
        if (this.getClass() == MyPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().updatePlayer(this);
            } else if (Global.getGame().getClass() == ConnectedToServerGame.class) {
                ((ConnectedToServerGame)Global.getGame())
                        .getConcurrencyProtocol().updatePlayer(this);
            } 
        } else if (this.getClass() == ServerPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().updatePlayer(this);
            }
        } 
    }

    public void stopMovement() {
        super.setSprite(staticSprite);
    }

    public void shoot(Vector2D direction) {
        if (respawnTime > 0d) {
            return;
        }
        Vector2D position = new Vector2D(this.getBody().getCenter());
        Bullet bullet = weapon.produceBullet(direction, position, this);
        if (bullet == null) {
            // notify out of ammo
        } else {
            Global.getGame().getGameEntities().addBullet(bullet);
        }
        // TODO
        if (this.getClass() == MyPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().shootPlayer(this, direction);
            } else if (Global.getGame().getClass() == ConnectedToServerGame.class) {
                ((ConnectedToServerGame)Global.getGame())
                        .getConcurrencyProtocol().shootPlayer(this, direction);
            } 
        } else if (this.getClass() == ServerPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().shootPlayer(this, direction);
            }
        }
    }

    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0) {
            kill();
        }
    }

    public void regenerate(int health) {
        this.currentHealth = Math.min(currentHealth + health, maxHealth);
    }

    public void regenerateFully() {
        this.currentHealth = maxHealth;
        updatePlayerStatus();
    }

    public void kill() {
        this.respawnTime = 0d;
        Global.getGame().getGameEntities().getPlayers().remove(this);
        Global.getGame().getRespawner().addPlayer(this);
    }

    public double getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(double time) {
        this.respawnTime = time;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public abstract void processMovement();

    public abstract void processRotation();

    public abstract void processShooting();

    public void rotate(double angle) {
        super.getBody().relativeRotate(
                super.getBody().getCenter(),
                angle - angleOffset);
        // TODO
        if (this.getClass() == MyPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().rotatePlayer(this, angle);
            } else if (Global.getGame().getClass() == ConnectedToServerGame.class) {
                ((ConnectedToServerGame)Global.getGame())
                        .getConcurrencyProtocol().rotatePlayer(this, angle);
            } 
        } else if (this.getClass() == DummyPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().rotatePlayer(this, angle);
            }
        }
        if (Global.getGameFlow().isPlayerColliding(this)) {
            super.getBody().relativeRotate(
                    super.getBody().getCenter(),
                    -(angle - angleOffset));
        } else {
            currentAngleOfRotation = angle - angleOffset;
            angleOffset = angle;
        }
    }

    public double getCurrentAngleOfRotation() {
        return currentAngleOfRotation;
    }

    public double getAngleOffset() {
        return angleOffset;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    
}
