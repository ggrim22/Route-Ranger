package edu.bsu.cs222;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.IOException;

public class ErrorModalBox {

    private Dialog<String> configureErrorModalBox(){
        Dialog<String> errorBox = new Dialog<>();
        errorBox.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        errorBox.setHeight(111);
        return errorBox;
    }

    public void networkConnectionPopUp() {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.networkConnectionError()) {
            Dialog<String> errorBox = configureErrorModalBox();
            errorBox.setContentText("There has been a network error. Please try again later.");
            errorBox.show();
        }
    }

    public void noInputFoundPopUp(String input) {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.noInputFoundError(input)) {
            Dialog<String> errorBox = configureErrorModalBox();
            errorBox.setContentText("There has been an error, please fill each alotted space.");
            errorBox.show();

        }
    }

    public void noAddressFoundPopUp(String keyword, String address) throws IOException {
        ErrorHandler errorHandler = new ErrorHandler();
        if(errorHandler.noAddressFoundError(keyword,address)) {
            Dialog<String> errorBox = configureErrorModalBox();
            errorBox.setContentText("There has been an error, please enter a valid address");
            errorBox.show();

        }
    }

}
