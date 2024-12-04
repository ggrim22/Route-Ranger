package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ErrorTypeHandlerTest {

    @Test
    public void testNoInputFound(){
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        boolean testResult = true;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError(""));
    }

    @Test
    public void testInputFound(){
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        boolean testResult = false;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError("1101 N Linden St"));
    }

    private final JsonMapMaker jsonMapMaker = new JsonMapMaker();

    @Test
    public void testNoAddressFound() throws InterruptedException, IOException {
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();
        AccessAPI api = new AccessAPI();
        InputStream testInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String jsonString = api.saveToString(testInputStream);

        Map<String, Object> resultMap = jsonMapMaker.parseResultsJson(jsonString);

        boolean result = true;
        Thread.sleep(1000);
        Assertions.assertEquals(result,errorHandler.noAddressFoundError(resultMap));
    }

}
