package imageviewer;

import imageviewer.ImageDisplay.Dragged;
import imageviewer.ImageDisplay.Released;

public interface Presenter {
    void released(Released released);
    void dragged(Dragged dragged);
}
