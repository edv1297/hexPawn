<<<<<<< HEAD
//SSP-EDV 

public class ComputerPlayer implements Player {

    protected char player;

    protected String result = "";

    public ComputerPlayer(char m) {
	this.player = m;
    }

    public char getSymbol() {return player;}

    public boolean isComp() {return true;}

    public String getResult(){return this.result;}
    
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(this.player)) {
	    // The game is won, celebrate!
	    System.out.println("The computer (" + player + ") wins!");
	    result += "win";
	    return this;
	} else if (node.getBoard().win(opponent.getSymbol()) ||
		   node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    node.removeParent();
	    System.out.println("The computer has lost!");
	    result += "loss";
	    return opponent.play(node,this);
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }
}
=======
//SSP-EDV 

public class ComputerPlayer implements Player {

    protected char player;
    
    public ComputerPlayer(char m) {
	this.player = m; 
    }

    public char getSymbol() {
	return player;
    }
    
    public Player play(GameTree node, Player opponent) {
	
	// Scenario analysis: win, loss, or neither?
	if (node.getBoard().win(this.player)) {
	    // The game is won, notify the other player and celebrate!
	    System.out.println("The computer has won!");
	    return this;
	} else if (node.getChildren().isEmpty()) {
	    // The game is lost, admit defeat
	    node.removeParent();
	    System.out.println("Computer has lost");
	    return opponent;
	} else {
	    // Make a move
	    node = node.makeAMove();
	    return opponent.play(node, this);
	}
    }
}
>>>>>>> 252149d951b25b73f4c545b1f6b15857fff4c5cc
