package imageviewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageDrawable implements Drawable {
    private final String name;
    private final BufferedImage bufferedImage;
    private Drawable next;
    private Drawable prev;
    private final int height;
    private final int width;

    public ImageDrawable(String name, Drawable next, Drawable prev) throws IOException {
        this.name = name;
        this.bufferedImage = ImageIO.read(new File(name));
        this.height = bufferedImage.getHeight();
        this.width = bufferedImage.getWidth();
        this.next = next;
        this.prev = prev;
    }

    public ImageDrawable(String name, Drawable next, Drawable prev, int newHeight, int newWidth) throws IOException {
        this.name = name;
        this.bufferedImage = ImageIO.read(new File(name));
        this.next = next;
        this.prev = prev;
        this.height = newHeight;
        this.width = newWidth;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public int width() {
        return this.width;
    }

    @Override
    public Drawable resize(int canvasHeight, int canvasWidth) {
        try {
            boolean imageIsGreaterThanCanvas = (height > canvasHeight) && (width > canvasWidth);
            if(!imageIsGreaterThanCanvas && height > canvasHeight || (imageIsGreaterThanCanvas && (height > width))) {
                int newWidth = (width * canvasHeight) / height;
                return new ImageDrawable(name, next, prev, canvasHeight, newWidth);
            }
            else if(imageIsGreaterThanCanvas || width > canvasWidth) {
                int newHeight = (height * canvasWidth) / width;
                return new ImageDrawable(name, next, prev, newHeight, canvasWidth);
            }
            else return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void setNext(Drawable next) {
        this.next = next;
    }

    public void setPrev(Drawable prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "ImageDrawable{" +
                "name='" + name + '\'' +
                ", bufferedImage=" + bufferedImage +
                ", next=" + (next != null ? next.name() : "null") +
                ", prev=" + (prev != null ? prev.name() : "null") +
                ", drawableHeight=" + height +
                ", drawableWidth=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDrawable that = (ImageDrawable) o;
        return height == that.height && width == that.width && Objects.equals(name, that.name) && Objects.equals(bufferedImage, that.bufferedImage) && Objects.equals(next, that.next) && Objects.equals(prev, that.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bufferedImage, next, prev, height, width);
    }
}
