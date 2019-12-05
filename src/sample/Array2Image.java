package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * This class is designed to minimize amount of code needed to get an image file
 * from an array of pixel values. Supports multiple color spaces & array input
 * options.
 */
public class Array2Image {

    public static void RGBImage(int[][][] RGBArray, int[][] mask, String filename, String ext) throws Exception {
        if (RGBArray.length == 0 || RGBArray[0].length == 0) {
            throw new Exception("ERROR: Cannot convert an array of length 0!");
        }

        if (RGBArray[0][0].length < 3) {
            throw new Exception("ERROR: Pixel value has incorrect format!");
        }

        BufferedImage buffered = new BufferedImage(RGBArray[0].length, RGBArray.length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < RGBArray.length; i++) {
            for (int j = 0; j < RGBArray[0].length; j++) {
                if (mask[i][j] == 1) {
                    buffered.setRGB(j, i, (RGBArray[i][j][0] << 16) | (RGBArray[i][j][1] << 8) | RGBArray[i][j][2]);
                } else {
                    buffered.setRGB(j, i, 0);
                }
            }
        }

        try {
            ImageIO.write(buffered, ext, new File(filename));
        } catch (IOException e) {
            System.out.println("ERROR: Cannot write to file: " + e.getMessage());
        }
    }
}
