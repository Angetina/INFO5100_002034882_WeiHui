package com.example.finalproject_imagemanager.model;

//Encapsulates the image metadata
public class ImageMetadata {
    private String cameraModel;//Store camera models
    private String dateTaken;//Store date and time

    //Constructor, create ImageMetadata object
    public ImageMetadata(String cameraModel, String dateTaken) {
        this.cameraModel = cameraModel;
        this.dateTaken = dateTaken;
    }
    //Getter method: access cameraModel
    public String getCameraModel() {
        return cameraModel;
    }
    //Getter method: access dateTaken
    public String getDateTaken() {
        return dateTaken;
    }
}
