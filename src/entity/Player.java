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

    public Player(GamePanel gp, CharacterHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x=100;
        y=100;
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
            y -= speed;
        } else if (keyH.downPressed) {
            y += speed;
        } else if (keyH.leftPressed) {
            x-= speed;
        } else if (keyH.rightPressed) {
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = im;
        g2.drawImage(image, x, y,gp.tileSize, gp.tileSize, null );
    }
}
