package entities;

import gfx.ImageLoader;
import main.ArrayLists;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBullet extends MainBullet {

    private BufferedImage image;

    public EnemyBullet(Game game, float x, float y) {
        super(x, y);

        this.x = x;
        this.y = y;
        image = ImageLoader.loadImage("/textures/playerShot.png");

    }

    /**
     * Bullet tick
     */
    @Override
    public void tick() {
        move();
    }

    /**
     * Bullet render
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    /**
     * moves forward an disappears at the bottom
     */
    public void move() {
        y += 10;
        if (y <= -100 || y >= 1100) {
            ArrayLists.enemyBullets.remove(this);
        }
    }

    /**
     * getter/setters
     */
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
