package edu.bsu.cs222;

import javafx.scene.control.Dialog;
import net.minidev.json.JSONArray;

import java.io.IOException;

public class ErrorModalBox {

    public void networkConnectionPopUp() {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.networkConnectionError()) {
            Dialog<String> errorBox = new Dialog<>();
            errorBox.setContentText("There has been a network error. Please try again later.");
            errorBox.show();
        }
    }

    public void noInputFoundPopUp(String input) {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.noInputFoundError(input)) {
            Dialog<String> errorBox = new Dialog<>();
            errorBox.setContentText("There has been an error, please fill each alotted space.");
            errorBox.show();
        }
    }

    public void noAddressFoundPopUp(String keyword, String address) throws IOException {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.noAddressFoundError(keyword,address)) {
            Dialog<String> errorBox = new Dialog<>();
            errorBox.setContentText("There has been an error, please enter a valid address");
            errorBox.show();
        }
    }

}
