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
 *  ***THOUGHT QUESTIONS***
 *
 *  1. In a R x C tree, where R is a row number and C is the number of columns, we derived
 *  that the resulting number of moves was (R^C+1)(R^(C-1)) = 252 for a 3x3 board. For a
 *  3x4 board there would be (3^4 +1)(3^3) = 2214 moves. In a 3x5 board we would have
 *  (3^5+1)(3^4) = 19764 moves. 
 *
 *  2. If you have two computers pitted against each other and H.I.M. makes the first move,
 *  based on our design, H.I.M. will ultimately win more games. The first player will remove
 *  bad moves by removing the parent of its parent. The second computer will rarely make winning
 *  moves because it will concede early if it "knows" that there are no move possible winning 
 *  moves left. The first computer prunes the parent of the parent which does not allow the second
 *  machine to have any winning moves so it will result to conceding fairly frequently.
 *  In larger boards, the ratio will be more balanced for a small number of games but first computer will
 *  eventually have the same ratio when you scale the number of games played because the GameTree would
 *  be more effectively pruned.
 * 
 **/

public class HexaPawn {
    
    public static void main (String[] args) {
	// The two players
        Player firstPlayer, secondPlayer;
	
	// The dimensions for the board
	int rows, cols;
	
	// Input check to make sure that the user has given the right amount of information
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

	//corrrect dimensions
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
	
	// Construct the GameTree as specified
	GameTree gt = new GameTree (new HexBoard(rows, cols), '*');

        // Read inputs
	Scanner scan = new Scanner (System.in);

        // Is there a human player?
	if((firstPlayer.isComp() && secondPlayer.isComp())) {
            
	    // No human players
	    System.out.println("How many games do you want the computers to play?");
	    
	    //holds the value of the computer wins, for printing methods
	    int playerOneWins = 0;
            int playerTwoWins = 0;
            
	    for (int reps = scan.nextInt(); reps > 0; reps--) {	
		//our design will return the player who loses.
		Player loser = firstPlayer.play(gt,secondPlayer);
		if (loser == firstPlayer) playerTwoWins++;
		if (loser == secondPlayer) playerOneWins++;
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
