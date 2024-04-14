package me.projekt.game.objects.pickable;

import me.projekt.game.main.Game;
import me.projekt.game.objects.GameObject;
import me.projekt.game.objects.ObjectType;

public class Potion extends GameObject {

    public Potion(int x, int y, ObjectType objectType) {
        super(x, y, objectType);
        doAnimation = true;
        initHitbox(7, 14);
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);
    }

    public void update() {
        updateAnimationTick();
    }
}
