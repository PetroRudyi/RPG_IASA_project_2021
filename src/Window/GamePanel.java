package Window;

import Character.CharacterHandler;
import Collision.CollisionChecker;
import Objects.Build.Corovan;
import Objects.Build.Money;
import Objects.Entity.Slime;
import Tile.TileManager;
import Objects.Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    //Screen Settings
    final int originalTileSize = 16; //16*16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //so 48*48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768pix
    public final int screenHeight = tileSize * maxScreenRow; //576pix
    public Interface inter = new Interface(this);

    //Menu
    public static int gameState;
    public final static int menu = 0;
    public final static int play = 1;
    //World Settings
    public int maxWorldCol;
    public int maxWorldRow;
/*
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize* maxWorldRow;
*/

    //fps - frames per second
    int fps = 2;

    public TileManager tileM = new TileManager(this, Settings.k,Settings.p);
    CharacterHandler keyH = new CharacterHandler();
    Thread gameThread;
    public Player player = new Player(this, keyH);




    public GamePanel(int k, int p) {

        for(int i =0;i<5+Math.random()*20;i++){
            Slime slime = new Slime(this);
            Settings.Mobs.add(slime);
        }
        for(int i =0;i<1;i++){
            Corovan corovan = new Corovan(this);
            Settings.Builds.add(corovan);
        }
        for(int i =0;i<(Settings.xyisland.get(Settings.rating[0]).size())/30;i++){
            Money money = new Money(this);
            Settings.Builds.add(money);
        }


        System.out.println("Corovan   x: " + Settings.Builds.get(0).worldX + "   Y: " + Settings.Builds.get(0).worldY);
        Settings.player = player;
        this.maxWorldCol = 600/p;
        this.maxWorldRow = 600/p;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        this.inter.showMessage("Welcome, player! Kill all the enemies and gather treasures!", this.screenHeight/4 - this.tileSize/2, 150,
                10, new Color(81, 0, 61 ));
        this.inter.showMessage2("Press -> <- to move", this.screenHeight/2 - this.tileSize/2, 65, 10,
                new Color(81, 0, 61 ));

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
        gameState = menu;
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
        while (!Interface.gameFinished) {//gameThread != null
            long currentTime = System.nanoTime();
            try {
                update();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void update() throws IOException {
        if(gameState == play) {
            player.update();
            for (int i = 0; i < Settings.Mobs.size(); i++) {
                Settings.Mobs.get(i).update();
            }
        }
    }

    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            if(gameState == menu){
                inter.draw(g2);
            }
        else {
            tileM.draw(g2);

            for (int i = 0; i < Settings.Mobs.size(); i++) {
                Settings.Mobs.get(i).draw(g2);
            }
            for (int i = 0; i < Settings.Builds.size(); i++) {
                Settings.Builds.get(i).draw(g2);
            }
            inter.draw(g2);
            player.draw(g2);
            g2.dispose();
        }
    }

}
