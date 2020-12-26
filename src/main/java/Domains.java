
import java.util.*;

class ValidationResult {
    public static final int STATE_EMPTY = 0;
    public static final int STATE_PLAYER1 = 1;
    public static final int STATE_PLAYER2 = 2;

    private int winner = STATE_EMPTY;
    private List<Position> positions;

    public ValidationResult(){}

    public ValidationResult(int winner, List<Position> positions) {
        this.winner = winner;
        this.positions = positions;
    }

    public ValidationResult setWinner(int winner) {
        this.winner = winner;
        return this;
    }
    public int getWinner() {
        return winner;
    }

    public ValidationResult setPositions(List<Position> positions) {
        this.positions = positions;
        return this;
    }
    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return String.format("\nwinner:\t%s\npositions:\t%s\n", winner, positions);
    }
}

class Position {
    private int x = -1, y = -1;

    public Position(){}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position setX(int x) {
        this.x = x;
        return this;
    }
    public int getX() {
        return x;
    }

    public Position setY(int y) {
        this.y = y;
        return this;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        Position position = (Position) object;
        
        return position.getX() == this.getX() && position.getY() == this.getY();
    }

    @Override
    public String toString() {
        return String.format("{x: %s, y: %s}", x, y);
    }
}
