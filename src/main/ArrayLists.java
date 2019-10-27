package main;

import java.util.ArrayList;

public class ArrayLists {
    public static ArrayList bullets; // list of the bullets
    public static ArrayList enemies; // list of the Enemies
    public static ArrayList enemyBullets; // list of the bullets of the enemies



    public ArrayLists() {
        bullets = new ArrayList();
        enemies = new ArrayList();
        enemyBullets = new ArrayList();
    }

    public static ArrayList getBullets() {
        return bullets;
    }

    public static ArrayList getEnemies() {
        return enemies;
    }

    public static ArrayList getEnemyBullets() {
        return enemyBullets;
    }
}
