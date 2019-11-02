package Worlds;

import display.HealthBar;
import display.Score;
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
    private int enemyWaveCounter;
    private Score score;


    /**
     * Constructor
     */
    public GameWorld(Game game) {
        super(game);
        Image = ImageLoader.loadImage("/textures/spaceBack.jpg");
        player = new Player(game, 100, 500);
        healthBar = new HealthBar();
        spawnWave(0, 0);
        enemyWaveCounter++;
        score = new Score(50, 980);
    }


    /**
     * ticks GameWorld
     */
    @Override
    public void tick() {
        checkScore();

        //spawns Enemies
        spawnEnemy();

        //sets MenuWorld if there are no Enemies left
        //checkEnemies();

        //tick Player
        player.tick();

        //tick healthBar
        healthBar.tick();

        //tick Score
        score.tick();

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

        //render HealthBar
        healthBar.render(g);

        //render Score
        score.render(g);

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

    public void spawnEnemy() {
        spawnWave(enemyWaveCounter, 5000);
        enemyWaveCounter++;
        if(enemyWaveCounter == 5){
            enemyWaveCounter = 0;
        }
    }

    private void spawnWave(int wave, int delay) {
        int x = 50;
        int y = -500;
        switch (wave) {

            case 0: // line
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 6; i++) {

                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        x = x + 112;
                        if (x > 672) {
                            x = 50;
                        }
                    }
                }
                break;

            case 1: // V
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    x = 115;
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 5; i++) {
                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        x = x + 112;
                        if (i < 2) {
                            y = y + 87;
                        } else {
                            y = y - 87;
                        }
                    }
                }
                break;

            case 2: // vertical line
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    x = 272;
                    y = -1050;
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 5; i++) {
                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        y = y + 110;
                    }
                }
                break;

            case 3: // tilted line left
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    x = 115;
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 5; i++) {
                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        y = y + 87;
                        x = x + 112;
                    }
                }
                break;

            case 4: // tilted line right
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    x = 557;
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 5; i++) {
                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        y = y + 87;
                        x = x - 112;
                    }
                }
                break;

            case 5: // reverted V
                if ((System.currentTimeMillis() - enemyCounter) > delay) {
                    x = 115;
                    enemyCounter = System.currentTimeMillis();
                    for (int i = 0; i < 5; i++) {
                        Enemy z = new Enemy(game, x, y);
                        ArrayLists.enemies.add(z);
                        x = x + 112;
                        if (i < 2) {
                            y = y - 87;
                        } else {
                            y = y + 87;
                        }
                    }
                }
                break;
        }
    }

    public void checkScore(){
        if(Score.score == 50){
            Score.score = 0;
            VictoryWorld victoryWorld = new VictoryWorld(game);
            World.setWorld(victoryWorld);
            resetWorld();
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