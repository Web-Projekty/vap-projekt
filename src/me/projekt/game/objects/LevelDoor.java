package me.projekt.game.objects;

public class LevelDoor extends GameObject {

    public LevelDoor(int x, int y) {
        super(x, y, ObjectType.LEVEL_DOORS);
        initHitbox(32, 32);
        doAnimation = false;
    }

    @Override
    public void update() {
        if (doAnimation) {
            updateAnimationTick();
        }
    }
}
