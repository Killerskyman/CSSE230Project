import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;

public class City implements Gui.Choosable {
	public String name;
	public double area;
	public int population;
	public HashMap<String, Attraction> attractions = new HashMap<String, Attraction>();
    
    private Color backGround;
    private Color foreGround;
    private Color border;
    private double x;
    private double y;
    private double size;
    private final double borderSize = 2;
    
    public City(String name, double area, int population, double x, double y, double size, Color backGround, Color foreGround, Color border){
        this.name = name;
        this.area = area;
        this.population = population;
        this.x = x;
        this.y = y;
        this.size = size;
        this.backGround = backGround;
        this.foreGround = foreGround;
        this.border = border;
    }
    
    public City(String name, double x, double y, double size, Color backGround, Color foreGround, Color border){
        this(name, 0, 0, x, y, size, backGround, foreGround, border);
    }
    
    public City(String name, double x, double y){
        this(name, 0, 0, x, y);
    }
    
    public City(String name, double area, int population, double x, double y){
        this(name, area, population, x, y, 40, Color.GRAY, Color.WHITE, Color.BLACK);
    }
	
	public City(String name) {
		this(name, 0, 0);
	}
	
	public void addAttraction(Attraction newAttraction) {
		String newAttractionName = newAttraction.getName();
		attractions.put(newAttractionName, newAttraction);
		newAttraction.city = this;
	}
	
	public void drawOn(Graphics2D g){
        g.translate(x, y);
        g.setColor(border);
        g.fill(new Ellipse2D.Double(-size/2, -size/2, size, size));
        g.setColor(backGround);
        g.fill(new Ellipse2D.Double(-(size-borderSize)/2, -(size-borderSize)/2,size-borderSize, size-borderSize));
        g.setColor(foreGround);
        g.drawString(name, (int) (-SwingUtilities.computeStringWidth(new JLabel("").getFontMetrics(g.getFont()), name)/2.0), (int)(g.getFont().getSize()/2.0));
        g.translate(-x,-y);
    }
    
    public void changeColor(Color border){
        this.border = border;
    }
	
	public HashMap<String, Attraction> getAttractions(){
		return attractions;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getArea() {
		return area;
	}
	
	public double getPopulation() {
		return population;
	}
	
	public double[] getPosition(){
        return new double[]{x,y};
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public City getCity() {
        return this;
    }
}
