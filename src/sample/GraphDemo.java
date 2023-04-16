package sample;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GraphDemo extends JFrame {
    private ArrayList<Point2D> points2d;

    public GraphDemo(ArrayList<Point2D> points2d) {
        this.points2d = points2d;

        JFreeChart chart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        setContentPane(chartPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private XYDataset createDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = new double[2][points2d.size()];
        for (int i = 0; i < points2d.size(); i++) {
            data[0][i] = points2d.get(i).getX();
            data[1][i] = points2d.get(i).getY();
        }
        dataset.addSeries("Points", data);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Graph", "X Axis", "Y Axis", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
        renderer.setSeriesPaint(0, Color.blue);
        plot.setRenderer(renderer);
        return chart;
    }

    public static void main(String[] args) {
        ArrayList<Point2D> points2d = new ArrayList<>();
        points2d.add(new Point2D.Double(1, 2));
        points2d.add(new Point2D.Double(3, 4));
        points2d.add(new Point2D.Double(5, 6));
        new GraphDemo(points2d);
    }
}
