# huffman_encoding_sample
##Usage
####Main Program: MakeHuffCode
To get going, run MakeHuffCode from the command line with a single text file as an argument, for example:

                                            java MakeHuffCode test1.txt

The text file must contain the characters you wish to use in your Huffman coding (test1.txt contains the alphabet and some punctuation.)

The Huffman tree will be displayed, along with an encoding/decoding box. The character:encoding pairs will be printed to the terminal.

##Code Explanation

The program MakeHuffCode performs Huffman coding (commonly used in data compression) of text from a sample input file. A GUI displaying the Huffman tree is created, with the option of displaying the Huffman coding of entered words ("encode"), or decoding binary entries into characters ("decode"). More information on Huffman coding can be found [here](https://en.wikipedia.org/wiki/Huffman_coding).

In short, Huffman coding creates a binary encoding of characters (in our case, from a sample file), with shorter binary values for characters that are used more frequently in the sample. To interpret the encoding created by this sample, follow the binary tree: starting at the root, go left for every '0' and right for every '1'. Once a leaf node is reached, record the character, return to the root, and repeat.

Also note that the sample file provided in this repo, test1.txt, contains the alphabet and some punctuation. Some characters are repeated to make a more intesting Huffman tree. However, this code is able to create a Huffman tree for any input file.

##Contents of Repo

NOTE: Data structures used are written by Mark Allen Weiss.

####MakeHuffCode:
Contains main. Constructs a Huffman tree using BinaryTree by bundling information in HuffNode. BinaryHeap is used for queuing. (These classes have been made non-generic.)

Gets a map of Huffman encodings for characters and another for in-between node points.

Creates a GUI with a single textbox--enter all encoding/decoding information here and click the proper button. The textbox will be updated with the result.

The GUI tree is created using a minimum spacing between nodes at the bottom level of the tree. Starting from the middle of panelWidth, the program uses the Huffman enconding for each node to add or subtract the proper delta-x at each height level of the tree for node placement. (For larger trees, panelWidth is limited to 3000. This might lead to slight crowding at the bottom of the tree.)

####BinaryTree:
Modified version of Weiss' binary tree to hold HuffNodes.

####DisplayHuff:
Creates the tree panel. Takes in an array of trees NodeShape and draws each.

####HuffNode:
Node that stores character frequency, character, and validity of each HuffNode.

####BinaryNode:
Required for Weiss' binary tree. Added methods to return a complete map of character encodings and full tree encodings.

####Overflow:
Required exception for Weiss.

####MyListener:
Listens for 'encode' and 'decode' button presses. Contains methods to convert from string to encoding, and vice versa.

####BinaryHeap:
Binary heap from Weiss.

####NodeShape:
Contains GUI character, ellipse, enconding, and trailing line for each drawn node point. Drawn by DisplayHuff.
