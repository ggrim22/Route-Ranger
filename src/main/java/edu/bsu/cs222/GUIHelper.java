package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    protected void configureErrorHandling(String address1, String address2) throws IOException {
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.assertErrorType("lat", address1, address2);
    }
}

