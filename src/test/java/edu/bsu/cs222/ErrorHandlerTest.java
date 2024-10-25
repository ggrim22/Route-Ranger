package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ErrorHandlerTest {

    @Test
    public void testNoInputFound(){
        ErrorHandler errorHandler = new ErrorHandler();

        boolean testResult = true;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError(""));
    }

    @Test
    public void testInputFound(){
        ErrorHandler errorHandler = new ErrorHandler();

        boolean testResult = false;
        Assertions.assertEquals(testResult, errorHandler.noInputFoundError("1101 N Linden St"));
    }

    @Test
    public void testNoAddressFound() throws IOException, InterruptedException {
        ErrorHandler errorHandler = new ErrorHandler();
        boolean result = true;
        Thread.sleep(1000);
        Assertions.assertEquals(result,errorHandler.noAddressFoundError("lat","No Address Found Place Holder"));
    }

}
