/**
 *  SHIVAM PATEL & EDDY VARELA // WEDNESDAY PM
 *
 *  RandomPlayer.java
 *
 *  Defines the play behavior for an unintelligent
 *  computer player
 * 
 **/
public class RandomPlayer implements Player {

    /* The player's board symbol */
    protected char player;
    
    /** Constructor */
    public RandomPlayer(char m) {this.player = m;}

    /** Returns this player's symbol */
    public char getSymbol() {return player;}

    /** Returns true because this player is automated */
    public boolean isComp() {return true;}

    /**
     * @pre node != null and opponent != null
     * @post Plays a move or admits defeat
     */
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: did this player lose?
        if (node.getBoard().win(opponent.getSymbol()) ||
            node.getChildren().isEmpty()) {
            // The game is lost, admit defeat
            return opponent;
        } else {
            // Make a move
            node = node.makeAMove();
            return opponent.play(node, this);
        }
    }
}
