package ru.MrCrashLab;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;

public class FractalCreator {

    private final String fractalsPath = "G:\\Java code\\FractalNFT\\src\\resources\\fractals\\Fractal_";
    private final String gradientsPath = "G:\\Java code\\FractalNFT\\src\\resources\\gradients\\Gradient_";
    private final String fractalFormatName = "jpg";
    private final String gradientFormatName = "jpg";
    private final int imgWidth = 3840*5;
    private final int imgHeight = 2560*5;
    private final int iterNum = 200;
    private final int numFractals = 10;
    private final double fractalRadius = 100;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");

    public void createFractal() {
        Instant start = Instant.now();
        for (int k = 0; k < numFractals; k++) {
            createMandelbrotFractal(k, -2.25, 1, null, 2);
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("\u001B[32mРендер завершен!\u001B[0m");
        System.out.println("\u001B[36mВремени потрачено на рендер:\u001B[0m " + dateFormat.format(timeElapsed.toMillis()));
    }


    private void createMandelbrotFractal(int k, double leftX, double rightX, Double tmpUpY, int exp) {
        double upY;
        double downY;
        int percent;
        int oldPercent = 0;
        try {
            BufferedImage gradient;
            BufferedImage bufferedImage = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
            File file = new File(fractalsPath + k + "." + fractalFormatName);
            gradient = ImageIO.read(new File(gradientsPath + k + "." + gradientFormatName));
            Instant start = Instant.now();
            if (tmpUpY == null) {
                upY = ((rightX - leftX) / ((double) imgWidth / imgHeight)) / 2;
                downY = -upY;
            } else {
                upY = Double.valueOf(tmpUpY);
                downY = upY - ((rightX - leftX) / ((double) imgWidth / imgHeight));
            }
            for (int i = 0; i < imgWidth; i++) {
                for (int j = 0; j < imgHeight; j++) {
                    ComplexNum z = new ComplexNum(0, 0);
                    ComplexNum c = new ComplexNum(leftX + i * (rightX - leftX) / imgWidth, upY - j * (upY - downY) / imgHeight);
                    for (int l = 0; l < iterNum; l++) {
                        z = ComplexMath.sum(ComplexMath.pow(z, exp), c);
                        if (ComplexMath.modulus(z) > fractalRadius) {
                            bufferedImage.setRGB(i, j, gradient.getRGB(0, l*(gradient.getHeight()/iterNum)));
                            break;
                        }
                    }
                }
                percent = (int) ((double) i / imgWidth * 100);
                if (percent > oldPercent) {
                    System.out.println("\u001B[36mПроцентов " + (k + 1) + " фрактала отрендерено:\u001B[0m " + percent + "%");
                    oldPercent = percent;
                }
            }
            ImageIO.write(bufferedImage, fractalFormatName, file);
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("\u001B[32mРендер " + (k + 1) + " фрактала завершен!\u001B[0m");
            System.out.println("\u001B[36mВремени потрачено на рендер " + (k + 1) + " фрактала:\u001B[0m " + dateFormat.format(timeElapsed.toMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
