import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballPosX;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = 2;
    private MapGenerator map;

    public Gameplay() {
        Random rand = new Random();
        ballPosX = rand.nextInt(350) + 255;
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // drawing map
        map.draw((Graphics2D) g);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(692, 0, 3, 592);

        // scores
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        // you won!
        if (totalBricks == 0) {
            play = false;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("YOU WON,   Score: " + score, 240, 300);

            g.setColor(Color.GREEN);
            g.setFont(new Font("serif", Font.BOLD, 15));
            g.drawString("Press the Spacebar to Restart", 264, 350);

        }

        // game over
        if (ballPosY > 570) {
            play = false;
            ballXDir = 0;
            ballXDir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Game Over, Score: " + score, 250, 300);

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 15));
            g.drawString("Press the Spacebar to Restart", 264, 350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play == true) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYDir = -ballYDir;
            }
            A: for (int m = 0; m < map.map.length; m++) {
                for (int n = 0; n < map.map[0].length; n++) {
                    if (map.map[m][n] > 0) {
                        int brickX = n * map.brickWidth + 80;
                        int brickY = m * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, m, n);
                            totalBricks--;
                            score += 5;

                            if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
                                ballXDir = -ballXDir;
                            } else {
                                ballYDir = -ballYDir;
                            }
                            if (totalBricks <= 17) {
                                ballYDir = 3;
                            }
                            if (totalBricks <= 12) {
                                ballYDir = 4;
                            }
                            if (totalBricks <= 7) {
                                ballYDir = 5;
                            }
                            if (totalBricks <= 3) {
                                ballYDir = 6;
                            }
                            break A;
                        }

                    }
                }
            }

            ballPosX += ballXDir;
            ballPosY += ballYDir;
            if (ballPosX < 0) {
                ballXDir = -ballXDir;
            }
            if (ballPosY < 0) {
                ballYDir = -ballYDir;
            }
            if (ballPosX > 670) {
                ballXDir = -ballXDir;
            }

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!play) {
                play = true;
                Random rand2 = new Random();
                ballPosX = rand2.nextInt(350) + 255;
                ballPosY = 350;
                ballXDir = -1;
                ballYDir = +2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        if (totalBricks <= 17) {
            playerX += 25;
        } else if (totalBricks <= 12) {
            playerX += 30;
        } else if (totalBricks <= 7) {
            playerX += 35;
        } else {
            playerX += 20;
        }
    }

    public void moveLeft() {
        play = true;
        if (totalBricks <= 17) {
            playerX -= 25;
        } else if (totalBricks <= 12) {
            playerX -= 30;
        } else if (totalBricks <= 7) {
            playerX -= 35;
        } else {
            playerX -= 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}