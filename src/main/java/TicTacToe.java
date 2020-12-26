
import java.util.*;

public class TicTacToe {
    private int [][] state;

    public TicTacToe(int [][] state){
        this();

        if(state != null)
            this.state = state;
    }

    public TicTacToe() {
        this.state = new int[3][3];
    }

    public boolean select(Position p, int player){
        try {
            if(player != ValidationResult.STATE_EMPTY && this.state[p.getX()][p.getY()] == ValidationResult.STATE_EMPTY){
                this.state[p.getX()][p.getY()] = player;
                return true;
            }
            else return false;
        }
        catch(ArrayIndexOutOfBoundsException | NullPointerException ex){
            return false;
        }
    }

    public void cleanField(){
        for(int[] array : this.state)
            Arrays.fill(array, ValidationResult.STATE_EMPTY);
    }

    public ValidationResult validateWinner(){
        return TicTacToe.validateWinner(this.state);
    }

    public static ValidationResult validateWinner(final int[][] state){
        for(int m = 0; m < 4; m++){
            for(int x = 0; x < (m < 2 ? state.length : 1); x++){
                Set<Integer> s = new HashSet<>();
                List<Position> p = new ArrayList<>();

                // Check vertical and horizontal
                if(m == 0 || m == 1){
                    for(int y = 0; y < (m == 0 ? state[0].length : state.length); y++){
                        Position pos = new Position(m == 0 ? x : y, m == 0 ? y : x);
                        p.add(pos);
                        s.add(state[pos.getX()][pos.getY()]);
                    }
                }
                else
                // Check diagonal
                if(m == 2 || m == 3){
                    for(int i = 0; i < state.length; i++){
                        Position pos = new Position(i, m == 2 ? i : (state[i].length -1) - i);
                        p.add(pos);
                        s.add(state[pos.getX()][pos.getY()]);
                    }
                }

                // Check for winner
                if(s.size() == 1){
                    int winner = (int) s.toArray()[0];
                    if(winner != ValidationResult.STATE_EMPTY)
                        return new ValidationResult()
                                .setWinner(winner)
                                .setPositions(p);
                }
            }
        }

        return null;
    }

    public List<Position> findEmptyPositions(){
        return TicTacToe.findEmptyPositions(this.state);
    }

    private static List<Position> findEmptyPositions(int [][] state){
        List<Position> empty = new ArrayList<>();

        for(int x = 0; x < state.length; x++){
            for(int y = 0; y < state[x].length; y++){
                if(state[x][y] == ValidationResult.STATE_EMPTY)
                    empty.add(new Position(x, y));
            }
        }

        return empty;
    }

    public int[][] getVirtualState(){
        int[][] virtual = new int[this.state.length][];

        for(int i = 0; i < this.state.length; i++)
            virtual[i] = Arrays.copyOf(this.state[i], this.state[i].length);

        return virtual;
    }

    public TicTacToe getVirtualGame(){
        return new TicTacToe(this.getVirtualState());
    }

    public void dumpState(){
        System.out.print("TicTacToe Game ");
        TicTacToe.dumpState(this.state);
    }

    public static void dumpState(int [][] state){
        char [] labels = new char[]{' ', 'X', 'O'};
        
        System.out.println();
        
        for(int[] arrState : state){
            for(int valueState : arrState)
                System.out.print("[" + labels[valueState] + "] ");
            System.out.println();
        }

        /*for(int x = 0; x < state.length; x++){
            for(int y = 0; y < state[x].length; y++)
                System.out.print("[" + labels[state[x][y]] + "] ");

            System.out.println();
        }*/
    }
}


