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
