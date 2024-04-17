package me.projekt.game.gamestates;

import me.projekt.game.main.Game;
import me.projekt.game.ui.buttons.PauseButton;
import me.projekt.game.ui.buttons.SoundButton;
import me.projekt.game.ui.buttons.UrmButton;
import me.projekt.game.ui.buttons.VolumeButton;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static me.projekt.game.utils.Constants.Buttons.*;
import static me.projekt.game.utils.Constants.Buttons.SOUND_SIZE;

public class GameOptions extends State implements StateMethods {

    private BufferedImage optImg, optBg;
    private int optX, optY, optWidth, optHeight;
    private int bgOptWidth, bgOptHeight;

    private UrmButton menuB;
    private SoundButton musicButton, sfxButton;
    private VolumeButton volumeButton;

    public GameOptions(Game game) {
        super(game);

        loadBackground();
        setVolumeBUtton();
        setSoundButtons();
        setUrmButtons();
    }

    private void loadBackground() {
        this.optImg = LoadSave.getSpriteAtlas(LoadSave.MAIN_MENU_IMG);
        optWidth = (int) (optImg.getWidth() * Game.SCALE);
        optHeight = (int) (optImg.getHeight() * Game.SCALE);
        optX = Game.GAME_WIDTH / 2 - optWidth / 2;
        optY = (int) (45 * Game.SCALE);

        this.optBg = LoadSave.getSpriteAtlas(LoadSave.MAIN_MENU_BACKGROUND);
        bgOptWidth = (int) (optBg.getWidth() * Game.SCALE);
        bgOptHeight = (int) (optBg.getHeight() * Game.SCALE);
    }

    private void setUrmButtons() {
        int menuX = (int) (387 * Game.SCALE);
        int buttonY = (int) (325 * Game.SCALE);
        menuB = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
    }

    private void setVolumeBUtton() {
        int vX = (int) (309 * Game.SCALE);
        int vY = (int) (278 * Game.SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void setSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    @Override
    public void update() {
        musicButton.update();
        sfxButton.update();
        volumeButton.update();
        menuB.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(optBg, 0, 0, bgOptWidth, bgOptHeight, null);
        g.drawImage(optImg, optX, optY, optWidth, optHeight, null);

        musicButton.draw(g);
        sfxButton.draw(g);
        volumeButton.draw(g);
        menuB.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.setState(GameState.MENU);
            }
        }

        menuB.reset();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        volumeButton.setMouseOver(false);
        menuB.setMouseOver(false);

        if (isIn(e, musicButton)) musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton)) sfxButton.setMouseOver(true);
        else if (isIn(e, menuB)) menuB.setMouseOver(true);
        else if (isIn(e, volumeButton)) volumeButton.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
