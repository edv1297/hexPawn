import structure5.*;

public class GameTree {

    protected HexBoard root;
    protected Vector<HexMove> possibleMoves;
    protected Vector<GameTree> children;
    protected char player;
    public GameTree(HexBoard start, char m) {

	// Initialize the root as the given HexBoard
	this.root = start;
	this.player = m;
	this.possibleMoves = start.moves(m);
	
	// Initialize the children as the possible moves for 'm' from 'start'
	this.children = new Vector<GameTree>();
	this.populateChildren(this.root, this.possibleMoves);
	
    }
    
    /**
     * @pre start != null
     * @post populates the GameTree with every possible game
     **/
    public void populateChildren(HexBoard start, Vector<HexMove> moves) {
	
	// Return when there are no more children to add
	if (moves.isEmpty()) return;

	// Populate the children of root
	for (HexMove hm : moves) {

	    HexBoard board = new HexBoard(start, hm);
	    
	    // Add the next child of root
	    children.add(new GameTree(board, this.root.opponent(player)));
	}
    }

    /**
     *@pre given a losing board layout
     *@post remove this leaf from the the gameTree
     **/

    public void remove(GameTree outcome) {
	children.remove(outcome);
    }

    public static void main(String[] args) {	
	GameTree gm = new GameTree(new HexBoard(), '*');
    }

    
}
