//Keenan Albee
//3/3/2015
//Huffman Node, containing a character, its frequency, and an indicator stating whether the node is valid

public class HuffNode implements Comparable<HuffNode> {
	public String theChar;
	public int theFreq;
	public int isValid;
	
	public HuffNode(String inChar, int inFreq, int isValid){
		this.theChar = inChar;
		this.theFreq = inFreq;
		this.isValid = isValid;
	}
	
	public int compareTo(HuffNode otherNode){
		int output = Integer.compare(this.theFreq, otherNode.theFreq);
		return output;
	}
	
	public String toString() { 
		return theChar + Integer.toString(theFreq);
	}
}
