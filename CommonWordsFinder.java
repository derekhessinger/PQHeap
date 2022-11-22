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

public class CommonWordsFinder{

	PQHeap<MapSet.KeyValuePair<String, Integer>> heap;
	KeyValuePairComparatorByValue<String, Integer> comparator;

	public CommonWordsFinder(){

		this.comparator = new KeyValuePairComparatorByValue<String, Integer>();

		//Create heap object
		// Figure out why this is causing an error
		this.heap = new PQHeap<MapSet.KeyValuePair<String, Integer>>(comparator);
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

		// Figure out how to write main function described in project instructions
		for (String file: args){

			cwf.buildHeap(file);


		}

		cwf.buildHeap("OUTPUTreddit_comments_2008.txt");

		//System.out.println(cwf.heap);

		System.out.println(cwf.heap.peek());

		// for (int i = 0; i < 2; i++){
		// 	System.out.println(cwf.heap.poll());
		// }
	}
}