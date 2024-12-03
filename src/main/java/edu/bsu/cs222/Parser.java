package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class Parser {

    public JSONArray makeJSONArray(InputStream inputStream, String keyword) throws IOException {
        return JsonPath.read(inputStream, "$.." + keyword);
    }

    public String parseToString(JSONArray jsonArray) {
        String resultString;
        try {
            resultString = jsonArray.getFirst().toString();
        } catch(Exception e) {
            resultString = null;
        }
        return resultString;
    }
    protected String parseFullAddress(InputStream inputStream) throws IOException {
        return parseToString(makeJSONArray(inputStream, "formatted"));
    }

}