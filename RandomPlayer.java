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

