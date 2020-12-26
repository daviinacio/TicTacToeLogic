import java.io.IOException;
import java.util.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        TicTacToe game = new TicTacToe(new int[3][3]);
        
        Machine machine = new Machine();
        
        int player = new Random().nextInt(2) + 1;
        
        game.cleanField();
        
        Main.clearScreen();
        
        System.out.println((player == ValidationResult.STATE_PLAYER1 ? "You start" : "Machine starts") + " the game\n");
        
        Thread.sleep(1500);
        
        while(true){
            Main.clearScreen();
            
            // Show the state
            game.dumpState();
            
            // Define/reset the choise
            Position choise = null;
            
            System.out.println();
            
            // Human player
            if(player == ValidationResult.STATE_PLAYER1){
                System.out.print("x: ");
                int x = input.nextInt();

                System.out.print("y: ");
                int y = input.nextInt();

                choise = new Position(x, y);
            }
            else
            // Machine
            if(player == ValidationResult.STATE_PLAYER2){
                choise = machine.choose(game.getVirtualGame());
            }
            
            // Wait for choise
            Thread.sleep(1000);
            
            // Incorrect choise
            if(!game.select(choise, player)){
                System.out.println("Invalid choise, try again!");
                Thread.sleep(2000);
                continue;
            }
            
            // Show the choise
            if(player == ValidationResult.STATE_PLAYER2){
                System.out.println((player == ValidationResult.STATE_PLAYER1 ? "Your" : "Machine") + " choise: " + choise);
                Thread.sleep(3000);
            }
            
            player %= 2;
            player++;
            
            ValidationResult winner = game.validateWinner();
            
            if(winner != null){
                Main.clearScreen();
                        
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
                
                System.out.println("\n" + (winner.getWinner() == ValidationResult.STATE_PLAYER1 ? "You" : "Machine") + " won!");
                break;
            }
            else
            if(game.findEmptyPositions().isEmpty()){
                System.out.println("\nNo one won the game!");
                break;
            }
        }
    }
    
    private static void clearScreen() throws IOException, InterruptedException{
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
}


