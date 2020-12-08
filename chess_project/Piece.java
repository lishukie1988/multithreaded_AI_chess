package chess_project;
import chess_project.pieces.*;

import java.util.*;


public abstract class Piece {
    protected int player;
    protected String character;
    protected List<Integer> position = new ArrayList<>(2);

    public int getPlayer() {
        return this.player;
    }

    public String getCharacter() {
        return this.character;
    }

    public List<Integer> getPosition() {
        return this.position;
    }

    public void setPosition(int x, int y) {
        this.position.set(0, x);
        this.position.set(1, y);
    }

    public abstract List<List<List<Integer>>> getTheoreticMoves(Board input_board);

    public abstract List<List<List<Integer>>> getLegalMoves(Board input_board);

}
