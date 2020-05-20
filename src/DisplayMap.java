import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DisplayMap extends JPanel {
    private Graph<City> graph;
    private ArrayList<DisplayCity> cities = new ArrayList<>();
    private ArrayList<DisplayEdge> edges = new ArrayList<>();

    public DisplayMap(Graph<City> graph){
        this.graph = graph;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(getWidth()/1920.0, getHeight()/1080.0);
        for (DisplayEdge edge : edges) {
            edge.drawOn(g2);
        }
        for (DisplayCity city : cities) {
            city.drawOn(g2);
        }
    }

    private class DisplayCity{
        City city;
        private Color backGround;
        private Color foreGround;
        private Color border;
        private double x;
        private double y;
        private double size;
        private double borderSize;

        DisplayCity(City city, double x, double y, double size, Color backGround, Color foreGround, Color border, double borderSize){
            this.city = city;
            this.x = x;
            this.y = y;
            this.size = size;
            this.backGround = backGround;
            this.foreGround = foreGround;
            this.border = border;
            this. borderSize = borderSize;
        }

        DisplayCity(City city, double x, double y, double size, Color backGround, Color foreGround, Color border){
            this(city, x, y, size, backGround, foreGround, border, 2);
        }

        DisplayCity(City city, double x, double y, double size, Color backGround, Color foreGround){
            this(city, x, y, size, backGround, foreGround, Color.BLACK);
        }

        DisplayCity(City city, double x, double y, double size){
            this(city, x, y, size, Color.DARK_GRAY, Color.WHITE);
        }

        DisplayCity(City city, double x, double y){
            this(city, x, y, 40);
        }

        DisplayCity(City city){
            this(city, 0, 0);
        }

        public void drawOn(Graphics2D g){
            g.translate(x, y);
            g.setColor(border);
            g.fill(new Ellipse2D.Double(-size/2,-size/2, size, size));
            g.setColor(backGround);
            g.fill(new Ellipse2D.Double(-(size-borderSize)/2, -(size-borderSize)/2,size-borderSize, size-borderSize));
            g.setColor(foreGround);
            g.drawString(city.name, (int) (-SwingUtilities.computeStringWidth(getFontMetrics(g.getFont()), city.name)/2.0), (int)(-g.getFont().getSize()/2.0));
            g.translate(-x,-y);
        }
    }

    private class DisplayEdge{
        private Color color;
        private DisplayCity startCity;
        private DisplayCity endCity;
        private double width;

        DisplayEdge(){

        }

        public void drawOn(Graphics2D g){

        }
    }
}