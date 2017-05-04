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
	default : System.out.println ("Error: Invalid playertype!");
	    return;
        }
	
        switch (args[3]) {
	case "comp" : secondPlayer = new ComputerPlayer('o');
	    break;
	case "human": secondPlayer = new HumanPlayer('o');
	    break;
	case "rand" : secondPlayer = new RandomPlayer('o');
	    break;
	default :     System.out.println ("Error: Invalid playertype!");
	    return;
        }
	
	// Construct the Game Tree as specified
	GameTree gt = new GameTree (new HexBoard(rows, cols), '*');
	
	// Play the game
	if((firstPlayer instanceof ComputerPlayer||
	    firstPlayer instanceof RandomPlayer) &&
	   (secondPlayer instanceof ComputerPlayer ||
	    secondPlayer instanceof RandomPlayer )){

	       System.out.println("How many games do you want the computers to play?");
	    
	    Scanner scan = new Scanner (System.in);
	    int reps = scan.nextInt();
	    int playerOneWins = 0;
	    int playerTwoWins = 0;
	    while(reps>0){
		
		Player temp = firstPlayer.play(gt,secondPlayer);

		if(temp == firstPlayer){
		    playerOneWins++;
		}else if(temp != secondPlayer){
		    playerTwoWins++;
		}
		reps--;
	    }

	    System.out.println("Computer one wins: " + playerOneWins);
	    	    
	    System.out.println("Computer two wins: " + playerTwoWins);
	    
	}	else{
	    firstPlayer.play(gt, secondPlayer);
	}
    }
}
