package com.example.finalproject_imagemanager.model;

//Process image reading, conversion, output and other functions
import java.io.File;
import java.io.IOException;

//Inherits ImageFile
public class BMPImage extends ImageFile {
    public BMPImage(File file) throws IOException {
        super(file);//Call the constructor of the parent class ImageFile to let ImageFile process the image
    }
}
