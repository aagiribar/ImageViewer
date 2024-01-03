package imageviewer.swing;

import imageviewer.*;

import java.io.IOException;

public class SwingMain {
    public static void main(String[] args) throws IOException {
        DrawableLoader loader = new FileDrawableLoader(new String[] {"jpeg", "png", "jpg", "gif"});
        Presenter presenter = new ImagePresenter(loader);
        ImageDisplay display = new SwingImageDisplay(presenter);
        presenter.setDisplay(display);
    }
}
