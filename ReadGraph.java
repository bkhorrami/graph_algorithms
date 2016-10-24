package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by babak_khorrami on 7/14/16.
 */
//public class ReadGraph<V extends Comparable<V>, E extends Comparable<E>> {
public class ReadGraph{
    private int VertexCount; // # Vertices
    private int EdgeCount; // # Edges
    Set<Integer> Nodes; // Set of Nodes in Graph
    List<Edge<Integer>> weightedEdges;  // List of weighted edges (for Weighted Graph)
    List<Tuple<Integer>> edges;  // List of un-weighted Edges

    /** Constructor */
    public ReadGraph(){
        this.VertexCount = 0;
        this.EdgeCount = 0;
        this.Nodes = new HashSet<Integer>();
        this.weightedEdges = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    /** Parses an Edge Weighted Graph */
    public void weightedGraph(String fileName){
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            //this.Nodes = new HashSet<Integer>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split(" ");

                Integer head = Integer.parseInt(details[0]); //First Element of the line (Head)
                Integer tail = Integer.parseInt(details[1]); //Second Element of the line (Tail)
                Double weight = Double.parseDouble(details[2]); //Third Element of the line (Weight)

                weightedEdges.add(new Edge(head, tail, weight)); //Add the weighted edge to the list
                edges.add(new Tuple(head,tail)); //Add un-weighted edge to the list

                //Add head and tail to the Node set:
                this.Nodes.add(head);
                this.Nodes.add(tail);
            }
            this.VertexCount = Nodes.size();
            this.EdgeCount = weightedEdges.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Parses a regular graph (No Weights on edges) */
    public void regularGraph(String fileName){
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);


            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split("\t");
                Integer head = Integer.parseInt(details[0]);
                Integer tail = Integer.parseInt(details[1]);
                edges.add(new Tuple(head, tail));
                this.Nodes.add(head);
                this.Nodes.add(tail);
            }
            this.VertexCount = Nodes.size();
            this.EdgeCount = this.edges.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /** Return # Vertices */
    public int getVertexCount(){
        return this.VertexCount;
    }

    /** Return # Edges */
    public int getEdgeCount(){
        return this.EdgeCount;
    }
    /** Return Set of Nodes */
    public Set<Integer> getNodes(){
        return this.Nodes;
    }

    /** Return List of Weighted Edges (Only Applicable for Weighted Graphs)*/
    public List<Edge<Integer>> getWeightedEdges(){
        return this.weightedEdges;
    }

    /** Return List of Edges without Weights */
    public List<Tuple<Integer>> getEdges(){
        return this.edges;
    }

}
