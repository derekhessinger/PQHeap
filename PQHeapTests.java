/*
* File: PQHeapTests
* Derek Hessinger
* CS231 B
* 11/20/22
*/

import java.util.Comparator;

public class PQHeapTests{

	public static void main(String[] args){

		// heap object
		PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2){
				return o1 - o2;
			}
		});

		// case 1: testing PQHeap()
		{
			// setup
			heap = heap;

			// verify
			System.out.println(heap.size + " == 0");

			// test
			assert heap.size == 0 : "Error in PQHeap::PQHeap()";
		}

		// case 2: testing offer()
		{
			// setup
			heap = heap;
			heap.offer(1);

			// verify
			System.out.println(heap + " == 1: 1\n");

			// test
			assert heap.size == 1 : "Error in PQHeap::offer()";
		}

		// case 3: testing peek()
		{
			// setup
			heap = heap;
			heap.offer(2);

			// verify
			System.out.println(heap.peek() + " == 2");

			// test
			assert heap.peek() == 2 : "Error in PQHeap::peek()";
		}

		// case 4: testing poll()
		{
			// setup
			heap = heap;
			for (int i = 3; i < 11; i++){
				heap.offer(i);
			}
			int deleted = heap.poll();

			// verify
			System.out.println(deleted + " == 10");
			System.out.println(heap);

			// test
			assert deleted == 10 : "Error in PQHeap::poll()";
		}
	}
}