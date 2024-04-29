package me.projekt.game.enemies;

import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.player.Player;
import me.projekt.game.player.PlayerAction;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected Playing playing;

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    protected PlayerAction action;
    protected int animTick, animIndex;

    protected int maxHealth, currentHealth;
    protected float moveSpeed;
    protected float airSpeed;
    protected boolean inAir = false;

    public Entity(Playing playing, float x, float y, int width, int height) {
        this.playing = playing;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void drawHitbox(Graphics g, int xLvlOffset, int yLvlOffset) {
        // for debugging hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset, (int) hitbox.width, (int) hitbox.height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public PlayerAction getAction() {
        return action;
    }

    public int getAnimationIndex() {
        return animIndex;
    }
}
