package me.projekt.game.gamestates;

import me.projekt.game.main.Game;
import me.projekt.game.sounds.SoundManager;
import me.projekt.game.ui.buttons.*;
import me.projekt.game.ui.buttons.Button;
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
    private SoundButton musicButton;
    private SFXButton sfxButton;

    public GameOptions(Game game) {
        super(game);

        loadBackground();
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

    private void setSoundButtons() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SFXButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    @Override
    public void update() {
        musicButton.update();
        sfxButton.update();
        menuB.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(optBg, 0, 0, bgOptWidth, bgOptHeight, null);
        g.drawImage(optImg, optX, optY, optWidth, optHeight, null);

        musicButton.draw(g);
        sfxButton.draw(g);
        menuB.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) musicButton.setMousePressed(true);
        else if (isIn(e, sfxButton)) sfxButton.setMousePressed(true);
        else if (isIn(e, menuB)) menuB.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                SoundManager.setSoundsMuted(!SoundManager.isSoundsMuted());
            }
        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed()) {
                SoundManager.setSFXMuted(!SoundManager.isSFXMuted());
            }
        } else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                GameState.setState(GameState.MENU);
            }
        }

        menuB.reset();
        musicButton.reset();
        sfxButton.reset();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);

        if (isIn(e, musicButton)) musicButton.setMouseOver(true);
        else if (isIn(e, sfxButton)) sfxButton.setMouseOver(true);
        else if (isIn(e, menuB)) menuB.setMouseOver(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean isIn(MouseEvent e, Button b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
