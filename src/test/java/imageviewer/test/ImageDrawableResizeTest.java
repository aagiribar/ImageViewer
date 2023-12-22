package imageviewer.test;

import imageviewer.Drawable;
import imageviewer.ImageDrawable;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static imageviewer.test.ImageDrawableResizeTest.Cases.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class ImageDrawableResizeTest {

    @Test
    void should_return_same_image_when_canvas_is_greater_than_image() {
        Drawable result = testImage.resize(2000, 2000);
        assertThat(result).isEqualTo(testImage);
        assertThat((double) result.height() / result.width())
                .isEqualTo((double) testImage.height() / testImage.width(), withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(2000 * 2000);
    }

    @Test
    void should_return_image_with_correct_size_when_only_height_is_greater_than_canvas_height() {
        Drawable result = testImage.resize(800, 2000);
        assertThat(result.height()).isEqualTo(lessHeightResult.height());
        assertThat(result.width()).isEqualTo(lessHeightResult.width());
        assertThat((double) result.height() / result.width())
                .isEqualTo((double) testImage.height() / testImage.width(), withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(800 * 2000);
    }

    @Test
    void should_return_image_with_correct_size_when_only_width_is_greater_than_canvas_width() {
        Drawable result = testImage.resize(2000, 800);
        assertThat(result.height()).isEqualTo(lessWidthResult.height());
        assertThat(result.width()).isEqualTo(lessWidthResult.width());
        assertThat((double) result.height() / result.width())
                .isEqualTo((double) testImage.height() / testImage.width(), withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(2000 * 800);
    }

    @Test
    void should_return_image_with_correct_size_when_image_is_greater_than_canvas_and_image_is_taller() {
        Drawable result = testImage.resize(600, 800);
        assertThat(result.height()).isEqualTo(imageGraterThanCanvasGreaterHeightResult.height());
        assertThat(result.width()).isEqualTo(imageGraterThanCanvasGreaterHeightResult.width());
        assertThat((double) result.height() / result.width())
                .isEqualTo((double) testImage.height() / testImage.width(), withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(600 * 800);
    }

    @Test
    void should_return_image_with_correct_size_when_image_is_greater_than_canvas_and_image_is_wider() {
        Drawable result = imageGraterThanCanvasGreaterWidth.resize(600, 800);
        assertThat(result.height()).isEqualTo(imageGraterThanCanvasGreaterWidthResult.height());
        assertThat(result.width()).isEqualTo(imageGraterThanCanvasGreaterWidthResult.width());
        assertThat((double) result.height() / result.width())
                .isEqualTo((double) imageGraterThanCanvasGreaterWidth.height() / imageGraterThanCanvasGreaterWidth.width(), withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(800 * 600);
    }

    @Test
    void should_return_image_with_correct_size_when_image_is_a_square() {
        Drawable result = squareImage.resize(1200, 800);
        assertThat(result.height()).isEqualTo(800);
        assertThat(result.width()).isEqualTo(800);
        assertThat((double) result.height() / result.width()).isEqualTo(1.0, withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(1200 * 800);

        result = squareImage.resize(800, 1200);
        assertThat(result.height()).isEqualTo(800);
        assertThat(result.width()).isEqualTo(800);
        assertThat((double) result.height() / result.width()).isEqualTo(1.0, withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(1200 * 800);

        result = squareImage.resize(800, 800);
        assertThat(result.height()).isEqualTo(800);
        assertThat(result.width()).isEqualTo(800);
        assertThat((double) result.height() / result.width()).isEqualTo(1.0, withPrecision(1e-3));
        assertThat(result.height() * result.width()).isLessThanOrEqualTo(1200 * 800);
    }

    public static class Cases {
        public static final Drawable testImage;

        static {
            try {
                testImage = new ImageDrawable("test_images/first-game.jpeg", null, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable lessHeightResult;

        static {
            try {
                lessHeightResult = new ImageDrawable("test_images/first-game.jpeg", null, null, 800, 560);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable lessWidthResult;

        static {
            try {
                lessWidthResult = new ImageDrawable("test_images/first-game.jpeg", null, null, 1142, 800);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable imageGraterThanCanvasGreaterHeightResult;

        static {
            try {
                imageGraterThanCanvasGreaterHeightResult = new ImageDrawable("test_images/first-game.jpeg", null, null, 600, 420);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable imageGraterThanCanvasGreaterWidth;

        static {
            try {
                imageGraterThanCanvasGreaterWidth = new ImageDrawable("test_images/first-game.jpeg", null, null, 1000, 1428);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable imageGraterThanCanvasGreaterWidthResult;

        static {
            try {
                imageGraterThanCanvasGreaterWidthResult = new ImageDrawable("test_images/first-game.jpeg", null, null, 560, 800);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable squareImage;

        static {
            try {
                squareImage = new ImageDrawable("test_images/first-game.jpeg", null, null, 1000, 1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
