//Keenan Albee
//kea2134
//11/26/14

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

public class Problem1 {
	
	static HashMap<String,String> encodings;
	static HashMap<String,String> treeMap;
	static BinaryTree completeTree;

	public static void main(String[] args) throws Overflow, IOException {
		File inFile;
		BufferedReader scan;
		int currentInt;
		final int CAP = 128;
		HashMap<String,HuffNode> map = new HashMap<String, HuffNode>(CAP);
		HuffNode tmp;
		BinaryTree HuffTree;
		char current;
		int readTemp;
		String keyVal = null;
		
		if (args.length==1) {
			inFile = new File(args[0]);
			scan = new BufferedReader(new FileReader(inFile));
			
			//read in the file character by character
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
			//create binary heap queue of 1-node Huffman trees
			BinaryHeap MyHeap = new BinaryHeap( CAP );
			for (Map.Entry<String,HuffNode> entry : map.entrySet()) {
				  HuffNode value = entry.getValue();
				  HuffTree = new BinaryTree(value);
				  MyHeap.insert(HuffTree);
			}
			
			//create final Huffman tree
			int cntr = 0;
			for (; ;){
				BinaryTree num1 = MyHeap.deleteMin(); //set the smaller element as the left
				BinaryTree num2 = MyHeap.deleteMin();
				//creates a filler huffNode
				tmp = new HuffNode("T"+ Integer.toString(++cntr), num1.getRoot().getElement().theFreq + num2.getRoot().getElement().theFreq,0);
				//merge the trees around the new Huffman root node
				num1.merge(tmp,num1,num2);
				if (MyHeap.isEmpty()){
					completeTree = num1;
					break;
				}
				else
					MyHeap.insert(num1);
			}
			
			//print out the Huffman encondings map
			encodings = completeTree.getRoot().getHuffman(completeTree.size());
			treeMap = completeTree.getRoot().getTree(completeTree.size());
			System.out.println(encodings);
			
			//display the tree as a gui
		    JFrame frame = new JFrame();
		    JPanel panel = new JPanel();
		    JButton encode = new JButton("encode");
		    JButton decode = new JButton("decode");
		    JTextField textField = new JTextField(20);
		    NodeShape myNode;
		    ArrayList<NodeShape> myNodes = new ArrayList<NodeShape>();
		    DisplayHuff mc = null;
		    
		    int panelWidth;
		    int panelHeight;
		    final int spacing = 55; //minumum spacing b/n nodes (decreases if very wide)
		    int x;
		    int y;
		    double tmpVal;
		    double tmpX;
		    int totalHeight = completeTree.height();
		    Iterator<Map.Entry<String,String>> it = treeMap.entrySet().iterator();
		    
		    panelHeight = (completeTree.height()+1)*100;
		    panelWidth = (int) ((Math.pow(2,totalHeight) - 1)*spacing);
		    if (panelWidth > 3000){
		    	panelWidth = 3000;
		    }
		    
		    int prevX = panelWidth/2;
		    int prevY = 0;
		    	
		    //create the huffman nodes at the proper coordinates
		    while (it.hasNext() ){
		    	Map.Entry<String,String> pairs = (Map.Entry<String,String>)it.next();
		    	tmpX = panelWidth / 2;
		    	for (int i = 0; i < pairs.getValue().length(); i++){
		    		tmpVal = (double) pairs.getValue().charAt(i) - 48; //character at i (0 or 1). i is the height - 1
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
		    mc = new DisplayHuff(myNodes,panelHeight,panelWidth);
		    JScrollPane mcScroll = new JScrollPane(mc);
		    
		    textField.setText("Enter your decodin' or encodin' things here.");
		    
		    //listeners for the action buttons
		    encode.addActionListener(new MyListener(encodings,completeTree, textField, 0));
		    decode.addActionListener(new MyListener(encodings,completeTree, textField, 1));
		    
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

		else
			System.out.println("you dun goofed");
	}
}
