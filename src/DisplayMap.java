import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class DisplayMap extends JPanel {
    private Graph<City> graph;
    private Collection<DisplayEdge> edges;

    public DisplayMap(Graph<City> graph){
        this.graph = graph;
        edges = new HashSet<>();
        Collection<City> edgedCities = new HashSet<>();
        graph.getNodeList().forEach(node -> {
            node.getEdges().forEach(edge -> {
                if(!edgedCities.contains(edge.getElement())){
                    edges.add(new DisplayEdge(node.getElement(), edge.getElement()));
                }
            });
            edgedCities.add(node.getElement());
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (DisplayEdge edge : edges) {
            edge.drawOn(g2);
        }
        for (City city : graph.getElementList()) {
            city.drawOn(g2);
        }
    }

    private static class DisplayEdge{
        private Color color;
        private City startCity;
        private City endCity;

        DisplayEdge(){
            this(null, null);
        }
        
        DisplayEdge(City startCity, City endCity){
            this(startCity, endCity, Color.BLACK);
        }
        
        DisplayEdge(City startCity, City endCity, Color color){
            this.startCity = startCity;
            this.endCity = endCity;
            this.color = color;
        }

        public void drawOn(Graphics2D g){
            g.setColor(color);
            g.draw(new Line2D.Double(startCity.getPosition()[0],startCity.getPosition()[1], endCity.getPosition()[0],endCity.getPosition()[1]));
        }
        
        public void changeColor(Color color){
            this.color = color;
        }
    }
}