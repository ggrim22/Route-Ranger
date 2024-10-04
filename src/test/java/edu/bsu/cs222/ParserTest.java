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
        Assertions.assertEquals("40.115843", jsonArray.getFirst());

    }
}
