package edu.bsu.cs222;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
public class AccessAPI {
    public URLConnection connectToGeocode(String address) {
        assert !address.isEmpty();
        URLConnection resultConnection;
        try {
            String encodedURLString = "https://api.geoapify.com/v1/geocode/search?" +
                    "text=" + URLEncoder.encode(address, Charset.defaultCharset()) + "&format=json&" +
                    "apiKey=" + new Decoder().getAPIKey();
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
                    "apiKey=" + new Decoder().getAPIKey();
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
                    "apiKey=" + new Decoder().getAPIKey();
            resultConnection = createURL(encodedURLString);
        } catch (Exception e) {
            return null;
        }
        return resultConnection;
    }


    public InputStream getInputStream(URLConnection connection) throws IOException {
        return connection.getInputStream();
    }


    private URLConnection createURL(String encodedURL) throws IOException {

        URL url = URI.create(encodedURL).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FinalProject/0.1");
        connection.connect();
        return connection;

    }


    public String saveToString(InputStream inputStream) throws IOException {
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, StandardCharsets.UTF_8))) {
                int c;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            return textBuilder.toString();
    }

}