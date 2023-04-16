package sample;

/*
run a nearest neighbour on the passed in ArrayList
return the nearest neighbour result.
 */

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation; // ***
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation; // ***
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class Neighbour extends JFrame {


    public Neighbour(String title ,ArrayList<Point2D> point) {
        super(title);
        JFreeChart chart = createChart(createDataset(point));
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(500, 300));
        setContentPane(panel);
    }

    private XYDataset createDataset(ArrayList<Point2D> point) {
        XYSeries s1 = new XYSeries("S1");
        for(int i=0;i<point.size();i++){
            s1.add(point.get(i).getX(),point.get(i).getY());
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "2-opt", "X", "Y", dataset);
        chart.removeLegend();
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
        renderer.setSeriesFillPaint(0, Color.RED);
        renderer.setBasePaint(Color.RED);
        renderer.setSeriesPaint(0,Color.BLACK);
        renderer.setUseFillPaint(true);
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }




    protected static ArrayList<Point2D> nearest(ArrayList<Point2D> cities) {

        ArrayList<Point2D> result = new ArrayList<>(); //holds final result.
        Point2D currentCity = cities.remove(0); //set current city to first array item.
        Point2D closestCity = cities.get(0); //set closest city to new first array item.
        Point2D possible; //for holding possible city.
        double dist; //hold current node distance.

        result.add(currentCity);

        //outside loop to iterate through array
        while (cities.size() > 0) {

            dist = Double.MAX_VALUE; //reset dist to max.

            //inner loop checks distance between current city and possible.
            for (int count = 0; count < cities.size(); count++) {
                possible = cities.get(count);
                if (currentCity.distance(possible) < dist) {
                    dist = currentCity.distance(possible);
                    closestCity = possible;
                }
            }
            /*
            once inner loop finds closest node
            set current city to closest, remove closest from cities
            and add current city to result.
             */
            currentCity = closestCity;
            cities.remove(closestCity);
            result.add(currentCity);
        }

        JFrame app = new Neighbour("JFreeChart" , result);
        app.pack();
        app.setVisible(true);


        return result;


    }


}