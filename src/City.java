import java.util.HashMap;

public class City {
	public String name;
	public double area;
	public double population;
	public HashMap<String, Attraction> attractions = new HashMap<String, Attraction>();
	
	public City(String name, double area, double population) {
		this.name = name;
		this.area = area;
		this.population = population;
	}
	
	public City(String name) {
		this.name = name;
	}
	
	public void addAttraction(Attraction newAttraction) {
		String newAttractionName = newAttraction.getName();
		attractions.put(newAttractionName, newAttraction);
	}
	
	public HashMap<String, Attraction> getAttractions(){
		return attractions;
	}
	
	public double getArea() {
		return area;
	}
	
	public double getPopulation() {
		return population;
	}

}
