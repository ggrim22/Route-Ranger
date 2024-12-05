package edu.bsu.cs222;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import java.util.Map;


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
    private final Label firstAddressTime = new Label();
    private final Label secondAddressTime = new Label();
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
        configure(stage);
        configureGetDistanceButton();

        configureFirstStaticMapButton();
        configureSecondStaticMapButton();
        configureCloseButton();
        configureLogo();
        configureRectangle();
    }

    private void configure(Stage stage) throws IOException {
        stage.setTitle("Route Ranger");

        GUIStyle styler = new GUIStyle();
        BackgroundImage backgroundImage = styler.configureBackgroundImage();

        StackPane root = new StackPane();

        root.setBackground(new Background(backgroundImage));
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
        GUIHelper helper = new GUIHelper();

        HBox leftHeaderHBox = helper.configureHBox(50);
        HBox userInputHBox = helper.configureHBox(50);

        VBox locationLabel1 = helper.configureVBox(150);
        VBox distanceAndUnitVbox = helper.configureVBox(100);
        VBox locationLabel2 = helper.configureVBox(150);

        HBox latLonAndButtonsHBox = helper.configureHBox(100);

        HBox outPutFieldHBox = helper.configureHBox(50);

        HBox mapsHBox = helper.configureHBox(100);


        leftHeaderHBox.getChildren().addAll(
                configureText("First Address"),
                inputFirstAddress
        );

        HBox rightHeaderHBox = helper.configureHBox(50);
        rightHeaderHBox.getChildren().addAll(
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
                firstAddressTime,
                configureText("Distance"), distanceField,
                secondAddressTime

        );

        mapsHBox.getChildren().addAll(
                firstAddressImage,
                dynamicMapImage,
                secondAddressImage
        );
        root.getChildren().addAll(userInputHBox, latLonAndButtonsHBox, outPutFieldHBox, mapsHBox);
    }

    //Configuration of stage objects
    private Font createFont(int size, FontPosture posture) {
        return Font.font("Georgia", FontWeight.BOLD, posture, size);
    }

    private Text configureText(String textString) {
        Text resultText = new Text(textString);
        resultText.setFont(createFont(25, FontPosture.REGULAR));
        resultText.setFill(Color.WHITE);
        resultText.setStrokeWidth(1);
        resultText.setStroke(Color.BLACK);
        return resultText;
    }

    private void configureLatLonLabels() {
        Font font = createFont(10, FontPosture.REGULAR);
        Color textColor = Color.WHITE;
        latLabelAddress1.setFont(font);
        lonLabelAddress1.setFont(font);
        latLabelAddress2.setFont(font);
        lonLabelAddress2.setFont(font);
        latLabelAddress1.setTextFill(textColor);
        lonLabelAddress1.setTextFill(textColor);
        latLabelAddress2.setTextFill(textColor);
        lonLabelAddress2.setTextFill(textColor);
        latLabelAddress1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        latLabelAddress2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        lonLabelAddress1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        lonLabelAddress2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    private void configureInputBoxes(){
        Font font = createFont(12, FontPosture.REGULAR);

        inputFirstAddress.setFont(font);
        inputFirstAddress.setPrefWidth(170);
        inputFirstAddress.setPromptText("123 Main St City State ZIP");

        inputSecondAddress.setFont(font);
        inputSecondAddress.setPrefWidth(170);
        inputSecondAddress.setPromptText("123 Main St City State ZIP");

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
        credits.setFont(createFont(15, FontPosture.ITALIC));
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
        try {
            String lat = latLabel.getText().split(" ")[1];
            String lon = lonLabel.getText().split(" ")[1];


            if (lat != null && lon != null && !lat.equals("0") && !lon.equals("0")) {
                AccessAPI accessAPI = new AccessAPI();
                ImageHandler handler = new ImageHandler();
                Image image = handler.accessImage(accessAPI.connectToStaticMap(lat, lon));
                mapChoice.setImage(image);
            } else {
                mapChoice.setImage(null);
            }
        }catch (Exception e) {
            mapChoice.setImage(null);
        }
    }
    private void setAddressToCompleteAddress(Map<String, Object> map, TextField inputAddress) {

        Object completeAddress = map.get("formatted");
        inputAddress.setText((String) completeAddress);
    }

    private void configureDynamicMapImage() throws IOException {
        AccessAPI accessAPI = new AccessAPI();
        GeoCalculator geoCalculator = new GeoCalculator();

        try {
            String address1LatText = latLabelAddress1.getText();
            String address1Lat = address1LatText.split(" ")[1];

            String address1LonText = lonLabelAddress1.getText();
            String address1Lon = address1LonText.split(" ")[1];

            String address2LatText = latLabelAddress2.getText();
            String address2Lat = address2LatText.split(" ")[1];

            String address2LonText = lonLabelAddress2.getText();
            String address2Lon = address2LonText.split(" ")[1];

            double distance = geoCalculator.calculateDistanceKiloMeters(Double.parseDouble(address1Lat), Double.parseDouble(address1Lon), Double.parseDouble(address2Lat), Double.parseDouble(address2Lon));
            String zoomLevel = geoCalculator.calculateZoomLevel(distance);

            double[] centerLatAndLon = geoCalculator.calculateCenterLatAndLon(Double.parseDouble(address1Lon), Double.parseDouble(address1Lat), Double.parseDouble(address2Lon), Double.parseDouble(address2Lat));
            double centerLon = centerLatAndLon[1];
            double centerLat = centerLatAndLon[0];

            ImageHandler handler = new ImageHandler();
            Image image = handler.accessImage(accessAPI.connectToDynamicMap(address1Lat, address1Lon, address2Lat, address2Lon, Double.toString(centerLat), Double.toString(centerLon), zoomLevel));
            dynamicMapImage.setImage(image);
        } catch (Exception e) {
            dynamicMapImage.setImage(null);
        }
    }

    private void configureRectangle() {
        blankRectangleForSpace.setHeight(20);
        blankRectangleForSpace.setWidth(50);
        blankRectangleForSpace.setFill(Color.TRANSPARENT);
    }

    private void configureGetDistanceButton() {
        getDistanceButton.setOnAction(event -> {
            try {
                updateDistanceOutput(new GeoCalculator().unitConverter(unitOfMeasureSelector.getValue(), getDistance()));
                configureStaticMapImage(firstAddressImage,latLabelAddress1,lonLabelAddress1);
                configureStaticMapImage(secondAddressImage,latLabelAddress2,lonLabelAddress2);
                configureDynamicMapImage();
                configureTimeForFirstAddress();
                configureTimeForSecondAddress();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void configureFirstStaticMapButton(){

        address1MapButton.setOnAction(event -> {
            try {
                new GUIHelper().configureErrorHandling(inputFirstAddress.getText());
                setAddressGeo(inputFirstAddress, latLabelAddress1, lonLabelAddress1);
                configureTimeForFirstAddress();
                configureStaticMapImage(firstAddressImage,latLabelAddress1,lonLabelAddress1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private void configureSecondStaticMapButton(){

        address2MapButton.setOnAction(event -> {
            try {
                new GUIHelper().configureErrorHandling(inputSecondAddress.getText());
                setAddressGeo(inputSecondAddress, latLabelAddress2, lonLabelAddress2);
                configureTimeForSecondAddress();
                configureStaticMapImage(secondAddressImage,latLabelAddress2,lonLabelAddress2);
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

        new GUIHelper().configureErrorHandling(inputFirstAddress.getText(), inputSecondAddress.getText());

        setAddressGeo(inputFirstAddress, latLabelAddress1,lonLabelAddress1);
        setAddressGeo(inputSecondAddress, latLabelAddress2, lonLabelAddress2);
        try {
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
        } catch(Exception e) {
            return -1;
        }
    }

    private void setAddressGeo(TextField input, Label latLabel, Label lonLabel) {
        GeoCalculator geoCalculator = new GeoCalculator();
        JsonMapMaker mapMaker = new JsonMapMaker();
        Map<String, Object> map;
        try {
            mapMaker.getMapFromConnection(input.getText());
            map = mapMaker.getJsonMap();

            new GUIHelper().configureErrorHandling(map);

            setAddressToCompleteAddress(map, input);

            String lat = (String)map.get("lat");
            String lon = (String)map.get("lon");
            if (Double.parseDouble(lat) >= 0 || Double.parseDouble(lon) >= 0) {
                latLabel.setText("Latitude: " + (geoCalculator.roundDistanceFourDecimal(Double.parseDouble(lat))));
                lonLabel.setText("Longitude: " + (geoCalculator.roundDistanceFourDecimal(Double.parseDouble(lon))));
            }
        } catch(Exception e) {
            e.getSuppressed();
        }
    }

    protected void updateDistanceOutput(String distance) {
        if(Double.parseDouble(distance) < 0) {
            distanceField.setText("");
        }
        else {
            distanceField.setText(String.format("%s %s", distance, unitOfMeasureSelector.getValue().toLowerCase()));
        }
    }

    protected void configureTimeForFirstAddress() throws IOException {
        Timezone timezone = new Timezone();
        try{
            String address1LatText = latLabelAddress1.getText();
            String address1Lat = address1LatText.split(" ")[1];
            double latitude = Double.parseDouble(address1Lat);

            String address1LonText = lonLabelAddress1.getText();
            String address1Lon = address1LonText.split(" ")[1];
            double longitude = Double.parseDouble(address1Lon);

            firstAddressTime.setText("Time: " + timezone.getTimezone(latitude, longitude));
            styleTimeLabels();
        }catch(Exception e) {
            e.getSuppressed();
        }
    }


    protected void configureTimeForSecondAddress() throws IOException {
        Timezone timezone = new Timezone();

        try{
            String address2LatText = latLabelAddress2.getText();
            String address2Lat = address2LatText.split(" ")[1];
            double latitude = Double.parseDouble(address2Lat);

            String address2LonText = lonLabelAddress2.getText();
            String address2Lon = address2LonText.split(" ")[1];
            double longitude = Double.parseDouble(address2Lon);
            secondAddressTime.setText("Time: " + timezone.getTimezone(latitude, longitude));
            styleTimeLabels();
        }catch(Exception e) {
            e.getSuppressed();
        }
    }

    private void styleTimeLabels() {
        Font font = createFont(25, FontPosture.REGULAR);
        firstAddressTime.setFont(font);
        secondAddressTime.setFont(font);
        Color textColor = Color.WHITE;
        firstAddressTime.setTextFill(textColor);
        secondAddressTime.setTextFill(textColor);
        firstAddressTime.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        secondAddressTime.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
