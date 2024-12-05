package edu.bsu.cs222;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.util.Map;


public class ErrorModalBox {

    public void assertAPIConnectionError(String address1, String address2) {
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        if(errorHandler.networkConnectionError()){
            ConnectionErrorPopUp();
        }else if (errorHandler.noInputFoundError(address1, address2)) {
            noInputFoundPopUp();
        }

    }

    public void assertAPIConnectionError(String address) {
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        if(errorHandler.networkConnectionError()){
            ConnectionErrorPopUp();
        }else if (errorHandler.noInputFoundError(address)) {
            noInputFoundPopUp();
        }

    }

    public void checkValidAddress(Map<String, Object> geocodeMap){
        ErrorTypeHandler errorHandler = new ErrorTypeHandler();

        if (errorHandler.noAddressFoundError(geocodeMap) || errorHandler.noAddressFoundError(geocodeMap)) {
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

    private void ConnectionErrorPopUp() {
        createErrorPopUp("There has been a connection error. Please try again later.");
    }
    private void noInputFoundPopUp() {
        createErrorPopUp("There has been an error, please fill each allotted space.");
    }

    private void noAddressFoundPopUp(){
        createErrorPopUp("There has been an error, please enter a valid address");
    }


    private void createErrorPopUp(String errorMessage){
        Dialog<String> errorBox = configureErrorModalBox();
        errorBox.setContentText(errorMessage);
        errorBox.show();
    }

}
