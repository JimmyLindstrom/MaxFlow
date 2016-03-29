package algoritm;

/**Class representing an edge in a directed graph, holding references
 * to the startvertex, the end vertex, and the capacity and flow of the edge
 * Created by Jimmy on 2015-10-22.
 */
public class Edge {
    private Vertex from;
    private Vertex to;
    private int capacity;
    private int flow;

    /**
     * Constructor of an edge taking two parameters, the start vertex and end vertex.
     * Setting the capacity and flow to zero
     * @param from start vetrex
     * @param to end vertex
     */
    public Edge (Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
        capacity = 0;
        flow = 0;
    }

    /**
     * Constructor of an edge taking three parameters, the start vertex, end vertex and
     * capacity. Setting the flow to zero
     * @param from start vetrex
     * @param to end vertex
     * @param capacity the capacity of the edge
     */
    public Edge(Vertex from, Vertex to, int capacity){
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        flow = 0;

    }

    /**
     * Method for retrieving the start vertex
     * @return the start vertex
     */
    public Vertex getFrom() {

        return from;
    }

    /**
     * Method for retrieving the end vertex
     * @return the end vertex
     */
    public Vertex getTo() {

        return to;
    }

    /**
     * Method for retrieving the capacity of the edge
     * @return the capacity of the edge
     */
    public int getCapacity() {

        return capacity;
    }

    /**
     * Method for setting the capacity of an edge
     * @param capacity the capacity to be set
     */
    public void setCapacity(int capacity) {

        this.capacity = capacity;
    }

    /**
     * Method for retrieving the flow of an edge
     * @return the flow of the edge
     */
    public int getFlow() {

        return flow;
    }

    /**
     * Method for settng the flow of an edge
     * @param flow the flow to be set
     */
    public void setFlow(int flow) {

        this.flow = flow;
    }

    /**
     * Method for creating a residual edge for the residual
     * graph
     * @return the edge
     */
    public Edge getResidualEdge () {
        return new Edge(from,to,(capacity - flow));
    }


    @Override
    public String toString() {
        return "algoritm.Edge [From Node=" + from + ", To Node=" + to
                + ", capacity=" + capacity + ", flow=" + flow + "]";
    }

}
