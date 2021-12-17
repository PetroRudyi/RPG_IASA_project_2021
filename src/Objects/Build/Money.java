package Objects.Build;

import Collision.CollisionChecker;
import Window.GamePanel;
import Window.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Money extends Build{
    int gold;
    short isFull;

    public Money(GamePanel gp){
        super(gp, 0, 0, gp.screenWidth / 2 - (gp.tileSize / 2), gp.screenHeight / 2 - (gp.tileSize / 2));
        gold = (int) (Math.random() * (251));
        int[] spawnCord = Settings.getSpawnCord();
        this.worldX=spawnCord[0]*gp.tileSize;
        this.worldY=spawnCord[1]*gp.tileSize;
        isFull =(short) (Math.random()*2);
        getMoneyImage();
    }

    @Override
    public void update() {
        System.out.println("Coin  x: " + worldX+"   Y: "+worldY);
        Settings.player.coin+=gold;
        for (int i = 0; (i<Settings.Builds.size()); i++){
            if ((worldX == Settings.Builds.get(i).worldY) && (worldY == Settings.Builds.get(i).worldX)) {
                Settings.Builds.remove(i);
                break;
            }
        }

    }


    private void getMoneyImage(){
        try {
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/builds/coin_1.png")));
            im2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/builds/coin_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image=im;
        switch (isFull){
            case(1)-> {
                isFull=0;
                image = im;
            }
            case (0)->{
                isFull=1;
                image = im2;
            }
        }
        g2.drawImage(image, screenX+worldX-Settings.PlayerX, screenY+worldY-Settings.PlayerY, gp.tileSize, gp.tileSize, null);
    }
}
