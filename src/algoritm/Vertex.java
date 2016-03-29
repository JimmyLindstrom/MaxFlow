package algoritm;

/**Class representing a algoritm.Vertex in a directed graph
 *
 * Created by Jimmy on 2015-10-22.
 */
public class Vertex {
    private String id;

    /**
     * Constructor for a algoritm.Vertex taking a string id as parameter
     * @param id the id of the vertex
     */
    public Vertex (String id){
            this.id = id;
    }

    /**
     * Method for retrieving the id of a vertex
     * @return id of vertex
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals (Object obj){
        if (obj instanceof Vertex) {
            return id.equals(((Vertex) obj).id);
        }
        return false;
    }

    @Override
    public String toString(){
        return "algoritm.Vertex [id=" + id + "]";
    }
}
