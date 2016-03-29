package algoritm;

import java.util.LinkedList;

/**Class that implements the Ford Fulkerson method for finding the maximum flow in a graph
 * Created by Jimmy on 2015-11-08.
 */
public class FordFulkerson {

    /**
     * Method that calculates the maximum flow of a graph and returns a list
     * containing the edges that has flow in them after finding the maximum flow
     *
     * @param graph the graph to find maximum flow in
     * @param source the start vertex
     * @param sink the target vertex
     * @return list of all edges that has flow when maximum flow is achieved.
     */
    public static LinkedList<Edge> fordFulkerson (Graph graph, Vertex source, Vertex sink){
        Graph residualGraph = graph.getResidualGraph();
        LinkedList<Edge> augmentingPath = graph.getAugmentingPath(source, sink);
        int pathCapacity;
        while (!augmentingPath.isEmpty()) {
            pathCapacity = residualGraph.pathCapacity(augmentingPath);
            System.out.println("Adding flow " + pathCapacity + " to original graph");
            for (Edge edge : augmentingPath) {
                graph.addFlowToEdge(edge, pathCapacity);
            }
            residualGraph = graph.getResidualGraph();
            augmentingPath = residualGraph.getAugmentingPath(source, sink);
        }
        System.out.println("--------------------------------------------");

        //Calculating max flow through graph
        LinkedList<Edge> edges = graph.getEdgesWithFlow();
        int flow = 0;
        for (Edge e: edges) {
            if (e.getFrom().equals(source)) {
                flow += e.getFlow();
            }
        }
        System.out.println("MaxFlow of graph is: " + flow);
        return graph.getEdgesWithFlow();
    }


}
