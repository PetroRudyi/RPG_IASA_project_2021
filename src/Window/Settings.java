package Window;

import Objects.Entity.Entity;
import Objects.Entity.Player;
import Objects.Entity.Slime;

import java.util.ArrayList;

public class Settings {
    public static int p=10;
    public static int k=99;


    //Player stats
    public static int SpawnX=99;
    public static int SpawnY=99;
    public static Player player;

    //free space for spawn
    public static ArrayList<ArrayList<int[]>> xyisland;
    public static int[] rating;

    //world map
    public static int[][] mapTileNum;
    //character speed
    //public static int speed; //   //(Дуже крута ідея ходьби)

    public static ArrayList<Slime> Mobs=new ArrayList<>();


    public static int[] getSpawnCord(){
        int i = rating[0];
        int k = (int)(Math.random()*xyisland.get(i).size());
        int[] a = new int[2];
        a = (xyisland.get(i).get(k));
        xyisland.get(i).remove(k);
        return a;
    }
}
