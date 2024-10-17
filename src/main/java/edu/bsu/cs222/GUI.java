package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GUI extends Application {

    private final Button getDistanceButton = new Button("Get Distance");

    private final TextField inputFirstAddress = new TextField();
    private final TextField inputSecondAddress = new TextField();
    private final TextField distanceField = new TextField();
    private final Label lon1Label = new Label();
    private final Label lat1Label = new Label();
    private final Label lon2Label = new Label();
    private final Label lat2Label = new Label();

    private final ComboBox<String> unitOfMeasureSelector = new ComboBox<>();

    private static boolean milesFlag = false;

    @Override


    public void start(Stage stage) {
        inputFirstAddress.setPrefWidth(170);
        inputSecondAddress.setPrefWidth(170);
        configureComboBox();
        configureButton();
        configure(stage);
    }

    private void configure(Stage stage) {
        stage.setTitle("Location Explorer");
        stage.setScene(new Scene(createRoot()));
        stage.setMinWidth(800);
        stage.setMinHeight(500);
        stage.show();
    }

    private Pane createRoot() {
        VBox root = configureVBox(500);
        HBox testHBox = configureHBox(50);

        HBox userInputHBox = configureHBox(100);  // Local variable
        HBox latLonAndButtonsHBox = configureHBox(100);  // Local variable
        HBox outPutFieldHBox = configureHBox(100);  // Local variable

        VBox label1VBox = configureVBox(150);
        VBox buttonsVBox = configureVBox(100);
        VBox label2VBox = configureVBox(150);
        populateVBox(root, testHBox, userInputHBox, label1VBox, buttonsVBox, label2VBox, latLonAndButtonsHBox, outPutFieldHBox);
        return root;
    }

    private void populateVBox(VBox root, HBox testHBox, HBox userInputHBox,
                              VBox label1VBox, VBox buttonsVBox, VBox label2VBox,
                              HBox latLonAndButtonsHBox, HBox outPutFieldHBox) {

        testHBox.getChildren().addAll(
                new Label("First Address"),
                inputFirstAddress
        );

        HBox testHBox2 = configureHBox(50);
        testHBox.getChildren().addAll(
                new Label("Second Address"),
                inputSecondAddress
        );

        userInputHBox.getChildren().addAll(
                testHBox,
                testHBox2
        );

        label1VBox.getChildren().addAll(
                lat1Label,
                lon1Label
        );

        buttonsVBox.getChildren().addAll(
                unitOfMeasureSelector,
                getDistanceButton
        );

        label2VBox.getChildren().addAll(
                lat2Label,
                lon2Label
        );

        latLonAndButtonsHBox.getChildren().addAll(
                label1VBox,
                buttonsVBox,
                label2VBox
        );

        outPutFieldHBox.getChildren().addAll(
                new Label("Distance"), distanceField
        );


        root.getChildren().addAll(userInputHBox, latLonAndButtonsHBox, outPutFieldHBox);
    }

    private void configureButton() {
        getDistanceButton.setOnAction(event -> {
            try {
                turnAddressesToDistance();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void configureComboBox() {
        unitOfMeasureSelector.getItems().addAll("Miles","Kilometers");
    }
    private void comboBoxDecision(){
        if (unitOfMeasureSelector.getValue().equals("Miles")) {
            milesFlag = true;
        }
        else if(unitOfMeasureSelector.getValue().equals("Kilometers")) {
            milesFlag = false;
        }
    }

    public void turnAddressesToDistance() throws IOException, InterruptedException {
        GUIHelper helper = new GUIHelper();
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double lat1 = helper.getDouble("lat", inputFirstAddress.getText());
        double lon1 = helper.getDouble("lon", inputFirstAddress.getText());

        Thread.sleep(1000);

        double lat2 = helper.getDouble("lat", inputSecondAddress.getText());
        double lon2 = helper.getDouble("lon", inputSecondAddress.getText());

        lat1Label.setText("Latitude: " + (lat1));
        lon1Label.setText("Longitude: " + (lon1));
        lat2Label.setText("Latitude: " + (lat2));
        lon2Label.setText("Longitude: " + (lon2));


        double distance = distanceCalculator.calculateDistanceKiloMeters(lat1,lon1,lat2,lon2);

        comboBoxDecision();
        if (milesFlag) {
            distance = distanceCalculator.kilometersToMiles(distance);
        }
        distance = distanceCalculator.roundDistanceTwoDecimal(distance);

        distanceField.setText(Double.toString(distance));

    }

    private HBox configureHBox(int height){

        HBox hBox = new HBox();
        hBox.setPrefHeight(height);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        return hBox;
    }

    private VBox configureVBox(int width){

        VBox vBox = new VBox();
        vBox.setPrefWidth(width);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        return vBox;

    }
}
