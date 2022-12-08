package brickBreaker;

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.Timer;


public class GameSettings extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;

    DatabaseManager db_manager = DatabaseManager.db_manager;
    private int score = 0;

    private int totalBricks = 400;

    private Timer timer;
    private int delay = 8;

    Random random = new Random();
    private int ballposX = random.nextInt(0, 2000);
    private int ballposY = 900;
    private int ballXdir = 1;
    private int ballYdir = -2;

    private String username;
    private int high_score = 0;

    private int playerX = ballposX;

    private MapGenerator map;

    public GameSettings(String username) throws SQLException {
        this.username = username;
        this.high_score = this.db_manager.getHighScore(username);
        System.out.println(this.high_score);
        map = new MapGenerator(20, 20);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background
        g.setColor(Color.black);
        g.fillRect(0, 0, 1920, 1080);


        // drawing map
        map.draw((Graphics2D) g);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 1077);
        g.fillRect(0, 0, 1917, 3);
        g.fillRect(1917, 0, 3, 1077);
        g.fillRect(0, 1012, 1917, 3);

        //username
        g.setColor(Color.yellow);
        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("Username: " + username, 80, 50);

        // high score
        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("High Score: " + high_score, 1400, 50);

        // the scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("" + score, 1800, 50);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 950, 200, 12);

        // the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        // when you won the game
        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("You Won", 810, 400);

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press (Enter) to Restart", 730, 550);
        }

        // when you lose the game
        if (ballposY > 960) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("Game Over, Scores: " + score, 600, 400);

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press (Enter) to Restart", 730, 550);
        }

        g.dispose();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 1730) {
                playerX = 1730;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 20) {
                playerX = 20;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                ballposX = random.nextInt(0, 1900);
                ballposY = 900;
                ballXdir = -1;
                ballYdir = -2;
                playerX = ballposX;
                totalBricks = 400;
                map = new MapGenerator(20, 20);

                try {
                    if (db_manager.getHighScore(username) < score) {
                        db_manager.setHighScore(username, score);
                        high_score = score;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                score = 0;

                repaint();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void moveRight() {
        play = true;
        playerX += 40;
    }

    public void moveLeft() {
        play = true;
        playerX -= 40;
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        // check paddle collision with the ball
        if (play) {
            //check ball collision with left part of paddle
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 940, 55, 8))) {
                ballYdir *= -1;
//                ballXdir = -2;
                //check ball collision with central part of paddle
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 54, 940, 12, 8))) {
                ballYdir *= -1;
                //check ball collision with right part of paddle
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 64, 940, 100, 8))) {
                ballYdir = -ballYdir;
//                ballXdir = 2;
            }

            // check map collision with the ball
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        //scores++;
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            score += 5;
                            totalBricks--;

                            // when ball hit right or left of brick
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            }
                            // when ball hits top or bottom of brick
                            else {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 1900) {
                ballXdir = -ballXdir;
            }

            repaint();

        }
    }
}

