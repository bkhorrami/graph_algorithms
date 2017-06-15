package graph;

import java.util.ArrayList;

/**
 * Binary Heap (Priority Queue) Implementation
 * Created by babak_khorrami on 6/25/16.
 */
public class HeapMinPQ<K extends Comparable<K>,V>{


    private ArrayList<HeapNode<K,V>> heap;

    /** Constructor : build an empty Array (Queue) */
    public HeapMinPQ(){
        this.heap = new ArrayList<HeapNode<K,V>>();
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

    /** Swap entry at node i with entry at node j */
    public void swap(int i , int j){
        HeapNode tmp = this.heap.get(i); //get the content of node i
        this.heap.set(i,this.heap.get(j));
        this.heap.set(j,tmp);
    }

    /** Move the entry at node i up the heap */
    public void moveUp(int i){
        while(i>0){
            int p = this.parent(i); //get the index of parent of i
            if(heap.get(i).getKey().compareTo(heap.get(p).getKey())>=0) break; //if key[i] 'LE' its parent's STOP
            this.swap(i,p); //Exchange i and it's parent
            i=p;
        }
    }

    /** Move the entry at node i down the heap */
    public void moveDown(int i){
        while(this.hasLeft(i)){
            int leftIdx = this.leftChild(i);
            int smallestdIdx = leftIdx;
            if(this.hasRight(i)){
                int rightIdx = this.rightChild(i);
                if(heap.get(smallestdIdx).getKey().compareTo(heap.get(rightIdx).getKey())>0)
                    smallestdIdx = rightIdx;
            }
            if(heap.get(smallestdIdx).getKey().compareTo(heap.get(i).getKey())>=0) break;
            this.swap(i,smallestdIdx);
            i=smallestdIdx;
        }
    }

    /** Get the min key of the Heap */
    public HeapNode min() {
        if (this.heap.isEmpty())
            return null;
        return this.heap.get(0); //First element of the heap

    }

    /** Insert a new element into the Heap  */
    public HeapNode insert(K key , V val){
        HeapNode newNode = new HeapNode(key,val); //Create the node to insert
        this.heap.add(newNode); //Add the new node to the end of the Array List.
        this.moveUp(this.heap.size()-1); //Move up the new node, Heapify
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

    /** Print a node of the heap */
    public void printNode(HeapNode n){
        n.printNode();
    }
}
