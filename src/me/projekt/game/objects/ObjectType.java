package me.projekt.game.objects;

import me.projekt.game.main.Game;

public enum ObjectType {

    RED_POTION(0, 12, 16, 15, 7),
    BLUE_POTION(1, 12, 16, 10, 7),
    BARREL(2, 40, 30, -1, 8),
    BOX(3, 40, 30, -1, 8);

    private int id;
    private int width, height;
    private int value;
    private int spriteAmount;

    ObjectType(int id, int width, int height, int value, int spriteAmount) {
        this.id = id;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = value;
        this.spriteAmount = spriteAmount;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }
}
