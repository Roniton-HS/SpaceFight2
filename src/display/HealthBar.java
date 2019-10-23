package display;


import entities.Player;

import java.awt.*;

public class HealthBar {
    private int startHealth; //Player health at the beginning
    private int barHeight = 30; //height of the bar
    private int barWidth = 200; //width of the bar
    private int barFillPerLive; //width of the bar per health point
    private int barCounter = 0; //counter for the health

    /**
     * Constructor
     */
    public HealthBar() {
        this.startHealth = Player.health;
        barFillPerLive = barWidth/startHealth;
    }

    /**
     * Bar tick
     */
    public void tick() {
        if(!(barCounter == startHealth)) {
            barCounter = startHealth - Player.health;
        }
    }

    /**
     *  draws the bar
     */
    public void render(Graphics g) {
        g.drawRect(550, 950, barWidth, barHeight);
        g.setColor(Color.GRAY);
        g.fillRect(550,950, barWidth, barHeight);
        g.setColor(Color.RED);
        g.fillRect(550, 950, barWidth - barCounter * barFillPerLive, barHeight);
    }

}
