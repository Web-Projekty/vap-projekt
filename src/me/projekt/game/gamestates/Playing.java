package me.projekt.game.gamestates;

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

    private int xLvlOffset;
    private int leftBorder = (int) (0.5 * GAME_WIDTH);
    private int rightBorder = (int) (0.5 * GAME_WIDTH);
    private int lvlTilesWide = LoadSave.getLevelData()[0].length; // celková délka mapy na tily
    private int maxTilesOffsetX = lvlTilesWide - TILES_IN_WIDTH; // maximální offset
    private int maxLvlOffsetX = maxTilesOffsetX * TILES_SIZE; // transformace na pixely

    private int yLvlOffset;
    private int upBorder = (int) (0.5 * GAME_HEIGHT);
    private int downBorder = (int) (0.5 * GAME_HEIGHT);
    private int lvlTilesHigh = LoadSave.getLevelData().length; // celková délka mapy na tily
    private int maxTilesOffsetY = lvlTilesHigh - TILES_IN_HEIGHT; // maximální offset
    private int maxLvlOffsetY = maxTilesOffsetY * TILES_SIZE; // transformace na pixely

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        this.levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (32 * SCALE), (int) (32 * SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    public void windowFocusLost() {
        player.cancelMovement();
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
        checkCloseToBorder();
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int diffX = playerX - xLvlOffset;

        int playerY = (int) player.getHitbox().y;
        int diffY = playerY - yLvlOffset;

        if (diffX > rightBorder)
            xLvlOffset += diffX - rightBorder;
        else if (diffX < leftBorder)
            xLvlOffset += diffX - leftBorder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;

        if (diffY > upBorder)
            yLvlOffset += diffY - upBorder;
        else if (diffY < downBorder)
            yLvlOffset += diffY - downBorder;

        if (yLvlOffset > maxLvlOffsetY)
            yLvlOffset = maxLvlOffsetY;
        else if (yLvlOffset < 0)
            yLvlOffset = 0;

    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);

        /*if (paused) {
            //Tohle Matyáši jenom odkomentuj, bylo do toho něco přidáno
            g.setColor(new Color(0, 0, 0, 100));
            g.drawRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
            pauseOverlay.draw(g);
        }*/
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
            case KeyEvent.VK_ENTER:
                GameState.setState(GameState.MENU);
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
