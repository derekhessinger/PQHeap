/*
* File: CommonWordsFinder.java
* Derek Hessinger
* CS231 B
* 11/17/22
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Arrays;

public class CommonWordsFinder{

	PQHeap<MapSet.KeyValuePair<String, Integer>> heap;
	KeyValuePairComparatorByValue<String, Integer> comparator;
	int size;

	public CommonWordsFinder(){

		this.comparator = new KeyValuePairComparatorByValue<String, Integer>();

		//Create heap object
		// Figure out why this is causing an error
		this.heap = new PQHeap<MapSet.KeyValuePair<String, Integer>>(comparator);
		this.size = 0;
	}

	public void clear(){
		this.comparator = new KeyValuePairComparatorByValue<String, Integer>();

		//Create heap object
		// Figure out why this is causing an error
		this.heap = new PQHeap<MapSet.KeyValuePair<String, Integer>>(comparator);
		this.size = 0;

	}

	// Reads word counter file and builds heap
	public void buildHeap(String filename){

		try{

			// Create file reader object
			FileReader fr = new FileReader(filename);

			// Create buffered reader object
			BufferedReader br = new BufferedReader(fr);

			// Read the first line
			String line = br.readLine();

			// Split the first line to grab the total number of words in the reddit file
			String[] firstLine = line.split("[^a-zA-Z0-9']");

			this.size = Integer.parseInt(firstLine[2]);

			// Read again so the header is not included in the heap
			line = br.readLine();

			// While loop to read through each line in the file
			while (line != null){

				// Split on anything that isnt a letter or number
  				String[] words = line.split("[^a-zA-Z0-9']");

  				// Create a kvp to hold the word and count
  				MapSet.KeyValuePair<String, Integer> kvp = new MapSet.KeyValuePair(words[0], Integer.parseInt(words[1]));

  				// Add kvp to heap
  				this.heap.offer(kvp);

  				// Go to next line
  				line = br.readLine();
			}
		}

		catch(FileNotFoundException ex) {

      		System.out.println("WordCounter.analyze():: unable to open file " + filename );
    	}
   		catch(IOException ex) {

    		System.out.println("WordCounter.analyze():: error reading file " + filename);
    	}
	}


	public static void main(String[] args){

		CommonWordsFinder cwf = new CommonWordsFinder();

		int num = Integer.parseInt(args[0]);

		for (int i = 1; i < args.length; i++){

			cwf.buildHeap(args[i]);
			System.out.println("TEN MOST FREQUENT WORDS FOR: " + args[i]);
			for (int j = 0; j < num; j++){

				MapSet.KeyValuePair<String, Integer> word = cwf.heap.poll();
				int count = word.getValue();
				double freq = ((double)count) / cwf.size;
				System.out.println("Word Count: " +  word + ", frequency: " + freq + ", size: " + cwf.size);
			}
			System.out.println("\n");
			cwf.clear();
			//cwf.heap.clear();
		}

		// for (int i = 0; i < 2; i++){
		// 	System.out.println(cwf.heap.poll());
		// }
	}
}