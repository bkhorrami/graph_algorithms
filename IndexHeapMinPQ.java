package graph;

import java.util.ArrayList;

/**
 * Node of a Binary Min-Heap with index, to facilitate Change-Key operations
 * Created by babak_khorrami on 7/2/16.
 */
public class IndexHeapMinPQ<K extends Comparable<K>,V>{

    /** IndexHeapNode class is used for each node which has : Key, Value , Index fields */
    private ArrayList<IndexHeapNode<K,V>> heap; // heap has (Key, Value, Index)

    /** Constructor : build an empty Array (Queue) */
    public IndexHeapMinPQ(){
        this.heap = new ArrayList<IndexHeapNode<K,V>>();
    }

    /** Size of the heap */
    public int size(){
        return heap.size();
    }

    /** is the heap empty? */
    public boolean isEmpty(){
        return (heap.size()==0);
    }

    /** Find the index of parent of node i */
    public int parent(int i){
        return (i-1)/2;
    }

    /** Find the index of the left child of i */
    public int leftChild(int i){
        return 2*i+1;
    }

    /** Find the index of right child of i */
    public int rightChild(int i){
        return 2*i+2;
    }

    /** Check whether node index i has a left child */
    public boolean hasLeft(int i){
        return this.leftChild(i)<this.size();
    }

    /** Check whether node index i has a right child */
    public boolean hasRight(int i){
        return this.rightChild(i)<=this.size();
    }

    /** Move the entry at node i up the heap */
    public void moveUp(int i){
        while(i>0){
            int p = this.parent(i); //get the index of parent of i

            /**if key[i] "Less than or Equal to" its parent's key : STOP */
            if(heap.get(i).getKey().compareTo(heap.get(p).getKey())>=0) break;
            this.swap(i,p); //Exchange i and it's parent
            i=p;
        }
    }

    /** Move the entry at node i down the heap */
    public void moveDown(int i){
        while(this.hasLeft(i)){
            int smallestIdx = this.leftChild(i);
            if(this.hasRight(i)){
                int rightIdx = this.rightChild(i);
                if(heap.get(smallestIdx).getKey().compareTo(heap.get(rightIdx).getKey())>0)
                    smallestIdx = rightIdx;
            }
            if(heap.get(smallestIdx).getKey().compareTo(heap.get(i).getKey())>=0) break;
            this.swap(i,smallestIdx);
            i=smallestIdx;
        }
    }


    /** Swap entry at node i with entry at node j */
    public void swap(int i , int j){
        IndexHeapNode node_i = this.heap.get(i); //get node i from the ArrayList
        IndexHeapNode node_j = this.heap.get(j); //get node j from the ArrayList
        //** Exchange the indices of two nodes:
        node_i.setIndex(heap.get(j).getIndex());
        node_j.setIndex(heap.get(i).getIndex());
        //** Exchange two nodes in the array:
        this.heap.set(i,node_j); //put node_j in location i of the Array
        this.heap.set(j,node_i); //put node_i in location j of the Array
    }

    /** Insert a new element into the Heap  */
    public IndexHeapNode insert(K key , V val){
        IndexHeapNode newNode = new IndexHeapNode(key,val,heap.size()); //Create the node to insert
        this.heap.add(newNode); //Add the new node to the END of the Array List.
        this.moveUp(this.heap.size()); //Move up the new node (if required)
        return newNode;
    }

    /** Delete the minimum key node in the heap */
    public HeapNode deleteMin(){
        if(this.heap.isEmpty())
            return null;
        HeapNode minNode = this.heap.get(0); //get the first/min_key element
        this.swap(0,this.heap.size()-1); //Swap the first and the last element
        this.heap.remove(this.heap.size()-1); //Remove the last element (min key)
        this.moveDown(0); //put the new 0th element in the right place
        return minNode;
    }

    /** Adjust the location of element i to restore the Heap move Up/Down depending on the Key */
    public void heapify(int i){
        if(i<0){
            System.out.println("Array index should be at least Zero!");
            return;
        }
        if(heap.get(i).getKey().compareTo(heap.get(parent(i)).getKey())<0)
            this.moveUp(i);
        else
            this.moveDown(i);

    }

    /** Remove the heap node specified by (key,val,idx) */
    public void remove(K key , V val , int idx){
        if(idx<0 || idx>this.size()){
            System.out.println("Heap Index out of Bound!");
            return;
        }
        this.swap(idx,this.size()-1); //Swap the given element with the last element of the Array/Heap
        this.heap.remove(this.size()-1); //remove the last element (i.e., given element just swapped)
        this.heapify(idx); //Heapify (the element at location idx)
    }


    /** Romove node : a different version (argument) */
    public void remove(IndexHeapNode<K,V> elem){
        if(elem.getIndex()<0 || elem.getIndex()>this.size()){
            System.out.println("Heap Index out of Bound!");
            return;
        }
        int idx = elem.getIndex();
        this.swap(idx,this.size()-1); //Swap the given element with the last element of the Array/Heap
        this.heap.remove(this.size()-1); //remove the last element (i.e., given element just swapped)
        this.heapify(idx); //Heapify (the element at location idx)
    }

    /** Change key of a node */
    public void changeKey(IndexHeapNode<K,V> elem , K newKey){
        elem.setKey(newKey); //change the key of the element
        this.heapify(elem.getIndex()); //Restore the Heap property
    }

    /** Change the value of a node */
    public void changeValue(IndexHeapNode<K,V> elem, V newval){
        elem.setValue(newval); //Change the value of the node
    }

    /** Print a node of the heap */
    public void printNode(IndexHeapNode n){
        n.printNode();
    }
}
