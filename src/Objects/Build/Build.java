package Objects.Build;

import Objects.GameObject;
import Window.GamePanel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Build extends GameObject {
    public GamePanel gp;
    public final int screenX;
    public final int screenY;

    public BufferedImage im, im2;

    public Build(GamePanel gp,int spawnX, int spawnY,int screenX,int screenY){
        this.screenX = screenX;
        this.screenY = screenY;
        this.gp = gp;

        worldX =spawnX*gp.tileSize;
        worldY =spawnY*gp.tileSize;
    }

    public void update() throws IOException {

    }

}
