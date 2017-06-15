package graph;

/**
 * Class for each element of the Heap (Key , Value)
 * Created by babak_khorrami on 7/1/16.
 */

public class HeapNode<K extends Comparable<K>,V> {
        protected K key;
        protected V value;

        /** Constructor */
        public HeapNode(K k , V v){
            this.key = k;
            this.value = v;
        }

        /** Return key */
        public K getKey(){
            return this.key;}


        /** Return value */
        public V getValue(){
            return this.value;
        }

        /** Change the Key for this node and return it */
        public HeapNode changeKey(K newKey){
            if(this.key == null || this.value == null){
                System.out.println("Either key of value or both are null!");
                return null;
            }
            this.key = newKey;
            return this;
        }

        /** Print a Sting Version of the Node */
        public void printNode(){
            System.out.println(this.key.toString()+"; # "+this.value.toString());
        }
}

