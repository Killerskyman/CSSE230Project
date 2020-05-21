import java.util.*;

import javax.swing.JFrame;

public class Main {

	public HashMap<String, City> cities = new HashMap<String, City>();
	public Graph<City> graph = new Graph<City>();
	public int totalDistance;
	public int totalTime;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		defineCities();
		startUpAddNodes();
		startUpAddEdges();
		Gui gui = new Gui(graph);
	}

	// TODO: make the city cords more appropriate for a 1080x1920 screen
	private void defineCities() {
		cities.put("Chicago", new City("Chicago", 120, 20));
		cities.put("Peoria", new City("Peoria", 20, 700));
		cities.put("Springfield", new City("Springfield", 10, 900));
		cities.put("Bloomington", new City("Bloomington", 40, 800));
		cities.put("Naperville", new City("Naperville", 90, 40));
		cities.put("Decatur", new City("Decatur", 50, 900));
		cities.put("Champaign", new City("Champaign", 80, 850));
		cities.put("Fort Wayne", new City("Fort Wayne", 340, 400));
		cities.put("Evansville", new City("Evansville", 140, 2000));
		cities.put("Terre Haute", new City("Terre Haute", 140, 1000));
		cities.put("Gary", new City("Gary", 140, 60));
		cities.put("South Bend", new City("South Bend", 240, 60));
		cities.put("Indianapolis", new City("Indianapolis", 240, 900));
		cities.put("Lafayette", new City("Lafayette", 190, 800));
		cities.put("Carmel", new City("Carmel", 240, 850));
		startUpAddAttractions();
	}

	private void startUpAddAttractions() {
		cities.get("Indianapolis").addAttraction(new Attraction("Monument Circle", "Indianapolis", 0));
		cities.get("Terre Haute").addAttraction(new Attraction("Candles Holocaust Museum", "Terre Haute", 5));
		cities.get("Fort Wayne").addAttraction(new Attraction("Fort Wayne Children’s Zoo", "Fort Wayne", 15));
		cities.get("Evansville").addAttraction(new Attraction("Dream Car Museum", "Evansville", 10));
		cities.get("Decatur").addAttraction(new Attraction("Children’s Museum of Illinois", "Decatur", 8));
		cities.get("Champaign").addAttraction(new Attraction("Orpheum Children’s Museum", "Champaign", 5));
		cities.get("Naperville").addAttraction(new Attraction("Naper Settlement", "Naperville", 12));
		cities.get("Chicago").addAttraction(new Attraction("Millenium Park", "Chicago", 0));
		cities.get("Peoria").addAttraction(new Attraction("Peoria Zoo", "Peoria", 10.5));
		cities.get("Springfield")
				.addAttraction(new Attraction("Lincoln Home National Historic Site", "Springfield", 0));
		cities.get("Bloomington")
				.addAttraction(new Attraction("McLean County Museum of History", "Bloomington", 5));
		cities.get("Carmel").addAttraction(new Attraction("Monon Community Center", "Carmel", 20));
		cities.get("Lafayette").addAttraction(new Attraction("Columbian Park Zoo", "Lafayette", 0));
		cities.get("Gary").addAttraction(new Attraction("Majestic Star Casino", "Gary", 0));
		cities.get("South Bend").addAttraction(new Attraction("Studebaker National Museum", "South Bend", 10));
		cities.get("Indianapolis").addAttraction(new Attraction("Indianapolis Zoo", "Indianapolis", 16.95));
		cities.get("Terre Haute").addAttraction(new Attraction("Deming Park", "Terre Haute", 0));
		cities.get("Fort Wayne").addAttraction(new Attraction("Embassy Theatre", "Fort Wayne", 5));
		cities.get("Evansville").addAttraction(new Attraction("Willard Library", "Evansville", 0));
		cities.get("Decatur").addAttraction(new Attraction("Scovill Zoo", "Decatur", 7.5));
		cities.get("Champaign").addAttraction(new Attraction("University Of Illinois Arboretum", "Champaign", 0));
		cities.get("Naperville").addAttraction(new Attraction("DuPage Children’s Museum", "Naperville", 13));
		cities.get("Chicago").addAttraction(new Attraction("Art Institute of Chicago", "Chicago", 25));
		cities.get("Peoria").addAttraction(new Attraction("Peoria Riverfront Museum", "Peoria", 11));
		cities.get("Springfield")
				.addAttraction(new Attraction("Abraham Lincoln Presidential Library and Museum", "Springfield", 15));
		cities.get("Bloomington")
				.addAttraction(new Attraction("David Davis Mansion State Historic Site", "Bloomington", 10));
		cities.get("Carmel")
				.addAttraction(new Attraction("Museum of Miniature Houses and Other Collections", "Carmel", 10));
		cities.get("Lafayette").addAttraction(new Attraction("Subaru Auto Plant", "Lafayette", 15));
		cities.get("Gary").addAttraction(new Attraction("Bellaboo’s Play and Discovery Center", "Gary", 8));
		cities.get("South Bend").addAttraction(new Attraction("Potawatomi Zoo", "South Bend", 11));
		cities.get("Indianapolis").addAttraction(new Attraction("Indianapolis Motor Speedway", "Indianapolis", 0));
		cities.get("Terre Haute").addAttraction(new Attraction("Indiana Theatre", "Terre Haute", 0.25));
		cities.get("Fort Wayne").addAttraction(new Attraction("Tincaps Baseball", "Fort Wayne", 30));
		cities.get("Evansville").addAttraction(new Attraction("Mesker Park Zoo & Botanic Garden", "Evansville", 9.5));
		cities.get("Decatur").addAttraction(new Attraction("Spitler Woods State Park", "Decatur", 0));
		cities.get("Champaign").addAttraction(new Attraction("Spurlock Museum", "Champaign", 0));
		cities.get("Naperville").addAttraction(new Attraction("First Division Museum", "Naperville", 5));
		cities.get("Chicago").addAttraction(new Attraction("Lincoln Park", "Chicago", 0));
		cities.get("Peoria").addAttraction(new Attraction("Peoria Playhouse Children’s Museum", "Peoria", 8.5));
		cities.get("Springfield").addAttraction(new Attraction("Lincoln Tomb and War Memorials", "Springfield", 0));
		cities.get("Bloomington").addAttraction(new Attraction("Prairie Aviation Museum", "Bloomington", 5));
		cities.get("Carmel").addAttraction(new Attraction("Coxhall Gardens", "Carmel", 0));
		cities.get("Lafayette")
				.addAttraction(new Attraction("Art Museum of Greater Lafayette", "Lafayette", 0));
		cities.get("Gary").addAttraction(new Attraction("Marquette Park", "Gary", 75));
		cities.get("South Bend").addAttraction(new Attraction("South Bend Museum of Art", "South Bend", 15));
		cities.get("Indianapolis").addAttraction(new Attraction("Indiana State Museum", "Indianapolis", 16));
		cities.get("Terre Haute").addAttraction(new Attraction("Clabber Girl Museum", "Terre Haute", 0));
		cities.get("Fort Wayne").addAttraction(new Attraction("Lakeside Park & Rose Garden", "Fort Wayne", 0));
		cities.get("Evansville")
				.addAttraction(new Attraction("Evansville Museum of Arts, History & Science", "Evansville", 12));
		cities.get("Decatur").addAttraction(new Attraction("Lincoln Trail Homestead", "Decatur", 0));
		cities.get("Champaign").addAttraction(new Attraction("Virginia Theatre", "Champaign", 10));
		cities.get("Naperville").addAttraction(new Attraction("Centennial Beach", "Naperville", 14));
		cities.get("Chicago").addAttraction(new Attraction("Wrigley Field", "Chicago", 38));
		cities.get("Peoria").addAttraction(new Attraction("Luthy Botanical Garden", "Peoria", 0));
		cities.get("Springfield").addAttraction(new Attraction("Illinois State Capital", "Springfield", 0));
		cities.get("Bloomington").addAttraction(new Attraction("Pepsi Ice Center", "Bloomington", 10));
		cities.get("Carmel").addAttraction(new Attraction("Conner Prairie", "Carmel", 18));
		cities.get("Lafayette").addAttraction(new Attraction("Imagination Station", "Lafayette", 13));
		cities.get("Gary")
				.addAttraction(new Attraction("The Paul H. Douglas Center for Environmental Education", "Gary", 0));
		cities.get("South Bend")
				.addAttraction(new Attraction("Four Winds Casino South Bend", "South Bend", 20));
		cities.get("Indianapolis").addAttraction(new Attraction("Lucas Oil Stadium", "Indianapolis", 10));
		cities.get("Terre Haute").addAttraction(new Attraction("Swope Art Museum", "Terre Haute", 0));
		cities.get("Fort Wayne").addAttraction(new Attraction("Castle Gallery", "Fort Wayne", 5));
		cities.get("Evansville").addAttraction(new Attraction("USS LST Ship Memorial", "Evansville", 15));
		cities.get("Chicago").addAttraction(new Attraction("Field Museum", "Chicago", 23));
	}

	private void startUpAddNodes() {
		for (String key : cities.keySet()) {
			graph.addNode(cities.get(key));
		}
	}

	private void startUpAddEdges() {
		// assuming time cost is in minutes
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
}
