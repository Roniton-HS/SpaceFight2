package entities;

import gfx.ImageLoader;
import main.ArrayLists;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Entity {

    private BufferedImage image;

    private int speed = 0;

    private int enemyWidth = 128, enemyHeight = 128; // size of the Enemy

    public Enemy(Game game, float x, float y) {
        super(x, y);

        this.x = x;
        this.y = y;
        image = ImageLoader.loadImage("/textures/enemy.png");

    }

    @Override
    public void tick() {
        move();
        checkBullet();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, enemyWidth, enemyHeight, null);
    }

    private void move() {
        y += speed;
        if (y >= 1100) {
            ArrayLists.enemies.remove(0);
        }
    }

    private void checkBullet() {
        float BulletX, BulletY;
        ArrayList bullets = ArrayLists.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            BulletX = m.getX();
            BulletY = m.getY();
            if (BulletX > x && BulletX < x + enemyWidth
                    && BulletY > y && BulletY < y + enemyHeight) {
                ArrayLists.enemies.remove(this);
                ArrayLists.bullets.remove(m);
            }
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

    public BufferedImage getImage() {
        return image;
    }

}
