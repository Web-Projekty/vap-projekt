package me.projekt.game.ui.buttons;

import me.projekt.game.sounds.SoundManager;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static me.projekt.game.utils.Constants.Buttons.*;

public class SoundButton extends Button {

    private BufferedImage[][] soundImgs;
    private boolean mouseOver, mousePressed;
    private int rowIndex, columnIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadSoundImgs();
    }

    private void loadSoundImgs() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);
        soundImgs = new BufferedImage[2][3];
        for (int j = 0; j < soundImgs.length; j++)
            for (int i = 0; i < soundImgs[j].length; i++)
                soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);


    }

    public void update() {
        rowIndex = SoundManager.isSongsMuted() ? 1 : 0;

        columnIndex = 0;
        if (mouseOver)
            columnIndex = 1;
        if (mousePressed)
            columnIndex = 2;
    }

    public void reset() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        g.drawImage(soundImgs[rowIndex][columnIndex], x, y, width, height, null);
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
