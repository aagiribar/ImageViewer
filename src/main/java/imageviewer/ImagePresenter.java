package imageviewer;

public class ImagePresenter implements Presenter {
    private ImageDisplay display;
    private Drawable currentImage;
    private final DrawableLoader loader;

    public ImagePresenter(DrawableLoader loader) {
        this.loader = loader;
    }

    @Override
    public void setDisplay(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void released(ImageDisplay.Released released) {
        if (currentImage == null) return;
        int threshold = display.width() / 3;
        int offset = released.at();

        if (offset > threshold) {
            currentImage = getNextImageOf(currentImage);
        }
        else if (offset < -threshold) {
            currentImage = getPrevImageOf(currentImage);
        }

        display.clear();
        display.paintOnCenter(currentImage);
    }

    @Override
    public void imageOpened(String filePath, String fileName) {
        this.currentImage = searchImageByName(loader.load(filePath), fileName);

        display.clear();
        display.paintOnCenter(currentImage);
    }

    private Drawable searchImageByName(Drawable drawable, String fileName) {
        Drawable current = drawable;
        while (current != null) {
            if (current.name().endsWith(fileName)) return current;
            current = current.next();
        }
        return drawable;
    }

    private Drawable getPrevImageOf(Drawable image) {
        return image.prev() != null ? image.prev(): image;
    }

    private Drawable getNextImageOf(Drawable image) {
        return image.next() != null ? image.next() : image;
    }

    @Override
    public void dragged(ImageDisplay.Dragged dragged) {
        int offset = dragged.on();

        if (currentImage == null) return;
        display.clear();
        Drawable currentImageResized = currentImage.resize(display.height(), display.width());
        Drawable.Point currentImageCenter = currentImageResized.center(display.height(), display.width());
        display.paint(currentImage, new Drawable.Point(currentImageCenter.x() - dragged.on(), currentImageCenter.y()));

        Drawable secondImage;
        if (offset > 0) {
            secondImage = currentImage.next();
        }
        else {
            secondImage = currentImage.prev();
        }

        if (secondImage == null) return;
        Drawable secondImageResized = secondImage.resize(display.height(), display.width());
        Drawable.Point secondImageCenter = secondImageResized.center(display.height(), display.width());
        display.paint(secondImageResized, new Drawable.Point(
                getXPositionOf(offset, dragged.on(), secondImageResized.width()),
                secondImageCenter.y()));
    }

    @Override
    public void windowStateChanged() {
        display.clear();
        display.paintOnCenter(currentImage);
    }

    private int getXPositionOf(int offset, int draggedOn, int secondImageWidth) {
        return (offset > 0) ? (display.width() - draggedOn) : (-secondImageWidth - draggedOn);
    }
}
