package me.projekt.game.player;

public enum PlayerAction {

    IDLE(0, 4),
    RUNNING(1, 6),
    JUMP(2, 2),
    FALLING(3, 3),
    GROUND(4, 2),
    HIT(5, 3),
    ATTACK(4, 3);

    private int orderInSheet = 0;
    private int spriteAmount = 1;

    PlayerAction(int orderInSheet, int spriteAmount) {
        this.orderInSheet = orderInSheet;
        this.spriteAmount = spriteAmount;
    }

    public int getOrder() {
        return this.orderInSheet;
    }

    public int getSpriteAmount() {
        return this.spriteAmount;
    }

}