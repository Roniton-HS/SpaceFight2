package Worlds;

import main.Game;
import main.World;

import java.awt.*;

public class MenuWorld extends World {
    /**
     *  Constructor
     */
    public MenuWorld(Game game) {
        super(game);
    }

    /**
     * World tick
     */
    @Override
    public void tick() {
        if (game.getKeyHandler().enter) {
            GameWorld gameWorld = new GameWorld(game);
            World.setWorld(gameWorld);
        }
    }

    /**
     * World render
     */
    @Override
    public void render(Graphics g) {

    }
}
