import java.util.Scanner;

public class HexaPawn {
    
    public static void main (String[] args) {
	GameTree gt = new GameTree(new HexBoard(), '*');
	
    }

    public static void main(String[] args)
    {
	HexBoard b = new HexBoard(3,3); // change me!
	Vector<HexMove> moves;
	Scanner in = new Scanner(System.in);
	int yourMove;
	int myMove;
	Random gen = new Random();
	System.out.println(b);

	do
	{
	    // white to play (human): print moves
	    moves = b.moves(WHITE);
	    Iterator i = moves.iterator();
	    int j = 0;
	    while (i.hasNext())
	    {
		System.out.println(j+". "+i.next());
		j++;
	    }
	    // read move from keyboard
	    yourMove = in.nextInt();

	    // construct a new board, based on move
	    b = new HexBoard(b,moves.elementAt(yourMove));
	    System.out.println(b);

	    // if WHITE won, claim the victory and leave.
	    if (b.win(WHITE)) { System.out.println("You win!"); break; }

	    // black's move (compute): move randomly, but legally
	    // get moves
	    moves = b.moves(BLACK);
	    // pick one
	    myMove = gen.nextInt(moves.size());
	    // construct new board
	    b = new HexBoard(b,moves.elementAt(myMove));
	    System.out.println("I "+moves.elementAt(myMove));
	    System.out.println(b);
	    // claim victory, if true
	    if (b.win(BLACK)) { System.out.println("I win!"); break; }
	} while (true);
    
}