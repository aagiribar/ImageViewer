package imageviewer;

public interface Drawable {
    String name();
    int height();
    int width();

    Drawable resize(int canvasHeight, int canvasWidth);
    Point center(int canvasHeight, int canvasWidth);

    Drawable next();
    Drawable prev();

    void setNext(Drawable next);
    void setPrev(Drawable prev);

    record Point(int x, int y) {}
}
