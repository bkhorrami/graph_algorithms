package graph;

import java.util.*;

/**
 * Created by babak_khorrami on 6/2/16.
 */
public class ConnectedComponent {
    private Graph graph;
    private Map<Integer,Integer> Nodes; // Maps Node Numbers to Component ID.
    private Map<Integer,Boolean> marked; // Node is marked or not!
    private Map<Integer,Integer> order;
    private int[] path;
    private boolean hasCycle;

    /**----- CONSTRUCTOR -----**/
    public ConnectedComponent(Graph g)
    {
        this.graph = g;
        this.Nodes = new HashMap<Integer, Integer>();
        Set<Integer> node = g.getNodes();
        marked = new HashMap<Integer,Boolean>();
        order = new HashMap<Integer,Integer>();
        path = new int[g.getV()];
        int count = 0;
        for(int n : node){
            this.Nodes.put(n, 0); //Initiating the Component count to zero for each node
            this.marked.put(n,false);
            this.order.put(n,0);
            this.path[count++] = -1;
        }
        this.hasCycle = false;
    }

    /** Depth First Search Implementation **/
    public void dfs(Graph g , int s, int ccnt){
        Stack<Integer> stack = new Stack<Integer>();
        int current = s;
        int count = 1;
        stack.push(current);
        marked.put(current,true);
        order.put(current,count++);
        Nodes.put(current,ccnt);
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
                if (this.marked.get(d) == false) {
                    this.marked.put(d, true);
                    this.order.put(d, count++);
                    this.path[p++]=d;
                    this.Nodes.put(d,ccnt);
                    stack.push(d);
                }
                else{
                    this.hasCycle = true; //-- **** There is a Cycle ****
                }
            }
            else{
                stack.pop();
            }
        }
    }

    /** Find # components in a graph **/
    public int componentCount(){
        int count = 1;
        for(int n : this.graph.getNodes()){
            boolean mark = marked.get(n);
            if(!mark){
                this.dfs(this.graph,n,count++);
            }
        }
        return count-1;
    }

    /** Return Nodes and What component each node belongs to **/
    public void nodePrint(){
        for (Map.Entry<Integer, Integer> entry : Nodes.entrySet())
        {
            System.out.println(entry.getKey() + " in component " + entry.getValue());
        }
    }


    public boolean connected(int v , int w){
        if(this.Nodes.get(v)==this.Nodes.get(w))
            return true;
        else
            return false;

    }

    public boolean isHasCycle(){
        return this.hasCycle;
    }



}
