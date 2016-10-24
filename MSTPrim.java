package graph;

import java.net.Inet4Address;
import java.util.*;


/**
 * Created by babak_khorrami on 6/19/16.
 */
public class MSTPrim {
    private EdgeWeightedGraph graph; //Edge Weighted Graph
    private Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>(); //Visited Nodes
    private List<Edge<Integer>> mst = new ArrayList<Edge<Integer>>(); //List of Edges in MST
    PriorityQueue<Edge<Integer>> cut = new PriorityQueue<>(); //List(Priority Queue) of Edges in the Cut
                                                              // at each Iteration

    /** Constructor */
    public MSTPrim(String fileName){
        this.graph = new EdgeWeightedGraph(fileName);// Initialize the Weighted Graph
        for(int n : this.graph.getNodes()) //Populating visited nodes
            this.visited.put(n,false);
    }

    /** Find MST using Prim's */
    public void findPrimMST(){
        //** Pick a Node and visit it:
        Iterator<Integer> iter = this.graph.getNodes().iterator(); //Iterator to go through Nodes of Graph
        int node0 = iter.next(); //Pick the first node
        this.visitNode(node0);
        Edge<Integer> e;
        while(!cut.isEmpty()) {
            e = this.cut.remove(); //pick the smallest weighted edge from the cut
            int h = e.getHead();
            int t = e.getTail();
            if (this.visited.get(h) && this.visited.get(t)) continue;
            this.mst.add(e);
            if (!this.visited.get(h))
                this.visitNode(h);
            if (!this.visited.get(t))
                this.visitNode(t);
        }
    }

    public void visitNode(int v){
        this.visited.put(v , true); //Mark Node v as Visited
        ArrayList<Edge<Integer>> adj = this.graph.getAdjacent(v); //Get the Adjacent Edges to v
        for(Edge e : adj){
            if(!this.visited.get(e.getEnd(v)))
                this.cut.add(e);
        }

    }

    public void printMST(){
        for(Edge<Integer> e : this.mst)
            System.out.println(e.toString());
    }




}
