package edu.bsu.cs222;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class GUIStyle {

    public BackgroundImage configureBackgroundImage() throws IOException {
        return new BackgroundImage(
                loadBackgroundImage(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
    }

    private Image loadBackgroundImage() throws IOException {
        ImageHandler imageHandler = new ImageHandler();
        InputStream imageInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Background.jpg");
        assert imageInputStream != null;
        return imageHandler.convertToFxImage(ImageIO.read(imageInputStream));
    }

}
