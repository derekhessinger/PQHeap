/*
* File: WordCounter.java
* Derek Hessinger
* CS231 B
* 11/11/2022
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 
import java.io.FileWriter;

public class WordCounter{

	MapSet<String, Integer> ds;
	int wordCount;

	// Constuctor for WordCounter
	public WordCounter (String data_structure){

		if (data_structure.equals("bst")){
			ds = new BSTMap<String, Integer>();
		}
		else if (data_structure.equals("hashmap")){
			ds = new HashMap<String, Integer>(1000, 0.75);
		}
	}

	// Returns an arraylist of all words in file
	public ArrayList<String> readWords(String filename){

		try{

			// Create ArrayList to hold words
			ArrayList<String> wordlist = new ArrayList<String>();

  			// Create file reader with file passed
  			FileReader fr = new FileReader(filename);

  			// Create buffered reader with file reader passed
  			BufferedReader br = new BufferedReader(fr);

  			// Read the first line
  			String line = br.readLine();

  			// While the line is not equal to null
  			while (line != null){

  				// Split on anything that isnt a letter or number
  				String[] words = line.split("[^a-zA-Z0-9']");

  				for (String word : words){

  					// make each word lower case to remove case sensitivity
  					word = word.toLowerCase();

  					wordlist.add(word);

  					wordCount += 1;
  				}

  				// Go to next line
  				line = br.readLine();
  			}

  			return wordlist;
  		}
  		catch(FileNotFoundException ex) {

      	System.out.println("WordCounter.analyze():: unable to open file " + filename );
    	}
   		catch(IOException ex) {

    	System.out.println("WordCounter.analyze():: error reading file " + filename);
    	}
    	return null;
  	}

  	// Builds map and returns time in nanoseconds
  	public long buildMap(ArrayList<String> words){

  		// Start recording time
  		long start = System.nanoTime();

  		// Put each word into the map
  		for (String word : words){

  			if (ds.get(word) != null){

  				int val = ds.get(word) + 1;
  				ds.put(word, val);
  			}
  			else ds.put(word, 1);
  		}

  		// Stop recording time
  		long end = System.nanoTime();

  		// Calculate total time
  		long time = end - start;

  		return time;
  	}

  	// Clears map
  	public void clearMap(){

  		ds.clear();
  		this.wordCount = 0;
  	}

  	// Returns total word count
  	public int totalWordCount(){

  		return wordCount;
  	}

  	// Returns unique word count
  	public int uniqueWordCount(){

  		return ds.size();
  	}

  	// Returns count of word passed
  	public int getCount(String word){

  		if (ds.get(word) == null){

  			return 0;
  		}

  		int count = ds.get(word);

  		return count;
  	}

  	// Returns the frequency of the word passed
  	public double getFrequency(String word){

  		if (ds.get(word) == null){

  			return 0;
  		}

  		int wc = ds.get(word);

  		double freq = (double)wc / totalWordCount();

  		return freq;
  	}

  	public static void main(String[] args){

  		WordCounter wc = new WordCounter("hashmap");

  		ArrayList<String> words = wc.readWords("test.txt");

  		wc.buildMap(words);

  		System.out.println(wc.getCount("this"));
  	}
}