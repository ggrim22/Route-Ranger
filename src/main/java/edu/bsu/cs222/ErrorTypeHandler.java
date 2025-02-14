package edu.bsu.cs222;

import java.util.Map;

public class ErrorTypeHandler {

    public boolean noInputFoundError(String address1, String address2) {
        if (address1 == null || address2 == null) return true; // Null check
        return (address1.trim().isEmpty() || address2.trim().isEmpty()); // Trim and check
    }

    public boolean noInputFoundError(String address) {
        if (address == null) return true; // Null check
        return (address.trim().isEmpty()); // Trim and check
    }

    public boolean networkConnectionError() {
        AccessAPI access = new AccessAPI();
        return access.connectToGeocode("146 Nursery Rd Anderson IN") == null;
    }

    public boolean noAddressFoundError(Map<String, Object> map)  {
        return map.get("lat") == null;
    }
}
