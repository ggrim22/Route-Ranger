package edu.bsu.cs222;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapMaker {
    private static Map<String, Object> JsonMap;


    protected void parseResultsJson(String json) {
        if (JsonMap != null) {
            JsonMap.clear();
        }
        JSONObject jsonObject = new JSONObject(json);
        Map<String, Object> resultMap = new HashMap<>();

        JSONArray resultsArray = jsonObject.getJSONArray("results");
        if (!resultsArray.isEmpty()) {
            JSONObject resultObject = resultsArray.getJSONObject(0);
            extractResultData(resultObject, resultMap);
        }
        JsonMap = resultMap;
    }

    private void extractResultData(JSONObject resultObject, Map<String, Object> resultMap) {
        resultMap.put("lat", Double.toString(resultObject.getDouble("lat")));
        resultMap.put("lon", Double.toString(resultObject.getDouble("lon")));
        resultMap.put("formatted", resultObject.getString("formatted"));

    }

    public void getMapFromConnection(String address) throws IOException {
        AccessAPI access = new AccessAPI();
        parseResultsJson(access.saveToString(access.getInputStream(access.connectToGeocode(address))));

    }

    public Map<String, Object> getJsonMap() {
        return JsonMap;
    }


}
