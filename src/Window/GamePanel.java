package Window;

import Character.CharacterHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16*16
    final int scale = 3;
    final int tileSize = originalTileSize * scale; //so 48*48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768pix
    final int screenHeight = tileSize * maxScreenRow; //576pix

    //fps - frames per second
    int fps = 60;

    CharacterHandler keyH = new CharacterHandler();
    Thread gameThread;

    //default position of character
    int character_X = 100;
    int character_Y = 100;
    int characterSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            update();
            repaint();
            try {
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime /= 1000000;
                if (remainTime < 0) {
                    remainTime = 0;
                }
                Thread.sleep((long) remainTime);
                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (keyH.upPressed) {
            character_Y -= characterSpeed;
        } else if (keyH.downPressed) {
            character_Y += characterSpeed;
        } else if (keyH.leftPressed) {
            character_X -= characterSpeed;
        } else if (keyH.rightPressed) {
            character_X += characterSpeed;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(character_X, character_Y, tileSize, tileSize);
        g2.dispose();
    }

}
