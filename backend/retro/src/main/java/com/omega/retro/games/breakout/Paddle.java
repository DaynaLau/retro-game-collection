package com.omega.retro.games.breakout;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Paddle extends Sprite {

    private int dx;

    public Paddle() {

        initPaddle();
    }

    private void initPaddle() {

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {

        var paddleIcon = new ImageIcon("src/main/resources/static/icons/paddle.png");
        image = paddleIcon.getImage();
    }

    void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        // Keeping paddle within the confines of the board screen
        if (x >= Commons.WIDTH - imageWidth) {
            x = Commons.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

    }

    private void resetState() {

        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }

}
