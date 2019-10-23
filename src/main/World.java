package main;

import java.awt.*;

public abstract class World {

    private static World currentWorld = null;

    public static void setWorld(World world){
        currentWorld = world;
    }

    public static World getWorld(){
        return currentWorld;
    }

    protected Game game;

    public World(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
