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
    public void fetchAndSaveGeocodeTest() throws IOException {
        AccessAPI api = new AccessAPI();
        String address = "146 Nursery Rd Anderson IN";
        api.fetchAndSaveGeocode(address, "address1GeocodeResult");
    }

    @Test
    public void saveToStringTest() throws IOException {
        AccessAPI api = new AccessAPI();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String copyright = "\u00A9";
        api.saveToString(testInputStream);
        Assertions.assertEquals("{\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"country_code\": \"us\",\n" +
                "      \"housenumber\": \"146\",\n" +
                "      \"street\": \"South Nursery Road\",\n" +
                "      \"country\": \"United States\",\n" +
                "      \"county\": \"Madison County\",\n" +
                "      \"datasource\": {\n" +
                "        \"sourcename\": \"openaddresses\",\n" +
                "        \"attribution\": \""+ copyright + " OpenAddresses contributors\",\n" +
                "        \"license\": \"BSD-3-Clause License\"\n" +
                "      },\n" +
                "      \"postcode\": \"46012\",\n" +
                "      \"state\": \"Indiana\",\n" +
                "      \"district\": \"Anderson Township\",\n" +
                "      \"city\": \"Anderson\",\n" +
                "      \"state_code\": \"IN\",\n" +
                "      \"lon\": -85.657242,\n" +
                "      \"lat\": 40.117316,\n" +
                "      \"result_type\": \"building\",\n" +
                "      \"formatted\": \"146 South Nursery Road, Anderson, IN 46012, United States of America\",\n" +
                "      \"address_line1\": \"146 South Nursery Road\",\n" +
                "      \"address_line2\": \"Anderson, IN 46012, United States of America\",\n" +
                "      \"timezone\": {\n" +
                "        \"name\": \"America/Indiana/Indianapolis\",\n" +
                "        \"offset_STD\": \"-05:00\",\n" +
                "        \"offset_STD_seconds\": -18000,\n" +
                "        \"offset_DST\": \"-04:00\",\n" +
                "        \"offset_DST_seconds\": -14400,\n" +
                "        \"abbreviation_STD\": \"EST\",\n" +
                "        \"abbreviation_DST\": \"EDT\"\n" +
                "      },\n" +
                "      \"plus_code\": \"86GP488V+W4\",\n" +
                "      \"plus_code_short\": \"488V+W4, 46012 Anderson, United States\",\n" +
                "      \"rank\": {\n" +
                "        \"popularity\": 2.613542157567391,\n" +
                "        \"confidence\": 0.9,\n" +
                "        \"confidence_city_level\": 1,\n" +
                "        \"confidence_street_level\": 0.9,\n" +
                "        \"confidence_building_level\": 0.9,\n" +
                "        \"match_type\": \"full_match\"\n" +
                "      },\n" +
                "      \"place_id\": \"51b0e3bf40106a55c05916a6ef35040f4440c00203e203456f70656e6164647265737365733a616464726573733a75732f696e2f6d616469736f6e2d6164647265737365732d636f756e74793a30336630316635396132623035393133\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"query\": {\n" +
                "    \"text\": \"146 Nursery Rd Anderson IN\",\n" +
                "    \"parsed\": {\n" +
                "      \"housenumber\": \"146\",\n" +
                "      \"street\": \"nursery rd\",\n" +
                "      \"city\": \"anderson\",\n" +
                "      \"state\": \"in\",\n" +
                "      \"expected_type\": \"building\"\n" +
                "    }\n" +
                "  }\n" +
                "}", api.getJsonString());
    }

}
