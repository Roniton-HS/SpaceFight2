package entities;

public abstract class Creature extends Entity {

    public static int health;

    public Creature(float x, float y) {
        super(x, y);
        health = 10;
    }
}
