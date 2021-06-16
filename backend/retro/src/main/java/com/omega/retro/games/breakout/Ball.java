package com.omega.retro.games.breakout;

import javax.swing.*;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public Ball() {
        initBall();
    }

    private void initBall() {

        xdir= 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {

        var ballIcon = new ImageIcon("src/main/resources/static/icons/ball.png");
        image = ballIcon.getImage();
    }

    void move() {

        x += xdir;
        y += ydir;

        // When ball hits borders of board, the directions are changed
        if (x == 0) {
            setXDir(1);
        }

        if (x == Commons.WIDTH - imageWidth) {

            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }

    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {
        xdir = x;
    }

    void setYDir(int y) {
        ydir = y;
    }

    int getYDir() {
        return ydir;
    }

}
