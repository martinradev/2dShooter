/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.martin.radev.game.virtualcommando.game.unit;

import java.awt.Color;
import java.awt.Graphics2D;
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

    /**
     *
     */
    protected double angleOffset = 0d;
    /**
     *
     */
    protected double currentAngleOfRotation = 0d;
    /**
     *
     */
    protected int maxHealth;
    /**
     *
     */
    protected int currentHealth;
    /**
     *
     */
    protected double velocity = 2.0d;
    /**
     *
     */
    protected LinearAnimation walkAnimation;
    /**
     *
     */
    protected Sprite staticSprite;
    /**
     *
     */
    protected Vector2D lastMovement;
    /**
     *
     */
    protected Weapon weapon;
    private double respawnTime = 0d;
    private String name;

    /**
     * Creates a player with a given name, maxHealth, startingPosition, width, height and oclor
     * @param name
     * @param maxHealth
     * @param startingPosition
     * @param gObjectWidth
     * @param gObjectHeight
     * @param color
     */
    public Player(String name, int maxHealth, Vector2D startingPosition,
            int gObjectWidth, int gObjectHeight, Color color) {
        super(startingPosition,
                (double) gObjectWidth, (double) gObjectHeight, color);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        staticSprite = (Sprite) Global.getAssetManager().load(AssetType.Sprite,
                "sprites/soldier/solid.png");
        super.setSprite(staticSprite);
        walkAnimation = new LinearAnimation();
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "sprites/soldier/frame1.png"));
        walkAnimation.addSprite((Sprite) Global.getAssetManager().load(AssetType.Sprite, "sprites/soldier/frame2.png"));
        Global.getGame().bind(walkAnimation);
        lastMovement = new Vector2D(0d, 0d);
        currentAngleOfRotation = 0d;
        this.name = name;
        initWeapon();

    }

    private void initWeapon() {
        weapon = new NormalWeapon();
    }

    /**
     * moves the player to a given direction. The direction is multiplied by the velocity of the player
     * @param direction
     */
    public void move(Vector2D direction) {
        if (direction == null) return;
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

    /**
     * stops the movements of the player. The sprite of the player will
     * be set to static.
     */
    public void stopMovement() {
        super.setSprite(staticSprite);
    }

    /**
     * The player shoots at a given direction. 
     * This produces a bullet. The direction should be a unit vector
     * @param direction
     */
    public void shoot(Vector2D direction) {
        if (!isPlayerActive()) {
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

    /**
     * the player takes damage. If the damage is more than the current health,
     * the player is killed
     * @param damage
     */
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth <= 0) {
            kill();
        }
    }

    /**
     * the player regenerates with a given health.
     * @param health
     */
    public void regenerate(int health) {
        this.currentHealth = Math.min(currentHealth + health, maxHealth);
    }

    /**
     * the player regenerate to its maximum health
     */
    public void regenerateFully() {
        this.currentHealth = maxHealth;
        updatePlayerStatus();
    }

    /**
     * the player is killed. The player is added to be processed by the respawner
     * and removed from the rendering objects
     */
    public void kill() {
        this.respawnTime = 0d;
        Global.getGame().getGameEntities().getPlayers().remove(this);
        Global.getGame().getRespawner().addPlayer(this);
    }

    /**
     * returns the current respawn time of the player
     * @return
     */
    public double getRespawnTime() {
        return respawnTime;
    }

    /**
     * sets the respawn time of the player
     * @param time
     */
    public void setRespawnTime(double time) {
        this.respawnTime = time;
    }

    /**
     * returns the current health of the player
     * @return
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     *
     */
    public abstract void processMovement();

    /**
     *
     */
    public abstract void processRotation();

    /**
     *
     */
    public abstract void processShooting();

    /**
     * rotates the player to a given angle
     * @param angle
     */
    public void rotate(double angle) {
        super.getBody().relativeRotate(
                super.getBody().getCenter(),
                angle - angleOffset);
        // TODO
        if (Global.getGameFlow().isPlayerColliding(this)) {
            super.getBody().relativeRotate(
                    super.getBody().getCenter(),
                    -(angle - angleOffset));
        } else {
            currentAngleOfRotation = angle - angleOffset;
            angleOffset = angle;
        }
        if (MathUtil.relativelyEqualBigEps(currentAngleOfRotation, 0d)) return;
        if (this.getClass() == MyPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().rotatePlayer(this, angle);
            } else if (Global.getGame().getClass() == ConnectedToServerGame.class) {
                ((ConnectedToServerGame)Global.getGame())
                        .getConcurrencyProtocol().rotatePlayer(this, angle);
            } 
        } else if (this.getClass() == ServerPlayer.class) {
            if (Global.getGame().getClass() == MultiPlayerGame.class) {
                GameServer server = ((MultiPlayerGame) Global.getGame()).getServer();
                server.getServerSync().rotatePlayer(this, angle);
            }
        }
        
    }
    
    /**
     *
     * @return
     */
    public boolean isPlayerActive() {
        return getRespawnTime()<=0d || getRespawnTime() >= Global.getGame().getRespawner().getTimeTillRespawn();
    }

    /**
     * returns the current angle of rotation
     * @return
     */
    public double getAngleOffset() {
        return angleOffset;
    }

    /**
     * returns the name of the player
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * returns the max health of the player
     * @return
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * returns the weapon of the player
     * @return
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * sets the max health of the player
     * @param maxHealth
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * returns the current health of the player
     * @param currentHealth
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        return this.getName().equals(((Player)o).getName());
    }

    @Override
    public void render(Graphics2D g2d, int xOffset, int yOffset, double angle) {
        super.render(g2d, xOffset, yOffset, angle);
        drawHealthBar(g2d, xOffset, yOffset);
    }
    
    private void drawHealthBar(Graphics2D g2d, int xOffset, int yOffset) {
        int barWidth = 50;
        int barHeight = 10;
        int emptyHeightSpace = 8;
        int startingXCoordinate = -barWidth/2;
        int startingYCoordinate = -emptyHeightSpace -(int)super.getBody().getHeight()/2 - barHeight;
        double percentFilled = (double)this.getCurrentHealth() / (double)this.getMaxHealth();
        Vector2D sprV2d = new Vector2D(super.getBody().getCenter());
        g2d.translate(sprV2d.getX(), sprV2d.getY());
        g2d.setColor(Color.black);
        g2d.drawRect(startingXCoordinate-1, startingYCoordinate-1, barWidth+1, barHeight+1);
        g2d.setColor(Color.green);
        g2d.fillRect(startingXCoordinate,  startingYCoordinate,
                (int)(barWidth*percentFilled), barHeight);
        g2d.translate(-sprV2d.getX(), -sprV2d.getY());
    }
    
    
    
}
