package me.projekt.game.objects;

import me.projekt.game.main.Game;

public enum ObjectType {

    SOUL(101, 32, 32, 4),
    RED_POTION(102, 12, 16, 15, 7),
    BLUE_POTION(103, 12, 16, 10, 7),
    BARREL(104, 40, 30, 8),
    BOX(105, 40, 30, 8);

    private int redValue;
    private int width, height;
    private int value;
    private int spriteAmount;

    ObjectType(int redValue, int width, int height, int value, int spriteAmount) {
        this.redValue = redValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = value;
        this.spriteAmount = spriteAmount;
    }

    ObjectType(int redValue, int width, int height, int spriteAmount) {
        this.redValue = redValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = -1;
        this.spriteAmount = spriteAmount;
    }

    public int getRedValue() {
        return redValue;
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

    public static ObjectType getObjectByValue(int value) {
        for (ObjectType type : ObjectType.values()) {
            if (type.getRedValue() == value) return type;
        }
        return null;
    }
}
