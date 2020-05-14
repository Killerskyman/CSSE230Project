import java.util.HashMap;

import javax.swing.JFrame;

public class Main {
    
	public HashMap<String, City> cities = new HashMap<String, City>();
	public Graph<City> graph = new Graph<City>();;
	private JFrame frame;
	
    public static void main(String[] args){
    	new Main();
    }
    
    public Main() {
    	defineCities();
    	this.frame = new JFrame("Map");
    	//this.compentent = new //COMPONENT CLASS NAME HERE
    }

	private void defineCities() {
		cities.put("Chicago", new City("Chicago"));
		cities.put("Peoria", new City("Peoria"));
		cities.put("Springfield", new City("Springfield"));
		cities.put("Bloomington (IL)", new City("Bloomington (IL)"));
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
}
