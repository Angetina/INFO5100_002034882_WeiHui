package com.example.finalproject_imagemanager.utils;

//The "pixel data" of the image is used for pixel-level modification.
import java.awt.image.BufferedImage;

public class ImageFilterUtils {

    //Convert color images to black and white.
    public static void applyGrayscaleFilter(BufferedImage image) {
        //Double loop, read each pixel's (x, y) one by one.
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                //Get the RGB color value of this pixel (represented as an int).
                int rgb = image.getRGB(x, y);

                //Extract the red, green, and blue 8-bit values from the integer.
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = (rgb) & 0xff;

                //The grayscale value is the average of the three RGB colors.
                int gray = (red + green + blue) / 3;

                //The grayscale value is copied to the red, green, and blue channels, and each channel is set to the same value.
                int newRgb = (gray << 16) | (gray << 8) | gray;

                //Keep the original transparency and replace RGB with grayscale.
                image.setRGB(x, y, (rgb & 0xFF000000) | newRgb);
            }
        }
    }

    //Brighten the image, delta > 0 brightens
    public static void adjustBrightness(BufferedImage image, int delta) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);

                //Preserve transparency
                int a = (rgb >> 24) & 0xff;

                //Add delta to each color, while ensuring the value is between 0 and 255.
                int r = Math.min(255, Math.max(0, ((rgb >> 16) & 0xff) + delta));
                int g = Math.min(255, Math.max(0, ((rgb >> 8) & 0xff) + delta));
                int b = Math.min(255, Math.max(0, (rgb & 0xff) + delta));

                //Combines new color values.
                int newRgb = (a << 24) | (r << 16) | (g << 8) | b;

                //Set the new value back to the picture
                image.setRGB(x, y, newRgb);
            }
        }
    }

    //Adjust the red intensity
    public static void shiftRedTone(BufferedImage image, int delta) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = Math.min(255, ((rgb >> 16) & 0xff) + delta);//Only the red channel is added with delta, the others remain unchanged.
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;
                int newRgb = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, newRgb);
            }
        }
    }

    //Adjust blue intensity
    public static void shiftBlueTone(BufferedImage image, int delta) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = Math.min(255, (rgb & 0xff) + delta);//Only the blue channel is added with delta, the others remain as is.
                int newRgb = (a << 24) | (r << 16) | (g << 8) | b;
                image.setRGB(x, y, newRgb);
            }
        }
    }
}

