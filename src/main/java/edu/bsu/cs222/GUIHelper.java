package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.minidev.json.JSONArray;

import java.io.InputStream;

public class GUIHelper {

    public JSONArray getJSONArray(String keyword, String Address)  {
        AccessAPI access = new AccessAPI();
        Parser parser = new Parser();

        try {
            InputStream inputStream = access.getInputStream(access.connectToGeocode(Address));
            return parser.makeJSONArray(inputStream, keyword);
        }catch(Exception e){
            return null;
        }

    }
    public double makeJSONArrayIntoDouble(JSONArray jsonArray) {
        Parser parser = new Parser();
        return Double.parseDouble(parser.parseToString(jsonArray));
    }

    public double makeAddressIntoLatAndLonDouble(String keyword, String Address) {
        return makeJSONArrayIntoDouble(getJSONArray(keyword, Address));
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
