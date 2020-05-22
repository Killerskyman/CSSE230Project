import java.util.*;

public class Graph<T> {
	private Hashtable<T, Node> nodes;
	public enum Mode {DISTANCE, TIME}

	public Graph(){
		nodes = new Hashtable<T, Node>();
	}
 
	//implement me please
    public ArrayList<T> getElementList() {
        ArrayList<T> ret = new ArrayList<>();
        getNodeList().forEach(node -> ret.add(node.getElement()));
        return ret;
    }
    
    public Collection<Node> getNodeList(){
	    return nodes.values();
    }
    
    public class Node {
		private T element;
		private ArrayList<Edge> neighbors;
		
		public Node(T e){
			element = e;
			neighbors = new ArrayList<Edge>();
		}
		
		public void addEdge(T e, int distCost, int timeCost) {
			Node otherNode = nodes.get(e);
			neighbors.add(new Edge(otherNode, distCost, timeCost));
		}
		
		public ArrayList<Edge> getEdges()
		{
			return neighbors;
		}
		
		public T getElement()
		{
			return element;
		}
		
	}
	
	public class Edge {
		private Node otherNode;
		private int distCost;
		private int timeCost;
		
		public Edge(Node n, int d, int t){
			otherNode = n;
			distCost = d;
			timeCost = t;
		}
		
		public T getElement()
		{
			return otherNode.getElement();
		}
		
		public int getDistCost()
		{
			return distCost;
		}
		
		public int getTimeCost()
		{
			return timeCost;
		}
		
	}
	
	public class TempClass
	{
		private int totDistCost;
		private int totTimeCost;
		private T prevNode;
		
		public TempClass()
		{
			totDistCost = 100000;
			totTimeCost = 100000;
		}
		
		public int getTotDistCost()
		{
			return totDistCost;
		}
		
		public void setTotDistCost(int n)
		{
			totDistCost = n;
		}
		
		public int getTotTimeCost()
		{
			return totTimeCost;
		}
		
		public void setTotTimeCost(int n)
		{
			totTimeCost = n;
		}
		
		public T getPrevNode()
		{
			return prevNode;
		}
		
		public void setPrevNode(T n)
		{
			prevNode = n;
		}
	}
	
	public class RouteDetails
	{
		public int totalDistance;
		public int totalTime;
		public ArrayList<T> route;
	}
    
	public boolean addNode(T e) {
		nodes.put(e, new Node(e));
		return true;
	}
	
	public boolean addEdge(T e1, T e2, int distCost, int timeCost) {
		if (!nodes.containsKey(e1) && !nodes.containsKey(e2)) return false;
		nodes.get(e1).addEdge(e2, distCost, timeCost);
		nodes.get(e2).addEdge(e1, distCost, timeCost);
	    return true;
	}
	
	public Hashtable<T, TempClass> Dijkstras(T e)
	{
		Hashtable<T, TempClass> vertices = new Hashtable<T, TempClass>();
		ArrayList<T> UnVisited = new ArrayList<T>();
		ArrayList<T> Visited = new ArrayList<T>();
		Set<T> set = nodes.keySet();
		Iterator<T> i = set.iterator();
		while (i.hasNext())
		{
			T n = i.next();
			vertices.put(n, new TempClass());
			UnVisited.add(n);
		}
		vertices.get(e).setTotDistCost(0);
		vertices.get(e).setPrevNode(e);
		
		while (!UnVisited.isEmpty())
		{
			T minVert = e;
			int minCost = 10000000;
			for (T n: UnVisited)
			{
				TempClass temp = vertices.get(n);
				int tempCost = temp.getTotDistCost();
				if (tempCost < minCost)
				{
					minCost = tempCost;
					minVert = n;
				}
			}
			
			TempClass temp = vertices.get(minVert);
			int prevCostDist = temp.getTotDistCost();
			int prevCostTime = temp.getTotTimeCost();
			for (Edge edge: nodes.get(minVert).getEdges())
			{
				T neigh = edge.getElement();
				if (!Visited.contains(neigh))
				{
					int tempVal = prevCostDist + edge.getDistCost();
					int tempVal2 = prevCostTime + edge.getTimeCost();
					if (tempVal < vertices.get(neigh).getTotDistCost())
					{
						vertices.get(neigh).setTotDistCost(tempVal);
						vertices.get(neigh).setTotTimeCost(tempVal2);
						vertices.get(neigh).setPrevNode(minVert);
					}
				}
			}
			
			Visited.add(minVert);
			UnVisited.remove(minVert);
		}
		return vertices;
	}
	
	public Hashtable<T, TempClass> timeDijkstras(T e)
	{
		Hashtable<T, TempClass> vertices = new Hashtable<T, TempClass>();
		ArrayList<T> UnVisited = new ArrayList<T>();
		ArrayList<T> Visited = new ArrayList<T>();
		Set<T> set = nodes.keySet();
		Iterator<T> i = set.iterator();
		while (i.hasNext())
		{
			T n = i.next();
			vertices.put(n, new TempClass());
			UnVisited.add(n);
		}
		vertices.get(e).setTotTimeCost(0);
		vertices.get(e).setPrevNode(e);
		
		while (!UnVisited.isEmpty())
		{
			T minVert = e;
			int minCost = 10000000;
			for (T n: UnVisited)
			{
				TempClass temp = vertices.get(n);
				int tempCost = temp.getTotTimeCost();
				if (tempCost < minCost)
				{
					minCost = tempCost;
					minVert = n;
				}
			}
			
			TempClass temp = vertices.get(minVert);
			int prevCostDist = temp.getTotDistCost();
			int prevCostTime = temp.getTotTimeCost();
			for (Edge edge: nodes.get(minVert).getEdges())
			{
				T neigh = edge.getElement();
				if (!Visited.contains(neigh))
				{
					int tempVal = prevCostDist + edge.getDistCost();
					int tempVal2 = prevCostTime + edge.getTimeCost();
					if (tempVal2 < vertices.get(neigh).getTotTimeCost())
					{
						vertices.get(neigh).setTotDistCost(tempVal);
						vertices.get(neigh).setTotTimeCost(tempVal2);
						vertices.get(neigh).setPrevNode(minVert);
					}
				}
			}
			
			Visited.add(minVert);
			UnVisited.remove(minVert);
		}

		return vertices;
	}
	
	public RouteDetails Route(T start, T end, Mode type) {
		if (type == Mode.DISTANCE) {
			return distanceRoute(start,end);
		} else {
			return timeRoute(start, end);
		}
	}
	
	public RouteDetails distanceRoute(T start, T end)
	{
		RouteDetails routeStuff = new RouteDetails();
		Hashtable<T, TempClass> vertices;
		vertices = Dijkstras(start);
		T n = end;
		ArrayList<T> route = new ArrayList<T>();
		while (!vertices.get(n).getPrevNode().equals(n))
		{
			route.add(n);
			n = vertices.get(n).getPrevNode();
		}
        routeStuff.route = route;
        routeStuff.totalDistance = vertices.get(end).getTotDistCost();
        routeStuff.totalTime = vertices.get(end).getTotTimeCost()-100000;
        routeStuff.route.add(start);
		return routeStuff;
	}
	
	public RouteDetails timeRoute(T start, T end)
	{
		RouteDetails routeStuff = new RouteDetails();
		Hashtable<T, TempClass> vertices;
		vertices = timeDijkstras(start);
		T n = end;
		ArrayList<T> route = new ArrayList<T>();
		while (!vertices.get(n).getPrevNode().equals(n))
		{
			route.add(n);
			n = vertices.get(n).getPrevNode();
		}
        routeStuff.route = route;
        routeStuff.totalDistance = vertices.get(end).getTotDistCost()-100000;
        routeStuff.totalTime = vertices.get(end).getTotTimeCost();
        routeStuff.route.add(start);
		return routeStuff;
	}
	
	public ArrayList<T> prefDist(T in, double dist) {
		ArrayList<T> route = new ArrayList<T>();
		Node startNode = nodes.get(in);
		route = prefDistRec(startNode, dist);
		return route;
	}
	
	public ArrayList<T> prefDistRec(Node node, double dist) {
		System.out.println(dist);
		ArrayList<T> route = new ArrayList<T>();
		for (Edge i:node.neighbors) {
			Double newDist = dist - i.distCost;
			if (newDist > 0) {
				route.add(node.element);
				route.add(i.otherNode.element);
				route.addAll(prefDistRec(i.otherNode, newDist));
			}
		}
		return route;
	}
	
	public ArrayList<T> prefTime(T in, double time) {
		ArrayList<T> route = new ArrayList<T>();
		Node startNode = nodes.get(in);
		route = prefDistRec(startNode, time);
		return route;
	}
	
	public ArrayList<T> prefTimeRec(Node node, double time) {
		System.out.println(time);
		ArrayList<T> route = new ArrayList<T>();
		for (Edge i:node.neighbors) {
			Double newTime = time - i.timeCost;
			if (newTime > 0) {
				route.add(node.element);
				route.add(i.otherNode.element);
				route.addAll(prefDistRec(i.otherNode, newTime));
			}
		}
		return route;
	}
}
