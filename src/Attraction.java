
public class Attraction {
	String name;
	String city;
	double entryfee;
	
	public Attraction(String name, String city, double entryfee) {
		this.name = name;
		this.city = city;
		this.entryfee = entryfee;
	}
	
	public void setEntryFee(int newEntryFee) {
		entryfee = newEntryFee;
	}
	
	public double getEntryFee() {
		return entryfee;
	}
	
	public String getCityName() {
		return city;
	}
	
	public String getName() {
		return name;
	}
}
