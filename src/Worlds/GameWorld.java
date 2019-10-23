package Worlds;

import display.HealthBar;
import entities.Bullet;
import entities.Enemy;
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


    /**
     * Constructor
     */
    public GameWorld(Game game) {
        super(game);
        Image = ImageLoader.loadImage("/textures/startScreen.png");
        player = new Player(game, 100, 500);
        healthBar = new HealthBar();
        for (int i = 0; i < 4; i++) {
            Enemy z = new Enemy(game, H.getRandomNumber(0, 700), 100);
            ArrayLists.enemies.add(z);
        }
    }

    /**
     * ticks GameWorld
     */
    @Override
    public void tick() {
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
    }

    /**
     * renders GameWorld
     */
    @Override
    public void render(Graphics g) {
        //render background
        g.drawImage(Image, 0, 0, null);

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
    }

    public void checkEnemies(){
        ArrayList enemies = ArrayLists.getEnemies();
        if(enemies.isEmpty()){
            MenuWorld menuWorld = new MenuWorld(game);
            World.setWorld(menuWorld);
            resetWorld();
        }
    }

    /**
     * Resets World
     */
    public void resetWorld() {
        ArrayLists.enemies.clear();
        ArrayLists.bullets.clear();
    }
}