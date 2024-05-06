package me.projekt.game.objects.pickable;

import me.projekt.game.main.Game;
import me.projekt.game.objects.ObjectType;

public class Soul extends PickableGameObject {

    public Soul(int x, int y) {
        super(x, y, ObjectType.SOUL);
        doAnimation = true;
        initHitbox(32, 32);
        xDrawOffset = (int) (3 * Game.SCALE);
        yDrawOffset = (int) (2 * Game.SCALE);
    }

    @Override
    public void update() {
        super.update();
        updateHover();
    }
}
