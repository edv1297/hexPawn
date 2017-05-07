/**
 *  SHIVAM PATEL & EDDY VARELA // WEDNESDAY PM
 *
 *  ComputerPlayer.java
 *
 *  Defines the play behavior for an intelligent
 *  computer player
 * 
 **/
public class ComputerPlayer implements Player {

    /* The player's board symbol */
    protected char player;

    /** Constructor  */
    public ComputerPlayer(char m) {this.player = m;}

    /** Returns the player's symbol */
    public char getSymbol() {return player;}

    /** Returns true because this player is automated */
    public boolean isComp() {return true;}

    /**
     * @pre node != null and opponent != null
     * @post Plays a move or admits defeat and returns opponent
     */
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: did this player lose?
        if (node.getBoard().win(opponent.getSymbol()) ||
            node.getChildren().isEmpty()) {
            // The game is lost, learn from your mistakes and
            // admit defeat
            node.removeParent();
            return opponent;
        } else {
            // Make a move
            node = node.makeAMove();
            return opponent.play(node, this);
        }
    }
}
