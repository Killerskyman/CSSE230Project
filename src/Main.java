import java.util.*;

import javax.swing.JFrame;

public class Main {
    
	public HashMap<String, City> cities = new HashMap<String, City>();
	public Graph<City> graph = new Graph<City>();
	public int totalDistance;
	public int totalTime;
	private JFrame frame;
	
    public static void main(String[] args){
    	new Main();
    }
    
    public Main() {
    	defineCities();
    	startUpAddNodes();
    	startUpAddEdges();
    	this.frame = new JFrame("Map");
    	//this.compentent = new //COMPONENT CLASS NAME HERE
    }
    
	private void defineCities() {
		cities.put("Chicago", new City("Chicago"));
		cities.put("Peoria", new City("Peoria"));
		cities.put("Springfield", new City("Springfield"));
		cities.put("Bloomington", new City("Bloomington"));
		cities.put("Naperville", new City("Naperville"));
		cities.put("Decatur", new City("Decatur"));
		cities.put("Champaign", new City("Champaign"));
		cities.put("Fort Wayne", new City("Fort Wayne"));
		cities.put("Evansville", new City("Evansville"));
		cities.put("Terre Haute", new City("Terre Haute"));
		cities.put("Gary", new City("Gary"));
		cities.put("South Bend", new City("South Bend"));
		cities.put("Indianapolis", new City("Indianapolis"));
		cities.put("Lafayette", new City("Lafayette"));
		cities.put("Carmel", new City("Carmel"));
	}
	
	private void startUpAddNodes() {
		for(String key: cities.keySet()) {
			graph.addNode(cities.get(key));
		}
	}

	private void startUpAddEdges() {
		//assuming time cost is in minutes
		graph.addEdge(cities.get("Chicago"), cities.get("Peoria"), 161, 160);
		graph.addEdge(cities.get("Peoria"), cities.get("Springfield"), 74, 68);
		graph.addEdge(cities.get("Chicago"), cities.get("Bloomington"), 136, 138);
		graph.addEdge(cities.get("Peoria"), cities.get("Bloomington"), 39, 44);
		graph.addEdge(cities.get("Springfield"), cities.get("Bloomington"), 68, 67);
		graph.addEdge(cities.get("Chicago"), cities.get("Naperville"), 33, 37);
		graph.addEdge(cities.get("Peoria"), cities.get("Naperville"), 132, 143);
		graph.addEdge(cities.get("Bloomington"), cities.get("Naperville"), 111, 118);
		graph.addEdge(cities.get("Springfield"), cities.get("Decatur"), 40, 41);
		graph.addEdge(cities.get("Bloomington"), cities.get("Decatur"), 46, 54);
		graph.addEdge(cities.get("Chicago"), cities.get("Champaign"), 136, 129);
		graph.addEdge(cities.get("Bloomington"), cities.get("Champaign"), 51, 49);
		graph.addEdge(cities.get("Naperville"), cities.get("Champaign"), 145, 135);
		graph.addEdge(cities.get("Decatur"), cities.get("Champaign"), 49, 50);
		graph.addEdge(cities.get("Springfield"), cities.get("Evansville"), 230, 218);
		graph.addEdge(cities.get("Decatur"), cities.get("Evansville"), 208, 213);
		graph.addEdge(cities.get("Champaign"), cities.get("Evansville"), 190, 201);
		graph.addEdge(cities.get("Chicago"), cities.get("Terre Haute"), 180, 193);
		graph.addEdge(cities.get("Naperville"), cities.get("Terre Haute"), 200, 210);
		graph.addEdge(cities.get("Decatur"), cities.get("Terre Haute"), 99, 119);
		graph.addEdge(cities.get("Champaign"), cities.get("Terre Haute"), 92, 90);
		graph.addEdge(cities.get("Evansville"), cities.get("Terre Haute"), 111, 123);
		graph.addEdge(cities.get("Chicago"), cities.get("Gary"), 30, 35);
		graph.addEdge(cities.get("Peoria"), cities.get("Gary"), 160, 162);
		graph.addEdge(cities.get("Bloomington"), cities.get("Gary"), 140, 137);
		graph.addEdge(cities.get("Naperville"), cities.get("Gary"), 58, 60);
		graph.addEdge(cities.get("Champaign"), cities.get("Gary"), 135, 122);
		graph.addEdge(cities.get("Fort Wayne"), cities.get("Gary"), 133, 144);
		graph.addEdge(cities.get("Terre Haute"), cities.get("Gary"), 165, 159);
		graph.addEdge(cities.get("Fort Wayne"), cities.get("South Bend"), 90, 101);
		graph.addEdge(cities.get("Gary"), cities.get("South Bend"), 66, 67);
		graph.addEdge(cities.get("Champaign"), cities.get("Indianapolis"), 126, 116);
		graph.addEdge(cities.get("Fort Wayne"), cities.get("Indianapolis"), 121, 124);
		graph.addEdge(cities.get("Evansville"), cities.get("Indianapolis"), 172, 167);
		graph.addEdge(cities.get("Terre Haute"), cities.get("Indianapolis"), 77, 74);
		graph.addEdge(cities.get("South Bend"), cities.get("Indianapolis"), 139, 148);
		graph.addEdge(cities.get("Champaign"), cities.get("Lafayette"), 92, 95);
		graph.addEdge(cities.get("Fort Wayne"), cities.get("Lafayette"), 114, 128);
		graph.addEdge(cities.get("Terre Haute"), cities.get("Lafayette"), 95, 103);
		graph.addEdge(cities.get("Gary"), cities.get("Lafayette"), 92, 87);
		graph.addEdge(cities.get("South Bend"), cities.get("Lafayette"), 107, 118);
		graph.addEdge(cities.get("Indianapolis"), cities.get("Lafayette"), 63, 62);
		graph.addEdge(cities.get("Fort Wayne"), cities.get("Carmel"), 118, 113);
		graph.addEdge(cities.get("Gary"), cities.get("Carmel"), 151, 134);
		graph.addEdge(cities.get("South Bend"), cities.get("Carmel"), 124, 120);
		graph.addEdge(cities.get("Indianapolis"), cities.get("Carmel"), 16, 30);
		graph.addEdge(cities.get("Lafayette"), cities.get("Carmel"), 63, 61);
	}
	
	public Iterator<City> findRoute(String from, String to)
	{
		Graph<City>.routeDetails routeStuff = graph.Route(cities.get(from), cities.get(to));
		totalDistance = routeStuff.totalDistance;
		totalTime = routeStuff.totalTime;
		return routeStuff.route.iterator();
	}
	
}
