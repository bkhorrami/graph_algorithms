package graph;

/**
 * Created by babak_khorrami on 6/18/16.
 */
/**------- This class represents the tail of any edge for ------**/

public class Tail implements Comparable<Tail>{
    private int node; //Tail node of the edge
    private double weight; //weight of the edge

    public Tail(int t , double w){
        this.node = t;
        this.weight = w;
    }

    public double getWeight(){
        return this.weight;
    }

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
