/*
* File: BSTMap.java
* Derek Hessinger
* CS231
* 10/27/22
*/

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements MapSet<K, V>{

	private class Node{

		Node left;
		Node right;
		KeyValuePair<K,V> kvp;

		public Node(KeyValuePair<K, V> kvp){

			this.kvp = kvp;
			this.left = null;
			this.right = null;
		}

		public K getKey(){
			return kvp.getKey();
		}

		public V getValue(){

			return kvp.getValue();
		}
	}

	// Fields for BSTMap
	private Node root;
	private int size;
	int collisons = 0;


	// Constructor for BSTMap
	public BSTMap(){

		root = null;
		size = 0;
	}

	public String getObjType(){

		return "BSTMap";
	}

	// Returns value to key recursively
	public V get(K key){

		return get(key, root);
	}

	// Helper function to return value from key
	private V get(K key, Node cur){

		// If cur is null return null
		if (cur == null){

			return null;
		}

		// If the Node key is less than the key passed, go to right
		if (cur.getKey().compareTo(key) < 0){

			return get(key, cur.right);
		}

		// If the Node key is greater than the key passed, go to left
		else if (cur.getKey().compareTo(key) > 0){

			return get(key, cur.left);
		}

		// If this line is reached, the Node key and key passed must be equal
		else{

			return cur.getValue();
		}
	}

	// Returns the size of the BST
	public int size(){

		return this.size;
	}

	 public int getCollisions(){

        return this.collisons;
    }


	// Clears the BST
	public void clear(){

		this.root = null;
		this.size = 0;
	}

	// Associates the specified value with the specified key in this map
	public V put(K key, V value){

		// If root is null, create a new node and set root to it
		if (root == null){

			KeyValuePair kvp = new KeyValuePair(key, value);
			root = new Node(kvp);
			size++;
			return null;
		}

		return put(key, value, root);
	}

	// Puts a value at key k passed
	private V put(K key, V value, Node cur) {

	    if (key.compareTo(cur.getKey()) < 0){

	        if (cur.left != null){

	            return this.put(key, value, cur.left);
	        } 
	        else {
	            //insert a new Node with the given KeyValuePair to the left of cur
	            KeyValuePair kvp = new KeyValuePair(key, value);
	            Node newNode = new Node(kvp);
	            cur.left = newNode;
	            size++;
	            return null;
	        }
	    } 
	    else if (key.compareTo(cur.getKey()) > 0){

	        if (cur.right != null){

	            return this.put(key, value, cur.right);
	        } 
	        else {

	            //insert a new Node with the given KeyValuePair to the right of cur
	            KeyValuePair kvp = new KeyValuePair(key, value);
	            Node newNode = new Node(kvp);
	            cur.right = newNode;
	            size++;
	            return null;
	        }
	    } 
	    else { // in this case, cur.getKey() == key

	        //Set the value of cur's KeyValuePair to be the given value and return the old one
	    	V oldVal = cur.getValue();
	    	cur.kvp.setValue(value);
	    	return oldVal;
	    }
	}

	// Returns true if the key passed is in the BST
	public boolean containsKey(K key){

		if (this.get(key) != null){

			return true;
		}

		else{

			return false;
		}
	}

	// Returns an array list of all keys in the map ordered from least to greatest
	public ArrayList<K> keySet(){

		ArrayList<K> output = new ArrayList<K>();
		keySet(root, output);
		return output;
	}

	// Helper function for keySet()
	private void keySet(Node cur, ArrayList<K> output){

		if (cur == null){

			return;
		}

		keySet(cur.left, output);
		output.add(cur.getKey());
		keySet(cur.right, output);
	}

	// Returns an array list of all values in tree
	public ArrayList<V> values(){

		ArrayList<V> output = new ArrayList<V>();
		values(root, output);
		return output;
	}

	// Helper function to recursively get all values in tree
	private void values(Node cur, ArrayList<V> output){

		if (cur == null){

			return;
		}

		values(cur.left, output);
		output.add(cur.getValue());
		values(cur.right, output);
	}

	// Returns an array list of all KVP in the map ordered from least to greatest
	public ArrayList<KeyValuePair<K, V>> entrySet(){

		ArrayList<KeyValuePair<K, V>> kvpList = new ArrayList<KeyValuePair<K, V>>();

		ArrayList<K> keys = this.keySet();

		for (K k: keys){

			V v = this.get(k);
			KeyValuePair kvp = new KeyValuePair(k, v);
			kvpList.add(kvp);
		}

		return kvpList;
	}

	// To string method
	public String toString(){

		return toString(root, 0);
	}

	// Helper method to recursively create string in BST format
	private String toString(Node root, int counter){

		String str = "";

		if (root == null){

			return str;
		}

		// set the root as first
		if (counter == 0){
			str += "root:\t" + root.kvp.toString() + "\n";
		}
		else{

			// increment each indent with respect to level
			str += "\t".repeat(counter) + root.kvp.toString() + "\n";
		}

		if (root.left != null){

			// increment each indent with respect to level
			str += "left:\t" + toString(root.left, counter+1);
		}
		if (root.right != null){

			// increment each indent with respect to level
			str += "right:\t" + toString(root.right, counter+1);
		}
		return str;
	}
	public static void main(String[] args){

		BSTMap<String, Integer> map = new BSTMap<String, Integer>();

		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);

		// Tests for BSTMap
		System.out.println(map.getObjType());
		System.out.println(map.get("one"));
		System.out.println(map.size());
		map.clear();
		System.out.println(map);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		System.out.println(map.containsKey("one"));
		System.out.println(map.keySet());
		System.out.println(map.values());
		System.out.println(map.entrySet());
		

	}
}





