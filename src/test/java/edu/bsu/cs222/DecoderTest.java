package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class DecoderTest {

    @Test
    public void decodeTest() {
        Decoder decoder = new Decoder();

        String key = "bananas";
        decoder.analyzeKeyWord(key);
        String result = decoder.decode("JZYVJRZ");
        Assertions.assertEquals("CSROCKS", result);
    }

    @Test
    public void getterAndSetterTest(){
        Decoder decoder = new Decoder();
        decoder.setAPIKey("bananas");
        Assertions.assertEquals("bananas", decoder.getAPIKey());
    }
}
