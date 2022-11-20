/*
* File: PQHeap.java
* Derek Hessinger
* CS231 B
* 11/15/22
*/

import java.util.Comparator;

public class PQHeap<T>{
	//When I add a new node, I always add level by level, from left to right
	// On any root to leaf path, I am never increasing in value

	T[] heap;
	int size;
	Comparator<T> comparator;

	public PQHeap(Comparator<T> comparator){
		heap = (T[]) new Object[16];
		size = 0;
		this.comparator = comparator;
	}

	private void resize(int newSize){
		T[] newHeap = (T[]) new Object[newSize];
		for(int i = 1; i <= size; i++){
			newHeap[i] = heap[i];
		}
		heap = newHeap;
	}

	private int left(int index){
		return 2 * index;
	}

	private int right(int index){
		return 2 * index + 1;
	}
	private int parent(int index){
		return index / 2;
	}

	private void swap(int index1, int index2){

		T temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	private void bubbleUp(int index){
		if (index == 1){
			return;
		}

		T myself = heap[index];
		T myParent = heap[parent(index)];

		if (comparator.compare(myself, myParent) > 0){

			swap(index, parent(index));
			bubbleUp(parent(index));
		}
	}

	private void bubbleDown(int index){

		if (index * 2 > size) return;
		if (index * 2 + 1 > size) return;

		T myself = heap[index];
		T left = heap[left(index)];
		T right = heap[right(index)];

		if (left == null && right == null) return;

		if (comparator.compare(myself, left) > 0 && comparator.compare(myself, right) > 0) return;

		if (comparator.compare(myself, left) < 0 && comparator.compare(myself, right) < 0){

			if (comparator.compare(myself, left) < 0){

				swap(index, left(index));
				bubbleDown(left(index));
			}

			if (comparator.compare(myself, right) < 0){

				swap(index, right(index));
				bubbleDown(right(index));
			}
		}

		if (comparator.compare(myself, left) < 0){

			swap(index, left(index));
			bubbleDown(left(index));
		}

		if (comparator.compare(myself, right) < 0){

			swap(index, right(index));
			bubbleDown(right(index));
		}
	}

	public void offer(T item){
		if (size + 1 == heap.length) resize(2 * heap.length);	// dirty coding sty
		heap[size + 1] = item;
		size++;
		bubbleUp(size);
	}

	public T peek(){
		return heap[1];
	}

	public T poll(){
		if (size - 1 < heap.length / 4) resize(heap.length / 2);
		swap(1, size);
		T deleted = heap[size];
		//heap[size] = null;
		this.size -= 1;
		bubbleDown(1);
		return deleted;
	}

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

		for (int i = 0; i < 16; i ++){
			heap.poll();
		}

		System.out.println(heap);

		heap.offer(69);

		System.out.println(heap);
	}
}