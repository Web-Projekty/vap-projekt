package me.projekt.game.utils;

import me.projekt.game.main.Game;

public class Constants {

    public static class Map {
        public static final int SPRITES_IN_SHEET = 20;
        public static final int COLUMNS = 4;
        public static final int ROWS = 5;

        public static final int AIR_BLOCK = 16;
    }

    public static class Entities {
        public static final float PLAYER_SPEED = 1.2f * Game.SCALE;
        public static final float GRAVITY = 0.04f * Game.SCALE;
    }

    public static class Animations {
        public static final int DEFAULT_SPEED = 25; // čím menší, tím bude rychlejší animace
        public static final int MOVING_SPEED = 13; // čím menší, tím bude rychlejší animace
    }

    public static class Buttons {

        public static int B_WIDTH_DEFAULT = 140;
        public static int B_HEIGHT_DEFAULT = 56;
        public static int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
        public static int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);

        public static int URM_SIZE_DEFAULT = 56;
        public static int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);

        public static int SOUND_SIZE_DEFAULT = 42;
        public static int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
    }

    public static class Background {

        public static final int MIST_WIDTH_DEFAULT = 74;
        public static final int MIST_HEIGHT_DEFAULT = 24;

        public static final int MIST_WIDTH = (int) (MIST_WIDTH_DEFAULT * Game.SCALE);
        public static final int MIST_HEIGHT = (int) (MIST_HEIGHT_DEFAULT * Game.SCALE);
    }
}
