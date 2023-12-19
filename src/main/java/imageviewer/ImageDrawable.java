package imageviewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDrawable implements Drawable {
    private final String name;
    private final BufferedImage bufferedImage;
    private final Drawable next;
    private final Drawable prev;

    public ImageDrawable(String name, Drawable next, Drawable prev) throws IOException {
        this.name = name;
        bufferedImage = ImageIO.read(new File(name));
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int height() {
        return bufferedImage.getHeight();
    }

    @Override
    public int width() {
        return bufferedImage.getWidth();
    }

    @Override
    public Drawable resize(int canvasHeight, int canvasWidth) {
        return null;
    }

    @Override
    public Point center(int canvasHeight, int canvasWidth) {
        return null;
    }

    @Override
    public Drawable next() {
        return this.next;
    }

    @Override
    public Drawable prev() {
        return this.prev;
    }
}
