package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Decoder {
    private static String APIKey;


    int oddEven;
    int letters;
    String keyWord;

    public String getAPIKey() {
        return APIKey;
    }
    public void setAPIKey(String apiKey) {
        APIKey = apiKey;
    }
    public void analyzeKeyWord(String keyWord){
        letters = keyWord.length();
        oddEven = letters % 2;
        this.keyWord = keyWord;
    }

    public String decode(String encryptedKey) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < encryptedKey.length(); i++) {
            if(oddEven == 0) {
                result.append((char)(encryptedKey.charAt(i) + letters));
            }
            else{
                result.append((char)(encryptedKey.charAt(i) - letters));
            }
        }
        return result.toString().trim();
    }

    public String readFromAdminFile() throws IOException {

        try (InputStream inputStream = AccessAPI.class.getClassLoader().getResourceAsStream("APIToken.txt")) {
            assert inputStream != null;
            try (Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNextLine()) {
                    return scanner.nextLine();
                }
            }
        }

        return "";
    }
}
