package me.projekt.game.gamestates;

import me.projekt.game.UI.PauseOverlay;
import me.projekt.game.player.Player;
import me.projekt.game.levels.LevelManager;
import me.projekt.game.main.Game;
import me.projekt.game.ui.LevelCompletedOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static me.projekt.game.main.Game.*;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelManager levelManager;
    private LevelCompletedOverlay levelCompletedOverlay;

    private boolean gameOver;
    private boolean levelCompleted;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    private int xLvlOffset;
    private int leftBorder = (int) (0.5 * GAME_WIDTH);
    private int rightBorder = (int) (0.5 * GAME_WIDTH);
    private int maxLvlOffsetX;

    private int yLvlOffset;
    private int upBorder = (int) (0.5 * GAME_HEIGHT);
    private int downBorder = (int) (0.5 * GAME_HEIGHT);
    private int maxLvlOffsetY; // transformace na pixely

    public Playing(Game game) {
        super(game);
        initClasses();

        setLevelOffsets();
        loadStartLevel();
    }

    private void initClasses() {
        this.levelManager = new LevelManager(game);
        this.player = new Player(200, 200, (int) (32 * SCALE), (int) (32 * SCALE));
        this.player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        player.setSpawn(levelManager.getCurrentLevel().getSpawn());

        this.levelCompletedOverlay = new LevelCompletedOverlay(this);
        pauseOverlay = new PauseOverlay(this);
    }

    public void loadNextLevel() {
        resetEverything();
        levelManager.loadNextLevel();
        player.setSpawn(levelManager.getCurrentLevel().getSpawn());
    }

    private void resetEverything() {
        gameOver = false;
        levelCompleted = false;
//        paused = false;
        player.reset();
    }

    private void loadStartLevel() {
        // TODO - loadEnemies
    }

    private void setLevelOffsets() {
        this.maxLvlOffsetX = levelManager.getCurrentLevel().getMaxLvlOffsetX();
        this.maxLvlOffsetY = levelManager.getCurrentLevel().getMaxLvlOffsetY();
    }

    @Override
    public void update() {
        if(!paused) {
            levelManager.update();
            player.update();
            checkCloseToBorder();
        }
        else {
            pauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);

        if(paused)
            pauseOverlay.draw(g);
        /*if (paused) {
            //Tohle Matyáši jenom odkomentuj, bylo do toho něco přidáno
            g.setColor(new Color(0, 0, 0, 100));
            g.drawRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            pauseOverlay.draw(g);
        }*/
        if (levelCompleted) levelCompletedOverlay.draw(g);
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

    public void mouseDragged(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
            pauseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseMoved(e);
    }
    public void unpauseGame() {
        paused = false;
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
                game.getPlaying().loadNextLevel();
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
}
