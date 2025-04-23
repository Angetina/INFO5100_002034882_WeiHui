package com.example.finalproject_imagemanager.model;

//Process image reading, conversion, output and other functions
import java.io.IOException;
import java.io.File;

//Inherits ImageFile
public class JPGImage extends ImageFile {
    public JPGImage(File file) throws IOException {
        super(file);//Call the constructor of the parent class ImageFile to let ImageFile process the image
    }
}
