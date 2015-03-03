//Keenan Albee
//kea2134
//11/26/14

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
