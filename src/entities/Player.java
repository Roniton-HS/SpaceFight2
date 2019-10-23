package entities;

import gfx.ImageLoader;
import main.ArrayLists;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Creature {

    private BufferedImage image; // Image for the Player
    private Game game; // Game Object
    private static float x, y; // x and y Coordinates of the Player
    private long pause = System.currentTimeMillis();
    private int playerWidth = 128, playerHeight = 128; // size of the Player

    /**
     * Constructor
     */
    public Player(Game game, float x, float y) {
        super(x, y);
        this.x = x;
        this.y = y;
        health = 10;
        image = ImageLoader.loadImage("/textures/player.png");
        this.game = game;
    }

    /**
     * Player tick
     */
    @Override
    public void tick() {
        input();
        checkEnemy();
    }

    /**
     * Player render
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, playerWidth, playerHeight, null);
    }

    /**
     * Keyboard input
     */

    private void input() {
        //MOVE
        if (game.getKeyHandler().w) {
            if (y >= 3)
                y -= 3;
        }
        if (game.getKeyHandler().a) {
            if (x >= 3)
                x -= 3;
        }
        if (game.getKeyHandler().s) {
            if (y < game.height - playerHeight)
                y += 3;
        }
        if (game.getKeyHandler().d) {
            if (x < game.width - playerWidth)
                x += 3;
        }
        //SHOOT
        if (game.getKeyHandler().up) {
            fire();
        }
    }

    /**
     * Collision with the Enemy
     */
    public void checkEnemy() {
        int offset = 25;
        float EnemyUpX, EnemyUpY;
        float EnemyDownX, EnemyDownY;
        float EnemyRightX, EnemyRightY;
        float EnemyLeftX, EnemyLeftY;
        ArrayList enemies = ArrayLists.getEnemies();
        for (int w = 0; w < enemies.size(); w++) {
            Enemy m = (Enemy) enemies.get(w);

            EnemyUpX = m.getX() + 64;
            EnemyUpY = m.getY() + offset;
            EnemyDownX = m.getX() + 64;
            EnemyDownY = m.getY() + 128 - offset;
            EnemyRightX = m.getX() + 128 - offset;
            EnemyRightY = m.getY() + 64;
            EnemyLeftX = m.getX() + offset;
            EnemyLeftY = m.getY() + 64;

            if (EnemyUpX > x && EnemyUpX < x + playerWidth
                    && EnemyUpY > y && EnemyUpY < y + playerWidth
                    || EnemyDownX > x && EnemyDownX < x + playerWidth
                    && EnemyDownY > y && EnemyDownY < y + playerWidth
                    || EnemyRightX > x && EnemyRightX < x + playerWidth
                    && EnemyRightY > y && EnemyRightY < y + playerWidth
                    || EnemyLeftX > x && EnemyLeftX < x + playerWidth
                    && EnemyLeftY > y && EnemyLeftY < y + playerWidth) {
                ArrayLists.enemies.remove(m);
                health--;
            }
        }
    }

    /**
     * Player fires Bullets
     */
    private void fire() {
        if (System.currentTimeMillis() - pause > 100) {
            Bullet z = new Bullet(game, x, y);
            ArrayLists.bullets.add(z);

            Bullet j = new Bullet(game, x + 100, y);
            ArrayLists.bullets.add(j);

            pause = System.currentTimeMillis();

        }
    }
}
