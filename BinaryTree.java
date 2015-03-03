//Keenan Albee
//kea2134
//11/26/14

//BinaryTree class; stores a binary tree.
//
//CONSTRUCTION: with (a) no parameters or (b) an object to
// be placed in the root of a one-element tree.
//
//*******************PUBLIC OPERATIONS**********************
//Various tree traversals, size, height, isEmpty, makeEmpty.
//Also, the following tricky method:
//void merge( Object root, BinaryTree t1, BinaryTree t2 )
//                     --> Construct a new tree
//*******************ERRORS*********************************
//Error message printed for illegal merges.

/**
* BinaryTree class that illustrates the calling of
* BinaryNode recursive routines and merge.
*/
public class BinaryTree implements Comparable<BinaryTree>
{
	 public BinaryTree( )
	 {
	     root = null;
	 }
	
	 public BinaryTree( HuffNode rootItem )
	 {
	     root = new BinaryNode( rootItem, null, null );
	 }
	
	 public void printPreOrder( )
	 {
	     if( root != null )
	         root.printPreOrder( );
	 }
	
	 public void printInOrder( )
	 {
	     if( root != null )
	        root.printInOrder( );
	 }
	
	 public void printPostOrder( )
	 {
	     if( root != null )
	        root.printPostOrder( );
	 }
	
	 public void makeEmpty( )
	 {
	     root = null;
	 }
	
	 public boolean isEmpty( )
	 {
	     return root == null;
	 }
	 
	 /**
	  * Merge routine for BinaryTree class.
	  * Forms a new tree from rootItem, t1 and t2.
	  * Does not allow t1 and t2 to be the same.
	  * Correctly handles other aliasing conditions.
	  */
	 public void merge( HuffNode rootItem, BinaryTree t1, BinaryTree t2 )
	 {
	     if( t1.root == t2.root && t1.root != null )
	     {
	         System.err.println( "leftTree==rightTree; merge aborted" );
	         return;
	     }
	
	         // Allocate new node
	     root = new BinaryNode( rootItem, t1.root, t2.root );
	
	         // Ensure that every node is in one tree
	     if( this != t1 )
	         t1.root = null;
	     if( this != t2 )
	         t2.root = null;
	 }
	
	 public int size( )
	 {
	     return BinaryNode.size( root );
	 }
	
	 public int height( )
	 {
	     return BinaryNode.height( root );
	 }
	
	 public BinaryNode getRoot( )
	 {
	     return root;
	 }
	 
	 /** The tree root. */
	private BinaryNode root;
	
	public int compareTo(BinaryTree otherTree){
		int output = this.root.getElement().compareTo(otherTree.root.getElement());
		return output;
	}
	
	public String toString() { 
		return "Tree root: " + root.getElement().toString();
	}
}