package me.projekt.game.ui;

import java.awt.image.BufferedImage;
import me.projekt.game.gamestates.GameState;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private GameState state;
    private BufferedImage[] imgs;
    public int B_WIDTH_DEFAULT = 140;
    public int B_HEIGHT_DEFAULT = 56;
    public int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
    public int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
    public int xOffsetCenter = B_WIDTH / 2;
    private boolean mousePressed, mouseOver;
    private Rectangle bounds;


    public MenuButton(int xPos, int yPos, int rowIndex, GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
     }


    private void initBounds(){
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
     }

     private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);
        for(int i = 0; i< imgs.length;i++) {
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT,rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
     }
     public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
     }
     public void update() {
        index = 0;
        if(mouseOver)
            index = 1;
         if(mousePressed)
             index = 2;
     }
     public boolean isMouseOver() {
        return mouseOver;
     }
     public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
     }

     public boolean isMousePressed() {
        return mousePressed;
     }
     public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
     }
     public Rectangle getBounds() {
        return bounds;
     }

     public void applyGameState() {
        GameState.state = state;
     }

     public void resetBools(){
        mousePressed = false;
        mouseOver = false;
     }

}