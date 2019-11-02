package display;

import entities.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Score {
    public static int score;
    int x;
    int y;
    AffineTransform stretch = new AffineTransform();

    public Score(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        g.drawString("Score:" + score, x, y);
    }
}
