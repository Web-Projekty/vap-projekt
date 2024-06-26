package me.projekt.game.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    // Sprites
    public static final String PLAYER_ATLAS = "skeleton.png";
    public static final String LEVEL_ATLAS = "tileset.png";

    // UI
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String MENU_BUTTONS = "menu_buttons.png";
    public static final String MAIN_MENU_IMG = "main_menu.png";
    public static final String MAIN_MENU_BACKGROUND = "main_menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BG_DETAILS = "playing_bg_details.png";

    // Objects
    public static final String DECORATIONS = "decorations.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String SOUL_ATLAS = "soul.png";
    public static final String DOOR = "door.png";
    public static final String SPIKES = "spikes.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return img;
    }

    public static BufferedImage[] getLevels() {
        int mapCount = 4;
        BufferedImage[] images = new BufferedImage[mapCount];
        for (int i = 1; i <= mapCount; i++) {
            InputStream is = LoadSave.class.getResourceAsStream("/levels_img/" + i + ".png");
            try {
                images[i - 1] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }
}