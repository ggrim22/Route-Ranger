package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class ErrorHandler {

    public Boolean noAddressFoundError(JSONArray address) throws IOException {
        boolean addressNotFound = false;

        if (address.getFirst() == null){
            addressNotFound = true;
        }

        return addressNotFound;
    }
}
