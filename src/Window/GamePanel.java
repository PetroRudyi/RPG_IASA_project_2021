package Window;

import Character.CharacterHandler;
import Collision.CollisionChecker;
import Tile.TileManager;
import Objects.Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16*16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //so 48*48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768pix
    public final int screenHeight = tileSize * maxScreenRow; //576pix

    //World Settings
    public int maxWorldCol;
    public int maxWorldRow;
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize* maxWorldRow;

    //fps - frames per second
    int fps = 10;

    public TileManager tileM = new TileManager(this, Settings.k,Settings.p);
    CharacterHandler keyH = new CharacterHandler();
    Thread gameThread;
    public CollisionChecker colChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);


    public GamePanel(int k, int p) {
        this.maxWorldCol = 600/p;
        this.maxWorldRow = 600/p;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        System.out.println("Point 5");
        this.setDoubleBuffered(true);
        System.out.println("Point 6");
        this.addKeyListener(keyH);
        System.out.println("Point 7");
        this.setFocusable(true);
        System.out.println("Point 8");
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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

}
