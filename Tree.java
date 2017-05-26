/**Tree.java
 *@author: Val McCulloch
 *@date: 5/12/2017
 *
 *Creates a tree class to compute all possible values of 4^n-1
 *Similar to a Binary tree except each node will have 4 children (besides leaves)
 */

public class Tree<E>{
    //Value stored in node; the direction die will roll
    private E data;
    //far left
    private Tree<E> left;
    //far right
    private Tree<E> right;
    //middle left
    private Tree<E> mLeft;
    //middle right
    private Tree<E> mRight;

    /**This constructor creates a leaf node */
    public Tree(E data){
	this.data = data;
	this.left = null;
	this.right = null;
	this.mLeft = null;
	this.mRight = null;
    }

    /**This constructor creates a branch node */
    public Tree(E data, Tree<E> left, Tree<E> mLeft, Tree<E> mRight,Tree<E> right){
	this.data=data;
	this.left = left;
	this.right = right;
	this.mLeft = mLeft;
	this.mRight = mRight;
    }

    /**********************GETTER METHODS***********************/

    //Accessor for node data
    public E getData(){
	return this.data;
    }

    //Accessor for left child
    public Tree<E> getLeft(){
	return this.left;
    }
    //Accessor for middle left child
    public Tree<E> getMLeft(){
	return this.mLeft;
    }
    //Accessor for middle right child
    public Tree<E> getMRight(){
        return this.mRight;
    }
    //Accessor for right child
    public Tree<E> getRight(){
        return this.right;
    }

    /**********************SETTER METHODS***********************/
    //Manipulator for node data
    public void setData(E data){
	this.data = data;
    }

    //Manipulator for left child
    public void setLeft(Tree<E>left){
	this.left=left;
    }
    //Manipulator for middle left
    public void setMLeft(Tree<E>mLeft){
	this.mLeft=mLeft;
    }
    //Manipulator for middle right
    public void setMRight(Tree<E>mRight){
	this.mRight=mRight;
    }
    //Manipulator for right
    public void setRight(Tree<E>right){
	this.right=right;
    }



    /**
     *  Indents by the specified number of spaces
     *  @param n  Number of spaces
     */
    private static void indent(int n) {
	for (int i = 0; i < n; i++) {
	    System.out.print(' ');
	}
    }

    /** Prints the tree nodes with indentation indicating level */

    public void print() {
	System.out.print("Root:  ");
	printIndented(0);
    }

    /** 
     *  Prints the tree with indentation corresponding to level.
     *  Basically a fancy version of preorder.
     *
     *  @param depth  Level of this node, for indentation
     */
    public void printIndented(int depth) {
	System.out.println(data);
	depth++;
	if (this.left != null) {
	    indent(2*depth);
	    System.out.print("Left:  ");
	    this.left.printIndented(depth);
	}
	if (this.mLeft != null) {
            indent(2*depth);
            System.out.print("mLeft:  ");
            this.mLeft.printIndented(depth);
        }
	if (this.mRight != null) {
            indent(2*depth);
            System.out.print("mRight:  ");
            this.mRight.printIndented(depth);
        }
	if (this.right != null) {
	    indent(2*depth);
	    System.out.print("Right:  ");
	    this.right.printIndented(depth);
	}
    }

}
