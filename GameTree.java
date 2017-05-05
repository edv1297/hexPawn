import java.util.Random;
import structure5.*;

/**
 *  SHIVAM PATEL & EDDY VARELA // WEDNESDAY PM
 *
 *  GameTree.java
 *
 *  Defines the behavior for a Hex-a-Pawn GameTree, which contains
 *  a representation for every move and outcome possible
 *
 **/
public class GameTree {

    /* The parent node of this GameTree */
    protected GameTree parent;
    
    /* The initial game board */
    protected HexBoard startBoard;

    /* Stores every legal move for the player given the starting board */
    protected Vector<HexMove> possibleMoves;

    /* The resulting GameTree nodes after some move */
    protected Vector<GameTree> children;

    /* The symbol for the moving player */
    protected char player;

    /**
     *  Constructor for the GameTree object
     *  @pre start != null
     *  @post returns a GameTree containing all possible games resulting
     *        from the given start board
     */
    public GameTree(HexBoard start, char m) {

	// Initialize the root as the given HexBoard
	this.startBoard = start;
	this.player = m;
	this.possibleMoves = start.moves(m);
	
	// Initialize the children as the possible moves for 'm' from 'start'
	this.children = new Vector<GameTree>();
	this.populateChildren(this.startBoard, this.possibleMoves);
	
    }

    public GameTree(GameTree parent, HexBoard start, char m) {
	// Initialize the root as the given HexBoard
	this.parent = parent;
	this.startBoard = start;
	this.player = m;
	this.possibleMoves = start.moves(m);
	
	// Initialize the children as the possible moves for 'm' from 'start'
	this.children = new Vector<GameTree>();
	this.populateChildren(this.startBoard, this.possibleMoves);
    }
    
    /**
     *  Recursive function that populates the children of a given game board
     *  @pre start != null
     *  @post populates the GameTree with every possible game
     */
    private void populateChildren(HexBoard start, Vector<HexMove> moves) {
	
	// Return when there are no more children to add
	if (moves.isEmpty()) return;

	// Populate the children (and their children) of root
	for (HexMove hm : moves) children.add(new GameTree(this,
							   new HexBoard(this.startBoard, hm),
							   this.startBoard.opponent(player)));
    }

    /** Returns this GameTree's HexBoard */
    public HexBoard getBoard() {return this.startBoard;}

    /** Returns the children of this GameTree */
    public Vector<GameTree> getChildren() {return this.children;}

    /** Returns a specific child */
    public GameTree getChild(HexBoard resultBoard) {
	for (GameTree gt : children) if (gt.getBoard().equals(resultBoard)) return gt;
	return null;
    }
    
    /** Returns the parent node of this GameTree*/
    public GameTree getParent() {return parent;}

    /** Returns a good move to make */
    public GameTree makeAMove() {
	Random r = new Random();
	return children.get(r.nextInt(children.size()));
    }

    /** Remove this leaf from the the gameTree */
    public void removeParent() {
	this.parent.getParent().getChildren().remove(parent);
    }

    /** Returns a string representation for the GameTree */
    public String toString() {
	String output = "";

	for (GameTree gt : children) {
	    for (GameTree ch : gt.getChildren()) {
		output += ch.toString();
	    }
	}

	output += startBoard.toString();

	return output;
    }

    /**
     *  Simple test function
     */
    public static void main(String[] args) {	
	GameTree gt = new GameTree(new HexBoard(), '*');
	System.out.println(gt.toString());
    }

    
}
