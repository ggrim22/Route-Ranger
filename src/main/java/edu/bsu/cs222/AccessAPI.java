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
        URL url = URI.create(encodedUrlString).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FinalProject/0.1");
        connection.connect();
        return connection;
    }

    public URLConnection connectToGeoapify(String lon, String lat) throws IOException {
        String encodedUrlString = "https://maps.geoapify.com/v1/staticmap?" +
                "style=osm-bright-smooth&" +
                "width=600&height=400&" +
                "center=lonlat%3A" + URLEncoder.encode(lon, Charset.defaultCharset()) +
                "%2C" + URLEncoder.encode(lat, Charset.defaultCharset()) +
                "zoom=14.3497&" +
                "marker=lonlat%3A-122.29188334609739%2C47.54403990655936" +
                "%3Btype%3Aawesome%3Bcolor%3A%23bb3f73%3Bsize%3Ax-large%3Bicon%3Apaw%7Clonlat%3A-122.29282631194182%2C47.549609195001494%3Btype%3Amaterial%3Bcolor%3A%234c905a%3Bicon%3Atree%3Bicontype%3Aawesome%7Clonlat%3A-122.28726954893025%2C47.541766557545884%3Btype%3Amaterial%3Bcolor%3A%234c905a%3Bicon%3Atree%3Bicontype%3Aawesome&" +
                "apiKey=aa0bba8d694e473db0f91977c507a13a";
        URL url = URI.create(encodedUrlString).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FinalProject/0.1");
        connection.connect();
        return connection;
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

}
