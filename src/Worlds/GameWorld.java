package Worlds;

import display.HealthBar;
import entities.Bullet;
import entities.Enemy;
import entities.EnemyBullet;
import entities.Player;
import gfx.ImageLoader;
import main.ArrayLists;
import main.Game;
import main.H;
import main.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameWorld extends World {

    private BufferedImage Image; //background image
    private Player player; //Player object
    private HealthBar healthBar; //Healthbar object
    private int backgroundCounter = 0; //Counter for the background
    private long enemyCounter = System.currentTimeMillis();
    private int enemyType = 0;


    /**
     * Constructor
     */
    public GameWorld(Game game) {
        super(game);
        Image = ImageLoader.loadImage("/textures/spaceBack.jpg");
        player = new Player(game, 100, 500);
        healthBar = new HealthBar();
        Enemy z = new Enemy(game, H.getRandomNumber(0, 700), -100);
        ArrayLists.enemies.add(z);
    }


    /**
     * ticks GameWorld
     */
    @Override
    public void tick() {
        //spawns Enemies
        spawnEnemy();

        //sets MenuWorld if there are no Enemies left
        checkEnemies();

        //tick Player
        player.tick();

        //tick healthBar
        healthBar.tick();

        //tick Bullet
        ArrayList bullets = ArrayLists.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            m.tick();
        }

        //tick Enemy
        ArrayList enemies = ArrayLists.getEnemies();
        for (int w = 0; w < enemies.size(); w++) {
            Enemy m = (Enemy) enemies.get(w);
            m.tick();
        }

        //tick EnemyBullets
        ArrayList enemyBullets = ArrayLists.getEnemyBullets();
        for (int w = 0; w < enemyBullets.size(); w++) {
            EnemyBullet m = (EnemyBullet) enemyBullets.get(w);
            m.tick();
        }
    }

    /**
     * renders GameWorld
     */
    @Override
    public void render(Graphics g) {
        //render background
        if (backgroundCounter > 1125) {
            backgroundCounter = 0;
        }
        g.drawImage(Image, 0, backgroundCounter, null);
        g.drawImage(Image, 0, -1125 + backgroundCounter, null);
        backgroundCounter = backgroundCounter + 2;

        //render player
        player.render(g);

        healthBar.render(g);

        //render bullets
        ArrayList bullets = ArrayLists.getBullets();
        for (int w = 0; w < bullets.size(); w++) {
            Bullet m = (Bullet) bullets.get(w);
            m.render(g);
        }

        //render Enemy
        ArrayList enemies = ArrayLists.getEnemies();
        for (int w = 0; w < enemies.size(); w++) {
            Enemy m = (Enemy) enemies.get(w);
            m.render(g);
        }

        //render EnemyBullets
        ArrayList enemyBullets = ArrayLists.getEnemyBullets();
        for (int w = 0; w < enemyBullets.size(); w++) {
            EnemyBullet m = (EnemyBullet) enemyBullets.get(w);
            m.render(g);
        }
    }

    public void checkEnemies() {
        ArrayList enemies = ArrayLists.getEnemies();
        if (enemies.isEmpty()) {
            VictoryWorld victoryWorld = new VictoryWorld(game);
            World.setWorld(victoryWorld);
            resetWorld();
        }
    }

    private void spawnEnemy() {
        int x = 0;

            switch (enemyType) {
                case 0:
                    if ((System.currentTimeMillis() - enemyCounter) > 3000) {
                        enemyCounter = System.currentTimeMillis();
                        for (int i = 0; i < 4; i++) {

                            Enemy z = new Enemy(game, x, -100);
                            x = x + 200;
                            if (x == 700) {
                                x = 0;
                            }
                            ArrayLists.enemies.add(z);
                        }
                        enemyType = 1;
                    }
                    break;
                case 1:
                    if ((System.currentTimeMillis() - enemyCounter) > 1000) {
                        Enemy z = new Enemy(game, H.getRandomNumber(0, 700), -100);
                        ArrayLists.enemies.add(z);
                        enemyType = 0;
                    }
                    break;
            }
    }

    /**
     * Resets World
     */
    public static void resetWorld() {
        ArrayLists.enemies.clear();
        ArrayLists.bullets.clear();
    }
}