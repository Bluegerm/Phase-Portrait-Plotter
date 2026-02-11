import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

class ShapeDrawing extends JComponent {

    private double zoom = 5;
    private BufferedImage image;


    public ShapeDrawing() {

        setPreferredSize(new Dimension(800, 800));
        addMouseWheelListener(e -> {
            if (e.getPreciseWheelRotation() < 0) {
                zoom *= 0.9;
            } else {
                zoom *= 1.1;
            }
            renderImage();
            repaint();
        });
        renderImage();
    }

    private void renderImage()
    {
        image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        BasicStroke stroke = new BasicStroke(1);
        double step = zoom / 400.0;
        double startX = -zoom;
        double startY = zoom;
        IntStream.range(0, 800).parallel().forEach(i -> {
            for (int j = 0; j<800; j++) {
                double x = startX + i * step;
                double y = startY - j * step;
                double[] coord = {x, y};
                double[] value = Main.func(coord);
                double re = value[0];
                double im = value[1];

                double angle = Math.atan2(im, re);
                double radius = Main.rad(re, im);

                image.setRGB(i, j, Color.HSBtoRGB((float) (angle / (2 * Math.PI)), 1f, (float) (Main.sig(radius))));
            }
        });
        for(int i = (int) (-1*Math.round(zoom)); i<=Math.round(zoom); i+=(int)(Math.ceil(zoom/5))) {
            String t = String.valueOf(i);
            g2.setColor(Color.WHITE);
            g2.setStroke(stroke);
            g2.drawString(t+"i", 405, (int) (400 - 400/zoom*i));
            g2.drawString(t, (int) (400/zoom*i + 400), 415);
        }
        g2.drawLine(0, 400, 800, 400);
        g2.drawLine(400, 0, 400, 800);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            renderImage();
        }

        g.drawImage(image, 0, 0, null);
    }
}
