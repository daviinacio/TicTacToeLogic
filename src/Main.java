import java.util.*;

public class Main {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException {
		TicTacToe game = new TicTacToe(new int[3][3]);
		
		Machine machine = new Machine();
		
		int player = new Random().nextInt(2) + 1;
		
		game.cleanField();
		
		System.out.println((player == ValidationResult.STATE_PLAYER1 ? "You start" : "Machine starts") + " the game");
		
		Thread.sleep(1500);
		
		while(true){
			for(int i = 0; i < 32; i++)
				System.out.println();
				
			game.dumpState();
			
			Position choise = null;
			
			//input.match();
			
			// Human player
			if(player == ValidationResult.STATE_PLAYER1){
				try {
					//System.out.println(input.hasNextLine());
					
					System.out.print("x: ");
					int x = input.nextInt();

					System.out.print("y: ");
					int y = input.nextInt();

					choise = new Position(x, y);
				}
				catch(InputMismatchException ex){
					choise = null;
					ex.printStackTrace();
				}
			}
			else
			// Machine
			if(player == ValidationResult.STATE_PLAYER2){
				choise = machine.choose(game.getVirtualGame());
			}
			
			Thread.sleep(1000);
			
			if(!game.select(choise, player)){
				System.out.println("Invalid choise, try again!");
				Thread.sleep(2000);
				continue;
			}
			
			System.out.println((player == ValidationResult.STATE_PLAYER1 ? "Your" : "Machine") + " choise: " + choise);
			
			Thread.sleep(3000);
			
			player %= 2;
			player++;
			
			ValidationResult winner = game.validateWinner();
			
			if(winner != null){
				int [][] s = game.getVirtualState();
				
				System.out.println();
				for(int x = 0; x < s.length; x++){
					for(int y = 0; y < s[x].length; y++){
						System.out.print("[");
						if(winner.getPositions().contains(new Position(x, y))){
						char [] labels = new char[]{' ', 'X', 'O'};
							System.out.print(labels[winner.getWinner()] + "] ");
						}
						else
							System.out.print(" ] ");
					}
					System.out.println();
				}
				
				System.out.println((winner.getWinner() == ValidationResult.STATE_PLAYER1 ? "You" : "Machine") + " won!");
				break;
			}
			else
			if(game.findEmptyPositions().size() == 0){
				System.out.println("\nNo one won the game!");
				break;
			}
		}
		
		
		
		/*game.dumpState();
		
		System.out.println("\nValidation: " + game.validateWinner());
		
		//System.out.println(game.findEmptyPositions());.
		
		Position mChoose = machine.choose();
		
		System.out.println("\nMachine choose: " + mChoose);
		
		game.select(mChoose, ValidationResult.STATE_PLAYER2);
		
		game.dumpState();
		
		System.out.println("\nValidation: " + game.validateWinner());*/
	}
}


