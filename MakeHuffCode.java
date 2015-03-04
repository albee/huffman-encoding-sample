//Keenan Albee
//11/26/14, modified 3/3/15
//Creates a Huffman encoding from the characters in an input file, and displays the Huffman tree
//for this encoding. Words may be encoded and binary may be decoded in the GUI.

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MakeHuffCode{
	
	static HashMap<String,String> encodings;
	static HashMap<String,String> treeMap;
	static BinaryTree completeTree;
	final static int CAP = 128;

	public static void main(String[] args) throws Overflow, IOException {
		File inFile; //input file
		BufferedReader scan;
		int currentInt;
		HashMap<String,HuffNode> map = new HashMap<String, HuffNode>(CAP);
		BinaryTree HuffTree;
		char current;
		int readTemp;
		String keyVal = null;
		HuffNode tmp;
		BinaryHeap MyHeap = new BinaryHeap(CAP);
		
		if (args.length==1){
			inFile = new File(args[0]);
			scan = new BufferedReader(new FileReader(inFile));
			//read in the file character by character and put char in hashmap
			while ( (readTemp = scan.read()) != -1){
				current = (char) readTemp ;
				//account for a newline
				if (current == '\r'){ //don't want pesky \r
				}
				else{
					if (current == '\n'){	
						keyVal  = "\\n";
					}
					//account for a space
					else if (current == ' '){
						keyVal = "sp";
					}
					else
						keyVal = Character.toString(current);
					if (map.containsKey(keyVal))
						currentInt = map.get(keyVal).theFreq;
					else currentInt = 0;
					tmp = new HuffNode(keyVal, ++currentInt,1);
					map.put(keyVal, tmp);
				}
			}
			MyHeap = makeHeap(map); //make the heap of Huffman nodes	
			completeTree = makeTree(MyHeap); //make the final Huffman tree	
			//print out the Huffman encondings map to terminal
			encodings = completeTree.getRoot().getHuffman(completeTree.size());
			treeMap = completeTree.getRoot().getTree(completeTree.size());
			System.out.println(encodings);
        		makeGUI(); //display the Huffman tree
          	}	
		else
			System.out.println("Please provide an input file as a command line argument.");
	}

	static BinaryHeap makeHeap(HashMap<String,HuffNode> theMap) throws Overflow{
        	//create binary heap queue of 1-node Huffman trees
		BinaryTree HuffTree = new BinaryTree();
		BinaryHeap MyHeap = new BinaryHeap( CAP );
		for (Map.Entry<String,HuffNode> entry : theMap.entrySet()) {
			HuffNode value = entry.getValue();
			HuffTree = new BinaryTree(value);
			MyHeap.insert(HuffTree);
		}
		return MyHeap;
		
	}

	static BinaryTree makeTree(BinaryHeap MyHeap) throws Overflow{
	//create final Huffman tree
		int cntr = 0;
        	BinaryTree finishedTree = new BinaryTree();
		HuffNode tmp;
		for (; ;){
			BinaryTree num1 = MyHeap.deleteMin(); //set the smaller element as the left
			BinaryTree num2 = MyHeap.deleteMin();
			//creates a filler huffNode
			tmp = new HuffNode("T"+ Integer.toString(++cntr),
                	num1.getRoot().getElement().theFreq + num2.getRoot().getElement().theFreq,0);
			//merge the trees around the new Huffman root node
			num1.merge(tmp,num1,num2);
			if (MyHeap.isEmpty()){
				finishedTree = num1;
				break;
			}
			else
				MyHeap.insert(num1);
     		}
        	return finishedTree;
	}
	
	static void makeGUI(){
		//display the tree as a gui
		ArrayList<NodeShape> myNodes = new ArrayList<NodeShape>();
		int panelWidth;
		int panelHeight;
		final int SPACING = 55; //minumum spacing b/n nodes (decreases if very wide)

		panelHeight = (completeTree.height()+1)*100;
		panelWidth = (int) ((Math.pow(2,completeTree.height()) - 1)*SPACING);
		if (panelWidth > 3000){
			panelWidth = 3000;
		}	    
		myNodes = getNodes(panelWidth, panelHeight, SPACING);	
		packageGUI(panelWidth, panelHeight, myNodes); 
	}

	static ArrayList<NodeShape> getNodes(int panelWidth, int panelHeight, int spacing){
 		//create the huffman nodes at the proper coordinate
		int x;
		int y;
		double tmpVal;
		double tmpX;
		int totalHeight = completeTree.height();
		int prevX;
		int prevY; 
		ArrayList<NodeShape> myNodes = new ArrayList<NodeShape>();
		NodeShape myNode;
	    
		prevX = panelWidth/2;
		prevY = 0;
		Iterator<Map.Entry<String,String>> it = treeMap.entrySet().iterator();
		while (it.hasNext() ){
			Map.Entry<String,String> pairs = (Map.Entry<String,String>)it.next();
			tmpX = panelWidth / 2;
			for (int i = 0; i < pairs.getValue().length(); i++){
				//tmpVal is the character at i (0 or 1). i is the height - 1
				tmpVal = (double) pairs.getValue().charAt(i) - 48; 
				if (tmpVal == 0)
					tmpVal = -1;
				prevX = (int) tmpX;
				tmpX = tmpX + panelWidth* Math.pow(.5,i+2) * tmpVal;
			}
			x = (int) tmpX;
			y = (pairs.getValue().length()) * 100;
			prevY = y - 100;
			if( pairs.getValue().length() == 0){ //root node
				prevX = panelWidth/2;
				prevY = 0;
			}
			myNode = new NodeShape(prevX,prevY,x,y,pairs.getKey(),pairs.getValue());
			myNodes.add(myNode);
			it.remove();
		}
        	return myNodes;
	}

	static void packageGUI(int panelWidth, int panelHeight, ArrayList<NodeShape> myNodes){
		//initialize various GUI elements, draw
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton encode = new JButton("encode");
		JButton decode = new JButton("decode");
		JTextField textField = new JTextField(20);
		JScrollPane mcScroll = null;
		DisplayHuff mc = new DisplayHuff(myNodes,panelHeight,panelWidth);
		mcScroll = new JScrollPane(mc);
		
		textField.setText("Enter text for encoding, or binary for decoding.");
		
		//add listeners for the action buttons
		encode.addActionListener(new Listener(encodings,completeTree, textField, 0));
		decode.addActionListener(new Listener(encodings,completeTree, textField, 1));
		
		frame.setLayout(new BorderLayout());
		frame.add(textField,BorderLayout.NORTH);
		frame.add(mcScroll,BorderLayout.SOUTH);
		frame.add(panel,BorderLayout.CENTER);
		
		panel.setLayout(new FlowLayout());
		panel.add(encode);
		panel.add(decode);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	} 
}
