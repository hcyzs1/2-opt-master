package sample;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LineGraphExample {

    public static void main(String[] args) {

        // Create a JFrame object
        JFrame frame = new JFrame("Line Graph Example");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel object
        JPanel panel = new JPanel() {

            // Define the data points for the line graph
            ArrayList<Integer> data = new ArrayList<Integer>() {
                {
                    add(50);
                    add(100);
                    add(75);
                    add(120);
                    add(90);
                }
            };

            // Override the paintComponent method to draw the line graph
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                // Set anti-aliasing for smooth rendering
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set the background color
                g2d.setBackground(Color.WHITE);

                // Set the stroke for the line graph
                g2d.setStroke(new BasicStroke(2));

                // Set the color for the line graph
                g2d.setColor(Color.BLUE);

                // Draw the line graph
                for (int i = 0; i < data.size() - 1; i++) {
                    int x1 = 50 + i * 75;
                    int y1 = 400 - data.get(i) * 3;
                    int x2 = 50 + (i + 1) * 75;
                    int y2 = 400 - data.get(i + 1) * 3;
                    g2d.drawLine(x1, y1, x2, y2);
                }
            }
        };

        // Add the JPanel object to the JFrame object
        frame.add(panel);

        // Make the JFrame object visible
        frame.setVisible(true);

    }

}
