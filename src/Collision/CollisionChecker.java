package Collision;
import Objects.Entity.Entity;
import Objects.Entity.Player;
import Window.GamePanel;
import Window.Settings;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity entity) {
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
    }
}
