package Tile;

import Window.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/images/player/testMap.txt");
    }

    public void getTileImage() {
        tile[0] = new Tile();
        final Color color1 =
                tile[0].color = Color.YELLOW;
        tile[1] = new Tile();
        tile[1].color = Color.GRAY;
        tile[2] = new Tile();
        tile[2].color = Color.getHSBColor(150, 110, 7);
    }

    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = null;
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is));
            }
            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception ignored) {

        }
    }

    public void draw(Graphics2D g2) {
//        for (int y = 0; y < mapTileNum[0].length; y++) {
//            for (int[] ints : mapTileNum) {
//                System.out.print(ints[y] + " ");
//            }
//            System.out.println();
//        }
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.setColor(tile[tileNum].color);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize); //fillRect, drawRect
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
