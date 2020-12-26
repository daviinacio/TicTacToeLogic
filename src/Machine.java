
import java.util.*;
import java.util.concurrent.*;

public class Machine {
	private boolean debug = false;
	
	public Machine() {}
	
	public Position choose(TicTacToe game){
		if(game == null) return null;
		
		List<Position> emptyPositions = game.findEmptyPositions();
		
		// Find the last final attach (to win or to defense)
		for(int player = 2; player > 0; player--){
			for(Position pos : emptyPositions){	
				int [][] virtual = game.getVirtualState();

				virtual[pos.getX()][pos.getY()] = player;

				ValidationResult result = TicTacToe.validateWinner(virtual);
				
				if(result != null && result.getWinner() == player)
					return pos;
			}
		}
		
		// Find available and valid positions
		int c = new Random().nextInt(emptyPositions.size());
		
		if(debug)
			System.out.println("Machine: It was random :(");
		
		return emptyPositions.get(c);
	}
	
	public void enableDebug(){
		this.debug = true;
	}
}
