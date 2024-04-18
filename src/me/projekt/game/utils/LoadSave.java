package me.projekt.game.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class LoadSave {

    // Sprites
    public static final String PLAYER_ATLAS = "Skeleton_2.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";

    // UI
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String MENU_BUTTONS = "menu_buttons.png";
    public static final String MAIN_MENU_IMG = "main_menu.png";
    public static final String MAIN_MENU_BACKGROUND = "main_menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";

    // Objects
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String POTION_ATLAS = "potions_sprites.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] getLevels() {
        URL url = LoadSave.class.getResource("/levels_img");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png")) {
                    filesSorted[i] = files[j];
                }
            }
        }

        BufferedImage[] images = new BufferedImage[filesSorted.length];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return images;
    }
}