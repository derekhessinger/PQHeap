/*
* File: CommonWordsFinderNode.java
* Derek Hessinger
* CS231 B
* 11/26/22
*/

public class CommonWordsFinderNode{

	BSTMap<String, Integer> map;
	int size;

	public CommonWordsFinderNode(){

		this.map = new BSTMap<String, Integer>();
		this.size = 0;
	}

	public void clear(){

		this.map = new BSTMap<String, Integer>();
		this.size = 0;
	}

	
}