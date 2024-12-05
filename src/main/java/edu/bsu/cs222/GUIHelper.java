package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class GUIHelper {

    protected HBox configureHBox(int height){

        HBox hBox = new HBox(8);
        hBox.setPrefHeight(height);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        return hBox;
    }

    protected VBox configureVBox(int width){

        VBox vBox = new VBox(15);
        vBox.setPrefWidth(width);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        return vBox;
    }

    protected void configureErrorHandling(Map<String, Object> geocodeMap)  {
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.checkValidAddress(geocodeMap);
    }

    protected void configureErrorHandling(String address1, String address2){
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.assertAPIConnectionError(address1,address2);
    }

    protected void configureErrorHandling(String address){
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.assertAPIConnectionError(address);
    }


}

