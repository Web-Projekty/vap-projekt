package me.projekt.game.objects;

public enum ObjectAction {

    SOMETHING(0,0);

    private int orderInSheet = 0;
    private int spriteAmount = 1;

    ObjectAction(int orderInSheet, int spriteAmount) {
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
