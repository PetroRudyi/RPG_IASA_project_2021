import Generate.Generate;
import Generate.MyVectorNoise;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.print("Point 1");
        Generate M = new Generate(12*50,12*50,49,6);// ki+1 кратно w и h, p кратно w и h
        //MyVectorNoise noise = new MyVectorNoise(12*50,12*50,19);
        //PerlinNoise noise = new PerlinNoise(11*50,11*50);

        BufferedImage image = M.getMapImage();
        String str = "C://Users//P//Desktop//image"+11;
        str = str +".jpg";
        File output_pix = new File(str);
        ImageIO.write(image, "jpg", output_pix);
    }
}
