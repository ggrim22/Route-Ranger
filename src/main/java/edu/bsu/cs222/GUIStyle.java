package edu.bsu.cs222;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import javax.imageio.ImageIO;
import java.awt.*;
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
