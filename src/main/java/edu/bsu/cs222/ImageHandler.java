package edu.bsu.cs222;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class ImageHandler {
    public Image accessImage(URLConnection connection) throws IOException {
        AccessAPI access = new AccessAPI();
        InputStream inputStream = access.getInputStream(connection);
        return ImageIO.read(inputStream);
    }
}
