package imageviewer;

public interface ImageDisplay {
    void clear();

    void paint(Drawable drawable, Drawable.Point position);
    void paintOnCenter(Drawable drawable);

    interface Released {
        int at(int offset);
    }

    interface Dragged {
        int on(int offset);
    }
}
