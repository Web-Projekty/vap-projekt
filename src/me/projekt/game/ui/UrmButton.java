package me.projekt.game.ui;

import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UrmButton extends PauseButton{
    private BufferedImage[] imgs;
    public static int URM_SIZE_DEFAULT = 56;
    public static int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x,y,width,height);
        this.rowIndex = rowIndex;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
        imgs = new BufferedImage[3];
        for(int i = 0; i<imgs.length;i++) {
            imgs[i] = temp.getSubimage(i * URM_SIZE_DEFAULT,rowIndex * URM_SIZE_DEFAULT,URM_SIZE_DEFAULT,URM_SIZE_DEFAULT);
        }
    }

    public void update() {
        index = 0;
        if(mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }
    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
    }
    public void resetBools() {
        mousePressed = false;
        mouseOver = false;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
}
