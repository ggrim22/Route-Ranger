package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class AccessAPI {

    public URLConnection connectToGeocode(String address) throws IOException {
        String encodedUrlString = "https://geocode.maps.co/search?q=" +
                URLEncoder.encode(address, Charset.defaultCharset()) +
                "&api_key=" + readFromAdminFile();

        return createURL(encodedUrlString);
    }

    public URLConnection connectToGeoapify(String lon, String lat) throws IOException {
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
                "apiKey=aa0bba8d694e473db0f91977c507a13a";


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
