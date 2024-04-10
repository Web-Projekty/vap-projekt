package me.projekt.game.ui;

import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static me.projekt.game.ui.SoundButton.*;
import static me.projekt.game.ui.UrmButton.*;
import static me.projekt.game.ui.VolumeButton.*;


public class PauseOverlay {

    private Playing playing;
    private int bgX, bgY, bgW, bgH;
    private BufferedImage backgroundImg;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB, replayB, unpauseB;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeBUtton();
    }

    private void createVolumeBUtton() {
        int vX = (int)(309 * Game.SCALE);
        int vY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int buttonY = (int) (325 * Game.SCALE);
        menuB = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, buttonY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, buttonY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
            backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
            bgW = (int) (backgroundImg.getWidth()* Game.SCALE);
            bgH = (int) (backgroundImg.getHeight()*Game.SCALE);
            bgX = Game.GAME_WIDTH / 2 - bgW / 2;
            bgY = (int) (25 * Game.SCALE);
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();
        volumeButton.update();
    }

    public void draw(Graphics g) {
        //Background
        g.drawImage(backgroundImg, bgX, bgY, bgW ,bgH, null);

        //Sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        //Urm buttons (Unpause, replay, menu)
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        //Volume buttons + slider
        volumeButton.draw(g);

    }

    public void mousePressed(MouseEvent e) {
        if(IsIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (IsIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if (IsIn(e, menuB))
            menuB.setMousePressed(true);
        else if (IsIn(e, unpauseB))
            unpauseB.setMousePressed(true);
        else if (IsIn(e, replayB))
            replayB.setMousePressed(true);
        else if (IsIn(e, volumeButton))
            volumeButton.setMousePressed(true);

    }
    public void mouseReleased(MouseEvent e) {
        if(IsIn(e, musicButton)) {
            if(musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if (IsIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }
        else if (IsIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.state = GameState.MENU;
                playing.unpauseGame();
            }
        }
        else if (IsIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                System.out.println("Replay lvl!");
            }
        }
        else if (IsIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
            }
        }

        menuB.resetBools();
        unpauseB.resetBools();
        replayB.resetBools();
        musicButton.resetBools();
        sfxButton.resetBools();
        volumeButton.resetBools();
    }
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if(IsIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (IsIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if (IsIn(e, menuB))
            menuB.setMouseOver(true);
        else if (IsIn(e, unpauseB))
            unpauseB.setMouseOver(true);
        else if (IsIn(e, replayB))
            replayB.setMouseOver(true);
        else if (IsIn(e, volumeButton))
            volumeButton.setMouseOver(true);
    }
    public void mouseDragged(MouseEvent e) {
        if(volumeButton.isMousePressed()){
            volumeButton.changeX(e.getX());
        }
    }

    private boolean IsIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }


}
