import java.awt.Window.*;
import Generate.Generate;
import Window.GamePanel;
import Window.Settings;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Settings.k=49;
        Settings.p=10;

        Scanner in = new Scanner(System.in);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D RPG");
        GamePanel gamePanel = new GamePanel(Settings.k,Settings.p);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        in.close();
    }
}
