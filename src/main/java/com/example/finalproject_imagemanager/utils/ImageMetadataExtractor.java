package com.example.finalproject_imagemanager.utils;

//Read the EXIF data of an image.
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.example.finalproject_imagemanager.model.ImageMetadata;

import java.io.File;

//Extract information from images
public class ImageMetadataExtractor {
    public static ImageMetadata extract(File imageFile) {
        //default value. If the image metadata does not exist, "Unknown" is displayed.
        String cameraModel = "Unknown camera";
        String dateTaken = "Unknown date";

        try {
            //ImageMetadataReader reads image metadata
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);

            //ExifIFD0Directory stores model information
            ExifIFD0Directory exifIFD0 = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

            //If metadata contains TAG_MODEL, read the camera model
            if (exifIFD0 != null && exifIFD0.containsTag(ExifIFD0Directory.TAG_MODEL)) {
                cameraModel = exifIFD0.getString(ExifIFD0Directory.TAG_MODEL);
            }

            //ExifSubIFDDirectory stores shooting time information
            ExifSubIFDDirectory subIFD = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            //If there is TAG_DATETIME_ORIGINAL, get the original shooting time of the image.
            if (subIFD != null && subIFD.containsTag(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL)) {
                dateTaken = subIFD.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }//If metadata cannot be read, an error message is printed.

        //Create an ImageMetadata object and return the camera model and shooting time
        return new ImageMetadata(cameraModel, dateTaken);
    }
}
