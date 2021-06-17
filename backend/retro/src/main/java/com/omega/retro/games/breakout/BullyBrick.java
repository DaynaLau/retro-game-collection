package com.omega.retro.games.breakout;

import javax.swing.*;

public class BullyBrick extends Sprite {


    // Keeping track if brick is destroyed
    private boolean destroyed;

    public BullyBrick(int x, int y) {
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
        var bullyIcon = new ImageIcon("src/main/resources/static/icons/bullybrick.png");
        image = bullyIcon.getImage();
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean bool) {
        destroyed = bool;
    }

}
