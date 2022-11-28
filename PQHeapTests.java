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

		// case 2: testing offer
		{
			// setup
			heap = heap;
			heap.offer(1);

			// verify
			System.out.println(heap + " == 1: 1\n");

			// test
			assert System.out.println(heap).equals("1: 1\n") : "Error in PQHeap::offer()";
		}
	}
}