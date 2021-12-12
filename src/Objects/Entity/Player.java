package Objects.Entity;

import Objects.Entity.Entity;
import Window.GamePanel;
import Character.CharacterHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    CharacterHandler keyH;


    public Player(GamePanel gp, CharacterHandler keyH){
        super(gp,gp.screenWidth/2- (gp.tileSize/2),gp.screenHeight/2- (gp.tileSize/2),100,100,20);
        this.keyH = keyH;
        getPlayerImage();
    }

    public void getPlayerImage(){
        try{
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/pl1.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public double getHP() {
        return 0;
    }

    @Override
    public double getMaxHP() {
        return 0;
    }

    @Override
    public void setHP(double HP) {

    }

    @Override
    public void setMaxHP(double maxHP) {

    }
}
