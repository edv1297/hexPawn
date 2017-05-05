<<<<<<< HEAD
public class RandomPlayer implements Player {

    /* The player's board symbol */
    protected char player;
    protected String result = "";
    
    /** Constructor */
    public RandomPlayer(char m) {this.player = m;}

    /** Returns this player's symbol */
    public char getSymbol() {return player;}

    /** Returns true because this player is automated */
    public boolean isComp() {return true;}
    
    /** */
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(opponent.getSymbol()) ||
	    node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    return  opponent;
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }

=======
public class RandomPlayer implements Player {
    protected char player;
    
    public RandomPlayer(char m) {
	this.player = m; 
    }

    /** Returns this player's symbol */
    public char getSymbol() {return player;}

    /***/
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("The random player has won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    System.out.println("The random player has lost");
	    return opponent;
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }
}
>>>>>>> 252149d951b25b73f4c545b1f6b15857fff4c5cc
