import Generate.PerlinNoise;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.print("Point 1");
        PerlinNoise noise = new PerlinNoise(400,400);
        BufferedImage image = noise.getNoiseImage();
        File output_pix = new File("C://Users//P//Desktop//image.jpg");
        ImageIO.write(image, "jpg", output_pix);
    }
}
