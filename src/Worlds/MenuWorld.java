package Worlds;

import gfx.ImageLoader;
import main.Game;
import main.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuWorld extends World {

    private BufferedImage Image; //background image
    private boolean wait = false;

    /**
     * Constructor
     */
    public MenuWorld(Game game) {
        super(game);
        Image = ImageLoader.loadImage("/textures/menuWorld.png");
        wait = false;
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        if (!game.getKeyHandler().enter) {
            wait = true;
        }

        if (wait && game.getKeyHandler().enter) {
            GameWorld gameWorld = new GameWorld(game);
            World.setWorld(gameWorld);
        }
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {
//render background
        g.drawImage(Image, 0, 0, null);
    }
}
