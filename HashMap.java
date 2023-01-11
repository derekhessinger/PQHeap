/*
* File: HashMap.java
* Derek Hessinger
* CS231 B
* 11/2/22
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class HashMap<K, V> implements MapSet<K, V> {

    LinkedList<KeyValuePair<K, V>>[] map;
    int size;
    double loadFactor;
    int collisions;

    // Constructor
    public HashMap(){

        this.map = new LinkedList[1000];
        this.size = 0;
        this.loadFactor = 0.75;
    }

    // Constructor
    public HashMap(int initialCapacity) {
        this.map = new LinkedList[initialCapacity];
        this.size = 0;
        this.loadFactor = 0.75;
    }

    // Constuctor
    public HashMap(int initialCapacity, double loadFactor) {
        this.map = new LinkedList[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    // Returns size
    public int size(){
        return this.size;
    }

    // Clears map
    public void clear(){
        this.map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[(capacity())];
        this.size = 0;
        this.collisions = 0;
    }

    private int capacity() {
        return this.map.length;
    }

    // this method returns the index that will be used by any given key for this
    // mapping
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity()); // this returns a value between 0 and capacity() - 1, inclusive
    }

    // puts value in hashmap
    public V put(K key, V value) {
        int index = hash(key);

        if (map[index] == null) {
            map[index] = new LinkedList<KeyValuePair<K, V>>();
            map[index].add(new KeyValuePair<K, V>(key, value));
            this.size++;
            // if size ever gets too big compared to capacity, then I need to recreate my map to be bigger
            if (size() > (int)(capacity()*this.loadFactor)){

                ArrayList<KeyValuePair<K, V>> kvpList = entrySet();

                map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[map.length * 3];

                this.size = 0;

                this.collisions = 0;

                for (KeyValuePair<K, V> kvp : kvpList){

                    this.put(kvp.getKey(), kvp.getValue());
                }
            }
            return null;
        } 
        else {
            for (KeyValuePair<K, V> kvp : map[index]) {
                if (kvp.getKey().equals(key)) {
                    V oldValue = kvp.getValue();
                    kvp.setValue(value);
                    return oldValue;
                }
            }
            map[index].add(new KeyValuePair<K, V>(key, value));
            this.size++;
            this.collisions++;
            if (size() > (int)(capacity()*this.loadFactor)){

                ArrayList<KeyValuePair<K, V>> kvpList = entrySet();

                map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[map.length * 3];

                this.size = 0;

                this.collisions = 0;

                for (KeyValuePair<K, V> kvp : kvpList){

                    put(kvp.getKey(), kvp.getValue());
                }
            }
            return null;
        }
    }

    // Returns number of collisions
    public int getCollisions(){

        return this.collisions;
    }

    // Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key
    public V get(K key) {
        int index = hash(key);

        if (map[index] == null) {
            return null;
        } else {
            for (KeyValuePair<K, V> kvp : map[index]) {
                if (kvp.getKey().equals(key)) {
                    return kvp.getValue();
                }
            } return null;
        }
    }

    // Returns true if this map contains a mapping for the specified key to a value
    public boolean containsKey(K key){

        if (this.get(key) != null){
            return true;
        }

        return false;
    }

    // Ask professor about this one
    // Is this a BST or linked list?
    public V remove(K key){

        int index = hash(key);

        if (map[index] == null){

            return null;
        }

        for (KeyValuePair<K, V> kvp : map[index]){

            if (kvp.getKey().equals(key)){

                int idx = map[index].indexOf(kvp);

                V removed = kvp.getValue();

                map[index].remove(idx);

                this.size--;

                // call entry set
                if (this.size < (capacity()*(this.loadFactor*this.loadFactor*this.loadFactor))){

                    ArrayList<KeyValuePair<K, V>> kvpList = entrySet();

                    map = (LinkedList<KeyValuePair<K, V>>[]) new LinkedList[this.map.length / 3];

                    this.size = 0;

                    for (KeyValuePair<K,V> kv : kvpList){

                        K k = kv.getKey();
                        V v = kv.getValue();
                        this.put(k, v);
                    }
                }
                return removed;
            }
        }
        return null; 
    }

    // Returns arraylist of all keys
    public ArrayList<K> keySet(){

        ArrayList<K> keys = new ArrayList<K>();

        int index = 0;
        
        while (index < this.map.length){

            if (map[index] != null){

                for (KeyValuePair<K, V> kvp: map[index]){

                    keys.add(kvp.getKey());
                }
            }
            index += 1;
        }
        return keys;
    }

    // Returns arraylist of all values
    public ArrayList<V> values(){

        ArrayList<V> vals = new ArrayList<V>();

        int index = 0;

        while (index < this.map.length){

            if (map[index] != null){

                for (KeyValuePair<K, V> kvp: map[index]){

                    vals.add(kvp.getValue());
                }
            }
            index += 1;
        }
        return vals;
    }

    // Returns arraylist of all kvps
    public ArrayList<KeyValuePair<K, V>> entrySet(){

        ArrayList<KeyValuePair<K, V>> set = new ArrayList<KeyValuePair<K, V>>();

        int index = 0;

        while (index < this.map.length){

            if (map[index] != null){

                for (KeyValuePair<K, V> kvp: map[index]){

                    set.add(kvp);
                }
            }
            index += 1;
        }
        return set;
    }

    public String toString(){

        String str = "";

        int index = 0;

        while (index < this.map.length){ 

            str += index + ": ";

            if (map[index] != null){

                for (KeyValuePair<K,V> kvp: map[index]){

                    str += kvp + ", ";
                }
            }
            str += "\n";

            index += 1;
        }
        return str;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(5);

        System.out.println(hm);

        for (int i = 5; i < 10; i++) {
            hm.put(i, i + 1);
            System.out.println(hm);
        }

        
        hm.remove(8);
        hm.remove(9);
        hm.get(5);

        System.out.println(hm);
    }
}