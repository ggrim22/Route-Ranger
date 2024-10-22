package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ErrorHandler {

    public Boolean noInputFoundError(String input)  {
        return input.isEmpty();
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
}
