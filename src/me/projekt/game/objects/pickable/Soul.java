package me.projekt.game.objects.pickable;

import me.projekt.game.main.Game;
import me.projekt.game.objects.ObjectType;
import me.projekt.game.objects.PickableGameObject;

public class Soul extends PickableGameObject {

    public Soul(int x, int y, ObjectType objectType) {
        super(x, y, objectType);
        doAnimation = true;
        initHitbox(7, 14);
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);
    }

    @Override
    public void update() {
        super.update();
        updateHover();
    }
}
