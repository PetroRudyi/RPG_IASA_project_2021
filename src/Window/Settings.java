package Window;

import Objects.Entity.Entity;
import Objects.Entity.Player;

import java.util.ArrayList;

public class Settings {
    public static int p=10;
    public static int k=99;


    //Player stats
    public static int SpawnX=99;
    public static int SpawnY=99;
    public static Player player;

    //free space
    public static ArrayList<ArrayList<int[]>> xyisland;

    //world map
    public static int[][] mapTileNum;
    //character speed
    //public static int speed; //   //(Дуже крута ідея ходьби)

    public static ArrayList<Entity> Mobs;
}
