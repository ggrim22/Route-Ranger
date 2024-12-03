package edu.bsu.cs222;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AccessAPI {
    String JSONString;

    public URLConnection connectToGeocode(String address) {
        assert !address.isEmpty();
        URLConnection resultConnection;
        try {
            String encodedURLString = "https://api.geoapify.com/v1/geocode/search?" +
                    "text=" + URLEncoder.encode(address, Charset.defaultCharset()) + "&format=json&" +
                    "apiKey=" + readFromAdminFile();
            resultConnection = createURL(encodedURLString);
        } catch (Exception e) {
            return null;
        }

        return resultConnection;
    }

    public URLConnection connectToStaticMap(String lat, String lon) {
        URLConnection resultConnection;
        try {
            String encodedURLString = "https://maps.geoapify.com/v1/staticmap?" +
                    "style=osm-bright&" +
                    "width=300&" +
                    "height=200&" +
                    "center=lonlat:" + URLEncoder.encode(lon, Charset.defaultCharset()) +
                    "," + URLEncoder.encode(lat, Charset.defaultCharset()) +
                    "&zoom=14&" +
                    "marker=lonlat:" + URLEncoder.encode(lon, Charset.defaultCharset()) +
                    "," + URLEncoder.encode(lat, Charset.defaultCharset()) +
                    ";color:%23ff0000;size:small&" +
                    "apiKey=" + readFromAdminFile();
            resultConnection = createURL(encodedURLString);
        } catch (Exception e) {
            return null;
        }
        return resultConnection;
    }

    public URLConnection connectToDynamicMap(String address1Lat, String address1Lon, String address2Lat, String address2Lon, String centerLat, String centerLon, String zoomLevel) {
        URLConnection resultConnection;
        try {
            String encodedURLString = "https://maps.geoapify.com/v1/" +
                    "staticmap?style=osm-bright&" +
                    "width=400&" +
                    "height=300&" +
                    "center=lonlat:" +
                    URLEncoder.encode(centerLon, Charset.defaultCharset()) +
                    "," +
                    URLEncoder.encode(centerLat, Charset.defaultCharset()) +
                    "&zoom=" + URLEncoder.encode(zoomLevel, Charset.defaultCharset()) +
                    "&" +
                    "marker=lonlat:" +
                    URLEncoder.encode(address1Lon, Charset.defaultCharset()) +
                    "," +
                    URLEncoder.encode(address1Lat, Charset.defaultCharset()) +
                    ";color:%23ff0000;" +
                    "size:medium" + URLEncoder.encode("|", Charset.defaultCharset()) +
                    "lonlat:" +
                    URLEncoder.encode(address2Lon, Charset.defaultCharset()) +
                    "," +
                    URLEncoder.encode(address2Lat, Charset.defaultCharset()) +
                    ";color:%23ff0000;" +
                    "size:medium&" +
                    "apiKey=" + readFromAdminFile();
            resultConnection = createURL(encodedURLString);
            System.out.println("Zoom level:" + zoomLevel);
        } catch (Exception e) {
            return null;
        }
        return resultConnection;
    }


    public InputStream getInputStream(URLConnection connection) throws IOException {

        return connection.getInputStream();
    }

    public String readFromAdminFile() throws IOException {

        try (InputStream inputStream = AccessAPI.class.getClassLoader().getResourceAsStream("APIToken.txt")) {
            assert inputStream != null;
            try (Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine();
                }
            }
        }

        return "";
    }

    private URLConnection createURL(String encodedURL) throws IOException {

        URL url = URI.create(encodedURL).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FinalProject/0.1");
        connection.connect();
        return connection;

    }

    public void saveToFile(InputStream inputStream, String fileName) throws IOException {
        Path filePath = Paths.get("src/main/resources/" + fileName);

        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }


    public void fetchAndSaveGeocode(String address, String fileName) throws IOException {
        URLConnection connection = connectToGeocode(address);
        if (connection != null) {
            try (InputStream inputStream = getInputStream(connection)) {
                saveToFile(inputStream, fileName + ".json");
            }
        }

    }

    public void clearFile(String fileName) {
        String filePath = "src/main/resources/" + fileName + ".json";

        try (FileWriter writer = new FileWriter(filePath)) {
            // Writing an empty string effectively clears the file
            writer.write("");
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }
    }
    public String getJsonString() {
        return JSONString;
    }

    public void saveToString(InputStream inputStream) throws IOException {
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, StandardCharsets.UTF_8))) {
                int c;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            JSONString = textBuilder.toString();
    }

}