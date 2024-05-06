package me.projekt.game.sounds;

public class SoundManager {

    private static boolean soundsMuted;
    private static boolean sfxMuted;

    public static boolean isSoundsMuted() {
        return soundsMuted;
    }
    public static void setSoundsMuted(boolean mute) {
        soundsMuted = mute;
    }

    public static boolean isSFXMuted() {
        return sfxMuted;
    }
    public static void setSFXMuted(boolean mute) {
        sfxMuted = mute;
    }
}
