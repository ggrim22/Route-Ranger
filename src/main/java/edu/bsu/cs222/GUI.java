package edu.bsu.cs222;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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
    private final ImageView logo = new ImageView();
    private final ImageView firstAddressImage = new ImageView();
    private final ImageView secondAddressImage = new ImageView();
    private final ImageView bothAddressImage = new ImageView();

    private final ComboBox<String> unitOfMeasureSelector = new ComboBox<>();

    @Override
    public void start(Stage stage) throws IOException {
        inputFirstAddress.setPrefWidth(170);
        inputSecondAddress.setPrefWidth(170);
        configureComboBox();
        configureGetDistanceButton();
        configure(stage);
        configureCloseButton();
        configureImage();
    }

    private void configure(Stage stage) {
        stage.setTitle("Route Ranger");
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
        testHBox2.getChildren().addAll(
                new Label("Second Address"),
                inputSecondAddress
        );

        userInputHBox.getChildren().addAll(
                testHBox,
                testHBox2
        );

        locationLabel1.getChildren().addAll(
                latLabelAddress1,
                lonLabelAddress1,
                logo
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
    private void configureImage() throws IOException {
        InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("RouteRangerImage.jpg");
        assert imageInputStream != null;
        Image image = convertToFxImage(ImageIO.read(imageInputStream));
        logo.setImage(image);
        logo.setFitHeight(30);
    }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
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
