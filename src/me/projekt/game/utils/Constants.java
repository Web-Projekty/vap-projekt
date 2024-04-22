package me.projekt.game.utils;

import me.projekt.game.main.Game;

public class Constants {

    public static class Entities {
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

        public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;

        public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;

        public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

        public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
    }
}
