package imageviewer.swing;

import imageviewer.*;

import java.io.IOException;

public class SwingMain {
    public static void main(String[] args) throws IOException {
        ImageDisplay display = new SwingImageDisplay(null);
        DrawableLoader loader = new FileDrawableLoader("test_images", new String[]{"jpeg"});
        Drawable image = loader.load();
        display.paintOnCenter(image);
        display.clear();
        display.paintOnCenter(image.next());
    }
}
