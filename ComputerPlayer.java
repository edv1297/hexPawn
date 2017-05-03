public class ComputerPlayer implements Player {
    
    protected char player;
    public ComputerPlayer(char m) {
	this.player = m; 
    }
    
    public Player play(GameTree node, Player opponent) {
	
	if(node.root.win(opponent.player)){
	    node.remove(node.root);
	    
	}else{
	    node.
		}
	return opponent;
}
