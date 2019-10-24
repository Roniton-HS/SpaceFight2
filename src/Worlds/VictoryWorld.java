package Worlds;

import main.Game;
import main.World;

import java.awt.*;

public class VictoryWorld extends World {

    public VictoryWorld(Game game) {
        super(game);
    }

    @Override
    public void tick() {
        if (game.getKeyHandler().enter) {
            MenuWorld menuWorld = new MenuWorld(game);
            World.setWorld(menuWorld);
        }
    }


    @Override
    public void render(Graphics g) {

    }
}
