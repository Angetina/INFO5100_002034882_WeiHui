package com.example.finalproject_imagemanager.utils;

//For all converters to implement
import com.example.finalproject_imagemanager.interfaces.IImageConverter;

//Factory Pattern
public class ImageConverterFactory {
    //static: call this method directly without creating an ImageConverterFactory object
    public static IImageConverter getConverter(String format) {
        //Convert the format string to lowercase
        return switch (format.toLowerCase()){
            //According to different cases return different objects
            case "jpg", "jpeg" -> new JPGConverter();
            case "png" -> new PNGConverter();
            case "bmp" -> new BMPConverter();
            default -> null;//If the format is not one of these three, The format is not supported.
        };
    }
}
