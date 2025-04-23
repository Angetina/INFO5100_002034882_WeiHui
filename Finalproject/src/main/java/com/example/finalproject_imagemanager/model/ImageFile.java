package com.example.finalproject_imagemanager.model;

//Process image reading, conversion, output and other functions
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//Encapsulate image information
public class ImageFile {
    private String fileName;
    private int width;
    private int height;
    private BufferedImage bufferedImage;//Java internal image format, which can be used to modify, convert, and store images.

    //The constructor will read this file and initialize the image information. If the read fails, an IOException is thrown.
    public ImageFile(File file) throws IOException {
        this.fileName = file.getName();//Store the image's file name
        this.bufferedImage = ImageIO.read(file);//read the image and convert it to BufferedImage
        this.width = bufferedImage.getWidth();//Get the width and height of the image
        this.height = bufferedImage.getHeight();
    }

    //Getter method: to obtain the fileName value externally
    public String getFileName() {
        return fileName;
    }

    //Getter method: Get the width and height of the image.
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    //Getter method: Gets the pixel data of an image for conversion or filter processing.
    public BufferedImage getBufferedImage() {
    return bufferedImage;
    }
}
