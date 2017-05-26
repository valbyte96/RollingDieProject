/**Board.java
 @author: Val McCulloch
 @date: 5/12/2017
 CLASS DESCRIPTION: Creates a board object that will be used main function*/
import java.util.Arrays;

public class Board{
    //create and initialize variables
    private boolean[][] boolArray; //keeps track of reached squares
    private int positionX = 0; //start at (0,0)
    private int positionY = 0;
    private int xSize; //xSize=ySize nxn board
    private int ySize;

    //Board constructor
    public Board(int xSize, int ySize){ //maybe add start point
	     this.xSize = xSize; //initalize board size
	     this.ySize = ySize;
	     this.boolArray = new boolean[xSize][ySize]; //initalize array
	      //fill boolean array with false
	       for(int i=0; i<xSize; i++){
	          for(int j=0; j<ySize; j++){
		            boolArray[i][j]=false;
	             }
	            }
	             //make initial position equal to true
	              boolArray[positionX][positionY]=true;
              }
    /************SETTER METHODS******************/
    //updates current position on board
    public void move(int dx, int dy){
	     this.positionX+=dx;
	      this.positionY+=dy;
    }
    //update array values with true
    public void updateArray(){
	     this.boolArray[this.positionX][this.positionY]=true;
    }

    /************GETTER METHODS*********************/
    //get y value of position
    public int getY(){
	     return this.positionY;
    }
    //get x value of position
    public int getX(){
	     return this.positionX;
    }
    //get x dimension
    public int getSizeX(){
	     return this.xSize;
    }
    //get y dimension
    public int getSizeY(){
	     return this.ySize;
    }
    //gets if a square has been reached
    public boolean isTouched(int i, int j){
	     return boolArray[i][j];
    }

    //Check if any of the board squares of false
    public boolean checkArray(){
	     for(int i=0; i<this.xSize; i++){
		 for(int j=0; j<this.ySize; j++){
		            if (boolArray[i][j]==false){
		                return false;
		                }
	                 }
	     }
	                  return true;
                  }
}
