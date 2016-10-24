package graph;

/**
 * Created by babak_khorrami on 6/9/16.
 */

//public class Edge implements Comparable<Edge> {
//    private int head;
//    private int tail;
//    private double weight;
//
//    public Edge(int h , int t , double w){
//        this.head = h;
//        this.tail = t;
//        this.weight = w;
//    }
//
//    @Override
//    public int compareTo(Edge e){
//        if(this.weight > e.weight) return 1;
//        else if(this.weight < e.weight) return -1;
//        else return 0;
//    }
//
//}


public class Edge<T> implements Comparable<Edge<T>> {
    private T head;
    private T tail;
    private double weight;

    /** */
    public Edge(T h , T t , double w){
        this.head = h;
        this.tail = t;
        this.weight = w;
    }

    /** */
    public T getHead(){
        return this.head;
    }

    /** */
    public T getTail(){
        return this.tail;
    }

    /** */
    public double getWeight(){
        return this.weight;
    }

    /** */
    public T getEnd(T v){
        T oneEnd = this.getHead();
        if(v.equals(oneEnd))
            return this.getTail();
        else
            return this.getHead();
    }

    /** */
    @Override
    public int compareTo(Edge e){
        if(this.weight > e.weight) return 1;
        else if(this.weight < e.weight) return -1;
        else return 0;
    }

    /** */
    public String toString(){
        return this.getHead()+","+this.getTail()+","+this.getWeight();
    }

}
