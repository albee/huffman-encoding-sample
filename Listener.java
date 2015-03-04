//Keenan Albee
//3/3/15
//Listens for interaction with the GUI buttons, and begins Huffman encoding/decoding of text in entry box

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Listener implements ActionListener {

    private JTextField textField;
    private HashMap<String,String> encodings;
    private static BinaryTree completeTree;
    private int ind;

    public Listener(HashMap<String,String> inEncodings, BinaryTree inCompleteTree, JTextField aTextField, int inInd) {
     	textField = aTextField;
     	encodings = inEncodings;
     	completeTree = inCompleteTree;
     	ind = inInd;
    }

    public void actionPerformed(ActionEvent ae) {
    	if (ind == 0)
    		textField.setText(wordsToCode(textField.getText()));
    	else if (ind == 1)
    		textField.setText(codeToWords(textField.getText()));
    	else
    		textField.setText("tree");
    }
    
    private String wordsToCode(String words){
     	//converts words to Huffman encoding
     	String out = "";
     	char tmp;
     	for (int i = 0; i < words.length(); i++){
     		tmp = words.charAt(i);
     		if (tmp == ' ') { //account for spaces
     			if (encodings.get("sp") == null){
     				System.out.println("Invalid character entered.");
     				return "invalid character entered.";
     			}
     			out += encodings.get("sp");
     		}
     		else{
                    if (encodings.get(Character.toString(tmp)) == null){
     			     System.out.println("Invalid character entered.");
          			return "Invalid character entered. Make sure characters entered are" +
                             " in the Huffman tree.";
                    }
     			out += encodings.get(Character.toString(tmp));
               }
     	}
     	return out;
    }
    
    public static String codeToWords(String code){
     	//converts Huffman encoding to words
     	String out = "";
     	char tmp;
     	BinaryNode currentRoot = completeTree.getRoot();
     	for (int i = 0; i < code.length(); i++){
     		//if is a valid node, add its character to the string and reset root
     		tmp = code.charAt(i);
     		if (tmp == '0'){
     			currentRoot = currentRoot.getLeft();
     		}
     		else if (tmp == '1'){
     			currentRoot = currentRoot.getRight();
     		}
               else{
                    System.out.println("Invalid enconding entry.");
                    return "Invalid encoding entry. Encoding must be in binary!";
               }
     		if (currentRoot.getElement().isValid == 1){
     			if (currentRoot.getElement().theChar == "sp") //account for spaces
     				out += " ";
     			else
     				out += currentRoot.getElement().theChar;
     			currentRoot = completeTree.getRoot(); //go back to tree root, repeat
     		}
     	}
     	return out;
    }

}
