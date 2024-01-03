package imageviewer;

import imageviewer.ImageDisplay.Dragged;
import imageviewer.ImageDisplay.Released;

public interface Presenter {
    void setDisplay(ImageDisplay display);

    void released(Released released);
    void dragged(Dragged dragged);
    void windowStateChanged();
    void imageOpened(String filePath, String fileName);
}
