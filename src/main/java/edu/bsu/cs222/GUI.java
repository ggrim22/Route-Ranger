package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
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

    @Override
    public void start(Stage stage) {
        configure(stage);
    }
    private void configure(Stage stage) {
        stage.setTitle("Location Explorer");
        stage.setScene(new Scene(createRoot()));
        stage.setMinWidth(800);
        stage.setMinHeight(500);
        stage.show();
    }

    private Pane createRoot(){
        VBox root = new VBox();
        root.getChildren().addAll(
                new Label("First Address"),
                inputFirstAddress,
                new Label("Second Address"),
                inputSecondAddress,
                getDistanceButton,
                new Label("Distance"),
                distanceField);
        return root;
    }


}
