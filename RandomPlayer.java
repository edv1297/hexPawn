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
