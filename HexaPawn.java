import java.util.Scanner;

/**
 *  SHIVAM PATEL (+2 Design Doc) & EDDY VARELA (+2 Design Doc) // WEDNESDAY PM
 *
 *  HexaPawn.java
 *
 *  A simple six pawn variant of the classic chess board game where
 *  the objective is simply to reach the other side of the board or
 *  to eliminate all opponent pieces or moves
 *
 **/
public class HexaPawn {
    
    public static void main (String[] args) {
	// The two players
        Player firstPlayer, secondPlayer;
	
	// The dimensions for the board
	int rows, cols;
	
	// Input check
        if (args.length != 4) {
            System.out.println ("Usage: java HexaPawn <row> <col> <playertype> <playertype>");
            return;
        } else try {
                rows = Integer.parseInt(args[0]);
		cols = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println ("Usage: java HexaPawn <row> <col> <playertype> <playertype>");
                return;
            }
	
	if (rows < 0 || cols < 0) {
	    System.out.println("Error: Dimensions cannot be negative");
	    return;
	}
	
	switch (args[2]) {
	case "comp" : firstPlayer = new ComputerPlayer('*');
	              break;
	case "human": firstPlayer = new HumanPlayer('*');
	              break;
	case "rand" : firstPlayer = new RandomPlayer('*');
	              break;
	default     : System.out.println ("Error: Invalid playertype!");
	              return;
        }
	
        switch (args[3]) {
	case "comp" : secondPlayer = new ComputerPlayer('o');
	              break;
	case "human": secondPlayer = new HumanPlayer('o');
	              break;
	case "rand" : secondPlayer = new RandomPlayer('o');
	              break;
	default     : System.out.println ("Error: Invalid playertype!");
               	      return;
        }
	
	// Construct the Game Tree as specified
	GameTree gt = new GameTree (new HexBoard(rows, cols), '*');

        // Read in inputs
	Scanner scan = new Scanner (System.in);

        // Is there a human player?
	if((firstPlayer.isComp() && secondPlayer.isComp())) {
            // No human players
            
	    System.out.println("How many games do you want the computers to play?");
	    
	    int playerOneWins = 0;
            int playerTwoWins = 0;
            
	    for (int reps = scan.nextInt(); reps > 0; reps--) {	
		Player winner = firstPlayer.play(gt,secondPlayer);
		if (winner == firstPlayer) playerOneWins++;
		if (winner == secondPlayer) playerTwoWins++;
            }
	    
	    System.out.println("Computer one wins: " + playerOneWins +
                               "\nComputer two wins: " + playerTwoWins);
	    
	} else do {
                // Yes, at least one human player
                
                Player winner = firstPlayer.play(gt, secondPlayer);
                
                System.out.println("(" + winner.getSymbol() + ") has won!" +
                                   "\nPlay again? y/n");
                
	    } while (scan.next().equals("y"));
    }
}
