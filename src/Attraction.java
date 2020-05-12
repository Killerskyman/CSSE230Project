
public class Attraction {
	String name;
	String city;
	int entryfee;
	
	public Attraction(String name, String city, int entryfee) {
		this.name = name;
		this.city = city;
		this.entryfee = entryfee;
	}
	
	public void setEntryFee(int newEntryFee) {
		entryfee = newEntryFee;
	}

	public int getEntryFee() {
		return entryfee;
	}
	
	public String getCityName() {
		return city;
	}
	
	public String getName() {
		return name;
	}
}
