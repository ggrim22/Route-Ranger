package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessAPITest {

    @Test
    public void ConnectToGeocodeTest() {
        AccessAPI api = new AccessAPI();
        String address = "146 S Nursery Rd Anderson IN 46012 US";
        URLConnection result;
        result = api.connectToGeocode(address);
        Assertions.assertNotNull(result);
    }
    @Test
    void ConnectToGeocode_emptyAddressTest() {
        AccessAPI accessAPI = new AccessAPI();
        assertThrows(AssertionError.class, () -> accessAPI.connectToGeocode(""));
    }

    @Test
    void ConnectToStaticMap_validCoordinatesTest() {
        AccessAPI accessAPI = new AccessAPI();
        URLConnection connection = accessAPI.connectToStaticMap("37.7749", "-122.4194");
        Assertions.assertNotNull(connection);
    }

    @Test
    void ConnectToStaticMap_nullCoordinatesTest() {
        AccessAPI accessAPI = new AccessAPI();
        URLConnection connection = accessAPI.connectToStaticMap(null, null);
        Assertions.assertNull(connection);
    }

    @Test
    public void ConnectToDynamicMapTest() {
        AccessAPI api = new AccessAPI();
        URLConnection result;
        result = api.connectToDynamicMap("40.115843", "-85.657507", "40.191503", "-85.4102546", "40.191503","-85.4102546", "14");
        Assertions.assertNotNull(result);
    }
    @Test
    void ConnectToDynamicMap_invalidInputsTest() {
        AccessAPI accessAPI = new AccessAPI();
        URLConnection connection = accessAPI.connectToDynamicMap(null, null, null, null, null, null, null);
        Assertions.assertNull(connection);
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

    @Test
    public void saveToStringTest() throws IOException {
        AccessAPI api = new AccessAPI();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String result = api.saveToString(testInputStream);
        Assertions.assertTrue(result.contains("country_code"));
    }

}
