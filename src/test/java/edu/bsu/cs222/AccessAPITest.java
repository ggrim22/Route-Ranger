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
        result = api.connectToStaticMap("40.115843", "-85.657507");
        Assertions.assertNotNull(result);
    }

    @Test
    public void GeoapifyTwoAddressMapTest() throws IOException {
        AccessAPI api = new AccessAPI();
        URLConnection result;
        result = api.connectToDynamicMap("40.115843", "-85.657507", "40.191503", "-85.4102546", "40.191503","-85.4102546");
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
