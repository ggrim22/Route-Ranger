package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class Parser {

    public JSONArray makeJSONArray(InputStream inputStream, String keyword) throws IOException {
        return JsonPath.read(inputStream, "$.." + keyword);
    }
}
