package me.projekt.game.gamestates;

import me.projekt.game.objects.ObjectManager;
import me.projekt.game.sounds.SoundManager;
import me.projekt.game.ui.PauseOverlay;
import me.projekt.game.player.Player;
import me.projekt.game.levels.LevelManager;
import me.projekt.game.main.Game;
import me.projekt.game.ui.LevelCompletedOverlay;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static me.projekt.game.main.Game.*;
import static me.projekt.game.utils.Constants.Background.*;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelManager levelManager;
    private ObjectManager objectManager;
    private SoundManager soundManager;
    private LevelCompletedOverlay levelCompletedOverlay;
    private PauseOverlay pauseOverlay;

    private boolean gameOver;
    private boolean levelCompleted;
    private boolean paused = false;

    private int xLvlOffset;
    private int leftBorder = (int) (0.4 * GAME_WIDTH);
    private int rightBorder = (int) (0.6 * GAME_WIDTH);
    private int maxLvlOffsetX;

    private int yLvlOffset;
    private int upBorder = (int) (0.4 * GAME_HEIGHT);
    private int downBorder = (int) (0.6 * GAME_HEIGHT);
    private int maxLvlOffsetY;

    private BufferedImage backgroundImg, bigCloud, smallCloud;
    private int[] smallCloudsPos;
    private Random ran = new Random();

    public Playing(Game game) {
        super(game);
        initClasses();

        setLevelOffsets();
        loadStartLevel();

        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        bigCloud = LoadSave.getSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.getSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for (int i = 0; i < smallCloudsPos.length; i++) {
            smallCloudsPos[i] = (int) (90 * SCALE) + ran.nextInt((int) (100 * SCALE));
        }
    }

    private void initClasses() {
        this.levelManager = new LevelManager(game);
        this.objectManager = new ObjectManager(this);
        this.player = new Player(this, 200, 200, (int) (48 * SCALE), (int) (32 * SCALE));
        this.player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        player.setSpawn(levelManager.getCurrentLevel().getSpawn());

        this.levelCompletedOverlay = new LevelCompletedOverlay(this);
        this.pauseOverlay = new PauseOverlay(this);
    }

    public void loadNextLevel() {
        reset();
        levelManager.loadNextLevel();
        player.setSpawn(levelManager.getCurrentLevel().getSpawn());
    }

    private void reset() {
        gameOver = false;
        paused = false;
        levelCompleted = false;
        player.reset();
        objectManager.reset();
    }

    private void loadStartLevel() {
        // TODO - loadEnemies
        objectManager.loadObjects(levelManager.getCurrentLevel());
    }

    private void setLevelOffsets() {
        this.maxLvlOffsetX = levelManager.getCurrentLevel().getMaxLvlOffsetX();
        this.maxLvlOffsetY = levelManager.getCurrentLevel().getMaxLvlOffsetY();
    }

    @Override
    public void update() {
        if (paused) {
            pauseOverlay.update();
        } else if (levelCompleted) {
            levelCompletedOverlay.update();
        } else if (!gameOver) {
            levelManager.update();
            objectManager.update();
            player.update();
            checkCloseToBorder();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, GAME_HEIGHT, null);

        drawClouds(g);

        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
        objectManager.draw(g, xLvlOffset, yLvlOffset);

        if (paused) {
            pauseOverlay.draw(g);
        } else if (gameOver) {
            //gameOverOverlay.draw(g);
        } else if (levelCompleted) {
            levelCompletedOverlay.draw(g);
        }
    }

    private void drawClouds(Graphics g) {
        for (int i = 0; i < 3; i++) {
            int offSet = (int) (xLvlOffset * 0.3); // čím menší, tím rychlejší
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - offSet, (int) (204 * SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
        }
        for (int i = 0; i < smallCloudsPos.length; i++) {
            int offSet = (int) (xLvlOffset * 0.7); // čím větší, tím pomalejší
            g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - offSet, smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            if (paused) pauseOverlay.mousePressed(e);
            else if (levelCompleted) {
                levelCompletedOverlay.mousePressed(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (paused) pauseOverlay.mouseReleased(e);
            else if (levelCompleted) {
                levelCompletedOverlay.mouseReleased(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            if (paused) pauseOverlay.mouseMoved(e);
            else if (levelCompleted) {
                levelCompletedOverlay.mouseMoved(e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
//            case KeyEvent.VK_S:
//                player.setShift(true);
//                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
            case KeyEvent.VK_N:
                game.getPlaying().setLevelCompleted(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
//            case KeyEvent.VK_S:
//                getPlayer().setShift(false); - Pokud budeme chtít, aby se krčil
//                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void unpauseGame() {
        this.paused = false;
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int diffX = playerX - xLvlOffset;

        int playerY = (int) player.getHitbox().y;
        int diffY = playerY - yLvlOffset;

        if (diffX > rightBorder) xLvlOffset += diffX - rightBorder;
        else if (diffX < leftBorder) xLvlOffset += diffX - leftBorder;

        if (xLvlOffset > maxLvlOffsetX) xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0) xLvlOffset = 0;

        if (diffY > upBorder) yLvlOffset += diffY - upBorder;
        else if (diffY < downBorder) yLvlOffset += diffY - downBorder;

        if (yLvlOffset > maxLvlOffsetY) yLvlOffset = maxLvlOffsetY;
        else if (yLvlOffset < 0) yLvlOffset = 0;
    }

    public void windowFocusLost() {
        player.cancelMovement();
    }

    public void setLevelCompleted(boolean levelCompleted) {
        this.levelCompleted = levelCompleted;
    }

    public void setMaxLevelOffsets(int levelOffsetX, int levelOffsetY) {
        this.maxLvlOffsetX = levelOffsetX;
        this.maxLvlOffsetY = levelOffsetY;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}
