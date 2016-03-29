package algoritm;

import java.util.*;


/**Class representing a algoritm.Graph as an adjacency list using a hashmap. The key in the Map is the vertex and the value
 * is a list of the edges going from that algoritm.Vertex.
 *
 * Created by Jimmy on 2015-10-22.
 */
public class Graph {
    private Map<Vertex, LinkedList<Edge>> adjacencyMap;


    /**
     * Constuctor for a graph taking no parameters
     */
    public Graph () {
        this.adjacencyMap = new HashMap<>();
    }

    /**
     * Constuctor for a graph taking a Map containing the adjecency list of a algoritm.Graph
     * @param map the adjecency list for the graph
     */
    public Graph (HashMap<Vertex, LinkedList<Edge>> map) {
        this.adjacencyMap = map;
    }

    /**
     * Method for creating a residual graph
     * @return the residual graph
     */
    public Graph getResidualGraph() {
        Graph g = new Graph();

        for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencyMap.entrySet()) {

            for (Edge e : entry.getValue()) {
                // add edge to residual graph
                if ((e.getCapacity()- e.getFlow()) != 0)
                    try {
                        g.addEdge(e.getResidualEdge());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                // add parallel edge to residual graph, if flow exists on edge
                if (e.getFlow() != 0)
                    try {
                        g.addEdge(new Edge(e.getTo(),e.getFrom(),e.getFlow()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
            }
        }

        return g;
    }

    /**
     * Method for adding an edge to a graph
     * @param edge the edge to add
     */
    public  void addEdge (Edge edge) throws Exception{
        if (edge.getFrom().equals(edge.getTo())) {
            throw new Exception("algoritm.Edge is not allowed to loop to same vertex");
        }else if (adjacencyMap.containsKey(edge.getFrom())) {
            adjacencyMap.get(edge.getFrom()).add(edge);
        } else {
            LinkedList<Edge> temp = new LinkedList<>();
            temp.add(edge);
            adjacencyMap.put(edge.getFrom(), temp);
        }
    }

    /**
     * Method for retrieving a list of all the edges in a graph
     * @return list of edges in graph
     */
    public List<Edge> getEdges () {
        List<Edge> edges = new LinkedList<>();

        for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencyMap.entrySet()) {
            edges.addAll(entry.getValue());
        }
        return edges;
    }

    /**
     * Method for finding an augmented path in a graph
     * @param source the start vertex
     * @param sink the target vertex
     * @return a list containing the edges on a path from the source to the sink
     */
    public LinkedList<Edge> getAugmentingPath (Vertex source, Vertex sink){
        LinkedList<Vertex> visited = new LinkedList<>();
        LinkedList<Edge> edgesInPath = new LinkedList<>();
        breadthFirstSearch(source, sink, edgesInPath, visited);
        return edgesInPath;
    }

    /**
     * Method for traversing the graph breadth first recursively
     * @param current the start vertex
     * @param sink the target vertex
     * @param edges the list of edges in path
     * @param visited the list holding the vertex's that have been visited
     * @return true if target vertex has been reached, else false
     */
    public boolean breadthFirstSearch (Vertex current, Vertex sink, LinkedList<Edge> edges, LinkedList<Vertex> visited) {
        visited.add(current);
        if (current.equals(sink)) {
            return true;
        } else {
            LinkedList<Edge> neighbourEdges = adjacencyMap.get(current);
            if (neighbourEdges != null) {
                for (Edge e: neighbourEdges) {
                    if (!visited.contains(e.getTo()) || e.getCapacity() - e.getFlow() <= 0) {
                        if (breadthFirstSearch(e.getTo(), sink, edges, visited)) {
                            edges.addFirst(e);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Method for adding flow to an edge
     * @param e the edge that flow is to be added to
     * @param flow the flow to be added to edge e
     */
    public void addFlowToEdge (Edge e, int flow) {
        //Adding flow to regular edge
        for (Edge edge: adjacencyMap.get(e.getFrom())) {
            if (edge.getTo().equals(e.getTo())) {
                edge.setFlow(edge.getFlow() + flow);
                return;
            }
        }
        //Adding flow to parallel algoritm.Edge
        for (Edge edge: adjacencyMap.get(e.getTo())) {
            if (edge.getTo().equals(e.getFrom())) {
                edge.setFlow(edge.getFlow() - flow);
                return;
            }
        }
    }

    /**
     * Method for calculating the maximum capacity on the edges along a path,
     * which is the capacity of the edge with the lowest capacity
     * @param path list of edges in the path
     * @return the maximum capacity of the path
     */
    public int pathCapacity (List<Edge> path) {
        int pathCapacity = Integer.MAX_VALUE;
        for (Edge e: path) {
            if (e.getCapacity() < pathCapacity) {
                pathCapacity = e.getCapacity();
            }
        }
        return pathCapacity;
    }

    /**
     * Method for retrieving a list of all the edges in a graph that has som flow
     * @return list of edges that has som flow
     */
    public LinkedList<Edge> getEdgesWithFlow() {
        LinkedList<Edge> edgeList = new LinkedList<>();
        for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencyMap.entrySet()) {
            for (Edge e : entry.getValue()) {
                if (e.getFlow() > 0)
                    edgeList.add(e);
            }
        }
        return edgeList;
    }


    @Override
    public String toString() {
        String s = "";
        for (Map.Entry<Vertex, LinkedList<Edge>> entry : adjacencyMap.entrySet()) {
            for (Edge e : entry.getValue()) {

                s += "\n key: " + entry.getKey() + "          value :" + e;
            }
        }
        return s;
    }

}
