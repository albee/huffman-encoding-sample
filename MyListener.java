//Keenan Albee
//kea2134
//11/26/14

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class MyListener implements ActionListener {

    private JTextField textField;
    private HashMap<String,String> encodings;
    private static BinaryTree completeTree;
    private int ind;

    public MyListener(HashMap<String,String> inEncodings, BinaryTree inCompleteTree, JTextField aTextField, int inInd) {
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
				if (encodings.get(Character.toString(tmp)) == null){
					System.out.println("invalid character entered.");
					return "invalid character entered.";
				}
				out += encodings.get("sp");
			}
			else if (encodings.get(Character.toString(tmp)) == null){
				System.out.println("invalid character entered.");
				return "invalid character entered.";
			}
				out += encodings.get(Character.toString(tmp));
		}
		//need to account for huffman w/o values
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
			else{
				currentRoot = currentRoot.getRight();
			}
			if (currentRoot.getElement().isValid == 1){
				if (currentRoot.getElement().theChar == "sp") //account for spaces
					out += " ";
				else
					out += currentRoot.getElement().theChar;
				currentRoot = completeTree.getRoot();
			}
		}
		return out;
	}

}
