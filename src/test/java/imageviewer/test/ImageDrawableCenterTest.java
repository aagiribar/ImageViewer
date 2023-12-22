package imageviewer.test;

import imageviewer.Drawable;
import imageviewer.ImageDrawable;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static imageviewer.Drawable.Point;
import static imageviewer.test.ImageDrawableCenterTest.Cases.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageDrawableCenterTest {

    @Test
    void should_return_correct_point_when_canvas_is_wider_and_image_is_higher() {
        Point result = imageTestHigher.center(2000, 2400);
        assertThat(result).isEqualTo(pointResultCanvasWiderImageHigher);
    }

    @Test
    void should_return_correct_point_when_canvas_is_higher_and_image_is_higher() {
        Point result = imageTestHigher.center(2400, 2000);
        assertThat(result).isEqualTo(pointResultCanvasHigherImageHigher);
    }

    @Test
    void should_return_correct_point_when_canvas_is_a_square_and_image_is_higher() {
        Point result = imageTestHigher.center(2000, 2000);
        assertThat(result).isEqualTo(pointResultCanvasSquareImageHigher);
    }

    @Test
    void should_return_correct_point_when_canvas_is_wider_and_image_is_wider() {
        Point result = imageTestWider.center(2000, 2400);
        assertThat(result).isEqualTo(pointResultCanvasWiderImageWider);
    }

    @Test
    void should_return_correct_point_when_canvas_is_higher_and_image_is_wider() {
        Point result = imageTestWider.center(2400, 2000);
        assertThat(result).isEqualTo(pointResultCanvasHigherImageWider);
    }

    @Test
    void should_return_correct_point_when_canvas_is_a_square_and_image_is_wider() {
        Point result = imageTestWider.center(2000, 2000);
        assertThat(result).isEqualTo(pointResultCanvasSquareImageWider);
    }

    @Test
    void should_return_correct_point_when_canvas_is_wider_and_image_is_square() {
        Point result = imageTestSquare.center(2000, 2400);
        assertThat(result).isEqualTo(pointResultCanvasWiderImageSquare);
    }

    @Test
    void should_return_correct_point_when_canvas_is_higher_and_image_is_square() {
        Point result = imageTestSquare.center(2400, 2000);
        assertThat(result).isEqualTo(pointResultCanvasHigherImageSquare);
    }

    @Test
    void should_return_correct_point_when_canvas_is_a_square_and_image_is_square() {
        Point result = imageTestSquare.center(2000, 2000);
        assertThat(result).isEqualTo(pointResultCanvasSquareImageSquare);
    }

    public static class Cases {
        public static final Drawable imageTestHigher;

        static {
            try {
                imageTestHigher = new ImageDrawable("test_images/first-game.jpeg", null, null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable imageTestWider;

        static {
            try {
                imageTestWider = new ImageDrawable("test_images/first-game.jpeg", null, null, 1000, 1428);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Drawable imageTestSquare;

        static {
            try {
                imageTestSquare = new ImageDrawable("test_images/first-game.jpeg", null, null, 1000, 1000);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static final Point pointResultCanvasWiderImageHigher = new Point(700, 286);
        public static final Point pointResultCanvasHigherImageHigher = new Point(500, 486);
        public static final Point pointResultCanvasSquareImageHigher = new Point(500, 286);

        public static final Point pointResultCanvasWiderImageWider = new Point(486, 500);
        public static final Point pointResultCanvasHigherImageWider = new Point(286, 700);
        public static final Point pointResultCanvasSquareImageWider = new Point(286, 500);

        public static final Point pointResultCanvasWiderImageSquare = new Point(700, 500);
        public static final Point pointResultCanvasHigherImageSquare = new Point(500, 700);
        public static final Point pointResultCanvasSquareImageSquare = new Point(500, 500);
    }
}

