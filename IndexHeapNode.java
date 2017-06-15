package graph;

/**
 * Created by babak_khorrami on 7/2/16.
 */
public class IndexHeapNode<K extends Comparable<K>,V> extends HeapNode{
    public int index;

    /** Constructor */
    public IndexHeapNode(K key , V val , int idx){
        super(key,val);
        this.index = idx;
    }

    /** Return Index */
    public int getIndex(){
        return this.index;
    }

    /** Set the index of the node */
    public void setIndex(int idx){
        this.index = idx;
    }

    /** Set the key of the node */
    public void setKey(K newKey){
        this.key = newKey;
    }

    /** Set the value of the node */
    public void setValue(V newVal){
        this.value = newVal;
    }

    /** Print a Sting Version of the Node */
    @Override
    public void printNode(){
        System.out.println(super.key.toString()+"; # "+this.value.toString()+", Index: "+this.index);
    }

}
