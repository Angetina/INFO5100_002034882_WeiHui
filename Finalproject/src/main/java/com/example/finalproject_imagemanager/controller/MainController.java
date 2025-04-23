package com.example.finalproject_imagemanager.controller;

import com.example.finalproject_imagemanager.interfaces.IImageConverter;
import com.example.finalproject_imagemanager.model.ImageFile;
import com.example.finalproject_imagemanager.model.ImageMetadata;
import com.example.finalproject_imagemanager.utils.GeoLocationService;
import com.example.finalproject_imagemanager.utils.ImageConverterFactory;
import com.example.finalproject_imagemanager.utils.ImageFilterUtils;
import com.example.finalproject_imagemanager.utils.ImageMetadataExtractor;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

//Controller for handling image conversion and preview logic
public class MainController {
    //Bind the component fields in main-view.fxml
    @FXML
    private ImageView thumbnailOriginalView;
    @FXML
    private ImageView thumbnailConvertedView;
    @FXML
    private TextArea imageInfoLabel;
    @FXML
    private ComboBox<String> formatChoiceBox;
    @FXML
    private ComboBox<String> filterChoiceBox;

    //Automatically executes initialize() after the screen is loaded.
    @FXML
    private void initialize() {
        //Settings Format and Filter drop-down options
        formatChoiceBox.setItems(FXCollections.observableArrayList("jpg", "png", "bmp"));
        filterChoiceBox.setItems(FXCollections.observableArrayList("No filter", "Black and White", "Brightness +50", "Warm colors", "Cool colors"));
    }
    @FXML
    private void handleChooseImage() {
        //Step 1: Use FileChooser to let the user select an image.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp"));

        //Open the selection window and confirm the file
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            currentSelectedFile = selectedFile;

            //Step 2:Show thumbnails (automatically scaled down to 100x100)
            try {
                Image originalImage = new Image(new FileInputStream(selectedFile), 100, 100, true, true);
                thumbnailOriginalView.setImage(originalImage);
                thumbnailConvertedView.setImage(null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                imageInfoLabel.setText("Image not found");
            }

            //Step 3:Use ImageFile to get the basic properties of images
            try {
                ImageFile imageFile = new ImageFile(selectedFile);
                int width = imageFile.getWidth();
                int height = imageFile.getHeight();
                String fileName = imageFile.getFileName();
                BufferedImage bufferedImage = imageFile.getBufferedImage();

                //Use ImageMetadataExtractor to get additional metadata
                ImageMetadata metadata = ImageMetadataExtractor.extract(selectedFile);
                String cameraModel = metadata.getCameraModel();
                String dateTaken = metadata.getDateTaken();

                //GPS Information using GeoLocationService (calling API).
                String location = GeoLocationService.extractLocation(selectedFile);

                //Show image properties
                displayImageInfo(fileName, width, height, dateTaken, cameraModel, location);

            } catch (IOException e) {
                e.printStackTrace();
                imageInfoLabel.setText("Failed to read image or metadata");
            }
        }
    }

    //Combine the information and display it on the screen.
    private void displayImageInfo(String fileName, int width, int height, String dateTaken, String cameraModel, String location) {
        String info = String.format("""
                File Name: %s
                Size: %d x %d
                Photo time: %s
                Camera Model: %s
                Locations: %s
                """, fileName, width, height, dateTaken, cameraModel, location);

        imageInfoLabel.setText(info);
    }

    private File currentSelectedFile; //Save the file currently selected by the user

    //For converting and storing images
    @FXML
    private void handleConvertFormat() {
        //Step 1: Check if there is a selected image
        if (currentSelectedFile == null) {
            imageInfoLabel.setText("Select an image first");
            return;
        }

        // Step 2:Check if there is a selected format
        String selectedFormat = formatChoiceBox.getValue();
        if (selectedFormat == null || selectedFormat.isEmpty()) {
            imageInfoLabel.setText("Select the format to convert");
            return;
        }

        try {
            //Step 3: Read image
            BufferedImage originalImage = ImageIO.read(currentSelectedFile);

            //Step 4: Use ImageFilterUtils to apply filters
            String selectedFilter = filterChoiceBox.getValue();
            if (selectedFilter != null) {
                switch (selectedFilter) {
                    case "Black and White":
                        ImageFilterUtils.applyGrayscaleFilter(originalImage);
                        break;
                    case "Brightness +50":
                        ImageFilterUtils.adjustBrightness(originalImage, 50);
                        break;
                    case "Warm colors":
                        ImageFilterUtils.shiftRedTone(originalImage, 50);
                        break;
                    case "Cool colors":
                        ImageFilterUtils.shiftBlueTone(originalImage, 50);
                        break;
                    default:
                        // Do nothing
                        break;
                }
            }

            //Step 5:Create new file path (add "_converted")
            String fileName = currentSelectedFile.getName().replaceAll("\\.\\w+$", "");
            String newPath = currentSelectedFile.getParent() + File.separator + fileName + "_converted." + selectedFormat;
            File outputFile = new File(newPath);

            //Step 6:Use ImageConverterFactory to convert images according to the format
            IImageConverter converter = ImageConverterFactory.getConverter(selectedFormat);
            if (converter == null) {
                imageInfoLabel.setText("Unsupported conversion format");
                return;
            }

            //If the conversion is successful, the image will be automatically opened.
            boolean success = converter.convert(originalImage, outputFile);

            // Step 7:Display results and automatically open the image
            if (success) {
                imageInfoLabel.setText("Image has been converted to " + selectedFormat + "\nstored as: " + outputFile.getAbsolutePath());
                //Automatically open image files
                //Display the converted thumbnails to the right
                Desktop.getDesktop().open(outputFile);
                BufferedImage convertedImage = ImageIO.read(outputFile);
                Image convertedPreview = SwingFXUtils.toFXImage(convertedImage, null);
                thumbnailConvertedView.setImage(convertedPreview);
            } else {
                imageInfoLabel.setText("Failed to convert image");
            }
        } catch (IOException e) {
            e.printStackTrace();
            imageInfoLabel.setText("Failed to convert image");
        }
    }

    //Preview the image after pressing "Apply Filter"
    @FXML
    private void handlePreview() {
        if (currentSelectedFile == null) {
            imageInfoLabel.setText("Select an image first");
            return;
        }

        String selectedFormat = formatChoiceBox.getValue();
        String selectedFilter = filterChoiceBox.getValue();

        try {
            BufferedImage originalImage = ImageIO.read(currentSelectedFile);

            //Apply filter (not converting, just displaying)
            switch (selectedFilter) {
                case "Black and White" -> ImageFilterUtils.applyGrayscaleFilter(originalImage);
                case "Brightness +50" -> ImageFilterUtils.adjustBrightness(originalImage, 50);
                case "Warm colors" -> ImageFilterUtils.shiftRedTone(originalImage, 50);
                case "Cool colors" -> ImageFilterUtils.shiftBlueTone(originalImage, 50);
                default -> { //No filter
                }
            }

            //Display on the right ImageView
            Image preview = SwingFXUtils.toFXImage(originalImage, null);
            thumbnailConvertedView.setImage(preview);

            imageInfoLabel.setText("Preview ready.");

        } catch (IOException e) {
            e.printStackTrace();
            imageInfoLabel.setText("Failed to preview image.");
        }
    }

}