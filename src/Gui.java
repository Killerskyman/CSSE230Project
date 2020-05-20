import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.util.ArrayList;

public class Gui {
    private Graph<City> graph;
    private JFrame frame;
    private DisplayMap dispMap;
    
    public Gui(Graph<City> graph){
        this.graph = graph;
        frame = new JFrame();
        dispMap = new DisplayMap(graph);
        frame.setTitle("Midwestern Mappers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        JPanel sideBar = new JPanel();
        makeSideBar(sideBar);
        frame.add(new JScrollPane(sideBar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.EAST);
        
        frame.add(dispMap, BorderLayout.CENTER);
        frame.repaint();
        frame.setVisible(true);
    }
    
    private boolean isDestinations = true;
    private CityChoser startCity;
    public void makeSideBar(JPanel panel){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        startCity = new CityChoser(graph, "Starting City:");
        panel.add(startCity);
        
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
        panelChanger.setMaximumSize(panelChanger.getPreferredSize());
        panel.add(panelChanger);
        panel.add(routePanel);
        JButton calcRoute = new JButton("Calculate Route");
        calcRoute.addActionListener(e->{
            dispMap.resetColors();
            if(isDestinations){
                City start = startCity.getSelectedCity();
                for(CityChoser choser : destinations) {
                    Graph<City>.routeDetails route = graph.Route(start, choser.getSelectedCity());
                    dispMap.colorRoute(route.route);
                    start = choser.getSelectedCity();
                }
            }
            frame.repaint();
            frame.setVisible(true);
        });
        panel.add(calcRoute, BorderLayout.SOUTH);
    }
    
    JTextField prefDistanceField;
    JTextField prefTimeField;
    private void makeSideBarFindRoute(JPanel panel) {
        panel.removeAll();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JPanel prefDist = new JPanel();
        JLabel prefDistLabel = new JLabel("Preferred Distance:");
        prefDistanceField = new JTextField();
        prefDistanceField.setColumns(6);
        prefDist.add(prefDistLabel);
        prefDist.add(prefDistanceField);
    
        JPanel prefTime = new JPanel();
        JLabel prefTimeLabel = new JLabel("Preferred Time:");
        prefTimeField = new JTextField();
        prefTimeField.setColumns(6);
        prefTime.add(prefTimeLabel);
        prefTime.add(prefTimeField);
        
        inputPanel.add(prefDist);
        inputPanel.add(prefTime);
        panel.add(inputPanel);
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
        private JComboBox<City> citySel;
        
        public CityChoser(Graph<City> graph){
            this(graph, "");
        }
        
        public CityChoser(Graph<City> graph, String displayName){
            citySel = new JComboBox<>();
            if(updateCityList) {
                cityList.addAll(graph.getElementList());
                updateCityList = false;
            }
            for(City city: cityList){
                citySel.addItem(city);
            }
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            JLabel startCityLabel = new JLabel(displayName);
            this.add(startCityLabel);
            this.add(citySel);
            this.setMaximumSize(this.getPreferredSize());
        }
        
        public City getSelectedCity(){
            return (City) citySel.getSelectedItem();
        }
    }
}
