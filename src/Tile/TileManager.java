package Tile;

import Generate.Generate;
import Window.GamePanel;
import Window.Settings;

import java.awt.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;

    public TileManager(GamePanel gp, int k, int p) {
        this.gp = gp;
        tile = new Tile[5];
        /*System.out.print("maxWorldCol: " + gp.maxWorldCol);
        System.out.print("maxWorldRow: " + gp.maxWorldRow);
        Settings.mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];*/

        getTileImage();

        loadMap(k, p);
    }


    public void getTileImage() {
        tile[4] = new Tile();
        tile[4].color = new Color(0,0, 0);
        tile[4].collision = true;
        tile[0] = new Tile();
        tile[0].color = new Color(139, 69, 19);
        tile[0].collision = false;
        tile[1] = new Tile();
        tile[1].color = new Color(240, 230, 140);
        tile[1].collision = false;
        tile[2] = new Tile();
        tile[2].color =  new Color(152,251, 152);
        tile[2].collision = false;
        tile[3] = new Tile();
        tile[3].color = new Color(105, 105,105);
        tile[3].collision = true;
    }

    public void loadMap(int k, int p) {
        Generate M = new Generate(12 * 50, 12 * 50, k, p);
        Settings.mapTileNum = M.getIdMap();
        int[] scp = M.SpawnPlayerCord();
        Settings.SpawnX = scp[0];
        Settings.SpawnY = scp[1];
        Settings.xyisland= M.getIsland();
        /*вставить загрузку сюда даных о карте
        о том где спаун игрока
        О том какие ячейки доступны для спауна остальных вещей включая порталы и т.д*/
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = Settings.mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
                g2.setColor(tile[tileNum].color);
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize); //fillRect, drawRect
                g2.setColor(Color.black);
                g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
