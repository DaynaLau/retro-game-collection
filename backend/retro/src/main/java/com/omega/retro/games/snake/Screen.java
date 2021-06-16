package com.omega.retro.games.snake;

import org.springframework.core.task.support.TaskExecutorAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

    private final int WIDTH = 480;
    private final int HEIGHT = 480;
    private final int ICON_SIZE = 24;
    private final int MAX_ELEMENTS = (WIDTH * HEIGHT) / (24 * 24);
    private final int INIT_POS = 13;
    private final int SPEED = 180;

    private final int x[] = new int[MAX_ELEMENTS];
    private final int y[] = new int[MAX_ELEMENTS];

    // Declaring visual objects
    private int elements;
    private int food_x;
    private int food_y;

    private boolean leftDir = false;
    private boolean rightDir = false;
    private boolean upDir = false;
    private boolean downDir = false;
    private boolean gameStart = true;

    private Timer timer;
    private int score;
    private Image body;
    private Image food;
    private Image head;
    private Image dead;

    private final Color VERY_DARK_GREEN = new Color(0, 102, 0);
    private final Color DARK_GREEN = new Color(0, 153, 0);

    public Screen() {

        initScreen();
    }

    private void initScreen() {

        addKeyListener(new TAdapter());
        setBackground(DARK_GREEN);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        loadIcons();
        initGame();

    }

    private void loadIcons() {

        ImageIcon bodyIcon = new ImageIcon("src/main/resources/static/icons/hexagonal.png");
        body = bodyIcon.getImage();

        ImageIcon foodIcon = new ImageIcon("src/main/resources/static/icons/ramen.png");
        food = foodIcon.getImage();

        ImageIcon headIcon = new ImageIcon("src/main/resources/static/icons/smile.png");
        head = headIcon.getImage();

        ImageIcon deadIcon = new ImageIcon("src/main/resources/static/icons/dead.png");
        dead = deadIcon.getImage();

    }

    private void initGame() {
        score = 0;
        elements = 2;

        for (int i = 0; i < elements; i++) {
            x[i] = 120 - i * 24;
            y[i] = 120;
        }

        locateFood();

        timer = new Timer(SPEED, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

    }

    private void doDrawing(Graphics g) {

        if (gameStart) {

            g.drawImage(food, food_x, food_y, this);

            for (int i = 0; i < elements; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);

                } else {
                    g.drawImage(body, x[i], y[i], this);
                }
            }

            String scoreStr = "Score: " + String.valueOf(score);
            g.setColor(Color.black);
            g.setFont(new Font("Montserrat", Font.BOLD, 36));
            g.drawString(scoreStr, 10, 30);

            Toolkit.getDefaultToolkit().sync();



        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        g.drawImage(dead, WIDTH/2, HEIGHT/2, this);

        String scoreStr = "Score: " + String.valueOf(score);
        g.setColor(Color.black);
        g.setFont(new Font("Montserrat", Font.BOLD, 36));
        g.drawString(scoreStr, 10, 30);

        String msg = "Game Over";
        Font font = new Font("Montserrat", Font.BOLD, 72);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(msg, (WIDTH - metrics.stringWidth(msg))/2, HEIGHT/2);

    }

    private void checkFood() {

        if ((x[0] == food_x) && (y[0] == food_y)) {
            //System.out.println("yes");
            score++;
            elements++;
            locateFood();
        }
    }

    private void move() {

        for (int i = elements; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (leftDir) {
            x[0] -= ICON_SIZE;
        }

        if (rightDir) {
            x[0] += ICON_SIZE;
        }

        if (upDir) {
            y[0] -= ICON_SIZE;
        }

        if (downDir) {
            y[0] += ICON_SIZE;
        }
    }

    private void checkCollision() {

        for (int i = elements; i > 0; i--) {

            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
                gameStart = false;
            }
        }

        if (y[0] >= HEIGHT || y[0] < 0 || x[0] >= WIDTH || x[0] < 0) {
            gameStart = false;
        }

        if (!gameStart) {
            timer.stop();
        }
    }

    private void locateFood() {

        int rand = (int) (Math.random() * INIT_POS);
        food_x = ((rand * ICON_SIZE));

        rand = (int) (Math.random() * INIT_POS);
        food_y = ((rand * ICON_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameStart) {

            checkFood();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDir)) {
                leftDir = true;
                upDir = false;
                downDir = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDir)) {
                rightDir = true;
                upDir = false;
                downDir = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDir)) {
                upDir = true;
                leftDir = false;
                rightDir = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDir)) {
                downDir = true;
                leftDir = false;
                rightDir = false;
            }


        }
    }
}
