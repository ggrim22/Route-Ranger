package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ErrorTypeHandlerTest {

    @Test
    public void testNoInputFound(){
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        boolean testResult = true;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError("", ""));
    }

    @Test
    public void testInputFound(){
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        boolean testResult = false;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError("1101 N Linden St", "146 Nursery Rd Anderson IN"));
    }
    @Test
    public void testNoAddressFound() throws InterruptedException{
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        Map<String, Object> testMap = new HashMap<>();
        testMap.put("lat", null);

        boolean result = true;
        Thread.sleep(1000);
        Assertions.assertEquals(result,errorHandler.noAddressFoundError(testMap));
    }

}
