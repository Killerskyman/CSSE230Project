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
	
	public class tempClass
	{
		private int totDistCost;
		private int totTimeCost;
		private T prevNode;
		
		public tempClass()
		{
			totDistCost = 100000;
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
	
	public class routeDetails
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
	
	public Hashtable<T, tempClass> Dijkstras(T e)
	{
		Hashtable<T, tempClass> vertices = new Hashtable<T, tempClass>();
		ArrayList<T> UnVisited = new ArrayList<T>();
		ArrayList<T> Visited = new ArrayList<T>();
		Set<T> set = nodes.keySet();
		Iterator<T> i = set.iterator();
		while (i.hasNext())
		{
			T n = i.next();
			vertices.put(n, new tempClass());
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
				tempClass temp = vertices.get(n);
				int tempCost = temp.getTotDistCost();
				if (tempCost < minCost)
				{
					minCost = tempCost;
					minVert = n;
				}
			}
			
			tempClass temp = vertices.get(minVert);
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
	
	public Hashtable<T, tempClass> timeDijkstras(T e)
	{
		Hashtable<T, tempClass> vertices = new Hashtable<T, tempClass>();
		ArrayList<T> UnVisited = new ArrayList<T>();
		ArrayList<T> Visited = new ArrayList<T>();
		Set<T> set = nodes.keySet();
		Iterator<T> i = set.iterator();
		while (i.hasNext())
		{
			T n = i.next();
			vertices.put(n, new tempClass());
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
				tempClass temp = vertices.get(n);
				int tempCost = temp.getTotTimeCost();
				if (tempCost < minCost)
				{
					minCost = tempCost;
					minVert = n;
				}
			}
			
			tempClass temp = vertices.get(minVert);
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
	
	public routeDetails Route(T start, T end)
	{
		routeDetails routeStuff = new routeDetails();
		Hashtable<T, tempClass> vertices;
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
        routeStuff.totalTime = vertices.get(end).getTotTimeCost();
        routeStuff.route.add(start);
		return routeStuff;
	}
	
	public routeDetails timeRoute(T start, T end)
	{
		routeDetails routeStuff = new routeDetails();
		Hashtable<T, tempClass> vertices;
		vertices = timeDijkstras(start);
		T n = end;
		ArrayList<T> route = new ArrayList<T>();
		while (!vertices.get(n).getPrevNode().equals(n))
		{
			route.add(n);
			n = vertices.get(n).getPrevNode();
		}
        routeStuff.route = route;
        routeStuff.totalDistance = vertices.get(end).getTotDistCost();
        routeStuff.totalTime = vertices.get(end).getTotTimeCost();
        routeStuff.route.add(start);
		return routeStuff;
	}
	
}
