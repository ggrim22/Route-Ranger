package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class AccessAPI {

    public URLConnection connectToGeocode(String address) throws IOException {

        String encodedURLString = "https://api.geoapify.com/v1/geocode/search?" +
                "text=" +URLEncoder.encode(address, Charset.defaultCharset()) + "&format=json&" +
                "apiKey=" + readFromAdminFile();

        return createURL(encodedURLString);
    }

    public URLConnection connectToStaticMap(String lat, String lon) throws IOException {
        String encodedURLString = "https://maps.geoapify.com/v1/staticmap?" +
                "style=osm-bright&" +
                "width=450&" +
                "height=300&" +
                "center=lonlat:" + URLEncoder.encode(lon, Charset.defaultCharset()) +
                "," + URLEncoder.encode(lat, Charset.defaultCharset()) +
                "&zoom=14&" +
                "marker=lonlat:" + URLEncoder.encode(lon, Charset.defaultCharset()) +
                "," + URLEncoder.encode(lat, Charset.defaultCharset()) +
                ";color:%23ff0000;size:small&" +
                "apiKey=" + readFromAdminFile();


        return createURL(encodedURLString);
    }

    public URLConnection connectToDynamicMap(String address1Lat, String address1Lon, String address2Lat, String address2Lon, String centerLat, String centerLon) throws IOException {
        String encodedURLString = "https://maps.geoapify.com/v1/" +
                "staticmap?style=osm-bright&" +
                "width=600&" +
                "height=400&" +
                "center=lonlat:" +
                URLEncoder.encode(centerLon, Charset.defaultCharset()) +
                "," +
                URLEncoder.encode(centerLat, Charset.defaultCharset()) +
                "&zoom=14&" +
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

        return createURL(encodedURLString);
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

}
