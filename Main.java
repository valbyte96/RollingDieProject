/****************************************************************************************
CSC 274 FINAL PROJECT
@author: Val McCulloch
@date: 5/12/2017
Description: this is the main program that should be run once all the other
program files are compiled.

The purpose of this program is to compute every possible sequence of a die
rolled on an nxn board. The die orientation is hardcoded to be a standard
right-handed die with the 1 facing up initially. This however can be changed
fairly easily in the code. Change the field "int n = ?" to choose dimensions of the board.

Because this is a brute force algorithm most computers will only be able to handle
up to n=3. However very powerful computers could handle n>=4 cases.

__________________________________
Example running:                 |
_________________________________|
javac Board.java                 |
javac Tree.java                  |
javac Die.java                   |
javac Main.java                  |
java Main                        |
_________________________________|

*****************************************************************************************/

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    /**FIELDS*/
    //initialize all of your variables
    public static int n = 3; //BOARD SIZE; DEFAULT n=3
    //list for recording every possible sequence; used in tree traversal
    public static ArrayList<String> list1 = new ArrayList<String>();
    //list that stores all the viable sequences after tree traversal
    public static ArrayList<String> sList = new ArrayList<String>();
    //list that stores the different die sequences
    public static ArrayList<String> dieList = new ArrayList<String>();
    //List that stores the different arrays of die sequences
    public static ArrayList<String[][]> dieArrList = new ArrayList<String[][]>();
    //tree for generating all the sequences
    public static Tree<String> root;


    /***************************MAIN PROGRAM*****************************/
public static void main(String[] args){
    root = new Tree<String>(""); //initialize tree
	  createTree(root,n*n-1); //n^2 -1; create the tree
	  String seq = ""; //initialze blank sequence
	  traverseTree(root, seq); //traverse tree
	  testSeq(n); //narrow down to only viable sequences
    rollDie(); //roll die over viable sequences
    printDie(); //print out the final grids of sequences
    }
    /**************************HELPER FUNCTIONS***************************/

//prints out the final die grids
public static void printDie(){
	 System.out.println();
	for (int s=0; s<dieArrList.size(); s++){ //go through all the arrays
	    String[][] record = dieArrList.get(s); //get array
	    for (int i=n-1; i>=0; i--){
		      for (int j=0; j<n; j++){
		          System.out.print(record[j][i]+" ");
				 }
		       System.out.println();
	    }
	    System.out.println("Traversal Order: "+dieList.get(s)); //get corresponding sequence
	    System.out.println();
	}
    }

    //roll Die over sequences
    public static void rollDie(){
	for(int s=0; s<sList.size(); s++){
	    Die tDie = new Die("1","6","5","2","4","3"); //create a new die to traverse each sequence
	    String[][] record = new String[n][n]; //initalize array
	    record[0][0]="1"; //plug in default start at one CHANGE THIS IF CHANGE DIE ORIENTATION
	    String seq = sList.get(s); //get sequence
	    String L; //going to be a letter
	    String v; //value
	    int x=0; //coordinates in the array
	    int y=0;
	for (int i=0; i<seq.length(); i++){
	    L=seq.substring(i,i+1);
      //go through each sequence value and change array accordingly
	    if (L.equals("U")){
		      y+=1;
		      v=tDie.rollForward();
		      record[x][y]=v;
	    }
	    else if (L.equals("D")){
		      y+=-1;
		      v=tDie.rollBackward();
		      record[x][y]=v;
	    }
	    else if (L.equals("R")){
		      x+=1;
		      v=tDie.rollRight();
		      record[x][y]=v;
	    }
	    else if (L.equals("L")){
		      x+=-1;
		      v=tDie.rollLeft();
		      record[x][y]=v;
	    }
	   }
	   dieList.add(tDie.getSeq());
	   dieArrList.add(record); //save the array
	   }
    }

    //This function recursively creates a tree
    public static void createTree(Tree<String> parent, int order){

	     if (order<=0){
	        return;
	       }
	        else{
            //create null subtrees
	           Tree<String> left = new Tree<String>("L");
	           Tree<String> mLeft = new Tree<String>("D");
	           Tree<String> mRight = new Tree<String>("U");
	           Tree<String> right = new Tree<String>("R");

	            //add trees to parent
	            parent.setLeft(left);
 	            parent.setMLeft(mLeft);
	            parent.setMRight(mRight);
	            parent.setRight(right);

	            //recurse
	           createTree(left, order-1);
	           createTree(mLeft, order-1);
	           createTree(mRight, order-1);
	           createTree(right, order-1);
	          }
          }

    //recursively traverses tree
    public static void traverseTree(Tree<String> node, String seq){
      //stopping condition
	     if(node.getLeft()==null && node.getMLeft()==null && node.getMRight()==null && node.getRight()==null){//since tree is symmetric; only one stopping condition
	        seq = seq + node.getData(); //add final root value to sequence
	        list1.add(seq); //record final sequence in list1
	       }
	        else{
	           seq = seq + node.getData(); //add root value to sequence
             //recurse
	           traverseTree(node.getLeft(), seq);
	           traverseTree(node.getMLeft(), seq);
	           traverseTree(node.getMRight(), seq);
	           traverseTree(node.getRight(), seq);
	          }
    }


    //tests sequence to see if it is vaiable
    public static void testSeq(int n){
      //create variables
	     Board board;
	     String clist;
	     String move;
	     boolean t;
	     for (int i =0 ; i<list1.size(); i++){ //loop through all sequences
	     board = new Board(n,n); //initialze board
	     t = true;
	     for (int j=0; j<list1.get(i).length(); j++){ //loop through each letter in sequence
		       clist = list1.get(i); //sequence
		       move = clist.substring(j,j+1); //get letter
		         if (move.equals("U")){
		            board.move(0,1); //move
		              if (!check(board, n)){ //if it leaves board
			                 t=false;
		                   }
		                     else{
			                        board.updateArray(); //update t/f
		                          }
		                          }
		        else if (move.equals("R")){
		            board.move(1,0);
		              if (!check(board, n)){ //if it leaves board
			                 t=false;
			                  }
		              else{
			                 board.updateArray(); //update t/f
		                   }
                    }
		        else if (move.equals("D")){
		            board.move(0,-1);
		            if (!check(board, n)){ //if it leaves board
			               t=false;
		                 }
		            else{
			               board.updateArray(); //update t/f
		                 }
                    }
		        else if (move.equals("L")){
		            board.move(-1,0);
		              if (!check(board, n)){ //if it leaves board
			                 t=false;
		                   }
		                     else{
			                        board.updateArray(); //update t/f
		                          }
                    }

					    }
	             if(t==true && board.checkArray()){ //if all squares were reached it's a viable sequence
		               sList.add(list1.get(i));

	           }
	            else{
	    }
	   }
    }

    //helper function that checks if position has left the board
    public static boolean check(Board board, int n){
	if (board.getX()<0||board.getY()<0||board.getX()>=n||board.getY()>=n){//if it leaves the board
	    return false;
	}
	return true;
    }

}
