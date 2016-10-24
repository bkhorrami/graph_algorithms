package graph;

public class Tuple<T extends Comparable<T>> {
    public T head;
    public T tail;

    /** Constructor **/
    public Tuple(T h, T t) {
        this.head = h;
        this.tail = t;
    }

    public Tuple<T> put(T h , T t){
        this.head = h;
        this.tail = t;
        return new Tuple(h,t);
    }

    /** Return Head **/
    public T getHead(){
        return this.head;
    }

    /** Return Tail **/
    public T getTail(){
        return this.tail;
    }

    public void print(){
        System.out.println(head+","+tail);
    }
}

