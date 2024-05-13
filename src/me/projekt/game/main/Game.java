package me.projekt.game.main;

import me.projekt.game.audio.AudioPlayer;
import me.projekt.game.gamestates.GameOptions;
import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.GameMenu;
import me.projekt.game.gamestates.GameState;
import me.projekt.game.gamestates.Playing;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 120;

    private Playing playing;
    private GameMenu menu;
    private AudioPlayer audioPlayer;
    private GameOptions options;

    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 2f;

    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);

    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public boolean showFpsUps = true;

    public Game() {
        initClasses();

        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow("Platformer Game", gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        menu = new GameMenu(this);
        options = new GameOptions(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void update() {

        switch (GameState.getState()) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
                options.update();
                break;
            case QUIT:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {

        switch (GameState.getState()) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case OPTIONS:
                options.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

        /* Když se stane lag/bug, tak pomocí tohoto game loop systému jsme schopní ho dohnat a neztratí se. */

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                if (showFpsUps) {
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                }
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (GameState.getState() == GameState.PLAYING) {
            playing.windowFocusLost();
        }
    }

    public GameMenu getMenu() {
        return this.menu;
    }

    public GameOptions getOptions() {
        return this.options;
    }

    public Playing getPlaying() {
        return this.playing;
    }

    public AudioPlayer getAudioPlayer() {return this.audioPlayer;}
}

