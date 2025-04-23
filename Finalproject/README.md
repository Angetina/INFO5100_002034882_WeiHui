README.md
Final project: the Image Management Tool
This is a JavaFX-based desktop application that helps users upload, preview, edit, and convert image files.
Main Features
  User Interface (GUI)
    Users can upload images from their computer.
    After uploading, the image thumbnails (100x100) are displayed side-by-side: Original and Converted.
    Users can also preview and apply different filters before converting.
  Image Information
    Once an image is uploaded, the app displays useful metadata: File name, size, camera model, date taken
    If available, it even shows location info using GPS and OpenStreetMap API.
  Image Conversion
    I used ImageIO to convert image files into different formats like JPG, PNG, and BMP.
    Users can select their desired format and apply filters like: Black and White, Brightness +50, Warm or Cool tones
  Download Function
    After previewing the filtered image, users can click "Convert and Download" to save the converted image locally.
  Encapsulation
    All classes have private fields and use getters to control access.
  Inheritance
    BMPImage, PNGImage, and JPEGImage extend the base ImageFile class.
  Interface
    I created an IImageConverter interface with a convert() method.
    Each format (JPG, PNG, BMP) has its own class that implements this interface.
  Design Pattern – Factory Pattern
    The app uses the Factory Pattern to choose the correct converter based on the format selected.
    ImageConverterFactory returns the right converter without the main controller needing to know the details.
  CSS Styling
    I styled the GUI using a custom dark theme CSS file.
    Buttons have hover effects, ComboBoxes and TextArea have a consistent dark look
  Exception Handling
    The code handles IOException, FileNotFoundException, and missing metadata safely.
    Errors are shown in the info box without crashing the app.
