import java.util.Scanner;
import java.util.Iterator;

/**
 *  SHIVAM PATEL & EDDY VARELA // WEDNESDAY PM
 *
 *  HumanPlayer.java
 *
 *  Defines the play behavior for a human player
 * 
 **/
public class HumanPlayer implements Player {

    /* The player's board symbol */
    protected char player;

    /** Constructor */
    public HumanPlayer(char m) {this.player = m;}

    /** Returns this player's symbol */
    public char getSymbol() {return player;}

    /** Returns false because this player is not automated */
    public boolean isComp() {return false;}

    /**
     * @pre node != null and opponent != null
     * @post asks the player for a move and plays it, or admits defeat
     */
    public Player play(GameTree node, Player opponent) {

	// Print the current board state
	System.out.println(node.getBoard());
	
	// Scenario analysis: did this player lose?
	if (node.getBoard().win(opponent.getSymbol()) ||
            node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    return opponent;
	} else {
            // Present all possible moves
            Iterator iter = node.getBoard().moves(player).iterator();
            int j = 1;
	    while (iter.hasNext()) {
                    System.out.println(j+". "+iter.next());
                    j++;
            }
            
            // Listen for a selection
            Scanner scan = new Scanner(System.in);
	    int i = scan.nextInt();
	    
	    while (true) {
		if(i > 0 && i <= node.getChildren().size()){
		    node = node.getChildren().get(i - 1);
		    break;
		} else {
		    System.out.println("Nope! Try again.");
                    i = scan.nextInt();
		}
	    }
            
	    // Print out the new board
	    System.out.println(node.getBoard());
	    return opponent.play(node, this);
	}
    }	
}
