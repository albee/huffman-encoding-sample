# huffman_encoding_sample

##Code Explanation

The program EncodeText performs Huffman coding (commonly used in data compression) of text from a sample input file. A GUI displaying the Huffman tree is created, with the option of displaying the Huffman coding of entered words. More information on Huffman coding can be found [here](https://en.wikipedia.org/wiki/Huffman_coding).

In short, Huffman coding creates a binary encoding of characters in a sample file, with smaller coded values for characters that are used more frequently in the sample to be encoded. To interpret the encoding created by this sample, follow the binary tree: starting at the root, go left for every '0' and right for every '1'. Record the character and return to the root once a leaf node is reached.

Also note that the sample file to be encoded provided in this repo, test1.txt, contains the alphabet and some punctuation. Some characters are repeated to make a more intesting Huffman tree. However, this code is able to create a Huffman tree for any input file.

##Contents of Repo

####Main Program: EncodeText
To run: Run Problem1 from the command line with a single text file as an argument (e.g. Problem1 test1.txt)

####Problem1:
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
