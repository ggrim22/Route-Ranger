package edu.bsu.cs222;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GUI extends Application {
    //Instance Variables
    private final Button getDistanceButton = new Button("Get Distance");
    private final Button closeButton = new Button("Close Window");
    private final Button address1MapButton = new Button("Get Map");
    private final Button address2MapButton = new Button("Get Map");
    private final TextField inputFirstAddress = new TextField();
    private final TextField inputSecondAddress = new TextField();
    private final TextField distanceField = new TextField();
    private final Label lonLabelAddress1 = new Label();
    private final Label latLabelAddress1 = new Label();
    private final Label lonLabelAddress2 = new Label();
    private final Label latLabelAddress2 = new Label();
    private final Rectangle blankRectangleForSpace = new Rectangle();
    private final ImageView logo = new ImageView();
    private final ImageView firstAddressImage = new ImageView();
    private final ImageView secondAddressImage = new ImageView();
    private final ImageView dynamicMapImage = new ImageView();

    private final ComboBox<String> unitOfMeasureSelector = new ComboBox<>();

    //Stage Configuration
    @Override
    public void start(Stage stage) throws IOException {
        configureInputBoxes();
        configureLatLonLabels();
        configureComboBox();
        configureGetDistanceButton();
        configureStaticMapButtons(address1MapButton, firstAddressImage, latLabelAddress1, lonLabelAddress1);
        configureStaticMapButtons(address2MapButton, secondAddressImage, latLabelAddress2, lonLabelAddress2);
        configure(stage);
        configureCloseButton();
        configureLogo();
        configureRectangle();
    }

    private void configure(Stage stage) throws IOException {
        stage.setTitle("Route Ranger");

        GUIStyle styler = new GUIStyle();
        ImageView backgroundImage = styler.configureBackgroundImage(stage);

        StackPane root = new StackPane();

        root.getChildren().add(backgroundImage);
        root.getChildren().add(createRoot());

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);

        stage.setMinWidth(800);
        stage.setMinHeight(500);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("Route Ranger!");
        stage.show();
    }
    private Pane createRoot(){
        GUIHelper helper = new GUIHelper();
        VBox root = helper.configureVBox(700);
        root.getChildren().addAll(configureLogoHbox(), configureMainVBox(),configureFooterHBox());
        return root;
    }

    //Population of the stage
    private VBox configureMainVBox() {
        GUIHelper helper = new GUIHelper();
        VBox mainVBox = helper.configureVBox(500);
        mainVBox.setAlignment(Pos.CENTER);

        populateVBox(mainVBox);
        return mainVBox;
    }

    private void populateVBox(VBox root) {
        inputFirstAddress.setText("146 Nursery Rd Anderson IN");
        inputSecondAddress.setText("320 S Talley Ave Muncie IN");
        GUIHelper helper = new GUIHelper();

        HBox leftHeaderHBox = helper.configureHBox(50);
        HBox userInputHBox = helper.configureHBox(50);

        VBox locationLabel1 = helper.configureVBox(150);
        VBox distanceAndUnitVbox = helper.configureVBox(100);
        VBox locationLabel2 = helper.configureVBox(150);

        HBox latLonAndButtonsHBox = helper.configureHBox(100);

        HBox outPutFieldHBox = helper.configureHBox(50);

        HBox dynamicMapHbox = helper.configureHBox(100);


        leftHeaderHBox.getChildren().addAll(
                configureText("First Address"),
                inputFirstAddress
        );

        HBox rightHeaderHBox = helper.configureHBox(50);
        rightHeaderHBox.getChildren().addAll(
                blankRectangleForSpace,
                configureText("Second Address"),
                inputSecondAddress
        );

        userInputHBox.getChildren().addAll(
                leftHeaderHBox,
                rightHeaderHBox
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
                address1MapButton,
                locationLabel1,
                distanceAndUnitVbox,
                locationLabel2,
                address2MapButton
        );

        outPutFieldHBox.getChildren().addAll(
                firstAddressImage,
                configureText("Distance"), distanceField,
                secondAddressImage
        );

        dynamicMapHbox.getChildren().addAll(
                dynamicMapImage
        );
        root.getChildren().addAll(userInputHBox, latLonAndButtonsHBox, outPutFieldHBox, dynamicMapHbox);
    }

    //Configuration of stage objects
    private Text configureText(String textString) {
        Text resultText = new Text(textString);
        Font font = Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 25);
        resultText.setFont(font);
        resultText.setFill(Color.WHITE);
        resultText.setStrokeWidth(1);
        resultText.setStroke(Color.BLACK);
        return resultText;
    }
    private void configureLatLonLabels() {
        Font font = Font.font("Georgia", FontWeight.BOLD, FontPosture.REGULAR, 10);

        latLabelAddress1.setFont(font);
        latLabelAddress1.setTextFill(Color.WHITE);

        lonLabelAddress1.setFont(font);
        lonLabelAddress1.setTextFill(Color.WHITE);

        latLabelAddress2.setFont(font);
        latLabelAddress2.setTextFill(Color.WHITE);

        lonLabelAddress2.setFont(font);
        lonLabelAddress2.setTextFill(Color.WHITE);
    }
    private void configureInputBoxes(){
        Font font = Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 12);

        inputFirstAddress.setFont(font);
        inputFirstAddress.setPrefWidth(170);
        inputFirstAddress.setPromptText("123 Main St City State");

        inputSecondAddress.setFont(font);
        inputSecondAddress.setPrefWidth(170);
        inputSecondAddress.setPromptText("123 Main St City State");

    }
    private HBox configureLogoHbox() {
        GUIHelper helper = new GUIHelper();
        HBox logoHBox = helper.configureHBox(50);
        logoHBox.getChildren().addAll(
                logo
        );
        logoHBox.setAlignment(Pos.TOP_LEFT);

        return logoHBox;
    }

    private HBox configureFooterHBox() {
        GUIHelper helper = new GUIHelper();
        HBox footerHBox = helper.configureHBox(400);
        Text credits = new Text("A Grim West Culp Production. 2024.");
        Font font = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 15);
        credits.setFont(font);
        credits.setStroke(Color.LIGHTGRAY);
        footerHBox.getChildren().addAll(
                closeButton,
                blankRectangleForSpace,
                credits
        );
        footerHBox.setAlignment(Pos.BOTTOM_LEFT);
        return footerHBox;
    }

    private void configureLogo() throws IOException {
        ImageHandler imageHandler = new ImageHandler();
        InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("RouteRangerImage.png");
        assert imageInputStream != null;
        Image image = imageHandler.convertToFxImage(ImageIO.read(imageInputStream));
        logo.setImage(image);
        logo.setFitHeight(120);
        logo.setFitWidth(120);
    }

    private void configureStaticMapImage(ImageView mapChoice, Label latLabel, Label lonLabel) throws IOException {
        AccessAPI accessAPI = new AccessAPI();

        String latLabelText = latLabel.getText();
        String lat = latLabelText.split(" ")[1];

        String lonLabelText = lonLabel.getText();
        String lon = lonLabelText.split(" ")[1];

        ImageHandler handler = new ImageHandler();
        Image image = handler.accessImage(accessAPI.connectToStaticMap(lat, lon));
        mapChoice.setImage(image);

    }

    private void configureDynamicMapImage() throws IOException {
        AccessAPI accessAPI = new AccessAPI();
        GeoCalculator geoCalculator = new GeoCalculator();


        String address1LatText = latLabelAddress1.getText();
        String address1Lat = address1LatText.split(" ")[1];

        String address1LonText = lonLabelAddress1.getText();
        String address1Lon = address1LonText.split(" ")[1];

        String address2LatText = latLabelAddress2.getText();
        String address2Lat = address2LatText.split(" ")[1];

        String address2LonText = lonLabelAddress2.getText();
        String address2Lon = address2LonText.split(" ")[1];

        double distance = geoCalculator.calculateDistanceKiloMeters(Double.parseDouble(address1Lon), Double.parseDouble(address1Lat),Double.parseDouble(address2Lon), Double.parseDouble(address2Lat));

        String zoomLevel = geoCalculator.calculateZoomLevel(distance);

        double[] centerLatAndLon = geoCalculator.calculateCenterLatAndLon(Double.parseDouble(address1Lon), Double.parseDouble(address1Lat),Double.parseDouble(address2Lon), Double.parseDouble(address2Lat));
        double centerLon = centerLatAndLon[1];
        double centerLat = centerLatAndLon[0];

        ImageHandler handler = new ImageHandler();
        Image image = handler.accessImage(accessAPI.connectToDynamicMap(address1Lat, address1Lon, address2Lat, address2Lon, Double.toString(centerLat), Double.toString(centerLon), zoomLevel));
        dynamicMapImage.setImage(image);
    }

    private void configureRectangle() {
        blankRectangleForSpace.setHeight(20);
        blankRectangleForSpace.setWidth(50);
        blankRectangleForSpace.setFill(Color.TRANSPARENT);
    }

    private void configureGetDistanceButton() {
        getDistanceButton.setOnAction(event -> {
            try {
                configureErrorHandling();
                unitConverter();
                configureStaticMapImage(firstAddressImage,latLabelAddress1,lonLabelAddress1);
                configureStaticMapImage(secondAddressImage,latLabelAddress2,lonLabelAddress2);
                configureDynamicMapImage();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void configureStaticMapButtons(Button addressMapButton, ImageView imageView, Label label1, Label label2){

        addressMapButton.setOnAction(event -> {
            try {
                configureErrorHandling();
                setAddress1Geo();
                setAddress2Geo();
                configureStaticMapImage(imageView,label1,label2);
            } catch (IOException e) {
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


    //Distance Output Calculations
    public double getDistance() throws IOException{
        GeoCalculator geoCalculator = new GeoCalculator();

        setAddress1Geo();
        setAddress2Geo();

        String address1LatText = latLabelAddress1.getText();
        String address1Lat = address1LatText.split(" ")[1];
        double lat1 = Double.parseDouble(address1Lat);

        String address1LonText = lonLabelAddress1.getText();
        String address1Lon = address1LonText.split(" ")[1];
        double lon1 = Double.parseDouble(address1Lon);

        String address2LatText = latLabelAddress2.getText();
        String address2Lat = address2LatText.split(" ")[1];
        double lat2 = Double.parseDouble(address2Lat);

        String address2LonText = lonLabelAddress2.getText();
        String address2Lon = address2LonText.split(" ")[1];
        double lon2 = Double.parseDouble(address2Lon);


        return geoCalculator.calculateDistanceKiloMeters(lat1, lon1, lat2, lon2);
    }

    private void setAddress1Geo() throws IOException {
        GUIHelper helper = new GUIHelper();
        GeoCalculator geoCalculator = new GeoCalculator();

        double lat = helper.makeAddressIntoLatAndLonDouble("lat", inputFirstAddress.getText());
        double lon = helper.makeAddressIntoLatAndLonDouble("lon", inputFirstAddress.getText());

        latLabelAddress1.setText("Latitude: " + (geoCalculator.roundDistanceFourDecimal(lat)));
        lonLabelAddress1.setText("Longitude: " + (geoCalculator.roundDistanceFourDecimal(lon)));
    }

    private void setAddress2Geo() throws IOException {
        GUIHelper helper = new GUIHelper();
        GeoCalculator geoCalculator = new GeoCalculator();

        double lat = helper.makeAddressIntoLatAndLonDouble("lat", inputSecondAddress.getText());
        double lon = helper.makeAddressIntoLatAndLonDouble("lon", inputSecondAddress.getText());

        latLabelAddress2.setText("Latitude: " + (geoCalculator.roundDistanceFourDecimal(lat)));
        lonLabelAddress2.setText("Longitude: " + (geoCalculator.roundDistanceFourDecimal(lon)));
    }

    private void unitConverter() throws IOException, InterruptedException {
        GeoCalculator geoCalculator = new GeoCalculator();
        double distance = getDistance();
        if (Objects.equals(unitOfMeasureSelector.getValue(), "Miles")) {
            distance = geoCalculator.kilometersToMiles(distance);
        }
        String outputDistance = geoCalculator.roundDistanceFourDecimal(distance);

        distanceField.setText(String.format("%s %s",outputDistance, unitOfMeasureSelector.getValue().toLowerCase()));
    }


    //Error handling
    protected void configureErrorHandling() throws IOException {
        ErrorModalBox errorModalBox = new ErrorModalBox();
        errorModalBox.assertErrorType("lat", inputFirstAddress.getText(),inputSecondAddress.getText());

    }

}
