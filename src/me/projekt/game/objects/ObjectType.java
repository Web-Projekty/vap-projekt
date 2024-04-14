package me.projekt.game.objects;

import me.projekt.game.main.Game;

public enum ObjectType {

    RED_POTION(0, 12, 16, 15, 1, 7),
    BLUE_POTION(1, 12, 16, 10, 0, 7),
    BARREL(2, 40, 30, -1, 1, 8),
    BOX(3, 40, 30, -1, 0, 8);

    private int id;
    private int width, height;
    private int value;
    private int orderInSheet, spriteAmount;

    ObjectType(int id, int width, int height, int value, int orderInSheet, int spriteAmount) {
        this.id = id;
        this.width = (int) (width * Game.SCALE);
        this.height = (int) (height * Game.SCALE);
        this.value = value;
        this.orderInSheet = orderInSheet;
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

    public int getOrderInSheet() {
        return orderInSheet;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }
}
