package imageviewer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileDrawableLoader implements DrawableLoader {
    private final File directory;
    private final String[] types;

    public FileDrawableLoader(String dir, String[] types) {
        this.directory = new File(dir);
        this.types = types;
    }

    @Override
    public Drawable load() {
        List<String> imagesPaths = imagesPathsIn(directory);
        Drawable first, prev = null, current;
        try {
            first = new ImageDrawable(imagesPaths.get(0), null, null);
            current = first;
            for (int i = 0; i < imagesPaths.size(); i++) {
                current.setPrev(prev);
                current.setNext(getNextImage(i, imagesPaths));
                prev = current;
                current = current.next();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return first;
    }

    private Drawable getNextImage(int i, List<String> imagesPaths) throws IOException {
        return (i + 1) < imagesPaths.size() ?
                new ImageDrawable(imagesPaths.get(i + 1), null, null) :
                null;
    }

    private List<String> imagesPathsIn(File directory) {
        return Stream.of(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getPath)
                .filter(this::isRequiredType)
                .toList();
    }

    private boolean isRequiredType(String path) {
        for (String type : types) {
            if (path.endsWith(type)) return true;
        }
        return false;
    }
}
