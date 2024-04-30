package me.projekt.game.objects;

import me.projekt.game.main.Game;

public enum ObjectType {

    SOUL(0, 16, 16, 4),
    RED_POTION(1, 12, 16, 15, 7),
    BLUE_POTION(2, 12, 16, 10, 7),
    BARREL(3, 40, 30, 8),
    BOX(4, 40, 30, 8);

    private int blueValue;
    private int width, height;
    private int value;
    private int spriteAmount;

    ObjectType(int blueValue, int width, int height, int value, int spriteAmount) {
        this.blueValue = blueValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = value;
        this.spriteAmount = spriteAmount;
    }

    ObjectType(int blueValue, int width, int height, int spriteAmount) {
        this.blueValue = blueValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = -1;
        this.spriteAmount = spriteAmount;
    }

    public int getBlueValue() {
        return blueValue;
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
            System.out.print(type.getBlueValue());
            if (type.getBlueValue() == value) return type;
        }
        return null;
    }
}
