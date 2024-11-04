package edu.bsu.cs222;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.IOException;

public class ErrorModalBox {

    public void assertErrorType(String keyword, String address1, String address2) throws IOException {
        ErrorHandler errorHandler = new ErrorHandler();

        if (errorHandler.networkConnectionError()){
            networkConnectionPopUp();
        }else if(errorHandler.noAPIKey()){
            noAPIKeyFoundPopUp();
        }else if (errorHandler.noInputFoundError(address1) || errorHandler.noInputFoundError(address2)) {
            noInputFoundPopUp();
        } else if (errorHandler.noAddressFoundError(keyword,address1) || errorHandler.noAddressFoundError(keyword, address2)) {
            noAddressFoundPopUp();
        }

    }

    private Dialog<String> configureErrorModalBox(){
        Dialog<String> errorBox = new Dialog<>();
        errorBox.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        errorBox.setHeight(111);
        return errorBox;
    }

    private void networkConnectionPopUp() {
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText("There has been a network error. Please try again later.");
        errorBox.show();
    }

    private void noInputFoundPopUp() {
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText("There has been an error, please fill each allotted space.");
        errorBox.show();
    }

    private void noAddressFoundPopUp(){
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText("There has been an error, please enter a valid address");
        errorBox.show();
    }

    private void noAPIKeyFoundPopUp(){
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText("There has been an error, if you would like to use this application, contact us or visit the API website to get an API key.");
        errorBox.show();
    }

}
