package me.projekt.game.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import static me.projekt.game.sounds.Sounds.Songs.*;
import static me.projekt.game.sounds.Sounds.SFX.*;

public class SoundManager {

    private static Clip[] songs, effects;
    private static Sounds.Songs currentSong;
    private static float volume = 0.5f;
    private static Random rand = new Random();

    private static boolean songsMuted = false;
    private static boolean sfxMuted = false;

    public SoundManager() {
        loadSongs();
        loadEffects();
        if (!songsMuted) playSong(MENU_1);
    }

    private void loadSongs() {
        String[] names = {"main_v2"};
        songs = new Clip[names.length];
        for (int i = 0; i < songs.length; i++) {
            try {
                songs[i] = getClip(names[i]);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadEffects() {
        String[] effectNames = {"jump"};
        effects = new Clip[effectNames.length];
        for (int i = 0; i < songs.length; i++) {
            try {
                effects[i] = getClip(effectNames[i]);
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Clip getClip(String name) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        URL url = getClass().getResource("/audio/" + name + ".wav");
        AudioInputStream audio;

        audio = AudioSystem.getAudioInputStream(url);
        Clip c = AudioSystem.getClip();
        c.open(audio);

        return c;
    }
    public static void stopSong() {
        if (songs[currentSong.getId()].isActive()) {
            songs[currentSong.getId()].stop();
        }
    }

    public static Clip getCurrentSong() {
        return songs[currentSong.getId()];
    }

   /* public void setLevelSong(int lvlIndex) {
if (lvlIndex % 2 ==0){
    playSong(1);
}else {
    playSong(2);
}
    }*/

    public static void LvlCompleted() {
        stopSong();
        playEffect(LVL_COMPLETED);
    }

    public static void playEffect(Sounds.SFX effect) {
        effects[effect.getId()].setMicrosecondPosition(0);
        effects[effect.getId()].start();
    }

    public static void playSong(Sounds.Songs song) {
        if (currentSong != null) stopSong();

        currentSong = song;
        //updateSongVolume();
        songs[song.getId()].setMicrosecondPosition(0);
        songs[song.getId()].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static boolean isSongsMuted() {
        return songsMuted;
    }
    public static void setSongsMuted(boolean mute) {
        songsMuted = mute;
        if (songsMuted) stopSong();
        else playSong(currentSong);
    }

    public static boolean isSFXMuted() {
        return sfxMuted;
    }
    public static void setSFXMuted(boolean mute) {
        sfxMuted = mute;
    }
}
