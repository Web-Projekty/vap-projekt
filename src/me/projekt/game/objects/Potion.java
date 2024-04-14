package me.projekt.game.objects;

import me.projekt.game.main.Game;

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
