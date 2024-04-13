package me.projekt.game.objects;

import me.projekt.game.main.Game;
import me.projekt.game.utils.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GameObject {

    protected int x,y;
    protected ObjectAction action;
    protected Rectangle2D.Float hitbox;
    protected boolean animated, active = true;
    protected int animTick, animIndex;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, ObjectAction action) {
        this.x = x;
        this.y = y;
        this.action = action;
    }

    protected void updateAnimationTick() {
        animTick++;
        if (animTick >= Constants.ANIMATION_SPEED) {
            animTick = 0;
            animIndex++;
            if (animIndex >= action.getSpriteAmount()) {
                animIndex = 0;
            }
        }
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void drawHitbox(Graphics g, int xLvlOffset, int yLvlOffset) {
        // for debugging hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y - yLvlOffset, (int) hitbox.width, (int) hitbox.height);
    }

    public boolean isAnimated() {
        return animated;
    }
}
