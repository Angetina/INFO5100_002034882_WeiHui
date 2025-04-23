package com.example.finalproject_imagemanager.utils;

//For all converters to implement
import com.example.finalproject_imagemanager.interfaces.IImageConverter;

//Process image reading, conversion, output and other functions
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//Implement the convert() method of the IImageConverter interface
public class JPGConverter implements IImageConverter {
    @Override
    //Returns a Boolean value indicating whether the conversion was successful.
    public boolean convert(BufferedImage inputImage, File outputFile) throws IOException {
        //Write inputImage to outputFile in "jpg" format
        return ImageIO.write(inputImage, "jpg", outputFile);
    }

}
