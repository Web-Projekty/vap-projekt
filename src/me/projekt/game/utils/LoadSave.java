package me.projekt.game.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadSave {

    // Sprites
    public static final String PLAYER_ATLAS = "Skeleton.png";
    public static final String LEVEL_ATLAS = "Tileset.png";

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
    public static final String SOUL_ATLAS = "Soul.png";

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
        System.out.println("im not dead yet1");
        Path folderPath = Paths.get("res/levels_img/");
        // File file = null;
        //new URI("file", null, uri.getPath(), null)
        System.out.println("im not dead yet2");
        int numFiles = 0;
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
            System.out.println("im not dead yet3");
            for (Path filePath : directoryStream) {
                System.out.println("im not dead yet4");
                if (Files.isRegularFile(filePath)) {
                    numFiles++;
                    System.out.println("im not dead yet5");
                }
            }
        } catch (IOException e) {
            System.err.println("Error while counting files: " + e.getMessage());
            // Exit the program if an error occurs
        }

        BufferedImage[] images = new BufferedImage[numFiles];

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
            int index = 0;
            for (Path filePath : directoryStream) {
                if (Files.isRegularFile(filePath)) {
                    try (InputStream inputStream = Files.newInputStream(filePath)) {
                        BufferedImage image = ImageIO.read(inputStream);
                        if (image != null) {
                            images[index++] = image;
                            System.out.println("Image loaded successfully from: " + filePath);
                        } else {
                            System.err.println("Failed to load the image: " + filePath);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while loading images: " + e.getMessage());
        }


        return images;
    }
}