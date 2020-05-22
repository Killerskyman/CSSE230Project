
public class Attraction implements Gui.Choosable {
	String name;
	String cityName;
	double entryfee;
	City city;
	
	public Attraction(String name, String cityName, double entryfee) {
		this.name = name;
		this.cityName = cityName;
		this.entryfee = entryfee;
	}
	
	public void setEntryFee(int newEntryFee) {
		entryfee = newEntryFee;
	}
	
	public double getEntryFee() {
		return entryfee;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getName() {
		return name;
	}
    
    @Override
    public City getCity() {
        return city;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
