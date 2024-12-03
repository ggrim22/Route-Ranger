package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.InputStream;

public class ErrorHandler {

    public boolean noInputFoundError(String address)  {
        return address.isBlank();
    }

    public boolean networkConnectionError() {
        AccessAPI access = new AccessAPI();
        return access.connectToGeocode("146 Nursery Rd Anderson IN") == null;
    }

    public boolean noAddressFoundError(String keyword, String fileName)  {
        GUIHelper helper = new GUIHelper();
        JSONArray jsonArray = helper.getJSONArray(keyword, fileName);
        try {
            jsonArray.getFirst();
        } catch(Exception e) {
            return true;
        }
        return false;

    }
    public boolean noAddress1FoundError(String keyword)  {
        GUIHelper helper = new GUIHelper();
        JSONArray jsonArray = helper.getJSONArray(keyword, "address1GeocodeResult");
        try {
            jsonArray.getFirst();
        } catch(Exception e) {
            return true;
        }
        return false;

    }
    public boolean noAddress2FoundError(String keyword)  {
        GUIHelper helper = new GUIHelper();
        JSONArray jsonArray = helper.getJSONArray(keyword, "address2GeocodeResult");
        try {
            jsonArray.getFirst();
        } catch(Exception e) {
            return true;
        }
        return false;

    }

    public boolean noAPIKey(){

        InputStream inputStream = AccessAPI.class.getClassLoader().getResourceAsStream("APIToken.txt");

        return inputStream == null;
    }
}
