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
        frame.add(new JScrollPane(sideBar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);
        JPanel mapPanel = new JPanel();
        
        frame.setVisible(true);
    }
    
    private boolean isDestinations = true;
    public void makeSideBar(JPanel panel){
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new CityChoser(graph, "Starting City:"));
        
        JPanel routePanel = new JPanel();
        JPanel panelChanger = new JPanel();
        JButton toggleRoute = new JButton("Toggle Route Method");
        toggleRoute.addActionListener(e -> {
            if(isDestinations){
                makeSideBarFindRoute(routePanel);
            }else{
                makeSideBarDest(routePanel);
            }
            isDestinations = !isDestinations;
            frame.repaint();
            frame.setVisible(true);
        });
        makeSideBarDest(routePanel);
        panelChanger.add(toggleRoute);
        panel.add(panelChanger);
        panel.add(routePanel);
    }
    
    private void makeSideBarFindRoute(JPanel panel) {
        panel.removeAll();
    }
    
    private ArrayList<CityChoser> destinations;
    private JPanel destinationPanel;
    public void makeSideBarDest(JPanel panel){
        panel.removeAll();
        destinationPanel = new JPanel();
        destinations = new ArrayList<>();
        destinationPanel.add(new JLabel("Destinations:"));
        CityChoser dest = new CityChoser(graph, "Destination 1");
        destinations.add(dest);
        destinationPanel.add(dest);
        JButton addDest = new JButton("+");
        addDest.addActionListener(e -> {
            CityChoser temp = new CityChoser(graph, "Destination "+(destinations.size()+1));
            destinationPanel.add(temp, BorderLayout.CENTER, destinations.size()+1);
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
            this.setMaximumSize(this.getPreferredSize());
        }
    }
}
