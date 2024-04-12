package me.projekt.game.enemies;

import me.projekt.game.main.Game;
import me.projekt.game.player.Action;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected Action action;
    protected int animTick, animIndex;

    protected int maxHealth, currentHealth;
    protected float moveSpeed;
    protected float airSpeed;
    protected boolean inAir = false;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    protected void drawHitbox(Graphics g) {
        // for debugging hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public Action getAction() {
        return action;
    }

    public int getAnimationIndex() {
        return animIndex;
    }
}
