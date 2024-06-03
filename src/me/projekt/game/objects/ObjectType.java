package me.projekt.game.objects;

import me.projekt.game.main.Game;

public enum ObjectType {

    SOUL(101, 16, 16, 4, 0),
    RED_POTION(102, 12, 16, 15, 1),
    BLUE_POTION(103, 12, 16, 10, 0),
    BARREL(104, 40, 30, 8, 1),
    BOX(105, 40, 30, 8, 0),
    LAMP(106, 32, 32, 2, 0),
    TREE(107, 32, 32, 2, 5),
    TORCH(108, 32, 32, 4, 6),
    STONES(109, 32, 32, 1),
    LEVEL_DOORS(99, 32, 32, 2, 0),
    DEATH_ZONE(98, 32, 32);

    private final int redValue;
    private final int width, height;
    private final int value;
    private final int spriteAmount;
    private final int spritePos;

    ObjectType(int redValue, int width, int height, int spriteAmount, int spritePos) {
        this.redValue = redValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.spritePos = spritePos;
        this.value = -1;
        this.spriteAmount = spriteAmount;
    }

    ObjectType(int redValue, int width, int height, int spritePos) {
        this.redValue = redValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = -1;
        this.spriteAmount = 1;
        this.spritePos = spritePos;
    }

    ObjectType(int redValue, int width, int height) {
        this.redValue = redValue;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = -1;
        this.spriteAmount = 1;
        this.spritePos = 0;
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

    public int getSpritePos() {
        return spritePos;
    }

    public static ObjectType getObjectByValue(int value) {
        for (ObjectType type : ObjectType.values()) {
            if (type.getRedValue() == value) return type;
        }
        return null;
    }
}
