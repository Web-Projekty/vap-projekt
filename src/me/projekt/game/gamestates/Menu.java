package me.projekt.game.gamestates;

import me.projekt.game.UI.MenuButton;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth()*Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight()*Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2,(int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2,(int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2,(int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb: buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for(MenuButton mb: buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb: buttons) {
            if(IsIn(e,mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb: buttons) {
            if(IsIn(e,mb)) {
                if(mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    public void resetButtons() {
        for(MenuButton mb: buttons) {
            mb.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb: buttons) {
            mb.setMouseOver(false);
        }
        for(MenuButton mb: buttons) {
            if(IsIn(e,mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameState.setState(GameState.PLAYING);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}