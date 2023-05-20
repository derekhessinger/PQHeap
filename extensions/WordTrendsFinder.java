/*
* File: WordsTrendsFinder.java
* Derek Hessinger
* CS231 B
* 11/25/22
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 
import java.io.FileWriter;

public class WordTrendsFinder{
	// Takes in file as first argument, and words to be searched as following arguments
	// Returns file, word, total number of occurences, and frequency
	public static void main(String[] args){
		
		String filename = args[0];

		WordCounter wc = new WordCounter("hashmap");

		ArrayList<String> words = wc.readWords(filename + ".txt");

		wc.buildMap(words);

		for (int j = 1; j < args.length; j++){

			System.out.println(filename + "," + args[j] + "," + wc.getCount(args[j]) + "," + wc.getFrequency(args[j]));
		}
	}
}