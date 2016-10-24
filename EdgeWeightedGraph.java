package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by babak_khorrami on 6/18/16.
 */
public class EdgeWeightedGraph {

    /**------- This class represents the tail of any edge for ------**/

    public class Tail implements Comparable<Tail>{
        private int node; //Tail node of the edge
        private double weight; //weight of the edge

        /** **/
        public Tail(int t , double w){
            this.node = t;
            this.weight = w;
        }

        /** **/
        public double getWeight(){
            return this.weight;
        }

        /** **/
        public int getHeadNode(){
            return this.node;
        }


        @Override
        public int compareTo(Tail t){
            if(this.weight > t.weight) return 1;
            else if(this.weight < t.weight) return -1;
            else return 0;
        }

    }
    /**------------------------------------------------------------ **/

    private int V; // Node Count
    private int E; // Edge Count
    private Set<Integer> Nodes; //Set of Graph Nodes
    private Map<Integer, List<Tail>> AdjList; //Adjacency List for the Weighted Graph

    public EdgeWeightedGraph(String fileName) {
        this.AdjList = new HashMap<Integer, List<Tail>>();
        List<Edge<Integer>> edges = new ArrayList<Edge<Integer>>();

        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            this.Nodes = new HashSet<Integer>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split(" ");

                Integer head = Integer.parseInt(details[0]); //First Element of the line (Head)
                Integer tail = Integer.parseInt(details[1]); //Second Element of the line (Tail)
                Double weight = Double.parseDouble(details[2]); //Third Element of the line (Weight)

                edges.add(new Edge(head, tail, weight)); //Add the edge to the temporary list
                this.Nodes.add(head); //Add head and tail to the Node set:
                this.Nodes.add(tail);
            }
            this.V = Nodes.size();
            for (Integer n : Nodes)
                this.AdjList.put(n, new LinkedList<Tail>());

            this.E = edges.size();

            for (Edge<Integer> e : edges)
                this.setEdge(e.getHead(), e.getTail() , e.getWeight());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        edges.clear();
    }

    /** Return the Node Set */
    public Set<Integer> getNodes(){
        return this.Nodes;
    }

    /** Get Edges adjacent to a node v */
    public ArrayList<Edge<Integer>> getAdjacent(int v){
        List<Tail> adjtmp = this.AdjList.get(v);
        ArrayList<Edge<Integer>> adj = new ArrayList<>();
        for(Tail t : adjtmp)
            adj.add(new Edge<Integer>(v,t.getHeadNode(),t.getWeight()));
        return adj;
    }



    /** Adds nodes in the Adjacency list for the corresponding vertex */
    public void setEdge(int source, int destination , double weight) {
        List<Tail> slist = AdjList.get(source);
        slist.add(new Tail(destination,weight));
        List<Tail> dlist = AdjList.get(destination);
        dlist.add(new Tail(source,weight));
    }

    /** Add nodes to create Directed Edges **/
    public void setDiEdge(int source , int destination , double weight){
        List<Tail> slist = AdjList.get(source);
        slist.add(new Tail(destination,weight));
    }

    /** Print the Edge List **/
    public void printEdge(){
        for(int n : this.AdjList.keySet())
            for(Tail t : this.AdjList.get(n))
                System.out.println(n+" , "+t.getHeadNode()+" , "+t.getWeight());
    }
}
