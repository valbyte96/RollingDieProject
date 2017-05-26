/**Die.java
 *@author: Val McCulloch
 *@date: 5/12/2017
 *
 *creates a die that we can use to generate sequences                                          */

public class Die{
    private String seq; //keeps track of numerical sequences; mostly for debugging
    private String up;
    private String down;
    private String front;
    private String back;
    private String right;
    private String left;

    public Die(String up, String down, String front, String back, String right, String left){
	this.up=up;
	this.down=down;
	this.front=front;
	this.back=back;
	this.right=right;
	this.left=left;
	this.seq="";
	this.seq=up; //start at 1
    }

    /**Functions below roll die in 4 possible directions and logically update
    orientation values */

    public String rollForward(){
	//store the values in temporary variables
	String up = this.up;
	String down = this.down;
	String front = this.front;
	String back = this.back;

	this.up=front;
        this.front=down;
        this.down=back;
        this.back=up;

	this.seq=this.seq+this.up;
	return this.up;
    }

    public String rollBackward(){
	//store the values in temporary variables
	String up = this.up;
	String down = this.down;
	String front = this.front;
	String back = this.back;

        this.up=back;
	this.front=up;
	this.down=front;
	this.back=down;


	this.seq=this.seq+this.up;
	return this.up;

    }


    public String rollRight(){
	String up = this.up;
        String down = this.down;
        String right = this.right;
        String left = this.left;

	this.up=left;
	this.down=right;
	this.right=up;
	this.left=down;

	this.seq=this.seq+this.up;
	return this.up;
    }

    public String rollLeft(){
	String up = this.up;
	String down = this.down;
        String right = this.right;
	String left = this.left;

	this.up=right;
	this.down=left;
	this.right=down;
	this.left=up;

	this.seq=this.seq+this.up;
	return this.up;

    }

    public String getSeq(){
	return this.seq;

    }

}
