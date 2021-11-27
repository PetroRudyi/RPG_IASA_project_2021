import Generate.Generate;
import java.util.Scanner;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int count = 1;
        boolean c = true;
        int k,p;
        Scanner in = new Scanner(System.in);
        do {
            System.out.printf("Width: %d   Height: %d\n", 12*50,12*50);
            System.out.print("");
            System.out.print("Input a K (k+1): ");
            k = in.nextInt();
            System.out.print("Input a P: ");
            p = in.nextInt();

            Generate M = new Generate(12 * 50, 12 * 50, k, p);// k+1 кратно w и h, p кратно w и h
            //MyVectorNoise noise = new MyVectorNoise(12*50,12*50,19);
            //PerlinNoise noise = new PerlinNoise(11*50,11*50);

            BufferedImage image = M.getMapIDImage();
            String str = "C://Users//P//Desktop//Test_" + count;
            str = str + "_";
            str = str + k;
            str = str + "_";
            str = str + p;
            str = str + ".jpg";
            File output_pix = new File(str);
            ImageIO.write(image, "jpg", output_pix);
            count++;
        }while(c);
        in.close();
    }
}
