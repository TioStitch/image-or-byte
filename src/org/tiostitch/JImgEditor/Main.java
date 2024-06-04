package org.tiostitch.JImgEditor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;

public class Main {

    private Main() {

        try {

            final File file = new File("src/resources/image.png");

            byte[] buffered_result = Files.readAllBytes(file.toPath());
            ImageIcon imageIcon = new ImageIcon(buffered_result);

            BufferedImage bufferedImage = imageIconToBuffered(imageIcon);

            ImageIO.write(bufferedImage, "png", new File("src/resources/result.png"));

        } catch(IOException ignored) {}
    }

    private BufferedImage imageIconToBuffered(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }

    private byte[] getBytesFromFile(File image) {
        try {
            final BufferedImage bufferedImage = ImageIO.read(image);

            final WritableRaster writableRaster = bufferedImage.getRaster();
            final DataBufferByte dataBuffer = (DataBufferByte) writableRaster.getDataBuffer();

            return dataBuffer.getData();
        } catch (IOException e) {
            return new byte[2048];
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
