package Collision;
import Character.CharacterHandler;
import Objects.Entity.Entity;
import Objects.Entity.Player;
import Window.GamePanel;
import Window.Settings;

public class CollisionChecker {
    //GamePanel gp;
    /*public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }*/

    private static boolean checkWalk(int c){
        boolean g;
        switch (c) {
            case (3), (4) -> g = false;
            case (0), (1), (2) -> g = true;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        }
        return g;
    }

    public static boolean checkMove(Entity entity, CharacterHandler keyH){
        int nextPosX=entity.worldX,nextPosY = entity.worldY;
        if (keyH.upPressed) {
            nextPosY = entity.worldY - entity.speed;
        } else if (keyH.downPressed) {
            nextPosY =entity.worldY + entity.speed;
        } else if (keyH.leftPressed) {
            nextPosX =entity.worldX - entity.speed;
        } else if (keyH.rightPressed) {
            nextPosX = entity.worldX + entity.speed;
        }
        if ((nextPosX<0)||(nextPosY<0)||((1+nextPosX/entity.gp.tileSize)>entity.gp.maxWorldCol)||((1+nextPosY/entity.gp.tileSize)>entity.gp.maxWorldRow)){
            return false;
        }
        else{
            return checkWalk(Settings.mapTileNum[nextPosX / entity.gp.tileSize][nextPosY / entity.gp.tileSize]);
        }
    }
    public static boolean checkMoveMobs(Entity entity, int keyH){
        int nextPosX=entity.worldX,nextPosY = entity.worldY;
        if (keyH==0) {
            nextPosY = entity.worldY - entity.speed;
        } else if (keyH==1) {
            nextPosY =entity.worldY + entity.speed;
        } else if (keyH==2) {
            nextPosX =entity.worldX - entity.speed;
        } else if (keyH==3) {
            nextPosX = entity.worldX + entity.speed;
        }
        if ((nextPosX<0)||(nextPosY<0)||((nextPosX/entity.gp.tileSize)>(entity.gp.maxWorldCol-1))||((nextPosY/entity.gp.tileSize)>(entity.gp.maxWorldRow-1))){
            return false;
        }
        else{
            return checkWalk(Settings.mapTileNum[nextPosX / entity.gp.tileSize][nextPosY / entity.gp.tileSize]);
        }
    }

    /*public void checkTile(Entity entity) {  //(Дуже крута ідея ходьби) //пофіксити перевантаження індексім масивів при краю мапи
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow;
        int entityBottomRow;
        int tileNum1, tileNum2;
        if(Player.keyH.upPressed) {
            entityTopRow = (entityTopWorldY - Settings.speed) / gp.tileSize;
            tileNum1 = Settings.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = Settings.mapTileNum[entityRightCol][entityTopRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
            entityTopRow = entityTopWorldY / gp.tileSize;
            if(Player.keyH.downPressed){
                entityBottomRow = (entityBottomWorldY + Settings.speed) / gp.tileSize;
                tileNum1 = Settings.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = Settings.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                System.out.println(gp.tileM.tile[tileNum1].collision);
                System.out.println(gp.tileM.tile[tileNum2].collision);
            }
            entityBottomRow = entityBottomWorldY / gp.tileSize;
            if(Player.keyH.leftPressed){
                entityLeftCol = (entityLeftWorldX - Settings.speed) / gp.tileSize;
                tileNum1 = Settings.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = Settings.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            if(Player.keyH.rightPressed) {
                entityRightCol = (entityRightWorldX + Settings.speed) / gp.tileSize;
                tileNum1 = Settings.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = Settings.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
    }*/
}
