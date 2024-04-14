package me.projekt.game.objects;

import me.projekt.game.main.Game;

public class GameContainer extends GameObject {

    public GameContainer(int x, int y, ObjectType objectType) {
        super(x, y, objectType);
        setHitbox();
    }

    private void setHitbox() {
        if (objectType == ObjectType.BOX) {
            initHitbox(25, 18);
            xDrawOffset = (int) (7 * Game.SCALE);
            yDrawOffset = (int) (12 * Game.SCALE);
        } else {
            initHitbox(23, 25);
            xDrawOffset = (int) (8 * Game.SCALE);
            yDrawOffset = (int) (5 * Game.SCALE);
        }
    }

    public void update() {
        if (doAnimation) {
            updateAnimationTick();
        }
    }
}
