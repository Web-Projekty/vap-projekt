package me.projekt.game.ui;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.ui.buttons.UrmButton;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static me.projekt.game.main.Game.GAME_HEIGHT;
import static me.projekt.game.main.Game.GAME_WIDTH;
import static me.projekt.game.utils.Constants.Buttons.URM_SIZE;

public class LevelCompletedOverlay {

    private Playing playing;
    private UrmButton menu, next;
    private BufferedImage img;
    private int bgX, bgY, bgW, bgH;

    public LevelCompletedOverlay(Playing playing) {
        this.playing = playing;
        initImage();
        initButtons();
    }

    private void initButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int nextX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0); // TODO Button Type Enum
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void initImage() {
        img = LoadSave.getSpriteAtlas(LoadSave.COMPLETED_IMG);
        bgW = (int) (img.getWidth() * Game.SCALE);
        bgH = (int) (img.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (75 * Game.SCALE);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.drawImage(img, bgX, bgY, bgW, bgH, null);
        next.draw(g);
        menu.draw(g);
    }

    public void update() {
        next.update();
        menu.update();
    }

    private boolean isIn(UrmButton button, MouseEvent e) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        menu.setMouseOver(false);
        next.setMouseOver(false);

        if (isIn(menu, e)) menu.setMouseOver(true);
        else if (isIn(next, e)) next.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(menu, e)) {
            if (menu.isMousePressed()) GameState.setState(GameState.MENU);
        } else if (isIn(next, e)) {
            if (next.isMousePressed()) playing.loadNextLevel();
        }
        menu.reset();
        next.reset();
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(menu, e)) menu.setMousePressed(true);
        else if (isIn(next, e)) next.setMousePressed(true);
    }
}
