package imageviewer;

public interface Drawable {
    String name();
    int height();
    int width();

    Drawable resize(int canvasHeight, int canvasWidth);
    Point center(int canvasHeight, int canvasWidth);

    Drawable next();
    Drawable prev();

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
