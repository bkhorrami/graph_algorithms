package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by babak_khorrami on 6/6/16.
 */
public class DiGraph {
    private int V; // Node Count
    private int E; // Edge Count
    private Set<Integer> Nodes;
    private Map<Integer, List<Integer>> AdjList;
    private Map<Integer,Integer> indgree;   //** In-degree of every Node
    private Map<Integer,Integer> outdegree; //** Out-degree of every Node



    /**
     * Constructor with File Data
     **/
    public DiGraph(String fileName) {
        this.AdjList = new HashMap<Integer, List<Integer>>();
        List<Tuple<Integer>> edge = new ArrayList<Tuple<Integer>>();

        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);

            this.Nodes = new HashSet<Integer>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] details = line.split("\t");
                Integer head = Integer.parseInt(details[0]);
                Integer tail = Integer.parseInt(details[1]);
                edge.add(new Tuple(head, tail));
                this.Nodes.add(head);
                this.Nodes.add(tail);
            }
            this.V = Nodes.size();
            for (Integer n : Nodes)
                this.AdjList.put(n, new LinkedList<Integer>());

            this.E = edge.size();

            for (Tuple<Integer> e : edge)
                this.setDiEdge(e.getHead(), e.getTail());

            //----initializing indegee:
            this.indgree = new TreeMap<Integer,Integer>();
            for(int n : this.Nodes){
                this.indgree.put(n,0);
            }

            //** Calculating Indegrees:
            for(int n : this.Nodes){
                for(int i : this.AdjList.get(n)){
                    int d = indgree.get(i);
                    indgree.put(i,d+1);
                    }
                }
            //--------------------------

            //---  outdegree for each Node :
            this.outdegree = new TreeMap<Integer, Integer>();
            for(int n : this.Nodes)
                this.outdegree.put(n,this.AdjList.get(n).size());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Add nodes to create Directed Edges **/
    public void setDiEdge(int source , int destination){
        List<Integer> slist = AdjList.get(source);
        slist.add(destination);
    }

    /** Returns the List containing the vertices joining vertex v **/
    public List<Integer> getEdge(int v) {
        return AdjList.get(v);
    }



    /** **/
    public Tuple<Integer> maxIndegree(){
        int maxIndegree = 0 , maxNode = -1;
        for(int n : this.Nodes){
            for(int i : this.AdjList.get(n)){
                int d = indgree.get(i);
                indgree.put(i,d+1);
                if(d+1 > maxIndegree){
                    maxIndegree = d+1;
                    maxNode = i;
                }
            }
        }
        Tuple<Integer> max = new Tuple<Integer>(maxNode,maxIndegree);
        return max;
    }

    /** Get Indegree **/
    public Map<Integer,Integer> getIndegree(){
        return this.indgree;
    }

    /** Get Outdegree **/
    public Map<Integer,Integer> getOutdegree(){return this.outdegree;}



    /** Print Outdegree **/
    public void outdegreePrint(){
        for(int i : this.outdegree.keySet()){
            //if(this.outdegree.get(i)==0)
                System.out.println(i+" , "+this.outdegree.get(i));
        }
    }

    /** **/
    public Tuple<Integer> maxDegree(){
        Tuple<Integer> max_node = new Tuple<Integer>(0,0);
        int max = 0;
        for(int n : Nodes){
            if(AdjList.get(n).size()>max){
                max = AdjList.get(n).size();
                max_node = new Tuple<Integer>(max , n);
            }
        }
        return max_node;
    }


    /**
     * Print Graph Attributes
     **/
    public void printDiGraph() {
        System.out.println("(V= " + this.V + ",E= " + this.E + ")");
    }

    /** Return # Vertics/Nodes **/
    public int getV() {
        return this.V;
    }

    /** return # Edges **/
    public int getE() {
        return this.E;
    }

    /** Return Set of Nodes **/
    public Set<Integer> getNodes(){
        return this.Nodes;
    }

//    public DiGraph reverse(){
//
//    }


}


//    public Tuple<Integer> maxInDegree(){
//        Tuple<Integer> max_node = new Tuple<Integer>(0,0);
//        int max = 0;
//        int inDegree = 0;
//        for(int v : this.Nodes){
//            for(int i : this.Nodes){
//                List<Integer> adj = new ArrayList<Integer>();
//                adj = this.AdjList.get(i);
//                if(adj.contains(v))
//                    inDegree++;
//            }
//            if(inDegree > max){
//                max=inDegree;
//                max_node.put(v,max);
//            }
//            inDegree = 0;
//
//        }
//        System.out.println("Max Degree Found : "+max);
//        return max_node;
//    }


