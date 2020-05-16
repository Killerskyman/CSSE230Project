import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui {
    private Graph<City> graph;
    private JFrame frame;
    
    public Gui(Graph<City> graph){
        this.graph = graph;
        frame = new JFrame();
        frame.setTitle("Midwestern Mappers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setBackground(Color.DARK_GRAY);
        JPanel sideBar = new JPanel();
        makeSideBar(sideBar);
        sideBar.setBackground(Color.DARK_GRAY);
        frame.add(sideBar, BorderLayout.EAST);
        JPanel mapPanel = new JPanel();
        
        frame.setVisible(true);
    }
    
    public void makeSideBar(JPanel panel){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new CityChoser(graph, "Starting City:"));
        
        makeSideBarDest(panel);
        
    }
    
    private ArrayList<CityChoser> destinations = new ArrayList<>();
    private JPanel destinationPanel;
    public void makeSideBarDest(JPanel panel){
        destinationPanel = new JPanel();
        CityChoser dest = new CityChoser(graph, "Destination 1");
        destinations.add(dest);
        destinationPanel.add(dest);
        JButton addDest = new JButton("+");
        addDest.addActionListener(e -> {
            CityChoser temp = new CityChoser(graph, "Destination "+(destinations.size()+1));
            destinationPanel.add(temp, BorderLayout.CENTER, destinations.size());
            destinations.add(temp);
            frame.repaint();
            frame.setVisible(true);
        });
        JButton delDest = new JButton("-");
        delDest.addActionListener(e -> {
            if(destinations.size() > 1) {
                CityChoser temp = destinations.remove(destinations.size() - 1);
                destinationPanel.remove(temp);
                frame.repaint();
                frame.setVisible(true);
            }
        });
        JPanel destinationButtonPanel = new JPanel();
        destinationButtonPanel.add(addDest);
        destinationButtonPanel.add(delDest);
        destinationPanel.add(destinationButtonPanel, BorderLayout.SOUTH);
        destinationPanel.setLayout(new BoxLayout(destinationPanel, BoxLayout.Y_AXIS));
        panel.add(destinationPanel);
    }
    
    private static class CityChoser extends JPanel{
        private static boolean updateCityList = true;
        private static ArrayList<City> cityList = new ArrayList<>();
        
        public CityChoser(Graph<City> graph){
            this(graph, "");
        }
        
        public CityChoser(Graph<City> graph, String displayName){
            JComboBox<City> startCity = new JComboBox<>();
            if(updateCityList) {
                cityList.addAll(graph.getCityList());
                updateCityList = false;
            }
            for(City city: cityList){
                startCity.addItem(city);
            }
            startCity.setBackground(Color.DARK_GRAY);
            startCity.setForeground(Color.gray);
            JLabel startCityLabel = new JLabel(displayName);
            startCityLabel.setForeground(Color.WHITE);
            this.add(startCityLabel, BorderLayout.WEST);
            this.add(startCity, BorderLayout.EAST);
            this.setBackground(Color.DARK_GRAY);
        }
    }
}
