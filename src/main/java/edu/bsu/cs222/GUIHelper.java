package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class GUIHelper {

    public double getDouble(String keyword, String Address) throws IOException {
        AccessAPI access = new AccessAPI();
        Parser parser = new Parser();
        ErrorHandler errorHandler = new ErrorHandler();

        InputStream inputStream = access.getInputStream(access.connectToGeocode(Address));
        JSONArray jsonArray = parser.makeJSONArray(inputStream, keyword);

        if (errorHandler.noAddressFoundError(jsonArray)){
            ErrorModalBox errorPopUp = new ErrorModalBox();
            errorPopUp.popUp();
            return 0.0;
        }else {
            return Double.parseDouble(parser.parseToString(jsonArray));
        }
    }

    protected HBox configureHBox(int height){

        HBox hBox = new HBox();
        hBox.setPrefHeight(height);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        return hBox;
    }

    protected VBox configureVBox(int width){

        VBox vBox = new VBox();
        vBox.setPrefWidth(width);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        return vBox;

    }
}
