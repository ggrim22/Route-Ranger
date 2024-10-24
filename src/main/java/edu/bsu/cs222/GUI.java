package edu.bsu.cs222;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
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
import java.util.Objects;

public class GUI extends Application {

    private final Button getDistanceButton = new Button("Get Distance");
    private final Button closeButton = new Button("Close Window");
    private final TextField inputFirstAddress = new TextField();
    private final TextField inputSecondAddress = new TextField();
    private final TextField distanceField = new TextField();
    private final Label lonLabelAddress1 = new Label();
    private final Label latLabelAddress1 = new Label();
    private final Label lonLabelAddress2 = new Label();
    private final Label latLabelAddress2 = new Label();

    private final ComboBox<String> unitOfMeasureSelector = new ComboBox<>();

    @Override
    public void start(Stage stage) {
        inputFirstAddress.setPrefWidth(170);
        inputSecondAddress.setPrefWidth(170);
        configureComboBox();
        configureGetDistanceButton();
        configure(stage);
        configureCloseButton();
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
        HBox closeButtonHBox = helper.configureHBox(200);

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

        closeButtonHBox.getChildren().addAll(
                closeButton
        );

        closeButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        closeButtonHBox.setPadding(new Insets(0,40,0,20));

        root.getChildren().addAll(userInputHBox, latLonAndButtonsHBox, outPutFieldHBox, closeButtonHBox);
    }

    private void configureGetDistanceButton() {
        getDistanceButton.setOnAction(event -> {
            try {
                configureErrorHandling();
                unitConverter();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void configureCloseButton() {
        closeButton.setOnAction(event -> Platform.exit());
    }
    private void configureComboBox() {

        unitOfMeasureSelector.getItems().addAll("Miles","Kilometers");
        unitOfMeasureSelector.setValue("Miles");
    }

    public double turnAddressesToDistance() throws IOException{

        GUIHelper helper = new GUIHelper();
        DistanceCalculator distanceCalculator = new DistanceCalculator();


        double lat1 = helper.makeAddressIntoLatAndLonDouble("lat", inputFirstAddress.getText());
        double lon1 = helper.makeAddressIntoLatAndLonDouble("lon", inputFirstAddress.getText());


        double lat2 = helper.makeAddressIntoLatAndLonDouble("lat", inputSecondAddress.getText());
        double lon2 = helper.makeAddressIntoLatAndLonDouble("lon", inputSecondAddress.getText());

        latLabelAddress1.setText("Latitude: " + (distanceCalculator.roundDistanceFourDecimal(lat1)));
        lonLabelAddress1.setText("Longitude: " + (distanceCalculator.roundDistanceFourDecimal(lon1)));

        latLabelAddress2.setText("Latitude: " + (distanceCalculator.roundDistanceFourDecimal(lat2)));
        lonLabelAddress2.setText("Longitude: " + (distanceCalculator.roundDistanceFourDecimal(lon2)));


        return distanceCalculator.calculateDistanceKiloMeters(lat1,lon1,lat2,lon2);


    }
    private void unitConverter() throws IOException, InterruptedException {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double distance = turnAddressesToDistance();
        if (Objects.equals(unitOfMeasureSelector.getValue(), "Miles")) {
            distance = distanceCalculator.kilometersToMiles(distance);
        }
        String outputDistance = distanceCalculator.roundDistanceFourDecimal(distance);

        distanceField.setText(String.format("%s %s",outputDistance, unitOfMeasureSelector.getValue().toLowerCase()));
    }

    protected void configureErrorHandling() throws IOException {
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.assertErrorType("lat", inputFirstAddress.getText(),inputSecondAddress.getText());

    }
}
