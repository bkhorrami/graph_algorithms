package graph;


import java.io.File;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by babak_khorrami on 5/27/16.
 */
public class Graph {
    private int V; // Node Count
    private int E; // Edge Count
    private Set<Integer> Nodes;
    private Map<Integer, List<Integer>> AdjList;
    //private List<Tuple<Integer>> EdgeList;



    /**
     * Constructor with File Data
     **/
    public Graph(String fileName) {
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
                this.setEdge(e.getHead(), e.getTail());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Adds nodes in the Adjacency list for the corresponding vertex */
    public void setEdge(int source, int destination) {
        List<Integer> slist = AdjList.get(source);
        slist.add(destination);
        List<Integer> dlist = AdjList.get(destination);
        dlist.add(source);
    }

    /** Add nodes to create Directed Edges **/
    public void setDiEdge(int source , int destination){
        List<Integer> slist = AdjList.get(source);
        slist.add(destination);
    }

    /** Returns the List containing the vertex joining the source vertex **/
    public List<Integer> getEdge(int v) {
        return AdjList.get(v);
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
    public void printGraph() {
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

    /** **/
//    public boolean connected(int v , int w){
//        List<Integer> vAdj = this.AdjList.get(v);
//        List<Integer> wAdj = this.AdjList.get(w);
//        System.out.println(vAdj.contains(w));
//        System.out.println(wAdj.contains(v));
//        return (vAdj.contains(w) | wAdj.contains(v));
////            return false;
////        else
////            return false;
//    }



    /** **/
//    public List<Integer> getEdge(int v){
//        return AdjList.get(v);
//    }


    public static void main(String[] args) {
//        //String fileName = "/Users/babak_khorrami/Downloads/facebook/107.edges";
//        //String fileName = "/Users/babak_khorrami/Documents/tmp/dgt.txt";
//        String fileName = "/Users/babak_khorrami/Documents/tmp/g1.txt";
//        //String fileName = "/Users/babak_khorrami/Documents/tmp/dg2.txt";
//        //String fileName = "/Users/babak_khorrami/Downloads/amazon0302.txt";
//        //Graph g = new Graph(fileName);
//        DiGraph dg = new DiGraph(fileName);
//        dg.outdegreePrint();
//        //Tuple<Integer> mx = dg.maxInDegree();
//        //Tuple<Integer> mx = dg.maxIndegree();
//        //System.out.println("Node "+mx.getHead()+" has indegree "+mx.getTail());
//        //dg.indegPrint();
//        TopologicalSort tp = new TopologicalSort(dg);
//        tp.TopologicalOrder();
//        Map<Integer,Integer> topOrder = tp.getTopologicalOrder();
//        topOrder=tp.getTopologicalOrder();
////        System.out.println("------------------");
////        for(int e : topOrder.keySet()){
////            System.out.println(e+" , "+ topOrder.get(e));
////
////        }




        //g.printGraph();
        //Set<Integer> nodes = g.getNodes();
//
//        System.out.println("Max Degree = ");
//        Tuple<Integer> m = g.maxDegree();
//        m.print();
//        DepthFirstSearch dfs = new DepthFirstSearch(g,0);
//
//        long startTime = System.nanoTime();
//        dfs.dfs(g,0);
//        long endTime = System.nanoTime();
//
//        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
//        System.out.println("DFS took "+duration/1000000+" seconds");
//
//
//
//        System.out.println("......Job is Done!");

//        System.out.println("|V| = "+g.getV());
//        System.out.println("|E| = "+g.getE());
//        ConnectedComponent cc = new ConnectedComponent(g);
//        System.out.println("# components : "+ cc.componentCount());
//        System.out.println(cc.connected(0,9));
//        System.out.println(cc.isHasCycle());
        //cc.nodePrint();



        //dfs.printOrder();
//        String writeFile = "/Users/babak_khorrami/Downloads/amazon_res.txt";
//        dfs.printFile(writeFile);






//        //String fileName = "/Users/babak_khorrami/Downloads/facebook/0.edges";
//        String fileName = "/Users/babak_khorrami/Documents/tmp/g1.txt";
//        Graph g = new Graph(7);
//        g.addEdge(0,1);
//        g.addEdge(0,2);
//        g.addEdge(1,2);
//        g.addEdge(1,3);
//        g.addEdge(2,4);
//        g.addEdge(3,4);
//        g.addEdge(3,5);
//        g.addEdge(4,6);
//        g.addEdge(5,6);
//        g.printGraph();
//
//        DepthFirstSearch dfs = new DepthFirstSearch(g,0);
//        dfs.dfs(g,0);
//        //int[] ord = dfs.getOrder();
//        for(int i=0 ; i<dfs.path.length ; i++) {
//            //System.out.println(dfs.path[i]);
//            System.out.printf(dfs.path[i]+" --> ");
//            //System.out.println("Node "+i+" is visited at step "+dfs.order[i]);
//        }
//
//        System.out.println("-----------------------------");
//
//        BreathFirstSearch bfs = new BreathFirstSearch(g,0);
//        bfs.bfs(g,0);
//        int[] visit = bfs.getOrder();
////        for(int i=0 ; i<visit.length ; i++){
////            System.out.printf("Order of Visit: ");
////        }
//        bfs.printVisited();
////        Iterable<Tuple<Integer>> edg = bfs.getEdge();
////        for(Tuple<Integer> e : edg){
////            e.print();
////        }
//
//        //-------  Big Graph -------
//
//        String file = "/Users/babak_khorrami/Downloads/facebook/0.edges";
//        ParseData gData = new ParseData(file);
//        gData.readData(file);
//        List<Tuple<Integer>> edg = gData.getEdge();
//        for(Tuple<Integer> e : edg)
//            System.out.println(e.getHead()+","+e.getTail());
//
//
//
//
//
//
//
//
//    }

        System.out.println("--------------------------------------");

        String wg = "/Users/babak_khorrami/Documents/tmp/tiny.txt";
        MSTPrim kg = new MSTPrim(wg);
        kg.findPrimMST();
        kg.printMST();
//        EdgeWeightedGraph eg = new EdgeWeightedGraph(wg);
//        eg.printEdge();
    }
}


