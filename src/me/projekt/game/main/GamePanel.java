package me.projekt.game.main;

import me.projekt.game.input.KeyboardInput;
import me.projekt.game.input.MouseInput;

import javax.swing.*;
import java.awt.*;

import static me.projekt.game.main.Game.GAME_HEIGHT;
import static me.projekt.game.main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Game game;
    private MouseInput mouseInput;

    public GamePanel(Game game) {
        this.game = game;
        this.mouseInput = new MouseInput(this);

        setPanelSize();
        this.addKeyListener(new KeyboardInput(this));
        this.addMouseListener(this.mouseInput);
        this.addMouseMotionListener(this.mouseInput);
    }

    public void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void updateGame() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame() {
        return this.game;
    }
}