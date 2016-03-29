package test;

import algoritm.*;

import java.util.HashMap;
import java.util.LinkedList;
import static algoritm.FordFulkerson.fordFulkerson;

/**Class testing the Ford Fulkerson algoritm
 * Created by Jimmy on 2015-11-07.
 */
public class FordFulkersonTest {

        public static void main(String[] args) {
            HashMap<Vertex, LinkedList<Edge>> map = new HashMap<>();
            Vertex a = new Vertex("a");
            Vertex b = new Vertex("b");
            Vertex c = new Vertex("c");
            Vertex d = new Vertex("d");
            Vertex e = new Vertex("e");
            LinkedList<Edge> al = new LinkedList<>();
            al.add(new Edge(a, b, 7));
            al.add(new Edge(a, c, 5));
            LinkedList<Edge> bl = new LinkedList<>();
            bl.add(new Edge(b, c, 4));
            bl.add(new Edge(b, d, 8));
            LinkedList<Edge> cl = new LinkedList<>();
            cl.add(new Edge(c, d, 8));
		    cl.add(new Edge(c, e, 2));
            LinkedList<Edge> dl = new LinkedList<>();
		    LinkedList<Edge> el = new LinkedList<>();

            map.put(a, al);
            map.put(b, bl);
            map.put(c, cl);
            map.put(d, dl);
		    map.put(e, el);

            Graph g = new Graph(map);
            LinkedList<Edge> test = fordFulkerson(g, a, d);
            System.out.println("--------------------------------------------");
            System.out.println("Number of edges MaxFlow: " + test.size());
            System.out.println("---------------------------------------------");
            for(Edge edge : test){
                System.out.println(edge);
            }
            System.out.println("\n---------------algoritm.Graph-------------------------");
            System.out.println(g);
        }
}
