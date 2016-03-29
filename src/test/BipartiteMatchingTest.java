package test;

import algoritm.Edge;
import algoritm.Graph;
import algoritm.Vertex;
import algoritm.BipartiteMatching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**Class for testing bipartite matching
 * Created by Jimmy on 2015-11-07.
 */
public class BipartiteMatchingTest {

    public static void main(String[] args) {

        HashMap<Vertex, LinkedList<Edge>> bipartite = new HashMap<>();

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");

        LinkedList<Edge> al = new LinkedList<>();
        al.add(new Edge(a, d,1));
        al.add(new Edge(a, e,1));
        al.add(new Edge(a, f,1));

        LinkedList<Edge> bl = new LinkedList<>();
        bl.add(new Edge(b, d,1));
        bl.add(new Edge(b, e,1));
        LinkedList<Edge> cl = new LinkedList<>();
        cl.add(new Edge(c, e,1));
        cl.add(new Edge(c, f,1));
       // cl.add(new algoritm.Edge(c, a,1));


        bipartite.put(a, al);
        bipartite.put(b, bl);
        bipartite.put(c, cl);

        Graph g = new Graph(bipartite);
        try {
            List<Edge> bipartiteEdges = BipartiteMatching.runAlgorithm(g);
            System.out.println("--------------------------------------------");
            System.out.println("Number of edges in bipartite graph: " + bipartiteEdges.size());
            System.out.println("--------------------------------------------");
            for(Edge edge : bipartiteEdges){
                System.out.println(edge);
            }
            System.out.println("--------------------------------------------");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
