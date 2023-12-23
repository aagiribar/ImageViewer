package imageviewer.swing;

import imageviewer.Drawable;
import imageviewer.ImageDisplay;
import imageviewer.Presenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JFrame implements ImageDisplay {
    private final Presenter presenter;
    private final List<Order> orders = new ArrayList<>();
    private int offset = 0;
    private int lastX;

    public SwingImageDisplay(Presenter presenter) throws HeadlessException {
        setTitle("Image Viewer");
        setSize(1400, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.presenter = presenter;
        addListeners();
        setVisible(true);
    }

    private void addListeners() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                if (currentX < lastX) offset -= currentX;
                else offset += currentX;
                presenter.dragged(offset -> offset);
                lastX = currentX;
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                presenter.released(offset -> offset);
                offset = 0;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Order order : orders) {
            try {
                BufferedImage bufferedImage = ImageIO.read(new File(order.drawable.name()));
                g.drawImage(bufferedImage, order.position.x(), order.position.y(), order.drawable.width(), order.drawable.height(), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void clear() {
        orders.clear();
    }

    @Override
    public void paint(Drawable drawable, Drawable.Point position) {
        Drawable resizedImage = drawable.resize(getHeight(), getWidth());
        orders.add(new Order(resizedImage, position));
        repaint();
    }

    @Override
    public void paintOnCenter(Drawable drawable) {
        Drawable resizedImage = drawable.resize(getHeight(), getWidth());
        orders.add(new Order(resizedImage, resizedImage.center(getHeight(), getWidth())));
        repaint();
    }

    private record Order(Drawable drawable, Drawable.Point position) {}
}
