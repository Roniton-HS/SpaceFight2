package Worlds;

import gfx.ImageLoader;
import main.Game;
import main.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DefeatWorld extends World {
    private BufferedImage Image; //background image

    public DefeatWorld(Game game) {
        super(game);
        Image = ImageLoader.loadImage("/textures/defeatScreen.png");
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
        g.drawImage(Image, 0, 0, null);
    }
}
