package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ParserTest {

    @Test
    public void makeJSONArrayTest() throws IOException {
        Parser parser = new Parser();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String keyword = "lat";
        JSONArray jsonArray = parser.makeJSONArray(testInputStream, keyword);
        String result = parser.parseToString(jsonArray);
        Assertions.assertEquals("40.117316", result);
    }

    @Test
    public void getLongTest() throws IOException {
        Parser parser = new Parser();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String keyword = "lon";
        JSONArray jsonArray = parser.makeJSONArray(testInputStream,keyword);
        String result = parser.parseToString(jsonArray);
        Assertions.assertEquals("-85.657242", result);
    }

    @Test
    public void getFormattedAddressTest() throws IOException {
        Parser parser = new Parser();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        Assertions.assertEquals("146 South Nursery Road, Anderson, IN 46012, United States of America", parser.parseFullAddress(testInputStream));
    }


}
