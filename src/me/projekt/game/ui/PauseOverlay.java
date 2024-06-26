package me.projekt.game.ui;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.sounds.SoundManager;
import me.projekt.game.ui.buttons.*;
import me.projekt.game.ui.buttons.Button;
import me.projekt.game.utils.LoadSave;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static me.projekt.game.main.Game.GAME_HEIGHT;
import static me.projekt.game.main.Game.GAME_WIDTH;
import static me.projekt.game.utils.Constants.Buttons.*;

public class PauseOverlay {

    private Playing playing;

    private int bgX, bgY, bgW, bgH;
    private BufferedImage backgroundImg;

    private SFXButton sfxButton;
    private SoundButton musicButton;
    private UrmButton menuB, replayB, unpauseB;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        setSoundButtons();
        setUrmButtons();
        loadBackground();
    }

    private void setUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int buttonY = (int) (325 * Game.SCALE);
        menuB = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, buttonY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, buttonY, URM_SIZE, URM_SIZE, 0);
    }

    private void setSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SFXButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();
    }

    public void draw(Graphics g) {
        //Background
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        //Urm buttons (Unpause, replay, menu)
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton)) sfxButton.setMousePressed(true);
        else if (isIn(e, menuB)) menuB.setMousePressed(true);
        else if (isIn(e, unpauseB)) unpauseB.setMousePressed(true);
        else if (isIn(e, replayB)) replayB.setMousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                SoundManager.setSongsMuted(!SoundManager.isSongsMuted());
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                SoundManager.setSFXMuted(!SoundManager.isSFXMuted());
            }
        } else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                playing.reset();
                GameState.setState(GameState.MENU);
                playing.unpauseGame();
            }
        } else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                playing.reset();
                playing.unpauseGame();
            }
        } else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }

        menuB.reset();
        unpauseB.reset();
        replayB.reset();
        musicButton.reset();
        sfxButton.reset();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if (isIn(e, musicButton)) musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton)) sfxButton.setMouseOver(true);
        else if (isIn(e, menuB)) menuB.setMouseOver(true);
        else if (isIn(e, unpauseB)) unpauseB.setMouseOver(true);
        else if (isIn(e, replayB)) replayB.setMouseOver(true);
    }

    private boolean isIn(MouseEvent e, Button b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}