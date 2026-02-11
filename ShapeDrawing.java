import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ShapeDrawing extends JComponent {

    private double zoom = 5;
    private BufferedImage image;


    public ShapeDrawing() {

        setPreferredSize(new Dimension(800, 800));

        // Add listener ONCE (not in paintComponent!)
        addMouseWheelListener(e -> {

            if (e.getPreciseWheelRotation() < 0) {
                zoom *= 0.99;
            } else {
                zoom *= 1.01;
            }

            renderImage();   // recompute image
            repaint();       // redraw component
        });

        renderImage(); // render first image
    }

    private void renderImage()
    {
        image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = image.createGraphics();
        BasicStroke stroke = new BasicStroke(1);
        for (int i = 0; i<800; i++){
            for (int j = 0; j<800; j++) {
                double[] coord = {(double)i*zoom/400 - zoom, zoom-(double)j*zoom/400};
                image.setRGB(i, j, Color.HSBtoRGB((float) (Math.atan2(Main.func(coord)[1], Main.func(coord)[0])/6.28), 1f, (float) (Main.sig(Main.rad(Main.func(coord)[0], Main.func(coord)[1])))));
            }
        }
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

