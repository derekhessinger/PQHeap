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

	public static void main(String[] args){

		String filename = args[0];

		// int minNum = Integer.parseInt(args[1]);
		// int maxNum = Integer.parseInt(args[2]);

		// System.out.println("file, word, count, frequency");
		// for (int i = minNum; i < maxNum+1; i++){

		// 	WordCounter wc = new WordCounter("hashmap");

		// 	ArrayList<String> words = wc.readWords(filename + i + ".txt");

		// 	//System.out.println(words);

		// 	wc.buildMap(words);

		// 	for (int j = 3; j < args.length; j++){

		// 		//System.out.println(wc.totalWordCount());
		// 		System.out.println(filename + i + "," + args[j] + "," + wc.getCount(args[j]) + "," + wc.getFrequency(args[j]));
		// 	}

		// 	// wc.clearMap();
		// }

		WordCounter wc = new WordCounter("hashmap");

		ArrayList<String> words = wc.readWords(filename + ".txt");

		//System.out.println(words);

		wc.buildMap(words);

		for (int j = 1; j < args.length; j++){

			//System.out.println(wc.totalWordCount());
			System.out.println(filename + "," + args[j] + "," + wc.getCount(args[j]) + "," + wc.getFrequency(args[j]));
		}
	}
}