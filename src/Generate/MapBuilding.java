package Generate;
import Generate.Generate;
import Generate.MyVectorNoise;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class MapBuilding {

//k - это количество блоков, тоесть клеток по ширине/высоте (которіе кратніе k)
    public static int[][] pixilate(int weight,int height, int k, int[][] map) {
        int[][] g = new int[weight][height];
        for (int y = 0; y < height; y += k) {
            for (int x = 0; x < weight; x += k) {
                // Обрезка фото чтобы вытащить цвет пикселя
                int[][] pixelMap = getPixelMap(map, weight, height ,x, y, k, k);
                int dominantColor = getDominantColor(pixelMap, k,k);
                // Закрашиваем этот самый пиксель
                for (int yd = y; (yd < y + k) && (yd < height); yd++) {
                    for (int xd = x; (xd < x + k) && (xd < weight); xd++) {
                        g[xd][yd] = dominantColor;
                    }
                }
            }
        }
        return g;

    }

    public static int[][] getPixelMap(int[][] x, int weight,int height,int startx, int starty, int kw, int kh) {
        if (startx < 0) startx = 0;
        if (starty < 0) starty = 0;
        if (startx > weight) startx = weight;
        if (starty > height) starty = height;
        if (startx + kw > weight) kw = weight - startx;
        if (starty + kh > height) kh = height - starty;

        int[][] g = new int[kw][kw];
        for(int i = startx; i<(startx+kw);i++){
            for(int j = starty; j<(starty+kh);j++){
                g[i-startx][j-starty]=x[i][j];
            }
        }

        return g;
    }

    //Выделяем цвет. Записываем все в Хашмапу считая количество определенных цветов
    public static int getDominantColor(int[][] m, int weight, int height) {
        Map<Integer, Integer> colorCounter = new HashMap<>();
        for (int x = 0; x < weight; x++) {
            for (int y = 0; y < height; y++) {
                int currentRGB = m[x][y];
                int count = colorCounter.getOrDefault(currentRGB, 0);
                colorCounter.put(currentRGB, count + 1);
            }
        }
        return getDominantColor(colorCounter);
    }

    private static int getDominantColor(Map<Integer, Integer> colorCounter) {
        int dominantRGB = colorCounter.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)// Stream API и Тернарная операция и лямбда выражение и https://metanit.com/java/tutorial/10.11.php  и вообще https://t.me/include_anime/27512 тут все обьяснено (Просите Петю, он перекинет вам)
                .get()
                .getKey();
        return dominantRGB;
    }
}
