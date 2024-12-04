package edu.bsu.cs222;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Map;


public class ErrorModalBox {

    public void assertErrorType(Map<String, Object> geocodeMap, String address1, String address2) {
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        if (errorHandler.noAPIKey()){
            noAPIKeyFoundPopUp();
        }else if(errorHandler.networkConnectionError()){
            networkConnectionPopUp();
        }else if (errorHandler.noInputFoundError(address1) || errorHandler.noInputFoundError(address2)) {
            noInputFoundPopUp();
        } else if (errorHandler.noAddressFoundError(geocodeMap) || errorHandler.noAddressFoundError(geocodeMap)) {
            noAddressFoundPopUp();
        }

    }

    private Dialog<String> configureErrorModalBox(){
        Dialog<String> errorBox = new Dialog<>();
        errorBox.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        errorBox.setHeight(150);
        errorBox.setWidth(500);
        return errorBox;
    }

    private void networkConnectionPopUp() {
        createErrorPopUp("There has been a network error. Please try again later.");
    }
    private void noInputFoundPopUp() {
        createErrorPopUp("There has been an error, please fill each allotted space.");
    }

    private void noAddressFoundPopUp(){
        createErrorPopUp("There has been an error, please enter a valid address");
    }

    private void noAPIKeyFoundPopUp(){
        createErrorPopUp("There has been an error, if you would like to use this application, contact us or visit the API website to get an API key.");
    }

    private void createErrorPopUp(String errorMessage){
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText(errorMessage);
        errorBox.show();
    }

}
