package imageviewer.swing;

import imageviewer.*;

import java.io.IOException;

public class SwingMain {
    public static void main(String[] args) throws IOException {
        DrawableLoader loader = new FileDrawableLoader("test_images", new String[] {"jpeg"});
        Drawable firstImage = loader.load();
        Presenter presenter = new ImagePresenter(firstImage);
        ImageDisplay display = new SwingImageDisplay(presenter);
        presenter.setDisplay(display);
    }
}
