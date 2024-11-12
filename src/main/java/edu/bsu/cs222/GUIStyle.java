package edu.bsu.cs222;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class GUIStyle {

    public ImageView configureBackgroundImage(Stage stage) throws IOException {
        ImageView backgroundImage = new ImageView(loadBackgroundImage());
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);
        backgroundImage.setCache(true);

        backgroundImage.fitWidthProperty().bind(stage.widthProperty());
        backgroundImage.fitHeightProperty().bind(stage.heightProperty());

        return backgroundImage;
    }

    private Image loadBackgroundImage() throws IOException {
        ImageHandler imageHandler = new ImageHandler();
        InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Background_Satellite.jpg");
        assert imageInputStream != null;
        return imageHandler.convertToFxImage(ImageIO.read(imageInputStream));
    }

}
