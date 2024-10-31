package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class AccessAPITest {

    @Test
    public void GeocodeAPIURLReturnTest() throws IOException {
        AccessAPI api = new AccessAPI();
        String address = "146 S Nursery Rd Anderson IN 46012 US";
        URLConnection result;
        result = api.connectToGeocode(address);
        Assertions.assertNotNull(result);
    }

    @Test
    public void GeoapifyAPIURLReturnTest() throws IOException {
        AccessAPI api = new AccessAPI();
        URLConnection result;
        result = api.connectToGeoapify("-122.29009844646316", "47.54607447032754");
        Assertions.assertNotNull(result);
    }

    @Test
    public void inputStreamTest() throws IOException {
        AccessAPI api = new AccessAPI();
        String address = "146 S Nursery Rd Anderson IN 46012 US";
        URLConnection connection;
        connection = api.connectToGeocode(address);
        InputStream result;
        result = api.getInputStream(connection);
        Assertions.assertNotNull(result);
    }

}
