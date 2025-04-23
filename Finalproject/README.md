README.md
# Final Project – Image Management Tool

This is a JavaFX-based desktop application that helps users upload, preview, edit, and convert image files.

## Features

### User Interface (GUI)
- Upload images from your computer.
- Display original and converted image thumbnails side-by-side (100x100 pixels).
- Apply filters and preview effects before conversion.
- Save the converted image locally.

### Image Information
- After uploading, the app displays metadata:
  - File name, size, camera model, and date taken.
- If GPS metadata is available, location is shown using OpenStreetMap.

### Image Conversion
- Uses ImageIO to convert images to **JPG**, **PNG**, and **BMP**.
- Supports filters:
  - Black & White
  - Brightness +50
  - Warm/Cool tones

### Object-Oriented Design
- **Encapsulation**: All image classes use private fields with getter methods.
- **Inheritance**: `BMPImage`, `PNGImage`, and `JPEGImage` extend the base `ImageFile` class.
- **Interface**: `IImageConverter` defines a standard `convert()` method.
- **Design Pattern**: Uses the **Factory Pattern** to return the correct image converter based on the user-selected format.

### CSS Styling
- Consistent dark theme using custom `style.css`.
- Buttons feature hover effects.
- ComboBoxes and TextAreas follow a unified visual style.

### Exception Handling
- Handles `IOException`, `FileNotFoundException`, and missing metadata gracefully.
- Errors are shown in an alert box instead of crashing the app.

## Demo Videos

- Demo 1: https://youtu.be/RgwKZAl8Ybw
- Demo 2: https://youtu.be/XdEwPNeFxok
