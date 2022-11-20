/*
* File: PQHeap.java
* Derek Hessinger
* CS231 B
* 11/15/22
*/

import java.util.Comparator;

public class PQHeap<T>{

	// Fields
	T[] heap;
	int size;
	Comparator<T> comparator;

	// Constructor
	public PQHeap(Comparator<T> comparator){
		heap = (T[]) new Object[16];
		size = 0;
		this.comparator = comparator;
	}

	// Resizes heap to new size passed as argument
	private void resize(int newSize){

		T[] newHeap = (T[]) new Object[newSize];	// Create new heap object
		for(int i = 1; i <= size; i++){

			newHeap[i] = heap[i];					// Add each element to the new heap
		}
		heap = newHeap;								// Set the heap to new heap
	}

	// Returns the child node to the left
	private int left(int index){
		return 2 * index;
	}

	// Returns the child node to the right
	private int right(int index){
		return 2 * index + 1;
	}

	// Returns the parent node
	private int parent(int index){
		return index / 2;
	}

	// Swaps the nodes at the indices passed as arguments
	private void swap(int index1, int index2){

		T temp = heap[index1];						// Create temporary reference to hold first item
		heap[index1] = heap[index2];				// Set first item to the second item
		heap[index2] = temp;						// Set second item to first item help by temp
	}

	// Compares upward the node to parent node, and swaps until the heap is in order
	private void bubbleUp(int index){
		if (index == 1){
			return;
		}

		T myself = heap[index];						// Reference to hold current node
		T myParent = heap[parent(index)];			// Reference to hold parent

		if (comparator.compare(myself, myParent) > 0){	// If the current node is greater than the parent, swap and continue bubbling up

			swap(index, parent(index));
			bubbleUp(parent(index));
		}
	}

	// Compares downward the parent node to child node, and swaps until heap is in order
	private void bubbleDown(int index){

		if (index * 2 > size) return;				// If the node has no left child, return
		if (index * 2 + 1 > size) return;			// If the node has no right child, return

		T myself = heap[index];						// Reference to hold current item
		T left = heap[left(index)];					// Reference to hold left item
		T right = heap[right(index)];				// Reference to hold right item

		if (left == null && right == null) return;	// If left and right are null, return

		if (comparator.compare(myself, left) > 0 && comparator.compare(myself, right) > 0) return;	// If the current node is greater than both children, return

		if (comparator.compare(myself, left) < 0 && comparator.compare(myself, right) < 0){			// If the node is less than both children

			if (comparator.compare(myself, left) < 0){	// If the left child is larger, swap and bubble down

				swap(index, left(index));
				bubbleDown(left(index));
			}

			if (comparator.compare(myself, right) < 0){	// If the right child is larger, swap and bubble down

				swap(index, right(index));
				bubbleDown(right(index));
			}
		}

		if (comparator.compare(myself, left) < 0){	// If only the left child is larger, swap and bubble down

			swap(index, left(index));
			bubbleDown(left(index));
		}

		if (comparator.compare(myself, right) < 0){	// If only the right child is larger, swap and bubble down

			swap(index, right(index));
			bubbleDown(right(index));
		}
	}

	// Adds new item to the heap 
	public void offer(T item){
		if (size + 1 == heap.length) resize(2 * heap.length);	// If heap becomes too large, resize
		heap[size + 1] = item;	// Add item to next available spot in heap
		size++;	// Increase the size
		bubbleUp(size);	// Bubble up the new node
	}

	// Returns the top item in the heap
	public T peek(){
		return heap[1];
	}

	// Removes the top item from the heap and returns it
	public T poll(){
		if (size - 1 < heap.length / 4) resize(heap.length / 2);	// If the heap comes too small, resize
		swap(1, size);	// Swap the first and last items
		T deleted = heap[size];	// Create a reference to item to be deleted
		this.size -= 1;	// Decrease size
		bubbleDown(1);	// Bubble down the item put to the top
		return deleted;	// Return deleted item
	}

	// To string method to display the heap
	public String toString(){
		String str = "";
		for (int i = 1; i <= size; i++){

			str += i + ": " + heap[i] + "\n";
		}
		return str;
	}

	public static void main(String[] args){

		PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2){
				return o1 - o2;
			}
		});

		for (int i = 0; i < 20; i++){
			heap.offer(i);
		}
		System.out.println(heap);

		for (int i = 0; i < 20; i ++){
			heap.poll();
			if (i == 10){
				System.out.println(heap);
			}
		}

		System.out.println(heap);

		heap.offer(69);

		System.out.println(heap);
	}
}