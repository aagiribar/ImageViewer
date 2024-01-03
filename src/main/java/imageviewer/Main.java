package imageviewer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DrawableLoader loader = new FileDrawableLoader(new String[]{"png", "jpeg"});
        Drawable image = loader.load("test_images");
        while (image.next() != null) {
            System.out.println(image.name());
            image = image.next();
        }
        while (image != null) {
            System.out.println(image.name());
            image = image.prev();
        }
    }
}
