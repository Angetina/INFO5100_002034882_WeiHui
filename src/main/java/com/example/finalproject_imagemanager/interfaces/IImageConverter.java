package com.example.finalproject_imagemanager.interfaces;

//Process image reading, conversion, output and other functions
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

//Strategy Pattern: Define a conversion strategy. Conversion classes for multiple image formats implement this interface
public interface IImageConverter {
    //Abstract Methods: Any classes that implement this interface must implement this method
    boolean convert(BufferedImage inputImage, File outputFile) throws IOException;
}
