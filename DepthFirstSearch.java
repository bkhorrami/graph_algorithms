package graph;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by babak_khorrami on 6/1/16.
 */
public class DepthFirstSearch {
    private int source;
    private Map<Integer,Integer> order;
    private Map<Integer,Boolean> marked;
    //private Graph g;
    private int[] path;

    /** Constructor **/
    public DepthFirstSearch(Graph g , int source){
        this.source = source;
        int nd = g.getV();
        Set<Integer> node = g.getNodes();
        marked = new HashMap<Integer , Boolean>();
        order = new HashMap<Integer, Integer>();
        path = new int[g.getV()];
        int count = 0;
        for(int n : node){
            marked.put(n,false);
            order.put(n,0);
            path[count++] = -1;
        }
    }

    public void dfs(Graph g , int s){
        Stack<Integer> stack = new Stack<Integer>();
        int current = s;
        int count = 1;
        stack.push(current);
        marked.put(current,true);
        order.put(current,count++);
        TreeMap<Integer,Iterator<Integer>> adj = new TreeMap<>();
        for(int n : g.getNodes()){
            adj.put(n,g.getEdge(n).iterator());
        }

        int p=0;
        this.path[p++]=current;
        while(!stack.isEmpty()){
            current = stack.peek();
            if(adj.get(current).hasNext()) {
                int d = adj.get(current).next();
                if (marked.get(d) == false) {
                    marked.put(d, true);
                    order.put(d, count++);
                    this.path[p++]=d;
                    stack.push(d);
                }
            }
            else{
                stack.pop();
            }
        }
        System.out.println("Done!");
    }

    /** **/
    public Map<Integer,Boolean> getMarks(){
        return this.marked;
    }

    /** Print DFS results to File **/
    public void printFile(String fileName){
        try {

            String content = "This is the content to write into file";

            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);

            for(int i=0 ; i<this.path.length ; i++) {
                bw.write(path[i]+" -> ");
            }


            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printOrder(){
        System.out.println("The DFS Path is : ");
        for(int i=0 ; i<this.path.length ; i++) {
            System.out.printf(path[i] + " -> ");
            if(i%15==0)
                System.out.println("-> ");
        }
        System.out.println();
//        System.out.println("# Nodes visited : "+this.path.length);
//        for(int n : order.keySet())
//            System.out.println(n+","+order.get(n));


    }
}
