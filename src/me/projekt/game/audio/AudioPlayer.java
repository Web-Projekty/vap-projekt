package me.projekt.game.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class AudioPlayer {
    public static int MENU_1 = 0;
    public static int LEVEL_1 = 1;
    public static int LEVEL_2 = 2;

    public static int JUMP = 0;
    public static int DIE = 1;
    public static int GAMEOVER = 2;
    public static int LVL_COMPLETED = 3;
    public static int ATTACK_ONE = 4;
    public static int ATTACK_TWO = 5;
    public static int ATTACK_THREE = 6;

    private Clip[] songs, effects;
    private int currentSongId;
    private float volume = 0.5f;
    private boolean songMute, effectMute;
    private Random rand = new Random();

    public AudioPlayer() {
        loadSongs();
        loadEffects();
        playSong(MENU_1);
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

    /*public void setVolume(){
            this.volume = volume;
            updateSongVolume();
            updateEffectsVolume();
    }*/
    public void stopSong() {
        if (songs[currentSongId].isActive()) {
            songs[currentSongId].stop();
        }
    }

   /* public void setLevelSong(int lvlIndex) {
if (lvlIndex % 2 ==0){
    playSong(1);
}else {
    playSong(2);
}
    }*/

    public void LvlCompleted() {
        stopSong();
        playEffect(LVL_COMPLETED);
    }

    public void playEffect(int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void playSong(int song) {
        stopSong();

        currentSongId = song;
        //updateSongVolume();
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
    }
}
