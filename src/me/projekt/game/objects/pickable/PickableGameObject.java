package me.projekt.game.objects.pickable;

import me.projekt.game.main.Game;
import me.projekt.game.objects.GameObject;
import me.projekt.game.objects.ObjectType;

public class PickableGameObject extends GameObject {

    protected float hoverOffset;
    protected int maxHoverOffset, hoverDir = 1;

    public PickableGameObject(int x, int y, ObjectType objectType) {
        super(x, y, objectType);

        maxHoverOffset = (int) (5 * Game.SCALE);
    }

    public void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        if (hoverOffset >= maxHoverOffset)
            hoverDir = -1;
        else if (hoverOffset < 0)
            hoverDir = 1;

        hitbox.y = y + hoverOffset;
    }

    public void update() {
        updateAnimationTick();
    }
}
