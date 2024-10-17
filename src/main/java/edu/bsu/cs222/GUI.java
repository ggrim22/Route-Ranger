package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private final HBox hBox1 = new HBox();
    private final HBox hBox2 = new HBox();
    private final Label lon1Label = new Label();
    private final Label lat1Label = new Label();
    private final Label lon2Label = new Label();
    private final Label lat2Label = new Label();

    @Override


    public void start(Stage stage) {
        inputFirstAddress.setPrefWidth(170);
        inputSecondAddress.setPrefWidth(170);
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
        VBox root = new VBox();
        hBox1.getChildren().addAll(
                new Label("First Address"),
                inputFirstAddress,
                new Label("Second Address"),
                inputSecondAddress);
        hBox2.getChildren().addAll(lat1Label,
                lon1Label,
                getDistanceButton,
                lat2Label,
                lon2Label
        );
        formatHBox(hBox1);
        formatHBox(hBox2);
        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(new Label("Distance"), distanceField);
        root.getChildren().addAll(hBox1, hBox2, hBox3);
        return root;
    }

    private void formatHBox(HBox hBox) {
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
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

    public void turnAddressesToDistance() throws IOException, InterruptedException {
        GUIHelper helper = new GUIHelper();
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double lat1 = helper.getDouble("lat", inputFirstAddress.getText());
        double lon1 = helper.getDouble("lon", inputFirstAddress.getText());
        Thread.sleep(1000);

        double lat2 = helper.getDouble("lat", inputSecondAddress.getText());
        double lon2 = helper.getDouble("lon", inputSecondAddress.getText());

        lat1Label.setText(Double.toString(lat1));
        lon1Label.setText(Double.toString(lon1));
        lat2Label.setText(Double.toString(lat2));
        lon2Label.setText(Double.toString(lon2));


        double distance = distanceCalculator.calculateDistanceKiloMeters(lat1,lon1,lat2,lon2);
        double distanceInMiles = distanceCalculator.kilometersToMiles(distance);
        distanceField.setText(Double.toString(distanceInMiles));

    }

}
