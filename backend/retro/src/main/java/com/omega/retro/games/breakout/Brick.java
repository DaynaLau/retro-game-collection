package com.omega.retro.games.breakout;

import javax.swing.*;

public class Brick extends Sprite{

    // Keeping track if brick is destroyed
    private boolean destroyed;

    public Brick(int x, int y) {
        initBrick(x, y);
    }

    private void initBrick(int x, int y) {

        this.x = x;
        this.y = y;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }

    private void loadImage() {
        var brickIcon = new ImageIcon("src/main/resources/static/icons/brick.png");
        image = brickIcon.getImage();
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean bool) {
        destroyed = bool;
    }



}
