package me.projekt.game.gamestates;

import me.projekt.game.UI.PauseOverlay;
import me.projekt.game.player.Player;
import me.projekt.game.levels.LevelManager;
import me.projekt.game.main.Game;
import me.projekt.game.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static me.projekt.game.main.Game.*;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelManager levelManager;
    private boolean paused = false;
    private PauseOverlay pauseOverlay;

    private int xLvlOffset;
    private int leftBorder = (int) (0.4 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.6 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.getLevelData()[0].length; // celková délka mapy na tily
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH; // maximální offset
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE; // transformace na pixely

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        this.levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (32 * SCALE), (int) (32 * SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
    }

    public void windowFocusLost() {
        player.cancelMovement();
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

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffset;

        if (diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder)
            xLvlOffset += diff - leftBorder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;

    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        if(paused)
            pauseOverlay.draw(g);
        /*if (paused) {
            //Tohle Matyáši jenom odkomentuj, bylo do toho něco přidáno
            g.setColor(new Color(0, 0, 0, 100));
            g.drawRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            pauseOverlay.draw(g);
        }*/
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

    public Player getPlayer() {
        return this.player;
    }
}
