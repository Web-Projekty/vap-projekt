package me.projekt.game.objects.destroyable;

import me.projekt.game.main.Game;
import me.projekt.game.objects.GameObject;
import me.projekt.game.objects.ObjectType;

public class Box extends GameObject {

    public Box(int x, int y, ObjectType objectType) {
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

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2); // aby se containery (box, barrel...) dotýkaly tilu a nebyly ve vzduchu
        hitbox.x += xDrawOffset / 2; // posunutí do středu
    }

    public void update() {
        if (doAnimation) {
            updateAnimationTick();
        }
    }
}
