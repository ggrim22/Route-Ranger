package edu.bsu.cs222;


import java.io.InputStream;

public class ErrorHandler {

    public boolean noInputFoundError(String address)  {
        return address.isBlank();
    }

    public boolean networkConnectionError() {
        AccessAPI access = new AccessAPI();
        return access.connectToGeocode("146 Nursery Rd Anderson IN") == null;
    }

    public boolean noAddressFoundError()  {
        return false;
    }

    public boolean noAPIKey(){

        InputStream inputStream = AccessAPI.class.getClassLoader().getResourceAsStream("APIToken.txt");

        return inputStream == null;
    }
}
