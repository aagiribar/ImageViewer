package imageviewer.swing;

import imageviewer.Drawable;
import imageviewer.ImageDisplay;
import imageviewer.Presenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class SwingImageDisplay extends JFrame implements ImageDisplay {
    private final Presenter presenter;
    private final List<Order> orders = new ArrayList<>();
    private int offset = 0;
    private int firstX;
    private final Map<String, BufferedImage> imageCache = new HashMap<>();
    private final JFileChooser fc;

    public SwingImageDisplay(Presenter presenter) throws HeadlessException {
        setTitle("Image Viewer");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.fc = createFileChooser();
        this.presenter = presenter;
        addListeners();
        setVisible(true);
    }

    private JFileChooser createFileChooser() {
        JFileChooser fc = new JFileChooser();
        return fc;
    }

    private void addListeners() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                offset = firstX - currentX;
                if (abs(offset) > width()) offset = sing(offset) * width();
                presenter.dragged(() -> offset);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int returnVal = fc.showOpenDialog(SwingImageDisplay.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    presenter.imageOpened(selectedFile.getParent(), selectedFile.getName());
                }
                clearImageCache();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                firstX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                presenter.released(() -> offset);
                offset = 0;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                presenter.windowStateChanged();
            }
        });

    }

    private int sing(int i) {
        return i < 0 ? -1 : 1;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Order order : orders) {
            try {
                BufferedImage bufferedImage = getImageToDraw(order.drawable);
                g.drawImage(bufferedImage, order.position.x(), order.position.y(), order.drawable.width(), order.drawable.height(), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private BufferedImage getImageToDraw(Drawable drawable) throws IOException {
        if (!imageCache.containsKey(drawable.name())) {
            imageCache.put(drawable.name(), ImageIO.read(new File(drawable.name())));
        }
        return imageCache.get(drawable.name());
    }

    private void clearImageCache() {
        imageCache.clear();
    }

    @Override
    public int height() {
        return getHeight();
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public void clear() {
        orders.clear();
    }

    @Override
    public void paint(Drawable drawable, Drawable.Point position) {
        Drawable resizedImage;
        if(!drawable.resized()) {
            resizedImage = drawable.resize(getHeight(), getWidth());
        }
        else {
            resizedImage = drawable;
        }
        orders.add(new Order(resizedImage, position));
        repaint();
    }

    @Override
    public void paintOnCenter(Drawable drawable) {
        if (drawable != null) {
            Drawable resizedImage;
            if(!drawable.resized()) {
                resizedImage = drawable.resize(getHeight(), getWidth());
            }
            else {
                resizedImage = drawable;
            }
            orders.add(new Order(resizedImage, resizedImage.center(getHeight(), getWidth())));
        }
        repaint();
    }

    private record Order(Drawable drawable, Drawable.Point position) {}
}
