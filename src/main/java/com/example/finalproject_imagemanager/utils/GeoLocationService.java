package com.example.finalproject_imagemanager.utils;

//Read the EXIF/GPS metadata of an image
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.GpsDirectory;
import com.drew.lang.GeoLocation;

import java.io.File;

//Send HTTP request to OpenStreetMap API
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Use Gson to parse the returned JSON and extract the "geographic address"
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//Read the GPS metadata and send back the geographic location
public class GeoLocationService {
    public static String extractLocation(File imageFile) {
        try {
            //Use metadata-extractor to read metadata information in the image
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            //Find the GPS metadata folder
            GpsDirectory gpsDir = metadata.getFirstDirectoryOfType(GpsDirectory.class);

            //If there is GPS information, get the latitude and longitude
            if (gpsDir != null) {
                GeoLocation geoLocation = gpsDir.getGeoLocation();
                //Make sure the latitude and longitude are valid, not (0, 0)
                if (geoLocation != null && !geoLocation.isZero()) {
                    //Call getAddressFromCoordinates() and pass in the latitude and longitude → get the address.
                    return getAddressFromCoordinates(geoLocation.getLatitude(), geoLocation.getLongitude());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//If an error occurs during the process (e.g. the image has no metadata), an error message is printed.

        return "No GPS information";
    }

    //Call OpenStreetMap API to get place names
    private static String getAddressFromCoordinates(double latitude, double longitude) {
        try {
            //Constructs a query URL for the reverse geocoding API.
            String url = String.format("https://nominatim.openstreetmap.org/reverse?lat=%.6f&lon=%.6f&format=json", latitude, longitude);
            //Building an HTTP Client
            HttpClient client = HttpClient.newHttpClient();
            //Make an HTTP GET request, attaching the necessary headers.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "JavaFxImageManager/1.0")
                    .build();

            //Send a request and get a JSON string response.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Convert the response JSON string to JsonObject.
            String responseBody = response.body();
            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            //Extract the "display_name" field from the response, which is the full address description
            return json.get("display_name").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to obtain actual address";
        }//If the API request fails or the JSON parsing error occurs, the error message will be returned.
    }
}
