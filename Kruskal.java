package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by babak_khorrami on 6/18/16.
 */
public class Kruskal {
    private int V; // Node Count
    private int E; // Edge Count
    private Set<Integer> Nodes; //Set of Graph Nodes
    private LinkedList<Edge<Integer>> edgeList = new LinkedList<Edge<Integer>>();
    private List<Edge<Integer>> mst = new ArrayList<Edge<Integer>>(); //List of edges in MST
    private int edgeCount;

    /** Constructor **/
    public Kruskal(String fileName) {

        try {
            this.edgeCount = 0;
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            this.Nodes = new HashSet<Integer>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split(" ");

                Integer head = Integer.parseInt(details[0]); //First Element of the line (Head)
                Integer tail = Integer.parseInt(details[1]); //Second Element of the line (Tail)
                Double weight = Double.parseDouble(details[2]); //Third Element of the line (Weight)

                this.edgeList.add(new Edge(head, tail, weight)); //Add the edge to the temporary list
                this.Nodes.add(head); //Add head and tail to the Node set:
                this.Nodes.add(tail);
            }
            this.V = Nodes.size();

            this.E = this.edgeList.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(this.edgeList);
    }

    public void KruskalMST(){
        UnionFind<Integer> mstNode = new UnionFind<Integer>(this.Nodes);
        while(!edgeList.isEmpty() && this.edgeCount< (this.V - 1)) {
            Edge<Integer> current = this.edgeList.pop();
            int head = current.getHead();
            int tail = current.getTail();
            if(!(mstNode.find(head).equals(mstNode.find(tail)))){
                mstNode.union(head,tail);
                this.mst.add(current);
            }

        }


    }

    public void printMST(){
        for(Edge<Integer> e : this.mst)
            System.out.println(e.toString());
    }


    /** */
    public void printG(){
        for(Edge e : this.edgeList)
            System.out.println(e.toString());
    }


}
