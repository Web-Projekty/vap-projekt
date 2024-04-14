package me.projekt.game.enemies;

public enum EnemyAction {


    IDLE(0, 4),
    RUNNING(2, 3),
    JUMP(0, 4),
    HIT(5, 4),
    ATTACK(0, 4);

    private int orderInSheet = 0;
    private int spriteAmount = 1;

    EnemyAction(int orderInSheet, int spriteAmount) {
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
