package ru.MrCrashLab;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class GradientCreator {

    private final String gradientsPath = "G:\\Java code\\FractalNFT\\src\\resources\\gradients\\Gradient_";
    private final int gradientWidth = 2048;
    private final int gradientHeight = 2048;
    private final String gradientFormatName = "jpg";
    private final ArrayList<Integer> colorList = new ArrayList<>();
    private final int maxNumColor = 7;
    private final int minNumColor = 3;
    private final int numGrad = 1000;

    public GradientCreator() {
        colorList.add(new Color(215, 78, 78).getRGB());
        colorList.add(new Color(135, 203, 54).getRGB());
        colorList.add(new Color(87, 144, 229).getRGB());
        colorList.add(new Color(236, 219, 32).getRGB());
        colorList.add(new Color(32, 236, 212).getRGB());
        colorList.add(new Color(112, 42, 218).getRGB());
        colorList.add(new Color(170, 0, 255).getRGB());
        colorList.add(new Color(2, 175, 175).getRGB());
        colorList.add(new Color(255, 137, 75).getRGB());
        colorList.add(new Color(89, 197, 239).getRGB());
        colorList.add(new Color(74, 231, 53).getRGB());
        colorList.add(new Color(239, 174, 89).getRGB());
        colorList.add(new Color(232, 75, 75).getRGB());
        colorList.add(new Color(78, 113, 243).getRGB());
        colorList.add(new Color(197, 71, 132).getRGB());
        colorList.add(new Color(47, 218, 31).getRGB());
        colorList.add(new Color(34, 124, 173).getRGB());
        colorList.add(new Color(191, 252, 71).getRGB());
        colorList.add(new Color(46, 35, 148).getRGB());
        colorList.add(new Color(65, 227, 170).getRGB());
        colorList.add(new Color(113, 29, 122).getRGB());
        colorList.add(new Color(171, 64, 64).getRGB());
        colorList.add(new Color(227, 108, 23).getRGB());
        colorList.add(new Color(30, 225, 68).getRGB());
        colorList.add(new Color(225, 30, 222).getRGB());
        colorList.add(new Color(241, 219, 54).getRGB());
    }

    public void createGradient() {
        Instant start = Instant.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        for (int k = 0; k < numGrad; k++) {
            int colorNum = (int) (Math.random() * (maxNumColor - minNumColor + 1) + minNumColor);
            int[] colors = new int[colorNum];
            ArrayList<Integer> useIndex = new ArrayList<>();
            for (int i = 0; i < colorNum; i++) {
                int randIndex = (int) (Math.random() * colorList.size());
                useIndex.add(randIndex);
                colors[i] = colorList.get(randIndex);
            }
            createLinearColorGradient(k, colors);
            System.out.println("\u001B[34mГрадиентов отрендерено:\u001B[0m " + k);
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("\u001B[32mРендер завершен!\u001B[0m");
        System.out.println("\u001B[36mВремени потрачено на рендер:\u001B[0m " + dateFormat.format(timeElapsed.toMillis()));
    }

    private void createLinearColorGradient(int k, int[] colors) {
        BufferedImage bufferedImage = new BufferedImage(gradientWidth, gradientHeight, BufferedImage.TYPE_INT_RGB);
        File file = new File(gradientsPath + k + "." + gradientFormatName);
        for (int z = 0; z < colors.length - 1; z++) {
            Color colorZ = new Color(colors[z]);
            Color colorZ1 = new Color(colors[z + 1]);
            double stepR = (double) (colorZ1.getRed() - colorZ.getRed()) / (gradientHeight / (colors.length - 1));
            double stepG = (double) (colorZ1.getGreen() - colorZ.getGreen()) / (gradientHeight / (colors.length - 1));
            double stepB = (double) (colorZ1.getBlue() - colorZ.getBlue()) / (gradientHeight / (colors.length - 1));
            for (int i = 0; i < gradientHeight / (colors.length - 1); i++) {
                for (int j = 0; j < gradientWidth; j++) {
                    bufferedImage.setRGB(j, i + (gradientHeight / (colors.length - 1)) * z, new Color(
                            (int) (colorZ.getRed() + stepR * i),
                            (int) (colorZ.getGreen() + stepG * i),
                            (int) (colorZ.getBlue() + stepB * i)).getRGB());

                }
            }
        }
        try {
            ImageIO.write(bufferedImage, gradientFormatName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
