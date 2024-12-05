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
    public void analyzeKeyWord(String keyWord){
        letters = keyWord.length();
        oddEven = letters % 2;
        this.keyWord = keyWord;
    }

    public void decode() throws IOException {
        String encryptedKey = readFromAdminFile();
        StringBuilder result = new StringBuilder();
        int count = 0;
        for(int i = 0; i < encryptedKey.length(); i++) {
            if(oddEven == 0) {
                result.append((char)(encryptedKey.charAt(i) + letters + keyWord.charAt(i) + count));
            }
            else{
                result.append((char)(encryptedKey.charAt(i) - letters));
            }
            if(i >= keyWord.length()) {
                count++;
            }
        }
        System.out.println(result);
        APIKey = result.toString().trim();
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

    //enter one word password
    //check if odd or even
    //check how many letters are in the word
    //read api key and decrypt it and assign it to instance variable


}
