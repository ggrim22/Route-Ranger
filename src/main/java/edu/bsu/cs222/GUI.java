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
    private final Label lonLabelAddress1 = new Label();
    private final Label latLabelAddress1 = new Label();
    private final Label lonLabelAddress2 = new Label();
    private final Label latLabelAddress2 = new Label();

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
        GUIHelper helper = new GUIHelper();
        VBox root = helper.configureVBox(500);
        root.setAlignment(Pos.CENTER);
        root.setStyle(GUIStyle.BACKGROUND_COLOR);

        populateVBox(root);
        return root;
    }

    private void populateVBox(VBox root) {
        GUIHelper helper = new GUIHelper();
        HBox testHBox = helper.configureHBox(50);
        HBox userInputHBox = helper.configureHBox(50);

        VBox locationLabel1 = helper.configureVBox(150);
        VBox distanceAndUnitVbox = helper.configureVBox(100);
        VBox locationLabel2 = helper.configureVBox(150);

        HBox latLonAndButtonsHBox = helper.configureHBox(100);

        HBox outPutFieldHBox = helper.configureHBox(50);

        testHBox.getChildren().addAll(
                new Label("First Address"),
                inputFirstAddress
        );

        HBox testHBox2 = helper.configureHBox(50);
        testHBox.getChildren().addAll(
                new Label("Second Address"),
                inputSecondAddress
        );

        userInputHBox.getChildren().addAll(
                testHBox,
                testHBox2
        );

        locationLabel1.getChildren().addAll(
                latLabelAddress1,
                lonLabelAddress1
        );

        distanceAndUnitVbox.getChildren().addAll(
                unitOfMeasureSelector,
                getDistanceButton
        );

        locationLabel2.getChildren().addAll(
                latLabelAddress2,
                lonLabelAddress2
        );

        latLonAndButtonsHBox.getChildren().addAll(
                locationLabel1,
                distanceAndUnitVbox,
                locationLabel2
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
        ErrorHandler errorHandler = new ErrorHandler();



        double lat1 = helper.getDouble("lat", inputFirstAddress.getText());
        double lon1 = helper.getDouble("lon", inputFirstAddress.getText());

        Thread.sleep(1000);

        double lat2 = helper.getDouble("lat", inputSecondAddress.getText());
        double lon2 = helper.getDouble("lon", inputSecondAddress.getText());

        latLabelAddress1.setText("Latitude: " + (lat1));
        lonLabelAddress1.setText("Longitude: " + (lon1));

        latLabelAddress2.setText("Latitude: " + (lat2));
        lonLabelAddress2.setText("Longitude: " + (lon2));


        double distance = distanceCalculator.calculateDistanceKiloMeters(lat1,lon1,lat2,lon2);

        comboBoxDecision();
        if (milesFlag) {
            distance = distanceCalculator.kilometersToMiles(distance);
        }
        distance = distanceCalculator.roundDistanceTwoDecimal(distance);

        distanceField.setText(distance + " " + unitOfMeasureSelector.getValue().toLowerCase());

    }
}
