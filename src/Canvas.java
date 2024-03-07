import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JFrame {

    private final JPanel panel;
    private final BufferedImage buffer;

    public Canvas() {
        setTitle("LineaDDA");
        setSize(600, 600);
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(600, 600);
        setLocationRelativeTo(null);
        buffer = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TYPE_INT_ARGB);
        add(panel);
    }

    public void paint(Graphics g) {
        super.paint(g);
        getGraphics().drawImage(buffer,0,0,panel);
        lineDDA(100, 100, 500, 500, Color.red);
    }

    public void lineDDA(int x1, int y1, int x2, int y2, Color a){
        getGraphics().drawImage(buffer,0,0,panel);
        int dy = y2 - y1;
        int dx = x2 - x1;
        double m = (double) dy /dx;
        System.out.println("M DDA: " + m);

        if (x1 > x2 || y1 > y2) {
            int tempX = x1;
            int tempY = y1;
            x1 = x2;
            y1 = y2;
            x2 = tempX;
            y2 = tempY;
        }

        if (Math.abs(m) <= 1){
            double y = y1;
            for(int x = x1; x <= x2; x++){
                putPixel(x, (int) y, a);
                y += m;
            }
        }else {
            double x = x1;
            for(int y = y1; y <= y2; y++){
                putPixel((int) x, y, a);
                x += (1/m);
            }
        }

    }
    private void putPixel(int x, int y, Color a){
        buffer.setRGB(x,y, a.getRGB());
    }
}