import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

public class DisplayMap extends JPanel {
    private Graph<City> graph;
    private HashMap<String, DisplayEdge> edges;

    public DisplayMap(Graph<City> graph){
        this.graph = graph;
        edges = new HashMap<>();
        Collection<City> edgedCities = new HashSet<>();
        graph.getNodeList().forEach(node -> {
            node.getEdges().forEach(edge -> {
                if(!edgedCities.contains(edge.getElement())){
                    DisplayEdge newEdge = new DisplayEdge(node.getElement(), edge.getElement());
                    edges.put(node.getElement().getName() + " " + edge.getElement().getName(), newEdge);
                    edges.put(edge.getElement().getName() + " " + node.getElement().getName(), newEdge);
                }
            });
            edgedCities.add(node.getElement());
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (DisplayEdge edge : edges.values()) {
            edge.drawOn(g2);
        }
        for (City city : graph.getElementList()) {
            city.drawOn(g2);
        }
    }
    
    public void colorRoute(Collection<City> route){
    	City[] routeArr = route.toArray(new City[0]);
        for(int i = 0; i < routeArr.length-1; i++) {
            City start = routeArr[i];
            City end = routeArr[i+1];
            start.changeColor(Color.RED);
            if (edges.get(start.name + " " + end.name) != null) {
            	edges.get(start.name + " " + end.name).changeColor(Color.RED);
            }
        }
        routeArr[routeArr.length-1].changeColor(Color.RED);
    }
    
    public void resetColors(){
        graph.getElementList().forEach(city -> city.changeColor(Color.BLACK));
        edges.values().forEach(edge -> edge.changeColor(Color.BLACK));
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