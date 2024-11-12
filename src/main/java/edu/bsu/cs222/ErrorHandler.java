package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ErrorHandler {

    public boolean noInputFoundError(String address)  {
        return address.isBlank();
    }

    public boolean networkConnectionError() {
        try{
            AccessAPI access = new AccessAPI();
            access.connectToGeocode("146 Nursery Rd Anderson IN");
        } catch(Exception e) {
            return true;
        }
        return false;
    }

    public boolean noAddressFoundError(String keyword, String address) throws IOException {
        GUIHelper helper = new GUIHelper();
        JSONArray jsonArray = helper.getJSONArray(keyword,address);
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
