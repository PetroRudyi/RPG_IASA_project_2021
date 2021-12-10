package entity;

import Window.GamePanel;
import Character.CharacterHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    CharacterHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, CharacterHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2; //- (gp.tileSize/2);
        screenY = gp.screenHeight/2; //- (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX =gp.tileSize * 23;
        worldY =gp.tileSize * 21;
        speed = 4;
    }
    public void getPlayerImage(){
        try{
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/pl1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed) {
            worldY -= speed;
        } else if (keyH.downPressed) {
            worldY += speed;
        } else if (keyH.leftPressed) {
            worldX -= speed;
        } else if (keyH.rightPressed) {
            worldX += speed;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = im;
        g2.drawImage(image, screenX, screenY,gp.tileSize, gp.tileSize, null );
    }
}
