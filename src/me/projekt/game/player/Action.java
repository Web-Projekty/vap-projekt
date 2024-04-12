package me.projekt.game.player;

public enum Action {

    IDLE(0, 4),
    RUNNING(2, 3),
    JUMP(0, 4),
    FALLING(0, 4),
    GROUND(4, 2),
    HIT(5, 4),
    ATTACK_1(0, 4),
    ATTACK_JUMP_1(7, 3),
    ATTACK_JUMP_2(8, 3);

    private int orderInSheet = 0;
    private int spriteAmount = 1;

    Action(int orderInSheet, int spriteAmount) {
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