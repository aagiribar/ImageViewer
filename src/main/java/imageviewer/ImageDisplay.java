package imageviewer;

public interface ImageDisplay {
    int height();
    int width();

    void clear();

    void paint(Drawable drawable, Drawable.Point position);
    void paintOnCenter(Drawable drawable);

    interface Released {
        int at();
    }

    interface Dragged {
        int on();
    }
}
