package Objects.Build;

import Collision.CollisionChecker;
import Window.GamePanel;
import Window.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Corovan extends Build{
    int gold;
    boolean isFull=true;

    public Corovan(GamePanel gp){
        super(gp, 0, 0, gp.screenWidth / 2 - (gp.tileSize / 2), gp.screenHeight / 2 - (gp.tileSize / 2));
        gold = (int) (Math.random() * (251));
        int[] spawnCord = Settings.getSpawnCord();
        this.worldX=spawnCord[0]*gp.tileSize;
        this.worldY=spawnCord[1]*gp.tileSize;
        getCorovanImage();
    }

    @Override
    public void update() {

        if (isFull) {
            Settings.player.coin+=gold;
            isFull=false;
            getCorovanImage();
        }

    }


    private void getCorovanImage(){
        try {
            if(isFull) {
                im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/builds/corovan_1.png")));
            }
            else {
                im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/builds/corovan_2.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = im;
        g2.drawImage(image, screenX+worldX-Settings.PlayerX, screenY+worldY-Settings.PlayerY, gp.tileSize, gp.tileSize, null);
    }
}
