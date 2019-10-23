package main;

import Worlds.GameWorld;
import Worlds.MenuWorld;
import display.Display;
import input.KeyHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {

    private Display display; //JFrame and Canvas
    public int width, height; //with and height of the game
    public String title; //title of the Window

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;//Graphics object


    private KeyHandler keyHandler; //KeyListener

    //Worlds
    private World gameWorld;
    public World menuWorld;

    /**
     * Constructor
     * @param title title of the Window
     * @param width with of the game
     * @param height height of the game
     */
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyHandler = new KeyHandler();
    }

    /**
     * initial Method
     */
    private void init() {
        display = new Display(title, width, height); //creates Display
        display.getFrame().addKeyListener(keyHandler); //adds KeyListener
        ArrayLists arrayLists = new ArrayLists(); //creates ArrayLists object
        menuWorld = new MenuWorld(this); //creates MenuWorld
        World.setWorld(menuWorld); //sets World to MenuWorld

    }

    /**
     * ticks the Game
     */
    private void tick() {
        keyHandler.tick();
        if (World.getWorld() != null)
            World.getWorld().tick();
    }

    /**
     * renders the Game
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        if (World.getWorld() != null) {
            World.getWorld().render(g);
        }
        //EndDrawing

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }


    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
