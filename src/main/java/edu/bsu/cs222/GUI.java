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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class GUI extends Application {
    private final Button getDistanceButton = new Button("Get Distance");
    private final TextField inputFirstAddress = new TextField();

    private final TextField inputSecondAddress = new TextField();
    private final TextField distanceField = new TextField();
    private final HBox hBox1 = new HBox();
    private final HBox hBox2 = new HBox();

    @Override


    public void start(Stage stage) {
        inputFirstAddress.setPrefWidth(170);
        inputSecondAddress.setPrefWidth(170);
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
        hBox2.getChildren().addAll(
                getDistanceButton
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

}
