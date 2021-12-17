package Objects.Entity;
import Collision.CollisionChecker;
import Window.GamePanel;
import Window.Settings;
import Character.CharacterHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    public static CharacterHandler keyH;

    public Player(GamePanel gp, CharacterHandler keyH) {
        super(gp, Settings.SpawnX, Settings.SpawnY, gp.screenWidth / 2 - (gp.tileSize / 2), gp.screenHeight / 2 - (gp.tileSize / 2), 100, 100, 20);
        Player.keyH = keyH;
        getPlayerImage();
        //solidArea = new Rectangle(gp.tileSize / 3, gp.tileSize /3, gp.tileSize / 3 + 1, gp.tileSize / 3+1);
    }
    public void getPlayerImage() {
        try {
            im = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/pl1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/pl1.png"))); doght2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/pl1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(){
        /*if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                if(!collisionOn) {
                    worldY -= Settings.speed;
                }
            } else if (keyH.downPressed) {
                direction = "down";
                if(!collisionOn) {
                    worldY += Settings.speed;
                }
            } else if (keyH.leftPressed) {
                direction = "left";
                if(!collisionOn) {
                    worldX -= Settings.speed;
                }
            } else if (keyH.rightPressed) {
                direction = "right";
                if(!collisionOn) {
                    worldX += Settings.speed;
                }
            }
            collisionOn = false;
            gp.colChecker.checkTile(this);

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }*/

        //System.out.print("maxWorldCol: " + gp.maxWorldCol+"\n");
        if(isEnemy()){ //проверка на врага. Если враг есть сразу же атакую его

        }
        else if(CollisionChecker.checkMove(this,keyH)) {
            move();
            }


    }


    public void draw(Graphics2D g2) {
        BufferedImage image = im;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void move() {
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

    private boolean isEnemy (){
        int nextPosX=worldX,nextPosY = worldY;
        if (keyH.upPressed) {
            nextPosY = worldY - speed;
        } else if (keyH.downPressed) {
            nextPosY =worldY + speed;
        } else if (keyH.leftPressed) {
            nextPosX =worldX - speed;
        } else if (keyH.rightPressed) {
            nextPosX = worldX + speed;
        }
        boolean c = true;
        for (int i = 0; (i<Settings.Mobs.size())&&c; i++){
            if ((nextPosY == Settings.Mobs.get(i).worldY) && (nextPosX == Settings.Mobs.get(i).worldX)) {
                attack(Settings.Mobs.get(i));
                c = false;
                break;
            }
        }
        return !c;
    }

    @Override
    public int getHP() {
        return 0;
    }

    @Override
    public int getMaxHP() {
        return 0;
    }

    @Override
    public void setHP(int HP) {

    }

    @Override
    public void setMaxHP(int maxHP) {

    }
}
