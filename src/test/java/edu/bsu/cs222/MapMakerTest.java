package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapMakerTest {

    private final JsonMapMaker jsonMapMaker = new JsonMapMaker();

    @Test
    void testValidJsonParsing() throws IOException {
        AccessAPI api = new AccessAPI();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String jsonString = api.saveToString(testInputStream);

        jsonMapMaker.parseResultsJson(jsonString);

        assertEquals("40.117316", jsonMapMaker.getJsonMap().get("lat"));
        assertEquals("-85.657242", jsonMapMaker.getJsonMap().get("lon"));

        assertEquals("146 South Nursery Road, Anderson, IN 46012, United States of America", jsonMapMaker.getJsonMap().get("formatted"));

    }
}