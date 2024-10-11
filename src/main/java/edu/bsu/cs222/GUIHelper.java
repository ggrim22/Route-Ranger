package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class GUIHelper {

    public double getDouble(String keyword, String Address) throws IOException {
        AccessAPI access = new AccessAPI();
        Parser parser = new Parser();
        InputStream inputStream = access.getInputStream(access.connectToGeocode(Address));
        JSONArray jsonArray = parser.makeJSONArray(inputStream, keyword);
        return Double.parseDouble(parser.parseToString(jsonArray));

    }
}
