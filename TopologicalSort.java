package graph;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by babak_khorrami on 6/7/16.
 */
public class TopologicalSort {
    private DiGraph dg;
    private Map<Integer,Integer> order;

    /** Constructor **/
    public TopologicalSort(DiGraph d){
        this.dg = d;
        this.order = new TreeMap<Integer, Integer>();
        for(int n : this.dg.getNodes()) //Initialize order
            this.order.put(n,0);

    }

    /** **/
    public void TopologicalOrder(){
        Deque<Integer> queue = new ArrayDeque<Integer>();
        Map<Integer,Integer> indeg = new HashMap<Integer, Integer>(this.dg.getIndegree());
        int next = 0;
        for(int n : indeg.keySet())
            if(indeg.get(n)==0) queue.push(n);
        if(queue.isEmpty()){
            System.out.println("Graph is a Cycle");
            return;
        }
        while (!queue.isEmpty()) {
            int current = queue.pop();
            next++;
            this.order.put(current, next);
            for (int i : this.dg.getEdge(current)) {
                int d_i = indeg.get(i);
                indeg.put(i, d_i - 1);
                if (d_i - 1 == 0) queue.push(i);
            }
        }
        if(next<this.dg.getV()){
            System.out.println("Graph has Cycle; No Topological Ordering");
            return;
        }

    }

    public Map<Integer,Integer> getTopologicalOrder(){
        return this.order;
    }
}
