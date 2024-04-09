package me.projekt.game.player;

public enum PlayerAction {

    IDLE(0, 5),
    RUNNING(1, 6),
    JUMP(2, 3),
    FALLING(3, 1),
    GROUND(4, 2),
    HIT(5, 4),
    ATTACK_1(6, 3),
    ATTACK_JUMP_1(7, 3),
    ATTACK_JUMP_2(8, 3);

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