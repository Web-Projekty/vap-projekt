package me.projekt.game.gamestates;

import me.projekt.game.ui.buttons.MenuButton;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameMenu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage menuImg, menuBgImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private int bgMenuWidth, bgMenuHeight;

    public GameMenu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        menuImg = LoadSave.getSpriteAtlas(LoadSave.MAIN_MENU_IMG);
        menuWidth = (int) (menuImg.getWidth() * Game.SCALE);
        menuHeight = (int) (menuImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);

        menuBgImg = LoadSave.getSpriteAtlas(LoadSave.MAIN_MENU_BACKGROUND);
        bgMenuWidth = (int) (menuBgImg.getWidth() * Game.SCALE);
        bgMenuHeight = (int) (menuBgImg.getHeight() * Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBgImg, 0, 0, bgMenuWidth, bgMenuHeight, null);
        g.drawImage(menuImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    public void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.reset();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
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